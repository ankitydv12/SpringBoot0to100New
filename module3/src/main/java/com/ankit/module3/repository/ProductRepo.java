package com.ankit.module3.repository;

import com.ankit.module3.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/*
* JpaRepository is an interface that contain various method like deleteAllInBatch , findAll, findById etc
* JpaRepository extend to PagingAndSortingRepository and QueryByExecutor<T>
* PagingAndSortingRepository extend to CrudRepository<T,ID>
* Note: SimpleJpaRepository is a concrete class that implement all the method of jpaRepository
*/
@Repository
public interface ProductRepo extends JpaRepository<Products, Long> {
    Products findByTitle(String parle);

    List<Products> findByCreatedAtAfter(LocalDateTime of);

    List<Products> findByPriceAndQuantity(BigDecimal bigInteger, int i);

    List<Products> findByPriceGreaterThanAndQuantityGreaterThan(BigDecimal bigDecimal, int i);

    List<Products> findByTitleLike(String s);

    List<Products> findByTitleContaining(String parle);

    List<Products> findByTitleContainingIgnoreCase(String parle);

 //   Optional<Products> findByTitleAndPrice(String title, BigDecimal price);

    //defining own query
    @Query("SELECT e from Products e where e.title=?1 and e.price=?2")
    // @Query("SELECT e from Products e where e.title=:title and e.price=:price")
    Optional<Products> findByTitleAndPrice(String title, BigDecimal price);
}
