package com.lzl.lab.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer Id;
    private String productName;
    private String productImage;
    private String productInformation;
    private String productType;
    private String productTimeStamp;
    private String location;
    private Boolean permission;
    private Integer productThumb;
}
