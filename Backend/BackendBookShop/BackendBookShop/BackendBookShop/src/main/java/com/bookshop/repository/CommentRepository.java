package com.bookshop.repository;

import com.bookshop.model.Book;
import com.bookshop.model.Comment;
import com.bookshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findCommentsByBook(Book book);
    List<Comment> findCommentsByUser(User user);
}
