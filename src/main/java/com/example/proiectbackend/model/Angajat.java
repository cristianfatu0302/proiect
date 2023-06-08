package com.example.proiectbackend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "angajat")
public class Angajat {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer angajatID;
    private String nume;
    private String prenume;
    private String functie;
    private double salariu;
    private LocalDate dataAngajare;

    private String email;
    @ManyToOne
    @JoinColumn(name="proiectID")
    @JsonIgnore
    Proiect proiect;

    //getter pentru a returna id-ul proiectului
    public Integer getProiectID() {
        return proiect != null ? proiect.getProiectID() : null;
    }
}
