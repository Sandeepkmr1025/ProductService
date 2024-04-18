package com.sandeep.productservice.repositories;

import com.sandeep.productservice.models.Product;
import com.sandeep.productservice.repositories.projections.ProductWithIdAndTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);

    Optional<Product> findByTitleAndDescription(String title, String description);

    List<Product> findByTitleContaining(String word);

    List<Product> findTopThreeByTitle(String title); // Limit the result by three

    void deleteById(Long id);

    void deleteByTitle(String title);

    Product save(Product product);

    @Query("select p.title, p.description from Product p where p.id = ?1")
    String findTitleAndDescriptionById(Long id);

    @Query("select p from Product p where p.id = 1")
    List<Product> someRandomQuery();

    @Query("select p from Product p where p.price>100000 and lower(p.title) like '%pro%'")
    List<Product> someRandomQuery2();

    //This will return a Product with only Id and Title.
    @Query("select p.id as id, p.title as title from Product p where p.price>100000 and lower(p.title) like '%pro%'")
    List<ProductWithIdAndTitle> someRandomQuery3();

    @Query("select p.id as id, p.title as title from Product p where p.id = :id")
    List<ProductWithIdAndTitle> someRandomQuery4(@Param("id") Long id);

    @Query(value = "select * from Product p where p.id = 2", nativeQuery = true)
    Product someRandomQuery5();

}