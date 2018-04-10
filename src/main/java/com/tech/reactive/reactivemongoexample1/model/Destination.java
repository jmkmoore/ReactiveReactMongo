package com.tech.reactive.reactivemongoexample1.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Document
public class Destination {
    @Id
    private String id;
    private String name;
    private String capital;
}
