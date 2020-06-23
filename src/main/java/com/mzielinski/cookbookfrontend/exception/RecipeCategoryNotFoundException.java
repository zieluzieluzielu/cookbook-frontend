package com.mzielinski.cookbookfrontend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RecipeCategoryNotFoundException extends RuntimeException {
}
