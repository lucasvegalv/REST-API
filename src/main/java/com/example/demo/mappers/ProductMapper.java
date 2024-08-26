package com.example.demo.mappers;

import com.example.demo.dtos.ProductDTO;
import com.example.demo.entities.ProductEntity;

public class ProductMapper {

    public static ProductDTO toProductDTO(ProductEntity productEntity) {

        if(productEntity == null) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productEntity.getId());
        productDTO.setName(productEntity.getName());
        productDTO.setPrice(productEntity.getPrice());
        productDTO.setDescription(productEntity.getDescription());
        return productDTO;

    }

    public static ProductEntity toProductEntity(ProductDTO productDTO) {

        if(productDTO == null) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productDTO.getId());
        productEntity.setName(productDTO.getName());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setDescription(productDTO.getDescription());
        return productEntity;

    }
}
