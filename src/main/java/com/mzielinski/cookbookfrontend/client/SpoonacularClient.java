package com.mzielinski.cookbookfrontend.client;

import com.mzielinski.cookbookfrontend.config.BackendConfig;
import com.mzielinski.cookbookfrontend.domain.dto.EdamamDto;
import com.mzielinski.cookbookfrontend.domain.dto.SpoonacularDto;
import com.mzielinski.cookbookfrontend.exception.EdamamNotFoundException;
import com.mzielinski.cookbookfrontend.exception.SpoonacularNotFoundException;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static java.util.Optional.ofNullable;

public class SpoonacularClient {
    private final RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());


    public SpoonacularDto getWineFromRecipe(Long recipeId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "wine/recipe/" + recipeId).build().encode().toUri();
        SpoonacularDto response = restTemplate.getForObject(url, SpoonacularDto.class);
        return ofNullable(response).orElseThrow(SpoonacularNotFoundException::new);
    }
}
