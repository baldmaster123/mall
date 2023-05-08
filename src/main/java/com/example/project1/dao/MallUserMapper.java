package com.example.project1.dao;

import com.example.project1.pojo.MallUser;
import com.example.project1.pojo.MallUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_user
     *
     * @mbg.generated Wed Mar 22 16:06:24 CST 2023
     */
    long countByExample(MallUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_user
     *
     * @mbg.generated Wed Mar 22 16:06:24 CST 2023
     */
    int deleteByExample(MallUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_user
     *
     * @mbg.generated Wed Mar 22 16:06:24 CST 2023
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_user
     *
     * @mbg.generated Wed Mar 22 16:06:24 CST 2023
     */
    int insert(MallUser row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_user
     *
     * @mbg.generated Wed Mar 22 16:06:24 CST 2023
     */
    int insertSelective(MallUser row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_user
     *
     * @mbg.generated Wed Mar 22 16:06:24 CST 2023
     */
    List<MallUser> selectByExample(MallUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_user
     *
     * @mbg.generated Wed Mar 22 16:06:24 CST 2023
     */
    MallUser selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_user
     *
     * @mbg.generated Wed Mar 22 16:06:24 CST 2023
     */
    int updateByExampleSelective(@Param("row") MallUser row, @Param("example") MallUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_user
     *
     * @mbg.generated Wed Mar 22 16:06:24 CST 2023
     */
    int updateByExample(@Param("row") MallUser row, @Param("example") MallUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_user
     *
     * @mbg.generated Wed Mar 22 16:06:24 CST 2023
     */
    int updateByPrimaryKeySelective(MallUser row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_user
     *
     * @mbg.generated Wed Mar 22 16:06:24 CST 2023
     */
    int updateByPrimaryKey(MallUser row);

    int countByUsername(String username);

    int countByEmail(String email);

    MallUser selectByUsername(String username);
}