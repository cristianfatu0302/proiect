package com.example.proiectbackend.service;

import com.example.proiectbackend.model.Angajat;
import com.example.proiectbackend.model.Proiect;
import com.example.proiectbackend.repository.AngajatRepository;
import com.example.proiectbackend.repository.ProiectRepository;
import com.example.proiectbackend.utils.FunctieEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AngajatService {

    private final AngajatRepository angajatRepository;
    private final ProiectRepository proiectRepository;



    @Autowired
    public AngajatService(AngajatRepository angajatRepository, ProiectRepository proiectRepository) {
        this.angajatRepository = angajatRepository;
        this.proiectRepository = proiectRepository;

    }



    public Angajat addAngajat(Angajat angajat) {


        if (angajat == null) {
            throw new IllegalArgumentException("Angajatul nu poate fi null.");
        }

        Integer proiectID = angajat.getProiectID();


        if (proiectID == null) {
            throw new IllegalArgumentException("ID-ul proiectului nu a fost furnizat.");
        }


        if (proiectID <= 0) {
            throw new IllegalArgumentException("ID-ul proiectului este invalid.");
        }


        Proiect proiect = proiectRepository.findById(proiectID)
                .orElseThrow(() -> new IllegalArgumentException("Proiectul cu id-ul " + proiectID + " nu exista in baza de date"));

        angajat.setProiect(proiect);
        return angajatRepository.save(angajat);
    }

//    public void setProiectForAngajat(Integer angajatID, Integer proiectID){
//        Angajat angajat = angajatRepository.findById(angajatID)
//                .orElseThrow(() -> new IllegalArgumentException("Angajatul cu id-ul" +  angajatID +"  nu exista in baza de date"));
//        Proiect proiect = proiectRepository.findById(proiectID)
//                .orElseThrow(() -> new IllegalArgumentException("Proiectul cu id-ul" + proiectID + "nu exista in baza de date"));
//
//        angajat.setProiect(proiect);
//        angajatRepository.save(angajat);
//    }

    public Angajat getAngajat(Integer id){
        return angajatRepository.findById(id).orElse(null);
    }

    public List<Angajat> getAllAngajati(){
        return angajatRepository.findAll();
    }

    public void deleteAngajatById(Integer id){
        angajatRepository.deleteById(id);
    }

    public Angajat updateAngajat(Integer angajatID, String camp, String valoareCamp) throws ResourceNotFoundException {
        Angajat angajatToUpdate = angajatRepository.findById(angajatID)
                .orElseThrow(() -> new ResourceNotFoundException("Angajatul nu a fost gasit!"));
        switch (camp){
            case "nume":
                angajatToUpdate.setNume(valoareCamp);
                break;
            case "salariu":
                angajatToUpdate.setSalariu(Double.parseDouble(valoareCamp));
                break;
            case "functie":
                angajatToUpdate.setFunctie(FunctieEnum.valueOf(valoareCamp));
                break;
            case "email":
                angajatToUpdate.setEmail(valoareCamp);
                break;
        }

        return angajatRepository.save(angajatToUpdate);
    }
}
