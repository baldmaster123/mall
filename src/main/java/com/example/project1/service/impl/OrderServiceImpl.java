package com.example.project1.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project1.dao.MallCartMapper;
import com.example.project1.dao.MallOrderItemMapper;
import com.example.project1.dao.MallOrderMapper;
import com.example.project1.dao.MallProductMapper;
import com.example.project1.dao.MallShippingMapper;
import com.example.project1.enums.OrderStatusEnum;
import com.example.project1.enums.PaymentTypeEnum;
import com.example.project1.enums.ProductStatusEnum;
import com.example.project1.enums.ResponceEnum;
import com.example.project1.pojo.MallCart;
import com.example.project1.pojo.MallOrder;
import com.example.project1.pojo.MallOrderItem;
import com.example.project1.pojo.MallProduct;
import com.example.project1.pojo.MallShipping;
import com.example.project1.service.IOrderService;
import com.example.project1.service.vo.OrderItemVO;
import com.example.project1.service.vo.OrderVO;
import com.example.project1.service.vo.ResponceVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private MallShippingMapper shippingMapper;

    // @Autowired
    // private ICartService cartService;

    @Autowired
    private MallCartMapper cartMapper;

    @Autowired
    private MallProductMapper productMapper;

    @Autowired
    private MallOrderMapper orderMapper;

    @Autowired
    private MallOrderItemMapper orderItemMapper;

    @Override
    public ResponceVO create(Integer userId, Integer shippingId) {
        MallShipping shipping = shippingMapper.selectByPrimaryKeyAndUserId(userId, shippingId);
        // 收货地址是否存在
        if (shipping == null) {
            return ResponceVO.error(ResponceEnum.SHIPPPING_NOT_EXIT);
        }

        // 获取购物车，校验（是否有商品、库存）
        List<MallCart> carts = cartMapper.selectByUserId(userId);

        List<MallCart> cartList = new ArrayList<>();
        HashSet<Integer> productIDset = new HashSet<>();
        for (MallCart mallCart : carts) {
            if (mallCart.getSelected()) {
                cartList.add(mallCart);
                productIDset.add(mallCart.getProductId());
            }
        }

        if (cartList.isEmpty()) {
            return ResponceVO.error(ResponceEnum.NO_CART_SELECTED);
        }

        // 获取cart中的productId
        List<MallProduct> mallProducts = productMapper.selectByProductIdSet(productIDset);
        HashMap<Integer, MallProduct> productMap = new HashMap<>();
        for (MallProduct product : mallProducts) {
            productMap.put(product.getId(), product);
        }
        Long orderNo = generateOrderNo();
        List<MallOrderItem> orderItemList = new ArrayList<>();
        for (MallCart mallCart : cartList) {
            MallProduct mallProduct = productMap.get(mallCart.getProductId());

            if (mallProduct == null) {
                return ResponceVO.error(ResponceEnum.PRODUCT_OFF_SALE_OR_DELETE);
            }

            if (!mallProduct.getStatus().equals(ProductStatusEnum.ON_SALE)) {
                return ResponceVO.error(ResponceEnum.PRODUCT_OFF_SALE_OR_DELETE);
            }

            if (mallProduct.getStock() < mallCart.getCount()) {
                return ResponceVO.error(ResponceEnum.PRODUCT_STOCK_LACK);
            }

            MallOrderItem orderItem = buildOrderItem(userId, orderNo, mallCart.getCount(), mallProduct);
            orderItemList.add(orderItem);

            // 减库存
            mallProduct.setStock(mallProduct.getStock() - mallCart.getCount());
            int row = productMapper.updateByPrimaryKey(mallProduct);
            if (row <= 0) {
                return ResponceVO.error(ResponceEnum.ERROR);
            }
        }

        // 创建订单
        MallOrder order = buildOrder(userId, orderNo, shippingId, orderItemList);

        // 添加订单
        int row = orderMapper.insertSelective(order);
        if (row == 0) {
            return ResponceVO.error(ResponceEnum.ERROR);
        }

        int res = orderItemMapper.batchInsertSelective(orderItemList);
        if (res == 0) {
            return ResponceVO.error(ResponceEnum.ERROR);
        }

        // 删除购物车对应商品
        for (MallCart cart : cartList) {
            cartMapper.deleteByProductIdAndUserID(userId, cart.getProductId());
        }

        // 创建orderVO
        OrderVO orderVO = buildOrderVO(order, orderItemList, shipping);
        return ResponceVO.success(ResponceEnum.SUCCESS, orderVO);
    }

    @Override
    public ResponceVO list(Integer UID, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<MallOrder> orderList = orderMapper.selectByUID(UID);
        if(orderList.isEmpty()){
            return ResponceVO.error(ResponceEnum.ORDER_EMPTY);
        }

        // 获取ordernoSet
        Set<Long> orderNoSet = new HashSet<>();
        Set<Integer> shippingIdSet = new HashSet<>();
        for (MallOrder mallOrder : orderList) {
            orderNoSet.add(mallOrder.getOrderNo());
            shippingIdSet.add(mallOrder.getShippingId());
        }

        //获取orderItemMap
        List<MallOrderItem> orderItemList = orderItemMapper.selectByOrderNoSet(orderNoSet);
        HashMap<Long, List<MallOrderItem>> orderItemMap = new HashMap<>();        
        
        for (MallOrderItem mallOrderItem : orderItemList) {
            Long ordernum = mallOrderItem.getOrderNo();
            if (orderItemMap.containsKey(ordernum)) {
                List<MallOrderItem> list = orderItemMap.get(ordernum);
                list.add(mallOrderItem);
                orderItemMap.put(ordernum, list);
            }else{
                List<MallOrderItem> list = new ArrayList<MallOrderItem>();
                list.add(mallOrderItem);
                orderItemMap.put(ordernum, list);
            }
        }


        //获取shippingMap
        List<MallShipping> shippingList = shippingMapper.selectByIdSet(shippingIdSet);
        HashMap<Integer, MallShipping> shippingMap = new HashMap<>();
        for (MallShipping mallShipping : shippingList) {
            shippingMap.put(mallShipping.getId(), mallShipping);
        }      

        ArrayList<OrderVO> orderVOList = new ArrayList<OrderVO>();        
        for (MallOrder order : orderList) {
            OrderVO orderVO = buildOrderVO(order, orderItemMap.get(order.getOrderNo()), shippingMap.get(order.getShippingId())); 
            orderVOList.add(orderVO);
        }

        PageInfo<OrderVO> pageInfo = new PageInfo<>(orderVOList);
        return ResponceVO.success(ResponceEnum.SUCCESS, pageInfo);
    }

    @Override
    public ResponceVO detail(Integer UID, Long orderNo) {
        MallOrder order = orderMapper.selectByOrderNo(orderNo);
        if (order == null || !order.getUserId().equals(UID)) {
            return ResponceVO.error(ResponceEnum.ORDER_EMPTY);
        }

        List<MallOrderItem> orderItemList = orderItemMapper.selectByOrderNo(orderNo);
        MallShipping shipping = shippingMapper.selectByPrimaryKey(order.getShippingId());

        OrderVO orderVO = buildOrderVO(order, orderItemList, shipping);
        return ResponceVO.success(ResponceEnum.SUCCESS, orderVO);
    } 

    @Override
    public ResponceVO cancel(Integer UID, Long orderNO) {
        MallOrder order = orderMapper.selectByOrderNo(orderNO);
        if (order == null || !order.getUserId().equals(UID)) {
            return ResponceVO.error(ResponceEnum.ORDER_EMPTY);
        }

        if (!order.getStatus().equals(OrderStatusEnum.NO_PAY.getCode())) {
            return ResponceVO.error(ResponceEnum.ORDER_EMPTY);
        }

        order.setStatus(OrderStatusEnum.CANCELED.getCode());
        order.setCloseTime(new Date());

        int row = orderMapper.updateByPrimaryKeySelective(order);
        if (row <= 0) {
            return ResponceVO.error(ResponceEnum.ERROR);
        }

        return ResponceVO.success(ResponceEnum.SUCCESS);        
    }

    @Override
    public ResponceVO paid(Integer UID, Long orderNO) {
        MallOrder order = orderMapper.selectByOrderNo(orderNO);
        if (order == null || !order.getUserId().equals(UID)) {
            return ResponceVO.error(ResponceEnum.ORDER_EMPTY);
        }

        if(order.getStatus().equals(OrderStatusEnum.NO_PAY.getCode())){
            return ResponceVO.error(ResponceEnum.ORDER_EMPTY);
        }

        order.setStatus(OrderStatusEnum.PAID.getCode());
        order.setPaymentTime(new Date());

        int row = orderMapper.updateByPrimaryKeySelective(order);
        if (row <= 0) {
            return ResponceVO.error(ResponceEnum.ERROR);
        }

        return ResponceVO.success(ResponceEnum.SUCCESS);
    }

    

    private OrderVO buildOrderVO(MallOrder order, List<MallOrderItem> orderItemList, MallShipping shipping) {
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(order, orderVO);

        List<OrderItemVO> orderItemVOList = new ArrayList<>();
        for (MallOrderItem mallOrderItem : orderItemList) {
            OrderItemVO orderItemVO = new OrderItemVO();
            BeanUtils.copyProperties(mallOrderItem, orderItemVO);
            orderItemVOList.add(orderItemVO);
        }

        orderVO.setOrderItemVoList(orderItemVOList);
        orderVO.setShippingId(shipping.getId());
        orderVO.setShippingVo(shipping);
        return orderVO;
    }

    private MallOrder buildOrder(Integer userID, Long orderNo, Integer shippingID, List<MallOrderItem> orderItems) {
        BigDecimal payment = BigDecimal.ZERO;
        for (MallOrderItem mallOrderItem : orderItems) {
            payment = payment.add(mallOrderItem.getTotalPrice());
        }

        MallOrder order = new MallOrder();
        order.setOrderNo(orderNo);
        order.setUserId(userID);
        order.setShippingId(shippingID);
        order.setPayment(payment);
        order.setPaymentType(PaymentTypeEnum.PAY_ONLINE);
        order.setPostage(0);
        order.setStatus(OrderStatusEnum.NO_PAY.getCode());
        return order;
    }

    private Long generateOrderNo() {
        return System.currentTimeMillis() + new Random().nextInt(999);
    }

    private MallOrderItem buildOrderItem(Integer uid, Long orderNo, Integer quantity, MallProduct product) {
        MallOrderItem item = new MallOrderItem();
        item.setUserId(uid);
        item.setOrderNo(orderNo);
        item.setProductId(product.getId());
        item.setProductName(product.getName());
        item.setProductImage(product.getMainImage());
        item.setCurrentUnitPrice(product.getPrice());
        item.setQuantity(quantity);
        item.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
        return item;
    }



}
