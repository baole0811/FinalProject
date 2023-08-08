package com.bookshop.service.impl;

import com.bookshop.converter.CommentConverter;
import com.bookshop.dto.CommentDTO;
import com.bookshop.model.Book;
import com.bookshop.model.Comment;
import com.bookshop.model.User;
import com.bookshop.repository.BookRepository;
import com.bookshop.repository.CommentRepository;
import com.bookshop.repository.UserRepository;
import com.bookshop.service.ICommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService {
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    @Override
    public void createComment(User user, CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setBook(bookRepository.findById(commentDTO.getBook_id()).get());
        comment.setUser(user);
        comment.setContent(commentDTO.getContent());
        comment.setCreateDate(new Date(System.currentTimeMillis()));
        commentRepository.save(comment);
    }

    @Override
    public List<CommentDTO> getAllCommentByBook(long bookID) {
        Book book = bookRepository.findById(bookID).get();
        List<Comment> comments = commentRepository.findCommentsByBook(book);
        List<CommentDTO> result = new ArrayList<>();
        for(Comment cmt : comments){
            result.add(CommentConverter.toModel(cmt));
        }
        return result;
    }

    @Override
    public List<CommentDTO> getAllCommentByUser(User user) {
        List<Comment> comments = commentRepository.findCommentsByUser(user);
        List<CommentDTO> result = new ArrayList<>();
        for(Comment cmt : comments){
            result.add(CommentConverter.toModel(cmt));
        }
        return result;
    }
}
