package com.mzielinski.cookbookfrontend.client;


import com.mzielinski.cookbookfrontend.config.BackendConfig;
import com.mzielinski.cookbookfrontend.domain.dto.RecipeCategoryDto;
import com.mzielinski.cookbookfrontend.exception.RecipeCategoryNotFoundException;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

public class RecipeCategoryClient {
    private final RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

    public List<RecipeCategoryDto> getRecipeCategories() {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "recipecategories").build().encode().toUri();
        RecipeCategoryDto[] response = restTemplate.getForObject(url, RecipeCategoryDto[].class);
        return Arrays.asList(ofNullable(response).orElse(new RecipeCategoryDto[0]));
    }

    public RecipeCategoryDto getRecipeCategory(Long recipeCategoryId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "recipecategories/" + recipeCategoryId).build().encode().toUri();
        RecipeCategoryDto response = restTemplate.getForObject(url, RecipeCategoryDto.class);
        return ofNullable(response).orElseThrow(RecipeCategoryNotFoundException::new);
    }


}
