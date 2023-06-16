package com.example.proiectbackend.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AngajatRequestBody {
    private Angajat angajat;
    private Integer proiectID;

    public AngajatRequestBody(Angajat angajat, Integer proiectID) {
        this.angajat = angajat;
        this.proiectID = proiectID;
    }

    public Angajat getAngajat() {
        return angajat;
    }

    public Integer getProiectID() {
        return proiectID;
    }

    public void setAngajat(Angajat angajat) {
        this.angajat = angajat;
    }

    public void setProiectID(Integer proiectID) {
        this.proiectID = proiectID;
    }
}
