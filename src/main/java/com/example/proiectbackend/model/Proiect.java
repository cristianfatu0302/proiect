package com.example.proiectbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity()
@Data
@NoArgsConstructor
@Table(name = "proiect")
public class Proiect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proiectID")
    private Integer proiectID;
    @Column(name = "nume")
    private String nume;
    @Column(name = "descriere")
    private String descriere;
    @Column(name = "costLunar")
    private double costLunar;
}
