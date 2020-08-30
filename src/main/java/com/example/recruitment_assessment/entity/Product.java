package com.example.recruitment_assessment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @SequenceGenerator(name = "product_id_seq",
            sequenceName = "main_ee.product_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    private Long id;

    @NotEmpty
    @Size(min = 1)
    @Size(max = 25)
    private String label;

    @NotEmpty
    @Size(min = 1)
    @Size(max = 40)
    private String details;

    private int seq;
}
