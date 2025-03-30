package com.asss.www.ApotekarskaUstanova.Repository;

import com.asss.www.ApotekarskaUstanova.Entity.ProductBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductBatchRepository extends JpaRepository<ProductBatch, Integer> {

    List<ProductBatch> findByProductId(int productId);

    Optional<ProductBatch> findByEan13(Long ean13);

    @Query("SELECT pb FROM ProductBatch pb WHERE CAST(pb.ean13 AS string) LIKE CONCAT(:ean13, '%')")
    List<ProductBatch> findByEan13(@Param("ean13") String ean13);

    Optional<ProductBatch> findById(Long batchId);


    // Pretraga po nazivu proizvoda (preko povezane Product tabele)
    @Query("SELECT pb FROM ProductBatch pb WHERE pb.product.name LIKE %:name%")
    List<ProductBatch> findByProductName(@Param("name") String name);

    @Query("SELECT MAX(CAST(SUBSTRING(pb.batchNumber, 6) AS int)) FROM ProductBatch pb WHERE pb.product.id = :productId")
    Optional<Integer> findMaxBatchNumberByProductId(@Param("productId") int productId);

    @Query("SELECT COALESCE(SUM(pb.quantityRemaining), 0) FROM ProductBatch pb WHERE pb.product.id = :productId")
    int sumRemainingQuantityByProductId(@Param("productId") int productId);
}
