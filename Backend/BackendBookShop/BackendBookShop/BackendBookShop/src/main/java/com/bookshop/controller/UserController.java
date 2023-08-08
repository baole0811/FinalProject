package com.bookshop.controller;

import com.bookshop.dto.*;
import com.bookshop.model.OrderItem;
import com.bookshop.model.User;
import com.bookshop.service.*;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = {"http://localhost:3000"})
@RequiredArgsConstructor
public class UserController {
    private final IBookService bookService;
    private final ICategoryService categoryService;
    private final ICartItemService cartItemService;
    private final IOrderService orderService;
    private final IOrderItemService orderItemService;
    private final IUserService userService;
    private final IContactService contactService;
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
    public List<BookDTO> getCategoryModelById(@PathVariable long id){

        return categoryService.getListBookByCategory(id);
    }
    //Cart Item
    @PostMapping(value = "/cart/{id}")
    public CartItemDTO saveCart(@PathVariable long id,@RequestBody CartItemDTO cartItemDTO){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int quantity = cartItemDTO.getQuantity();
        return cartItemService.saveCart(id,quantity,user);
    }
    @GetMapping(value = "/cart")
    public List<CartItemDTO> getAllCarts(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return cartItemService.getAllCarts(user);
    }
    //delete list ccart item
    @DeleteMapping(value = "/cart")
    public String deleteCartItems(@RequestBody long[] ids){
        cartItemService.deleteCartItems(ids);
        return "delete success";
    }
    //delete one cart item
    @DeleteMapping(value = "/cart/{id}")
    public String deleteCartItems(@PathVariable long id){
        cartItemService.deleteCartItem(id);
        return "delete success";
    }

    @PutMapping(value = "/cart/{id}")
    public CartItemDTO updateCartItem(@PathVariable long id,@RequestBody CartItemDTO cartItemDTO){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return cartItemService.updateCartItem(id,cartItemDTO, user);
    }


    //Order Book
    @PostMapping(value = "/order")
    public OrderDTO createOrderBook(@RequestBody OrderDTO orderDTO) throws MessagingException, UnsupportedEncodingException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return orderService.createOrderBook(orderDTO,user);
    }
    @GetMapping(value = "/order")
    public List<OrderDTO> getAllOrders(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return orderService.getAllOrders(user);
    }

    @GetMapping("/order/{id}")
    public List<OrderItemDTO> getOrderItemByOrderId(@PathVariable long id){

        return orderItemService.getOrderItemsById(id);
    }
    //manager account
    @GetMapping("/account")
    public UserDTO getInfo(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getUserInfo(user);
    }
    @PutMapping("/account")
    public void changeInfo(@RequestBody ChangeInfoDTO userDTO){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         userService.changeUserInfo(user,userDTO);
    }
    @PutMapping("/account/changepassword")
    public ResponseMessage changePassword(@RequestBody ChangePassword changePassword){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.changeUserPassword(user,changePassword);
    }

    @PutMapping("/account/resetpassword")
    public ResponseMessage resetPassword(@RequestBody ResetPassword resetPassword){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.resetPassword(user,resetPassword);
    }

    //Contact
    @PostMapping(value = "/contact")
    public void createContact(@RequestBody ContactDTO contactDTO){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        contactService.createContact(user,contactDTO);
    }
    //Comment
    @PostMapping(value = "/comment")
    public void createComment(@RequestBody CommentDTO commentDTO){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        commentService.createComment(user,commentDTO);
    }
    @GetMapping(value = "/comment")
    public List<CommentDTO> getAllComment(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return commentService.getAllCommentByUser(user);
    }
}
