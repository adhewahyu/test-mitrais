package com.example.testmitrais.service;

import com.alibaba.fastjson2.JSON;
import com.example.testmitrais.model.request.GetCategoriesByParentIdRequest;
import com.example.testmitrais.model.response.CategoryResponse;
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
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@Slf4j
class GetCategoryTreeServiceTest {

    @Mock
    private GetCategoriesByParentIdService getCategoriesByParentIdService;

    @InjectMocks
    private GetCategoryTreeService getCategoryTreeService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(getCategoryTreeService, "getCategoriesByParentIdService", getCategoriesByParentIdService);
    }

    @Test
    void doTest_emptyList(){
        List<CategoryResponse> expected = Collections.emptyList();
        Mockito.when(getCategoriesByParentIdService.execute(Mockito.any())).thenReturn(expected);
        List<CategoryResponse> result = getCategoryTreeService.execute().getList();
        log.info("expected = {}", JSON.toJSONString(expected));
        log.info("result = {}", JSON.toJSONString(result));
        Assertions.assertEquals(expected, result);
    }

    @Test
    void doTest_success(){
        List<CategoryResponse> expected = List.of(
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
        log.info("expected = {}", JSON.toJSONString(expected));
        Mockito.when(getCategoriesByParentIdService.execute(GetCategoriesByParentIdRequest.builder()
                .isParent(true)
                .id(0L)
                .build())).thenReturn(expected);
        List<CategoryResponse> result = getCategoryTreeService.execute().getList();
        log.info("result = {}", JSON.toJSONString(result));
        Assertions.assertEquals(expected, result);
    }

}
