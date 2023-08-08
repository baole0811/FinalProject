package com.bookshop.converter;

import com.bookshop.dto.CategoryDTO;
import com.bookshop.model.CategoryBook;
import org.springframework.stereotype.Component;

@Component
public class CategoryBookConverter {
    public static CategoryDTO toModel (CategoryBook categoryBookEntity){
        CategoryDTO categoryBookModel = new CategoryDTO();
        categoryBookModel.setId(categoryBookEntity.getId());
        categoryBookModel.setCode(categoryBookEntity.getCode());
        categoryBookModel.setName(categoryBookEntity.getName());
        categoryBookModel.setCreateBy(categoryBookEntity.getCreateBy());
        categoryBookModel.setCreateDate(categoryBookEntity.getCreateDate());
        categoryBookModel.setModifiedDate(categoryBookEntity.getModifiedDate());
        categoryBookModel.setModifiedBy(categoryBookEntity.getModifiedBy());
        return categoryBookModel;
    }
    public static CategoryBook toEntity (CategoryDTO categoryBookModel){
        CategoryBook categoryBookEntity = new CategoryBook();
        categoryBookEntity.setCode(categoryBookModel.getCode());
        categoryBookEntity.setName(categoryBookModel.getName());
        return categoryBookEntity;
    }
}
