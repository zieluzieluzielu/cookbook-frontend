package com.mzielinski.cookbookfrontend.client;


import com.mzielinski.cookbookfrontend.config.BackendConfig;
import com.mzielinski.cookbookfrontend.domain.dto.EdamamDto;
import com.mzielinski.cookbookfrontend.exception.EdamamNotFoundException;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

public class EdamamClient {
    private final RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());


    public EdamamDto getNutrition(Long ingredientId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "nutrition/" + ingredientId).build().encode().toUri();
        EdamamDto response = restTemplate.getForObject(url, EdamamDto.class);
        return ofNullable(response).orElseThrow(EdamamNotFoundException::new);
    }

    public List<EdamamDto> getNutritionOfEachIngredientInRecipe(Long recipeId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "nutrition/recipe/" + recipeId).build().encode().toUri();
        EdamamDto[] response = restTemplate.getForObject(url, EdamamDto[].class);
        return Arrays.asList(ofNullable(response).orElse(new EdamamDto[0]));
    }

    public EdamamDto getNutritionSum(Long recipeId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "nutrition/recipe/sum/" + recipeId).build().encode().toUri();
        EdamamDto response = restTemplate.getForObject(url, EdamamDto.class);
        return ofNullable(response).orElseThrow(EdamamNotFoundException::new);
    }


}
