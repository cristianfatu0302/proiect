package com.example.proiectbackend.service;

import com.example.proiectbackend.model.Proiect;
import com.example.proiectbackend.repository.ProiectRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ProiectService {

    private ProiectRepository proiectRepository;

    @Autowired
    public ProiectService(ProiectRepository proiectRepository){
        this.proiectRepository = proiectRepository;
    }

    public List<Proiect> getAllProjects(){
        return proiectRepository.findAll();
    }

    public Proiect addProject(Proiect proiect){
        return proiectRepository.save(proiect);
    }

    public Proiect getByID(Integer id){
        return proiectRepository.findById(id).orElse(null);
    }

    public void deleteProjectByID(Integer id){
        proiectRepository.deleteById(id);
    }

    public Proiect updateProiect(Integer id, String camp, String valoareCamp) throws ResourceNotFoundException {
        Proiect proiectToUpdate = proiectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proiectul nu a fost gasit"));
        switch (camp){
            case "nume":
                proiectToUpdate.setNume(valoareCamp);
                break;
            case "descriere":
                proiectToUpdate.setDescriere(valoareCamp);
                break;
            case "costLunar":
                proiectToUpdate.setCostLunar(Double.parseDouble(valoareCamp));
                break;
        }

        return proiectRepository.save(proiectToUpdate);
    }
}
