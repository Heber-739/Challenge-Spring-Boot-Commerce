package com.challenge.Dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleDto {

    @NotNull
    private String registDate;

    @NotBlank
    private int units;

    @NotBlank
    private int client_dni;

    @NotBlank
    private int product_id;

    @NotBlank
    private double total_price;

}
