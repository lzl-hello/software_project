# 思路整理反思
# 1.
maven对项目进行依赖管理，新建springboot项目后如果maven没有自动下载选择的依赖：

> https://blog.csdn.net/weixin_36827459/article/details/108317079

或者可以试试maven下载到本地，修改配置文件，添加环境变量；现在的项目用的是idea自动添加的maven
# 2.
后端编写的整体思路：
**controller**层 --> **service**层 --> **mapper**层
**pojo**实体类

首先controller接受前端传来的请求（使用@restcontroller），然后对service层的接口进行自动注入，调用函数，将结果返回给前端：

```
@RestController
public class ProductController {

        @Autowired
        private ProductService productService;

        @GetMapping("/product1")
        public Result list1(){
            System.out.println("查询商品1信息");
            List<Product> productList = productService.list1();

            return Result.success(productList);
        }

}

```
然后service层又对mapper层的接口进行调用，此时mapper层中会写对应的sql语句，进行查询；查到后返回给service层，service层对数据做处理，完事后再返回给controller层
```
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;


    @Override
    public List<Product> list1() {
        return productMapper.list1();
    }
}
```
注意override注解是重写，所以这个接口是继承productservice的：
```
public interface ProductService {
    List<Product> list1();
}
```
mapper层就是对数据库的操作：
```
@Mapper
public interface ProductMapper {
    @Select("select * from product where productType = 1")
    List<Product> list1();
}
```

整体思路是这样，然后pojo里面定义所有实体类

# 3.
注解
多看看那几个，应该能记住吧



