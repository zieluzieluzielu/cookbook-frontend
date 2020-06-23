package com.mzielinski.cookbookfrontend.client;

import com.mzielinski.cookbookfrontend.config.BackendConfig;
import com.mzielinski.cookbookfrontend.domain.dto.ProductGroupDto;
import com.mzielinski.cookbookfrontend.exception.ProductGroupNotFoundException;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;


public class ProductGroupClient {
    private final RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

    public List<ProductGroupDto> getProductGroups() {

        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "productgroups").build().encode().toUri();
        ProductGroupDto[] response = restTemplate.getForObject(url, ProductGroupDto[].class);
        return Arrays.asList(ofNullable(response).orElse(new ProductGroupDto[0]));
    }

    public ProductGroupDto getProductGroup(Long productGroupId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "productgroups/" + productGroupId).build().encode().toUri();
        ProductGroupDto response = restTemplate.getForObject(url, ProductGroupDto.class);
        return ofNullable(response).orElseThrow(ProductGroupNotFoundException::new);
    }



}
