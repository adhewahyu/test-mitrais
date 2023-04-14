package com.example.testmitrais.configuration;

import com.example.testmitrais.model.entity.Category;
import com.example.testmitrais.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.List;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class TestConfiguration {

    private final CategoryRepository categoryRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void doInitData(){
        categoryRepository.saveAll(List.of(
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
        ));
    }

}