package com.lzl.lab.service.impl;

import com.lzl.lab.mapper.ProductMapper;
import com.lzl.lab.pojo.Product;
import com.lzl.lab.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;


    @Override
    public List<Product> list1(Long userId) {
        return productMapper.list1(userId);
    }

    @Override
    public void insertProduct(Product product,Long userId) throws Exception {
//        productMapper.insertProduct(product,userId);
        try {
            productMapper.insertProduct(product, userId);
            System.out.println("Product inserted successfully: " + product);
        } catch (Exception e) {
            System.out.println("Service层调用Mapper层异常: " + e.getMessage());
            e.printStackTrace(); // 打印详细的异常堆栈信息
            throw new Exception("插入产品失败：" + e.getMessage());
        }
    }

    @Override
    public void updateProduct(Product product) {
        productMapper.updateProduct(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        productMapper.deleteProduct(id);
    }

    @Override
    public List<Product> searchProducts(Long userId,String query) {
        return productMapper.searchProducts(userId,query);
    }

}
