package com.example.testmitrais.service;

import com.example.testmitrais.model.entity.Category;
import com.example.testmitrais.model.request.GetCategoriesByParentIdRequest;
import com.example.testmitrais.model.response.CategoryResponse;
import com.example.testmitrais.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetCategoriesByParentIdService {

    private final CategoryRepository categoryRepository;

    public List<CategoryResponse> execute(GetCategoriesByParentIdRequest request){
        List<Category> categoryList = Boolean.TRUE.equals(request.getIsParent()) ?
                categoryRepository.getCategoriesOrderedByParentAndSequence()
                : categoryRepository.getCategoriesByParentId(request.getId());
        if(ObjectUtils.isEmpty(categoryList)){
            return Collections.emptyList();
        }else{
            if(Boolean.TRUE.equals(request.getIsParent())){
                return categoryList
                        .stream()
                        .filter(data -> data.getParentId().equals(request.getId()))
                        .map(this::mapCategoryToCategoryResponse).collect(Collectors.toList());
            }else{
                return categoryList
                        .stream()
                        .map(this::mapCategoryToCategoryResponse).collect(Collectors.toList());
            }
        }
    }

    private CategoryResponse mapCategoryToCategoryResponse(Category category){
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        if(categoryRepository.countCategoriesByParentId(category.getId()).compareTo(0) > 0){
            categoryResponse.setSub(this.execute(GetCategoriesByParentIdRequest.builder()
                    .isParent(false)
                    .id(category.getId())
                    .build()));
        }
        return categoryResponse;
    }

}
