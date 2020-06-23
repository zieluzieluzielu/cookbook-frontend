package com.mzielinski.cookbookfrontend.client;


import com.mzielinski.cookbookfrontend.config.BackendConfig;
import com.mzielinski.cookbookfrontend.domain.dto.ProductDto;
import com.mzielinski.cookbookfrontend.domain.dto.ProductGroupDto;
import com.mzielinski.cookbookfrontend.exception.ProductGroupNotFoundException;
import com.mzielinski.cookbookfrontend.exception.ProductNotFoundException;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

public class ProductClient {
    private final RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

    public List<ProductDto> getProducts() {

        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "products").build().encode().toUri();
        ProductDto[] response = restTemplate.getForObject(url, ProductDto[].class);
        return Arrays.asList(ofNullable(response).orElse(new ProductDto[0]));
    }

    public ProductDto getProduct(Long productId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "products/" + productId).build().encode().toUri();
        ProductDto response = restTemplate.getForObject(url, ProductDto.class);
        return ofNullable(response).orElseThrow(ProductNotFoundException::new);
    }

    public List<ProductDto> getProductByProductGroup(Long productGroupId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "products/productgroups/" + productGroupId).build().encode().toUri();
        ProductDto[] response = restTemplate.getForObject(url, ProductDto[].class);
        return Arrays.asList(ofNullable(response).orElse(new ProductDto[0]));
    }

    public List<ProductDto> getProductsByRecipe(Long recipeId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "products/recipe/list/" + recipeId).build().encode().toUri();
        ProductDto[] response = restTemplate.getForObject(url, ProductDto[].class);
        return Arrays.asList(ofNullable(response).orElse(new ProductDto[0]));
    }

    public ProductDto getMainProductFromRecipe(Long recipeId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "products/recipe/" + recipeId).build().encode().toUri();
        ProductDto response = restTemplate.getForObject(url, ProductDto.class);
        return ofNullable(response).orElse(new ProductDto());
    }

    public ProductDto createProduct(ProductDto productDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "products").build().encode().toUri();
        ProductDto response = restTemplate.postForObject(url, productDto, ProductDto.class);
        return ofNullable(response).orElseThrow(ProductNotFoundException::new);
    }

    public void deleteProduct(Long productId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "products/" + productId).build().encode().toUri();
        restTemplate.delete(url);
    }


}
