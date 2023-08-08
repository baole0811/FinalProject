package com.bookshop.controller;

import com.bookshop.dto.*;
import com.bookshop.model.User;
import com.bookshop.service.*;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = {"http://localhost:3000"})
@RequiredArgsConstructor
public class NonAuthenticated {
    private final IBookService bookService;
    private final ICategoryService categoryService;
    private final ICartItemService cartItemService;
    private final IOrderService orderService;
    private final IOrderItemService orderItemService;
    private final IUserService userService;
    private final AuthenticationService authenticationService;
    private final ICommentService commentService;
    @GetMapping("/")
    public ResponseEntity<String> helloUser(){
        return ResponseEntity.ok("hello user");
    }
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
    @GetMapping(value = "/book/{id}")
    public BookDTO getBookById(@PathVariable long id){
        return bookService.getBookById(id);
    }
    //Category
    @GetMapping(value = "/category")
    public List<CategoryDTO> getAllCategory(){

        return categoryService.getAllCategory();
    }
    @GetMapping(value = "/category/{id}")
    public ResponseEntity<PagingDTO<BookDTO>> getCategoryModelById(@PathVariable long id ,
                                                                   @RequestParam(defaultValue = "1") int page,
                                                                    @RequestParam(defaultValue = "10") int pageSize,
                                                                    @RequestParam(defaultValue = "") String search,
                                                                    @RequestParam(defaultValue = "name") String sortBy,
                                                                    @RequestParam(defaultValue = "asc") String sortType,
                                                                    @RequestParam(defaultValue = "desc") String mostRecent){

        try {
            PagingDTO<BookDTO> books = categoryService.getBookByCategory(id, search, page, pageSize, sortType, sortBy, mostRecent);
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/account")
    public ResponseMessage getCode(@RequestBody String email) throws MessagingException {

        return authenticationService.forgotPass(email);
    }
    @GetMapping(value = "/book/soldtop")
    public List<BookDTO> getTopSold(){

        return bookService.getTopSoldBooks();
    }

    @GetMapping(value = "/book/newbook")
    public List<BookDTO> getNewBook(){

        return bookService.getNewBooks();
    }
    //comment
    @GetMapping(value = "/comment/{bookID}")
    public List<CommentDTO> getAllComment(@PathVariable long bookID){

        return commentService.getAllCommentByBook(bookID);
    }
}
