package com.hines.james.apis;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorResponse {
    private List<ErrorDetails> errors = new ArrayList<>();
    private String description = "Please correct the following validation errors and try re-submitting your request";

    @Data
    @AllArgsConstructor
    public static class ErrorDetails {
        private String fieldName;
        private String message;
    }
}
