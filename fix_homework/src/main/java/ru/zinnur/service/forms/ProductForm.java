package ru.zinnur.service.forms;

import lombok.Data;

@Data
public class ProductForm {
    private Long id;
    private String name;
    private String manufacturer;
    private Integer count;
    private Integer cost;
}