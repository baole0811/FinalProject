package com.bookshop.controller;

import com.bookshop.dto.BookDTO;
import com.bookshop.dto.CategoryDTO;
import com.bookshop.dto.PagingDTO;
import com.bookshop.dto.UserDTO;
import com.bookshop.model.User;
import com.bookshop.service.IBookService;
import com.bookshop.service.ICategoryService;
import com.bookshop.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = {"http://localhost:3000"})
@RequiredArgsConstructor
public class AdminController {

    private final IBookService bookService;
    private final ICategoryService categoryService;
    private final IUserService userService;
    @GetMapping("/")
    public ResponseEntity<String> helloUser(){
        return ResponseEntity.ok("hello, you are logged in with the admin role");
    }
    //Book
    @GetMapping(value = "/book")
    public ResponseEntity<PagingDTO<BookDTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int pageSize,
                                                     @RequestParam(defaultValue = "") String search,
                                                     @RequestParam(defaultValue = "") String category,
                                                     @RequestParam(defaultValue = "name") String sortBy,
                                                     @RequestParam(defaultValue = "asc") String sortType,
                                                     @RequestParam(defaultValue = "desc") String mostRecent) {
        try {
            PagingDTO<BookDTO> books = bookService.getAllBook(search, category, page, pageSize, sortType, sortBy, mostRecent);
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/book")
    public BookDTO createBook(@RequestBody BookDTO bookDTO){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return  bookService.saveBook(bookDTO,user);
    }
    @PutMapping(value = "/book/{id}")
    public BookDTO updateBook(@RequestBody BookDTO bookDTO, @PathVariable("id") long id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return bookService.update(bookDTO,user,id);
    }
    @GetMapping(value = "/book/{id}")
    public BookDTO getBookById(@PathVariable long id){
        return bookService.getBookById(id);
    }
    @DeleteMapping(value = "/book")
    public void deleteBooks(@RequestBody long [] ids){

        bookService.deleteBooks(ids);
    }

    @DeleteMapping(value = "/book/{id}")
    public void deleteBook(@PathVariable long id){

        bookService.deleteBook(id);
    }
    //Category
    @GetMapping(value = "/category")
    public List<CategoryDTO> getAllCategory(){

        return categoryService.getAllCategory();
    }
    @PostMapping(value = "/category")
    public CategoryDTO saveCategory(@RequestBody CategoryDTO categoryBookModel){

        return categoryService.saveCategoryBook(categoryBookModel);
    }
    @PutMapping(value = "/category/{id}")
    public CategoryDTO updateCategory(@PathVariable long id,@RequestBody CategoryDTO categoryBookModel){
        categoryBookModel.setId(id);
        return categoryService.updateCategory(categoryBookModel);
    }
    @DeleteMapping(value = "/category")
    public void deleteById(@RequestBody long[] ids){
        categoryService.deleteCategories(ids);
    }
    @GetMapping(value = "/category/{id}")
    public List<BookDTO> getCategoryModelById(@PathVariable long id){

        return categoryService.getListBookByCategory(id);
    }
    //Account
    @GetMapping("/accont")
    public List<UserDTO> getAllAccount(){

        return userService.getAllAccount();
    }
}
