package com.example.testmitrais.repository;

import com.example.testmitrais.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = " select id, name, parent_id, sequence from categories order by parent_id, sequence ", nativeQuery = true)
    List<Category> getCategoriesOrderedByParentAndSequence();

    @Query(value = " select id, name, parent_id, sequence from categories where parent_id = :id order by sequence ", nativeQuery = true)
    List<Category> getCategoriesByParentId(@Param("id") Long id);

    @Query(value = " select count(id) from categories where parent_id = :id ", nativeQuery = true)
    Integer countCategoriesByParentId(@Param("id") Long id);

}
