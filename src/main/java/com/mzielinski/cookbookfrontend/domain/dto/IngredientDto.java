package com.mzielinski.cookbookfrontend.domain.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class IngredientDto {
    Long ingredientId;
    BigDecimal amount;
    String unit;
    Long productId;
    Long recipeId;
    boolean mainProduct;

    public IngredientDto(BigDecimal amount, String unit, Long productId, Long recipeId, boolean mainProduct) {
        this.amount = amount;
        this.unit = unit;
        this.productId = productId;
        this.recipeId = recipeId;
        this.mainProduct = mainProduct;
    }

}
