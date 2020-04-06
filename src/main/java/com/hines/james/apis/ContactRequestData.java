package com.hines.james.apis;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Valid
public class ContactRequestData {
    private String name;
    @NotBlank
    private String email;
    private List<String> businessAreas = new ArrayList<>();
}
