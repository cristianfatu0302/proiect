package com.example.proiectbackend.model;

import com.example.proiectbackend.utils.FunctieEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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

    @Column(name = "functie")
    @Enumerated(EnumType.STRING)
    private FunctieEnum functie;
    private double salariu;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataAngajare;
    private String email;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="proiectID")
    Proiect proiect;

    @JsonProperty
    @Column(insertable=false, updatable=false)
    Integer proiectID;


    @JsonIgnore
    public Integer getProiectId(){ return this.proiectID;}

    public void setProiectId(Integer proiectID){this.proiectID = proiectID;}
    protected boolean canEqual(final Object other) {
        return other instanceof Angajat;
    }

}
