package ru.zinnur.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.zinnur.service.forms.ProductForm;
import ru.zinnur.service.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Product, Long> {

    @Modifying
    @Query(value = "update product b set b.cost=:cost where b.name=:name", nativeQuery = true)
    void updateProduct(@Param("name") String name, @Param("cost") Integer cost);

    Optional<Product> findOneByName(String name);
}
