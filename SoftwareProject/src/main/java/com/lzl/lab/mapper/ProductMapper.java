package com.lzl.lab.mapper;

import com.lzl.lab.pojo.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Select("select * from product where userId = #{userId}")
    List<Product> list1(Long userId);

    @Select("select * from product where permission = 1")
    List<Product> list2();

    @Select("select * from product where userId = #{userId} AND productType = #{productType}")
    List<Product> listForAlbum(Long userId, String productType);


//    @Insert("INSERT INTO product (productName, productImage, productInformation, productType,userId) " +
//            "VALUES (#{productName}, #{productImage}, #{productInformation}, #{productType}, #{userId})")
//    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
//    void insertProduct(Product product,Long userId);

    @Insert("INSERT INTO product (productName, productImage, productInformation, productType, userId, productTimeStamp, location, permission) " +
            "VALUES (#{product.productName}, #{product.productImage}, #{product.productInformation}, #{product.productType}, #{userId}, #{product.productTimeStamp}, #{product.location}, #{product.permission})")
    @Options(useGeneratedKeys = true, keyProperty = "product.id", keyColumn = "id")
    void insertProduct(Product product, @Param("userId") Long userId);

    @Update("UPDATE product SET productName = #{productName}, productImage = #{productImage}, productInformation = #{productInformation}, productType = #{productType}, productTimeStamp = #{productTimeStamp}, location = #{location}, permission = #{permission} WHERE Id = #{Id}")
    void updateProduct(Product product);

    @Delete("DELETE FROM product WHERE id = #{id}")
    void deleteProduct(Integer id);

    @Select("<script>" +
            "SELECT * FROM product WHERE 1=1 AND permission = 1" +
            "<if test='query != null and query != \"\"'>" +
            " AND (productName LIKE CONCAT('%', #{query}, '%')" +
            " OR productInformation LIKE CONCAT('%', #{query}, '%')" +
            " OR productType LIKE CONCAT('%', #{query}, '%'))" +
            " OR DATE_FORMAT(productTimeStamp, '%Y-%m-%d %H:%i:%s') LIKE CONCAT('%', #{query}, '%')" +
            "</if>" +
            " AND userId = #{userId}" +  // 添加查询当前用户的条件
            "</script>")
    List<Product> searchProducts(@Param("userId") Long userId,@Param("query") String query);


    @Select("<script>" +
            "SELECT * FROM product WHERE 1=1" +
            "<if test='query != null and query != \"\"'>" +
            " AND (productName LIKE CONCAT('%', #{query}, '%')" +
            " OR productInformation LIKE CONCAT('%', #{query}, '%')" +
            " OR productType LIKE CONCAT('%', #{query}, '%'))" +
            " OR DATE_FORMAT(productTimeStamp, '%Y-%m-%d %H:%i:%s') LIKE CONCAT('%', #{query}, '%')" +
            "</if>" +
            " AND permission = 1" +  // 添加查询当前用户的条件
            "</script>")
    List<Product> searchAllProducts(@Param("query") String query);
}