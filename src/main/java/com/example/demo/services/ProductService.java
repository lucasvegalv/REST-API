package com.example.demo.services;

import com.example.demo.dtos.ProductDTO;
import com.example.demo.entities.ProductEntity;
import com.example.demo.mappers.ProductMapper;
import com.example.demo.repositories.IProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final IProductRepository productRepository;

    @Autowired
    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // CRUD Methods

    // CREATE
    public void createProduct(ProductDTO productDTO) {
        ProductEntity productEntity = ProductMapper.toProductEntity(productDTO);
        productRepository.save(productEntity);
    }

    // READ BY ID
    public ProductDTO readProductByID(Integer id) {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(id);

        if(productEntityOptional.isPresent()) {
            ProductDTO productDTO = ProductMapper.toProductDTO(productEntityOptional.get());
            return productDTO;
        } else {
            throw new EntityNotFoundException("Product with id " + id + " not found");
        }

    }

    // READ ALL
    public List<ProductDTO> readAllProducts() {
        List<ProductEntity> productEntityList = productRepository.findAll();

        List<ProductDTO> productDTOList = productEntityList.stream()
                .map(ProductMapper::toProductDTO)
                .collect(Collectors.toList());

        return productDTOList;
    }

    // UPDATE
    public ProductDTO updateProduct(Integer id, ProductDTO productDTO) {

        Optional<ProductEntity> productEntityOptional = productRepository.findById(id);
        ProductDTO updatedProductDTO = null;

        if(productEntityOptional.isPresent()) {
            ProductEntity updatedProductEntity = productEntityOptional.get();

            updatedProductEntity.setName(productDTO.getName());
            updatedProductEntity.setDescription(productDTO.getDescription());
            updatedProductEntity.setPrice(productDTO.getPrice());

            ProductEntity updatedProduct = productRepository.save(updatedProductEntity);
            updatedProductDTO = ProductMapper.toProductDTO(updatedProduct);
        }
        return updatedProductDTO;
    }

    // DELETE BY ID
    public void deleteProductByID(Integer id) {
        productRepository.deleteById(id);
    }

    // DELETE ALL
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }
}
