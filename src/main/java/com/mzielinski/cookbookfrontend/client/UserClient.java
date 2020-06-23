package com.mzielinski.cookbookfrontend.client;


import com.mzielinski.cookbookfrontend.config.BackendConfig;
import com.mzielinski.cookbookfrontend.domain.dto.UserDto;
import com.mzielinski.cookbookfrontend.exception.UserNotFoundException;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

public class UserClient {
    private final RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

    public List<UserDto> getUsers() {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "users").build().encode().toUri();
        UserDto[] response = restTemplate.getForObject(url, UserDto[].class);
        return Arrays.asList(ofNullable(response).orElse(new UserDto[0]));
    }

    public UserDto getUser(Long userId) {
        URI url = UriComponentsBuilder.fromHttpUrl(BackendConfig.ENDPOINT + "users/" + userId).build().encode().toUri();
        UserDto response = restTemplate.getForObject(url, UserDto.class);
        return ofNullable(response).orElseThrow(UserNotFoundException::new);
    }

}
