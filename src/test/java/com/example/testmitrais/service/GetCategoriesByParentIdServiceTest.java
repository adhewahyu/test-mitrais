package com.example.testmitrais.service;

import com.alibaba.fastjson2.JSON;
import com.example.testmitrais.model.entity.Category;
import com.example.testmitrais.model.request.GetCategoriesByParentIdRequest;
import com.example.testmitrais.model.response.CategoryResponse;
import com.example.testmitrais.repository.CategoryRepository;
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
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
@Slf4j
class GetCategoriesByParentIdServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private GetCategoriesByParentIdService getCategoriesByParentIdService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void doTest_returnEmptyList(){
        List<CategoryResponse> expected = Collections.emptyList();
        List<CategoryResponse> result = getCategoriesByParentIdService.execute(GetCategoriesByParentIdRequest.builder()
                .isParent(true)
                .id(0L)
                .build());
        log.info("expected = {}", JSON.toJSONString(expected));
        log.info("result = {}", JSON.toJSONString(result));
        Assertions.assertEquals(expected, result);
    }

    @Test
    void doTest_success(){
        List<Category> categoryList = List.of(
                new Category(1L,"Computers & Tablets", 0L,0),
                new Category(2L,"All-in-One PCs",1L,0),
                new Category(3L,"Desktop PCs",1L,1),
                new Category(4L,"Laptops",1L,2),
                new Category(5L,"Servers",1L,3),
                new Category(7L,"Tablets",1L,5),
                new Category(8L,"Gaming PCs",3L,0),
                new Category(9L,"Business PCs",3L,1),
                new Category(10L,"Workstations",3L,2),
                new Category(11L,"Networking",0L,1),
                new Category(12L,"Firewalls",11L,0),
                new Category(13L,"Modems",11L,1)
        );
        ReflectionTestUtils.setField(getCategoriesByParentIdService, "categoryRepository", categoryRepository);
        Mockito.when(categoryRepository.getCategoriesOrderedByParentAndSequence()).thenReturn(categoryList);
        Mockito.when(categoryRepository.getCategoriesByParentId(1L)).thenReturn(categoryList.stream().filter(data -> data.getParentId().equals(1L)).collect(Collectors.toList()));
        Mockito.when(categoryRepository.getCategoriesByParentId(3L)).thenReturn(categoryList.stream().filter(data -> data.getParentId().equals(3L)).collect(Collectors.toList()));
        Mockito.when(categoryRepository.getCategoriesByParentId(11L)).thenReturn(categoryList.stream().filter(data -> data.getParentId().equals(11L)).collect(Collectors.toList()));
        Mockito.when(categoryRepository.countCategoriesByParentId(1L)).thenReturn((int) categoryList.stream().filter(data -> data.getParentId().equals(1L)).count());
        Mockito.when(categoryRepository.countCategoriesByParentId(2L)).thenReturn((int) categoryList.stream().filter(data -> data.getParentId().equals(2L)).count());
        Mockito.when(categoryRepository.countCategoriesByParentId(3L)).thenReturn((int) categoryList.stream().filter(data -> data.getParentId().equals(3L)).count());
        Mockito.when(categoryRepository.countCategoriesByParentId(4L)).thenReturn((int) categoryList.stream().filter(data -> data.getParentId().equals(4L)).count());
        Mockito.when(categoryRepository.countCategoriesByParentId(5L)).thenReturn((int) categoryList.stream().filter(data -> data.getParentId().equals(5L)).count());
        Mockito.when(categoryRepository.countCategoriesByParentId(7L)).thenReturn((int) categoryList.stream().filter(data -> data.getParentId().equals(7L)).count());
        Mockito.when(categoryRepository.countCategoriesByParentId(8L)).thenReturn((int) categoryList.stream().filter(data -> data.getParentId().equals(8L)).count());
        Mockito.when(categoryRepository.countCategoriesByParentId(9L)).thenReturn((int) categoryList.stream().filter(data -> data.getParentId().equals(9L)).count());
        Mockito.when(categoryRepository.countCategoriesByParentId(10L)).thenReturn((int) categoryList.stream().filter(data -> data.getParentId().equals(10L)).count());
        Mockito.when(categoryRepository.countCategoriesByParentId(11L)).thenReturn((int) categoryList.stream().filter(data -> data.getParentId().equals(11L)).count());
        Mockito.when(categoryRepository.countCategoriesByParentId(12L)).thenReturn((int) categoryList.stream().filter(data -> data.getParentId().equals(12L)).count());
        Mockito.when(categoryRepository.countCategoriesByParentId(13L)).thenReturn((int) categoryList.stream().filter(data -> data.getParentId().equals(13L)).count());
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
        List<CategoryResponse> result = getCategoriesByParentIdService.execute(GetCategoriesByParentIdRequest.builder()
                .isParent(true)
                .id(0L)
                .build());
        log.info("result = {}", JSON.toJSONString(result));
        Assertions.assertEquals(expected, result);
    }

    @Test
    void doTest_success_extendedChild(){
        List<Category> categoryList = List.of(
                new Category(1L,"Computers & Tablets", 0L,0),
                new Category(2L,"All-in-One PCs",1L,0),
                new Category(3L,"Desktop PCs",1L,1),
                new Category(4L,"Laptops",1L,2),
                new Category(5L,"Servers",1L,3),
                new Category(7L,"Tablets",1L,5),
                new Category(8L,"Gaming PCs",3L,0),
                new Category(9L,"Business PCs",3L,1),
                new Category(10L,"Workstations",3L,2),
                new Category(11L,"Networking",0L,1),
                new Category(12L,"Firewalls",11L,0),
                new Category(13L,"Modems",11L,1),
                new Category(14L,"Low End Gaming PCs",8L,0),
                new Category(15L,"Mid End Gaming PCs",8L,1),
                new Category(16L,"High End Gaming PCs",8L,2)
        );
        ReflectionTestUtils.setField(getCategoriesByParentIdService, "categoryRepository", categoryRepository);
        Mockito.when(categoryRepository.getCategoriesOrderedByParentAndSequence()).thenReturn(categoryList);
        Mockito.when(categoryRepository.getCategoriesByParentId(1L)).thenReturn(categoryList.stream().filter(data -> data.getParentId().equals(1L)).collect(Collectors.toList()));
        Mockito.when(categoryRepository.getCategoriesByParentId(3L)).thenReturn(categoryList.stream().filter(data -> data.getParentId().equals(3L)).collect(Collectors.toList()));
        Mockito.when(categoryRepository.getCategoriesByParentId(8L)).thenReturn(categoryList.stream().filter(data -> data.getParentId().equals(8L)).collect(Collectors.toList()));
        Mockito.when(categoryRepository.getCategoriesByParentId(11L)).thenReturn(categoryList.stream().filter(data -> data.getParentId().equals(11L)).collect(Collectors.toList()));
        Mockito.when(categoryRepository.countCategoriesByParentId(1L)).thenReturn((int) categoryList.stream().filter(data -> data.getParentId().equals(1L)).count());
        Mockito.when(categoryRepository.countCategoriesByParentId(2L)).thenReturn((int) categoryList.stream().filter(data -> data.getParentId().equals(2L)).count());
        Mockito.when(categoryRepository.countCategoriesByParentId(3L)).thenReturn((int) categoryList.stream().filter(data -> data.getParentId().equals(3L)).count());
        Mockito.when(categoryRepository.countCategoriesByParentId(4L)).thenReturn((int) categoryList.stream().filter(data -> data.getParentId().equals(4L)).count());
        Mockito.when(categoryRepository.countCategoriesByParentId(5L)).thenReturn((int) categoryList.stream().filter(data -> data.getParentId().equals(5L)).count());
        Mockito.when(categoryRepository.countCategoriesByParentId(7L)).thenReturn((int) categoryList.stream().filter(data -> data.getParentId().equals(7L)).count());
        Mockito.when(categoryRepository.countCategoriesByParentId(8L)).thenReturn((int) categoryList.stream().filter(data -> data.getParentId().equals(8L)).count());
        Mockito.when(categoryRepository.countCategoriesByParentId(9L)).thenReturn((int) categoryList.stream().filter(data -> data.getParentId().equals(9L)).count());
        Mockito.when(categoryRepository.countCategoriesByParentId(10L)).thenReturn((int) categoryList.stream().filter(data -> data.getParentId().equals(10L)).count());
        Mockito.when(categoryRepository.countCategoriesByParentId(11L)).thenReturn((int) categoryList.stream().filter(data -> data.getParentId().equals(11L)).count());
        Mockito.when(categoryRepository.countCategoriesByParentId(12L)).thenReturn((int) categoryList.stream().filter(data -> data.getParentId().equals(12L)).count());
        Mockito.when(categoryRepository.countCategoriesByParentId(13L)).thenReturn((int) categoryList.stream().filter(data -> data.getParentId().equals(13L)).count());
        Mockito.when(categoryRepository.countCategoriesByParentId(14L)).thenReturn((int) categoryList.stream().filter(data -> data.getParentId().equals(14L)).count());
        Mockito.when(categoryRepository.countCategoriesByParentId(15L)).thenReturn((int) categoryList.stream().filter(data -> data.getParentId().equals(15L)).count());
        Mockito.when(categoryRepository.countCategoriesByParentId(16L)).thenReturn((int) categoryList.stream().filter(data -> data.getParentId().equals(16L)).count());
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
                                                CategoryResponse.builder()
                                                        .id(8L)
                                                        .name("Gaming PCs")
                                                        .sub(List.of(
                                                                CategoryResponse.builder().id(14L).name("Low End Gaming PCs").build(),
                                                                CategoryResponse.builder().id(15L).name("Mid End Gaming PCs").build(),
                                                                CategoryResponse.builder().id(16L).name("High End Gaming PCs").build()
                                                        ))
                                                        .build(),
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
        List<CategoryResponse> result = getCategoriesByParentIdService.execute(GetCategoriesByParentIdRequest.builder()
                .isParent(true)
                .id(0L)
                .build());
        log.info("result = {}", JSON.toJSONString(result));
        Assertions.assertEquals(expected, result);
    }


}
