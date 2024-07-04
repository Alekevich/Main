package com.example.SpringSecurity_3.model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "memory", columnDefinition = "text")
    private String memory;

    @Column(name = "price")
    private int price;

    @Column(name = "color")
    private String color;

    @Column(name = "imageUrl")
    private String imageUrl;



}
