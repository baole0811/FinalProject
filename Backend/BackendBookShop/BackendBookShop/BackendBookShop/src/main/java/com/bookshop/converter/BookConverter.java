package com.bookshop.converter;

import com.bookshop.dto.BookDTO;
import com.bookshop.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookConverter {
    public static BookDTO toModel(Book bookEntity){
        BookDTO bookModel = new BookDTO();
        bookModel.setId(bookEntity.getId());
        bookModel.setName(bookEntity.getName());
        bookModel.setAuthor(bookEntity.getAuthor());
        bookModel.setDescription(bookEntity.getDescription());
        bookModel.setUrlImage(bookEntity.getImageURL());
        bookModel.setQuantity(bookEntity.getQuantity());
        bookModel.setCodeCategory(bookEntity.getCategoryBook().getCode());
        bookModel.setNameCategory(bookEntity.getCategoryBook().getName());
        bookModel.setPrice(bookEntity.getPrice());
        bookModel.setCreateDate(bookEntity.getCreateDate());
        bookModel.setCreateBy(bookEntity.getCreateBy());
        bookModel.setModifiedDate(bookEntity.getModifiedDate());
        bookModel.setModifiedBy(bookEntity.getModifiedBy());

        return  bookModel;
    }
    public static Book toEntity(BookDTO bookModel){
        Book bookEntity = new Book();
        bookEntity.setName(bookModel.getName());
        bookEntity.setAuthor(bookModel.getAuthor());
        bookEntity.setDescription(bookModel.getDescription());
        bookEntity.setImageURL(bookModel.getUrlImage());
        bookEntity.setQuantity(bookModel.getQuantity());
        bookEntity.setPrice(bookModel.getPrice());
        return  bookEntity;
    }
    public Book toEntity(BookDTO bookModel,Book entity){
        entity.setAuthor(bookModel.getAuthor());
        entity.setName(bookModel.getName());
        entity.setDescription(bookModel.getDescription());
        entity.setImageURL(bookModel.getUrlImage());
        entity.setQuantity(bookModel.getQuantity());
        entity.setPrice(bookModel.getPrice());
        return  entity;
    }
    public static List<BookDTO> toListBook(List<Book> models) {
        return models.stream().map(BookConverter::toModel).toList();
    }

    public static Page<BookDTO> getPageProducts(Page<Book> products) {
        return products.map(BookConverter::toModel);
    }
}
