package com.mzielinski.cookbookfrontend.domain.dto;

import lombok.*;

import java.util.List;

@Data

public class ProductGroupDto {
    private Long productGroupId;
    private String groupName;
    private List<ProductDto> products;


    @Override
    public String toString() {
        return groupName ;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductGroupDto that = (ProductGroupDto) o;

        if (productGroupId != null ? !productGroupId.equals(that.productGroupId) : that.productGroupId != null)
            return false;
        return groupName != null ? groupName.equals(that.groupName) : that.groupName == null;
    }

    @Override
    public int hashCode() {
        int result = productGroupId != null ? productGroupId.hashCode() : 0;
        result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
        return result;
    }
}
