package com.example.testmitrais.service;

import com.alibaba.fastjson2.JSON;
import com.example.testmitrais.model.request.GetCategoriesByParentIdRequest;
import com.example.testmitrais.model.response.CategoryListResponse;
import com.example.testmitrais.model.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetCategoryTreeService {

    private final GetCategoriesByParentIdService getCategoriesByParentIdService;

    public CategoryListResponse execute(){
        List<CategoryResponse> resultList = getCategoriesByParentIdService.execute(GetCategoriesByParentIdRequest.builder()
                .isParent(true)
                .id(0L)
                .build());
        log.info("data result = {}", JSON.toJSONString(resultList));
        return CategoryListResponse.builder()
                .list(resultList)
                .build();
    }

}
