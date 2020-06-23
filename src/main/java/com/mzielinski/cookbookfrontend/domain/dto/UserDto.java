package com.mzielinski.cookbookfrontend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserDto {
    private Long userId;
    private String userName;
    private String emailAddress;
    private String userPassword;
    private boolean isLogged;

    @Override
    public String toString() {
        return userName;
    }
}
