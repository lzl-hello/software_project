package com.lzl.lab.service;

import com.lzl.lab.pojo.Product;

import java.util.List;

public interface ProductService {
    List<Product> list1(Long userId);

    List<Product> list2();

    void insertProduct(Product product,Long userId) throws Exception;

    void updateProduct(Product product);

    void deleteProduct(Integer id);

    List<Product> searchProducts(Long userId,String query);
    
    List<Product> searchAllProducts(String query);
}
