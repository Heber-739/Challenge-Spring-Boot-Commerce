package com.challenge.Dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private double price;
    @NotBlank
    private int stock;

}
