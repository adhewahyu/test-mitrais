package com.example.testmitrais.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryResponse {

    @JsonIgnore
    private Long id;
    private String name;
    @JsonIgnore
    private Long parentId;
    @JsonIgnore
    private Integer sequence;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CategoryResponse> sub;
}
