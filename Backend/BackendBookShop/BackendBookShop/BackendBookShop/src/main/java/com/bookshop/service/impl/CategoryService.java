package com.bookshop.service.impl;

import com.bookshop.converter.BookConverter;
import com.bookshop.converter.CategoryBookConverter;
import com.bookshop.dto.BookDTO;
import com.bookshop.dto.CategoryDTO;
import com.bookshop.dto.PagingDTO;
import com.bookshop.model.Book;
import com.bookshop.model.CategoryBook;
import com.bookshop.repository.BookRepository;
import com.bookshop.repository.CategoryRepository;
import com.bookshop.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;

    @Override
    public CategoryDTO saveCategoryBook(CategoryDTO categoryBookModel) {
        CategoryBook categoryBookEntity = categoryRepository.save(CategoryBookConverter.toEntity(categoryBookModel));
        return CategoryBookConverter.toModel(categoryBookEntity);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryBookModel) {
        Optional<CategoryBook> categoryBookEntity = categoryRepository.findById(categoryBookModel.getId());
        categoryBookEntity.get().setCode(categoryBookModel.getCode());
        categoryBookEntity.get().setName(categoryBookModel.getName());
        categoryRepository.save(categoryBookEntity.get());
        return CategoryBookConverter.toModel(categoryBookEntity.get());
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<CategoryDTO> result = new ArrayList<>();
        List<CategoryBook> listEntity = categoryRepository.findAll();
        for (CategoryBook item: listEntity) {
            result.add(CategoryBookConverter.toModel(item));
        }
        return result;
    }

    @Override
    public void deleteCategories(long[] ids) {
        for(long id : ids){
            categoryRepository.deleteById(id);
        }
    }

    @Override
    public List<BookDTO> getListBookByCategory(long id) {
        List<BookDTO> result = new ArrayList<>();
        Optional<CategoryBook> categoryBookEntity = categoryRepository.findById(id);
        List<Book> books = categoryBookEntity.get().getBooks();
        for(Book item : books){
            result.add(BookConverter.toModel(item));
        }
        return result;
    }

    @Override
    public PagingDTO<BookDTO> getBookByCategory(long id, String search, int page, int pageSize, String sortType, String sortBy, String mostRecent) {
        Sort sort;
        if (sortType != null && sortType.equals("desc")) {
            sort =  Sort.by(Sort.Order.desc(sortBy));
        } else {
            sort =  Sort.by(Sort.Order.asc(sortBy));
        }
        Pageable pageable = PageRequest.of(page, pageSize, sort);

        Optional<CategoryBook> categoryBookEntity = categoryRepository.findById(id);
        List<Book> listProducts = categoryBookEntity.get().getBooks();
        if (search != null) {
            listProducts = listProducts.stream().filter(product ->
                            product.getName().toLowerCase().contains(search.toLowerCase()) ||
                                    product.getAuthor().toLowerCase().contains(search.toLowerCase()))
                    .toList();
        }
        if (mostRecent.equals("asc")) {
            listProducts.sort((o1, o2) -> o2.getCreateDate().compareTo(o1.getCreateDate()));
        }
        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), listProducts.size());
        final Page<Book> pageList = new PageImpl<>(listProducts.subList(start, end), pageable, listProducts.size());
        var result = BookConverter.toListBook(pageList.getContent());
        int totalPage = (int) Math.ceil((double) listProducts.size() / pageSize);
        int totalItem = listProducts.size();
        return new PagingDTO<>(result, pageSize, totalItem, page, totalPage);
    }

}
