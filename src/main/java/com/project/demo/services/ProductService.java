package com.project.demo.services;


import com.project.demo.entities.Category;
import com.project.demo.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    public String addProduct(String name, Long categoryId, String content, double price, MultipartFile image);
    public List<Product> allProducts();
    public Page<Product> allProducts(Pageable pageable);
    public Product productById(Long id);
    public String editProduct(Long id, String name, Long categoryId, String content, double price, MultipartFile image);

    public String deleteProduct(Long id);
    public String restoreProduct(Long id);

    public Page<Product> getByCategory(Long id, Pageable pageable);


}
