package com.bookshop.repository;

import com.bookshop.model.CategoryBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryBook, Long> {
    Optional<CategoryBook> findCategoryBookByCode(String code);
}
