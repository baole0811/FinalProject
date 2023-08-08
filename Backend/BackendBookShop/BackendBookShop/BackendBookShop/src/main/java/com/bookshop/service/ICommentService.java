package com.bookshop.service;

import com.bookshop.dto.CommentDTO;
import com.bookshop.model.User;

import java.util.List;

public interface ICommentService {
    void createComment(User user, CommentDTO commentDTO);
    List<CommentDTO> getAllCommentByBook(long bookID);

    List<CommentDTO> getAllCommentByUser(User user);
}
