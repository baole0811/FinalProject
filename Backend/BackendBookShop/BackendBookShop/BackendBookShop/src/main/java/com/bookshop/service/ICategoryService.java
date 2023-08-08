package com.bookshop.service;

import com.bookshop.dto.BookDTO;
import com.bookshop.dto.CategoryDTO;
import com.bookshop.dto.PagingDTO;

import java.util.List;

public interface ICategoryService {
    CategoryDTO saveCategoryBook(CategoryDTO categoryBookModel);
    CategoryDTO updateCategory(CategoryDTO categoryBookModel);
    List<CategoryDTO> getAllCategory();
    void deleteCategories(long[] ids);
    List<BookDTO> getListBookByCategory(long id);
    PagingDTO<BookDTO> getBookByCategory(long id,String search, int page, int pageSize, String sortType, String sortBy, String mostRecent);
}
