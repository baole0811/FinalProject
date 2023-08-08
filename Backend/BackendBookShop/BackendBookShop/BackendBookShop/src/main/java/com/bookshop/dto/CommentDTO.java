package com.bookshop.dto;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentDTO{
    private long id;
    private String content;
    private Date createDate;
    private long user_id;
    private long book_id;
    private String userName;
    private String bookName;

}
