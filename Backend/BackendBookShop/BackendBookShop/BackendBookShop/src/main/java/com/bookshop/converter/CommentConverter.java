package com.bookshop.converter;

import com.bookshop.dto.CommentDTO;
import com.bookshop.model.Comment;

public class CommentConverter {
    public static CommentDTO toModel(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setUser_id(comment.getUser().getId());
        commentDTO.setBook_id(comment.getBook().getId());
        commentDTO.setUserName(comment.getUser().getFirstName());
        commentDTO.setContent(comment.getContent());
        commentDTO.setCreateDate(comment.getCreateDate());
        commentDTO.setBookName(comment.getBook().getName());
        return commentDTO;
    }
}
