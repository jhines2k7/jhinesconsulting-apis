package com.hines.james.apis;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorResponse {
    private String description = "Please correct the listed validation errors and try re-submitting your request";
    private List<ErrorDetails> errors = new ArrayList<>();

    @Data
    @AllArgsConstructor
    public static class ErrorDetails {
        private String fieldName;
        private String message;
    }
}
