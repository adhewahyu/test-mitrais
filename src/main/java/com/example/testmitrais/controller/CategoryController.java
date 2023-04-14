package com.example.testmitrais.controller;

import com.example.testmitrais.model.response.RestResponse;
import com.example.testmitrais.service.GetCategoryTreeService;
import com.example.testmitrais.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final GetCategoryTreeService getCategoryTreeService;

    @GetMapping("/v1/trees")
    public ResponseEntity<RestResponse> getCategoryTrees(){
        return new ResponseEntity<>(RestResponse.builder()
                .result(true)
                .message(Constants.SUCCESS_MSG_DATA_FOUND)
                .data(getCategoryTreeService.execute().getList())
                .build(), HttpStatus.OK);
    }

}
