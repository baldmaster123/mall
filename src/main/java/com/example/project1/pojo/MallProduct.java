package com.example.project1.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class MallProduct {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mall_product.id
     *
     * @mbg.generated Fri Mar 24 20:35:30 CST 2023
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mall_product.category_id
     *
     * @mbg.generated Fri Mar 24 20:35:30 CST 2023
     */
    private Integer categoryId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mall_product.name
     *
     * @mbg.generated Fri Mar 24 20:35:30 CST 2023
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mall_product.subtitle
     *
     * @mbg.generated Fri Mar 24 20:35:30 CST 2023
     */
    private String subtitle;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mall_product.main_image
     *
     * @mbg.generated Fri Mar 24 20:35:30 CST 2023
     */
    private String mainImage;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mall_product.price
     *
     * @mbg.generated Fri Mar 24 20:35:30 CST 2023
     */
    private BigDecimal price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mall_product.stock
     *
     * @mbg.generated Fri Mar 24 20:35:30 CST 2023
     */
    private Integer stock;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mall_product.status
     *
     * @mbg.generated Fri Mar 24 20:35:30 CST 2023
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mall_product.create_time
     *
     * @mbg.generated Fri Mar 24 20:35:30 CST 2023
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mall_product.update_time
     *
     * @mbg.generated Fri Mar 24 20:35:30 CST 2023
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mall_product.id
     *
     * @return the value of mall_product.id
     *
     * @mbg.generated Fri Mar 24 20:35:30 CST 2023
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mall_product.id
     *
     * @param id the value for mall_product.id
     *
     * @mbg.generated Fri Mar 24 20:35:30 CST 2023
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mall_product.category_id
     *
     * @return the value of mall_product.category_id
     *
     * @mbg.generated Fri Mar 24 20:35:30 CST 2023
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mall_product.category_id
     *
     * @param categoryId the value for mall_product.category_id
     *
     * @mbg.generated Fri Mar 24 20:35:30 CST 2023
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mall_product.name
     *
     * @return the value of mall_product.name
     *
     * @mbg.generated Fri Mar 24 20:35:30 CST 2023
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mall_product.name
     *
     * @param name the value for mall_product.name
     *
     * @mbg.generated Fri Mar 24 20:35:30 CST 2023
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mall_product.subtitle
     *
     * @return the value of mall_product.subtitle
     *
     * @mbg.generated Fri Mar 24 20:35:30 CST 2023
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mall_product.subtitle
     *
     * @param subtitle the value for mall_product.subtitle
     *
     * @mbg.generated Fri Mar 24 20:35:30 CST 2023
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle == null ? null : subtitle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mall_product.main_image
     *
     * @return the value of mall_product.main_image
     *
     * @mbg.generated Fri Mar 24 20:35:30 CST 2023
     */
    public String getMainImage() {
        return mainImage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mall_product.main_image
     *
     * @param mainImage the value for mall_product.main_image
     *
     * @mbg.generated Fri Mar 24 20:35:30 CST 2023
     */
    public void setMainImage(String mainImage) {
        this.mainImage = mainImage == null ? null : mainImage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mall_product.price
     *
     * @return the value of mall_product.price
     *
     * @mbg.generated Fri Mar 24 20:35:30 CST 2023
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mall_product.price
     *
     * @param price the value for mall_product.price
     *
     * @mbg.generated Fri Mar 24 20:35:30 CST 2023
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mall_product.stock
     *
     * @return the value of mall_product.stock
     *
     * @mbg.generated Fri Mar 24 20:35:30 CST 2023
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mall_product.stock
     *
     * @param stock the value for mall_product.stock
     *
     * @mbg.generated Fri Mar 24 20:35:30 CST 2023
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mall_product.status
     *
     * @return the value of mall_product.status
     *
     * @mbg.generated Fri Mar 24 20:35:30 CST 2023
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mall_product.status
     *
     * @param status the value for mall_product.status
     *
     * @mbg.generated Fri Mar 24 20:35:30 CST 2023
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mall_product.create_time
     *
     * @return the value of mall_product.create_time
     *
     * @mbg.generated Fri Mar 24 20:35:30 CST 2023
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mall_product.create_time
     *
     * @param createTime the value for mall_product.create_time
     *
     * @mbg.generated Fri Mar 24 20:35:30 CST 2023
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mall_product.update_time
     *
     * @return the value of mall_product.update_time
     *
     * @mbg.generated Fri Mar 24 20:35:30 CST 2023
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mall_product.update_time
     *
     * @param updateTime the value for mall_product.update_time
     *
     * @mbg.generated Fri Mar 24 20:35:30 CST 2023
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}