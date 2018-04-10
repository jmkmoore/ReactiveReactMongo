package com.tech.reactive.reactivemongoexample1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DestinationEvent {
    private Destination destination;
    private Date date;
}
