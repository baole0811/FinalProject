package com.bookshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PagingDTO<T> {
    public List<T> results;
    public int PageSize;
    public int TotalCount;
    public int CurrentPage;
    public int TotalPages;
}
