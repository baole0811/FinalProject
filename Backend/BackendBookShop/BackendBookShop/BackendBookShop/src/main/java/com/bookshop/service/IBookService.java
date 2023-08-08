package com.bookshop.service;

import com.bookshop.dto.BookDTO;
import com.bookshop.dto.PagingDTO;
import com.bookshop.model.Book;
import com.bookshop.model.User;

import java.util.List;

public interface IBookService {
    BookDTO saveBook(BookDTO bookDTO, User user);

    BookDTO update(BookDTO bookDTO, User user,long id);

    void deleteBooks(long[] ids);

    void deleteBook(long id);

    PagingDTO<BookDTO> getAllBook(String search, String category, int page, int pageSize, String sortType, String sortBy, String mostRecent);

    BookDTO getBookById(long id);
    List<BookDTO> getTopSoldBooks();

    List<BookDTO> getNewBooks();
}
