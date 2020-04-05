package com.hines.james.apis;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ContactDto {
    private String name;
    private String email;
    private List<String> businessAreas = new ArrayList<>();
}
