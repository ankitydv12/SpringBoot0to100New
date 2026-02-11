package com.ankit.module3.Controller;

import com.ankit.module3.entities.Products;
import com.ankit.module3.repository.ProductRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<Products> getAllProduct(@RequestParam(defaultValue = "id") String sortBy ,
                                        @RequestParam(defaultValue = "0") Integer page,
                                        @RequestParam(defaultValue = " ") String title) {

        Pageable pageable = PageRequest.of(page,5,Sort.by(sortBy).ascending());
//        return productRepo.findAll(pageable);

        //adding filter
        Page<Products> productsPage =productRepo.findByTitleContainingIgnoreCase(title,pageable);
        //Page contain the various attribute which can be access through function
        /*
        * content : [],
          "last": true,
          "totalPages": 2,
          "totalElements": 6,
          "size": 5,
          "number": 1,
          "first": false,
          "numberOfElements": 1,
          "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
          },
          "empty": false
        */
        System.out.println(productsPage.getTotalPages());
        System.out.println(productsPage.getContent());


        return productsPage;
    }
}
