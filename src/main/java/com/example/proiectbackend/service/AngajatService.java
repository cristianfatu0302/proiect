package com.example.proiectbackend.service;

import com.example.proiectbackend.model.Angajat;
import com.example.proiectbackend.repository.AngajatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AngajatService {

    private final AngajatRepository angajatRepository;

    public AngajatService(AngajatRepository angajatRepository) {
        this.angajatRepository = angajatRepository;
    }

    public Angajat addAngajat(Angajat angajat){
        return angajatRepository.save(angajat);
    }

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
                angajatToUpdate.setFunctie(valoareCamp);
                break;
            case "email":
                angajatToUpdate.setEmail(valoareCamp);
                break;
        }

        return angajatRepository.save(angajatToUpdate);
    }
}
