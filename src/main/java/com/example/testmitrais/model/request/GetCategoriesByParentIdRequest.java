package com.example.testmitrais.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCategoriesByParentIdRequest {

    private Boolean isParent;
    private Long id;

}
