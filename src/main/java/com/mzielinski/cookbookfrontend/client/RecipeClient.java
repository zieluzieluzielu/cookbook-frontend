package com.mzielinski.cookbookfrontend.client;


import com.mzielinski.cookbookfrontend.config.BackendConfig;
import com.mzielinski.cookbookfrontend.domain.dto.RecipeDto;
import com.mzielinski.cookbookfrontend.exception.RecipeNotFoundException;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

public class RecipeClient {
    private final RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

    public List<RecipeDto> getRecipes() {

        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "recipes").build().encode().toUri();
        RecipeDto[] response = restTemplate.getForObject(url, RecipeDto[].class);
        return Arrays.asList(ofNullable(response).orElse(new RecipeDto[0]));
    }

    public RecipeDto getRecipe(Long recipeId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "recipes/" + recipeId).build().encode().toUri();
        RecipeDto response = restTemplate.getForObject(url, RecipeDto.class);
        return ofNullable(response).orElseThrow(RecipeNotFoundException::new);
    }

    public List<RecipeDto> getRecipesByPreparationTime(Long preparationTime) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "recipes/preparationtime/" + preparationTime).build().encode().toUri();
        RecipeDto[] response = restTemplate.getForObject(url, RecipeDto[].class);
        return Arrays.asList(ofNullable(response).orElse(new RecipeDto[0]));
    }

    public List<RecipeDto> getRecipesByCategory(Long recipeCategoryId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "recipes/recipecategory/" + recipeCategoryId).build().encode().toUri();
        RecipeDto[] response = restTemplate.getForObject(url, RecipeDto[].class);
        return Arrays.asList(ofNullable(response).orElse(new RecipeDto[0]));
    }

    public List<RecipeDto> getRecipesByProduct(Long productId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "recipes/product/" + productId).build().encode().toUri();
        RecipeDto[] response = restTemplate.getForObject(url, RecipeDto[].class);
        return Arrays.asList(ofNullable(response).orElse(new RecipeDto[0]));
    }

    public List<RecipeDto> getRecipesByUser(Long userId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "recipes/user/" + userId).build().encode().toUri();
        RecipeDto[] response = restTemplate.getForObject(url, RecipeDto[].class);
        return Arrays.asList(ofNullable(response).orElse(new RecipeDto[0]));
    }
    public RecipeDto createRecipe(RecipeDto recipeDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "recipes").build().encode().toUri();
        RecipeDto response = restTemplate.postForObject(url, recipeDto, RecipeDto.class);
        return ofNullable(response).orElseThrow(RecipeNotFoundException::new);
    }



}
