package com.mzielinski.cookbookfrontend.client;

import com.mzielinski.cookbookfrontend.config.BackendConfig;
import com.mzielinski.cookbookfrontend.domain.dto.IngredientDto;
import com.mzielinski.cookbookfrontend.domain.dto.ProductDto;
import com.mzielinski.cookbookfrontend.domain.dto.ProductGroupDto;
import com.mzielinski.cookbookfrontend.exception.IngredientNotFoundException;
import com.mzielinski.cookbookfrontend.exception.ProductGroupNotFoundException;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

public class IngredientClient {

    private final RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

    public List<IngredientDto> getIngredients() {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "ingredients").build().encode().toUri();
        IngredientDto[] response = restTemplate.getForObject(url, IngredientDto[].class);
        return Arrays.asList(ofNullable(response).orElse(new IngredientDto[0]));
    }

    public IngredientDto getIngredient(Long ingredientId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "ingredients/" + ingredientId).build().encode().toUri();
        IngredientDto response = restTemplate.getForObject(url, IngredientDto.class);
        return ofNullable(response).orElseThrow(IngredientNotFoundException::new);
    }

    public List<IngredientDto> getIngredientsFromRecipe(Long recipeId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "ingredients/recipes/" + recipeId).build().encode().toUri();
        IngredientDto[] response = restTemplate.getForObject(url, IngredientDto[].class);
        return Arrays.asList(ofNullable(response).orElse(new IngredientDto[0]));
    }

    public IngredientDto createIngredient(IngredientDto ingredientDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "ingredients").build().encode().toUri();
        IngredientDto response = restTemplate.postForObject(url, ingredientDto, IngredientDto.class);
        return ofNullable(response).orElseThrow(IngredientNotFoundException::new);
    }

    public IngredientDto updateIngredient(IngredientDto ingredientDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "ingredients").build().encode().toUri();
        IngredientDto response = restTemplate.exchange(url, HttpMethod.PUT, null,IngredientDto.class).getBody();

        //restTemplate.patchForObject(url, ingredientDto, IngredientDto.class);
        return ofNullable(response).orElseThrow(IngredientNotFoundException::new);
    }

    public void deleteIngredient(long ingredientId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "ingredients/" + ingredientId).build().encode().toUri();
        restTemplate.delete(url);
    }







}
