package com.hines.james.apis;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Contact {
    public Contact(String name, String email, String businessAreas) {
        this.name = name;
        this.email = email;
        this.businessAreas = businessAreas;
    }

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    @Column(name = "business_areas")
    private String businessAreas;
    @Column(insertable = false,  updatable = false)
    private LocalDateTime created;
    @Column(insertable = false)
    private LocalDateTime updated;
}
