package com.example.testmitrais.controller;

import com.alibaba.fastjson2.JSON;
import com.example.testmitrais.model.response.CategoryListResponse;
import com.example.testmitrais.model.response.CategoryResponse;
import com.example.testmitrais.model.response.RestResponse;
import com.example.testmitrais.service.GetCategoryTreeService;
import com.example.testmitrais.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@Slf4j
class CategoryControllerTest {

    @Mock
    private GetCategoryTreeService getCategoryTreeService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(categoryController, "getCategoryTreeService", getCategoryTreeService);
    }

    @Test
    void doTest_success(){
        List<CategoryResponse> expectedData = List.of(
                CategoryResponse.builder()
                        .id(1L)
                        .name("Computers & Tablets")
                        .sub(List.of(
                                CategoryResponse.builder().id(2L).name("All-in-One PCs").build(),
                                CategoryResponse.builder()
                                        .id(3L)
                                        .name("Desktop PCs")
                                        .sub(List.of(
                                                CategoryResponse.builder().id(8L).name("Gaming PCs").build(),
                                                CategoryResponse.builder().id(9L).name("Business PCs").build(),
                                                CategoryResponse.builder().id(10L).name("Workstations").build()
                                        ))
                                        .build(),
                                CategoryResponse.builder().id(4L).name("Laptops").build(),
                                CategoryResponse.builder().id(5L).name("Servers").build(),
                                CategoryResponse.builder().id(7L).name("Tablets").build()
                        ))
                        .build(),
                CategoryResponse.builder()
                        .id(11L)
                        .name("Networking")
                        .sub(List.of(
                                CategoryResponse.builder().id(12L).name("Firewalls").build(),
                                CategoryResponse.builder().id(13L).name("Modems").build()
                        ))
                        .build()
        );
        ResponseEntity<RestResponse> expected = new ResponseEntity<>(RestResponse.builder()
                .message(Constants.SUCCESS_MSG_DATA_FOUND)
                .result(true)
                .data(CategoryListResponse.builder().list(expectedData).build().getList())
                .build(), HttpStatus.OK);
        log.info("expected = {}", JSON.toJSONString(expected));
        Mockito.when(getCategoryTreeService.execute()).thenReturn(CategoryListResponse.builder()
                .list(expectedData)
                .build());
        ResponseEntity<RestResponse> result = categoryController.getCategoryTrees();
        log.info("result = {}", JSON.toJSONString(result));
        Assertions.assertEquals(expected, result);
    }

}
