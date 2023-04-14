package com.example.testmitrais.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "categories", indexes = {
        @Index(name = "category_idx_0", columnList = "parent_id"),
})
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "sequence")
    private Integer sequence;

}
