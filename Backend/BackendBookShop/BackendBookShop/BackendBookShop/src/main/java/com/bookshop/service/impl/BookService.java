package com.bookshop.service.impl;

import com.bookshop.converter.BookConverter;
import com.bookshop.dto.BookDTO;
import com.bookshop.dto.PagingDTO;
import com.bookshop.model.Book;
import com.bookshop.model.CategoryBook;
import com.bookshop.model.User;
import com.bookshop.repository.BookRepository;
import com.bookshop.repository.CategoryRepository;
import com.bookshop.service.IBookService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService implements IBookService {
    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;

    @Override
    public BookDTO saveBook(BookDTO bookDTO, User user) {
        String name= "";
        if(!bookDTO.getName().equalsIgnoreCase(name)) {
            CategoryBook categoryBook = categoryRepository.findCategoryBookByCode(bookDTO.getCodeCategory()).get();
            Book book = new Book();
            book = BookConverter.toEntity(bookDTO);
            book.setCategoryBook(categoryBook);
            book.setCreateDate(new Date(System.currentTimeMillis()));
            return BookConverter.toModel(bookRepository.save(book));
        }else{
            return null;
        }
    }

    @Override
    public BookDTO update(BookDTO bookDTO, User user,long id) {
        Book book = bookRepository.findById(id).get();
        CategoryBook categoryBook = categoryRepository.findCategoryBookByCode(bookDTO.getCodeCategory()).get();
        book = BookConverter.toEntity(bookDTO);

        book.setCategoryBook(categoryBook);
        book.setModifiedBy(user.getFirstName());
        book.setModifiedDate(new Date(System.currentTimeMillis()));
        book.setId(id);
        return BookConverter.toModel(bookRepository.save(book));
    }

    @Override
    public void deleteBooks(long[] ids) {
        for(long id : ids){
            bookRepository.deleteById(id);
        }
    }

    @Override
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public PagingDTO<BookDTO> getAllBook(String keyword, String category, int page, int size, String sortType, String sortBy, String mostRecent) {
        List<Book> listProducts = null;
        Sort sort;
        if (sortType != null && sortType.equals("desc")) {
            sort =  Sort.by(Sort.Order.desc(sortBy));
        } else {
            sort =  Sort.by(Sort.Order.asc(sortBy));
        }
        Pageable pageable = PageRequest.of(page, size, sort);

            listProducts = bookRepository.findAll(sort);

        if (category != null) {
            listProducts = listProducts.stream().filter(product ->
                            product.getCategoryBook().getCode().toLowerCase().contains(category.toLowerCase()))
                    .toList();
        }

        if (keyword != null) {
            listProducts = listProducts.stream().filter(product ->
                            product.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                                    product.getAuthor().toLowerCase().contains(keyword.toLowerCase()))
                    .toList();
        }
        if (mostRecent.equals("asc")) {
            listProducts.sort((o1, o2) -> o2.getCreateDate().compareTo(o1.getCreateDate()));
        }

        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), listProducts.size());
        final Page<Book> pageList = new PageImpl<>(listProducts.subList(start, end), pageable, listProducts.size());
        List<BookDTO> result = BookConverter.toListBook(pageList.getContent());
        int totalPage = (int) Math.ceil((double) listProducts.size() / size);
        int totalItem = listProducts.size();
        return new PagingDTO<>(result, size, totalItem, page, totalPage);
    }

    @Override
    public BookDTO getBookById(long id) {
        Book book = bookRepository.findById(id).get();
        return BookConverter.toModel(book);
    }

    @Override
    public List<BookDTO> getTopSoldBooks() {
        Sort sortByDesc = Sort.by(Sort.Direction.DESC,"sold");
        PageRequest pageRequest = PageRequest.of(0,5,sortByDesc);
        List<Book> books =  bookRepository.findAll(pageRequest).getContent();
        List<BookDTO> result = new ArrayList<>();
        for (Book book: books){
            result.add(BookConverter.toModel(book));
        }
        return result;
    }

    @Override
    public List<BookDTO> getNewBooks() {
        Sort sortByDesc = Sort.by(Sort.Direction.DESC,"createDate");
        PageRequest pageRequest = PageRequest.of(0,5,sortByDesc);
        List<Book> books =  bookRepository.findAll(pageRequest).getContent();
        List<BookDTO> result = new ArrayList<>();
        for (Book book: books){
            result.add(BookConverter.toModel(book));
        }
        return result;
    }
}
