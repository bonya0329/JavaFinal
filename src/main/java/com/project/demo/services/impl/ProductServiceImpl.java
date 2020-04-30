package com.project.demo.services.impl;

import com.project.demo.entities.Category;
import com.project.demo.entities.Product;
import com.project.demo.repositories.CategoryRepository;
import com.project.demo.repositories.ProductRepository;
import com.project.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public String addProduct(String name, Long categoryId, String content, double price, MultipartFile image) {

        String redirect="product_added";

        Category category = categoryRepository.findById(categoryId).get();//Mozhno dopolnit, tipa ispresent() dep

        Product product = new Product(null, name, category, content, price, image, null);//imagePath daiy v konce
        productRepository.save(product);//here i save the product to get real id instead of null

        String imageName = product.getId()+"_"+ product.getName() + ".jpg";
        String imagePath = "product_images/"+category.getId()+"_"+category.getName()+"/"+imageName;
        product.setImagePath(imagePath);
        productRepository.save(product);

        File folder = new File("src/main/resources/static/product_images/"+category.getId()+"_"+category.getName());
        if (!folder.exists()) {
            folder.mkdir();
        }

        try {
            byte[] bytes = image.getBytes();
            FileOutputStream fos = new FileOutputStream(new File("src/main/resources/static/product_images/"+category.getId()+"_"+category.getName()+"/"+imageName));
//            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/product_images/"+category.getId()+"_"+category.getName()+"/"+imageName)));
            BufferedOutputStream stream = new BufferedOutputStream(fos);
            stream.write(bytes);
            fos.close();
            stream.close();//new line, now just chek how it works
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Oshibka zdec");
        }

        return redirect;
    }

    @Override
    public List<Product> allProducts() {

        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public Page<Product> allProducts(Pageable pageable) {

        Page<Product> products = productRepository.findAll(pageable);

        return products;
    }

    @Override
    public Product productById(Long id) {
        Product product = productRepository.findById(id).orElse(new Product());
        return product;
    }

    @Override
    public String editProduct(Long id, String name, Long categoryId, String content, double price, MultipartFile image) {
        String response="editted";

        try {
            Product product = productRepository.findById(id).orElse(null);
            product.setName(name);
            Category category = categoryRepository.findById(categoryId).orElse(null);
            product.setCategory(category);
            product.setContent(content);
            product.setPrice(price);

            File file = new File("src/main/resources/static/" + product.getImagePath());
            if(!image.isEmpty()) {
                boolean exists = file.exists();
                if (exists) {
                    try {
                        file.delete();
                    } catch (Exception e) {
                        //e.printStackTrace();
                        System.out.println("Error with deleting file");
                    }
                }
                try {
                    byte[] bytes = image.getBytes();
                    FileOutputStream fos = new FileOutputStream(file);
                    BufferedOutputStream stream = new BufferedOutputStream(fos);
                    stream.write(bytes);
                    fos.close();
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Oshibka zdec");
                }

            }

            product.setUpdatedAt(new Date());
            productRepository.save(product);
        }catch (Exception e){
            e.printStackTrace();
            response="name_editting_error";
        }

        return response;
    }


    @Override
    public String deleteProduct(Long id) {

        String response="product_deleted";

        Product product = productRepository.findById(id).orElse(null);
        product.setDeletedAt(new Date());
        productRepository.save(product);

        return response;
    }

    @Override
    public String restoreProduct(Long id) {

        String response="product_restored";

        Product product = productRepository.findById(id).orElse(null);
        product.setDeletedAt(null);
        productRepository.save(product);

        return response;
    }


    @Override
    public Page<Product> getByCategory(Long id, Pageable pageable) {

        Page<Product> products = productRepository.findAllByCategoryId(id, pageable);

        return products;
    }
}
