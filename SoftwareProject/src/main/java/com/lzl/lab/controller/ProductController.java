package com.lzl.lab.controller;

import com.lzl.lab.pojo.Product;
import com.lzl.lab.pojo.Result;
import com.lzl.lab.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

        @Autowired
        private ProductService productService;

        @GetMapping("/product1")
        public Result list1(@RequestParam Long userId){
            System.out.println("查询商品信息");
            List<Product> productList = productService.list1(userId);
            return Result.success(productList);
        }

        @GetMapping("/productAll")
        public Result list2(){
            System.out.println("查询全部信息");
            List<Product> productList = productService.list2();
            // List<Product> productList = productService.list1(userId);
            return Result.success(productList);
        }

        @PostMapping("/submitFormData")
        public Result submitFormData(@RequestBody Product product,@RequestParam Long userId) {
            System.out.println("提交表单:" + product + "userid:" + userId);
            try {
                productService.insertProduct(product, userId);
                return Result.success();
            } catch (Exception e) {
                System.out.println("控制器捕获异常: " + e.getMessage());
                e.printStackTrace(); // 打印堆栈跟踪以便调试
                return Result.error("提交表单失败：" + e.getMessage());
            } catch (Throwable t) {
                System.out.println("控制器捕获错误: " + t.getMessage());
                t.printStackTrace(); // 捕获所有类型的错误
                return Result.error("提交表单失败：" + t.getMessage());
            }
        }

        @PostMapping("/updateProduct")
        public Result updateProduct(@RequestBody Product product) {
            System.out.println("更新商品信息,"+"商品id"+product.getId());
            try {
                productService.updateProduct(product);
                return Result.success();
            } catch (Exception e) {
                return Result.error("更新商品信息失败：" + e.getMessage());
            }
        }

        @PostMapping("/deleteProduct")
        public Result deleteProduct(@RequestBody Product product) {
            System.out.println("删除商品信息，"+"产品id："+product.getId());
            try {
                productService.deleteProduct(product.getId());
                return Result.success();
            } catch (Exception e) {
                return Result.error("删除商品信息失败：" + e.getMessage());
            }
        }

        @GetMapping("/searchProducts")
        public Result searchProducts(@RequestParam Long userId, @RequestParam String query) {
            System.out.println("搜索商品信息,条件：" + "userid:"+userId+",condition:" + query);
            List<Product> productList = productService.searchProducts(userId,query);
            return Result.success(productList);
        }

        @GetMapping("/searchAllProducts")
        public Result searchAllProducts(@RequestParam String query) {
            System.out.println("搜索商品信息,条件：" + "condition:" + query);
            List<Product> productList = productService.searchAllProducts(query);
            return Result.success(productList);
        }

        @GetMapping("/albumInfo")
        public Result listForAlbum(@RequestParam Long userId, @RequestParam String productType){
            System.out.println("获取相册信息信息");
            int id = userId.intValue();
            System.out.println(id);
            String productType1 = productType;
            System.out.println(productType1);
            List<Product> productList = productService.listForAlbum(userId, productType);
            return Result.success(productList);
        }
}
