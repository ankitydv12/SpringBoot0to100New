package com.ankit.module3.Controller;

import com.ankit.module3.entities.Products;
import com.ankit.module3.repository.ProductRepo;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    public final ProductRepo productRepo;

    public ProductController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    //http://localhost:8080/products?sortBy=sku we can pass the value from the url
    @GetMapping
    public List<Products> getAllProduct(@RequestParam(defaultValue = "id") String sortBy){
       /*
       * 1st Way
       * Sort.Direction.DESC  --> Sorting Order
       * sortBy               --> Sorting Criteria
       * "price"              --> if two product has same sortBy criteria then sorting done on the price
       */
//        return productRepo.findBy(Sort.by(Sort.Direction.DESC,sortBy,"price"));

//2nd way
        return productRepo.findBy(Sort.by(
                Sort.Order.desc(sortBy),
                Sort.Order.asc("price")
        ));
    }
}
