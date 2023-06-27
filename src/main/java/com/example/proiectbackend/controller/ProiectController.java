package com.example.proiectbackend.controller;

import com.example.proiectbackend.model.Proiect;
import com.example.proiectbackend.service.ProiectService;
import com.example.proiectbackend.service.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proiect")
@Data
@CrossOrigin(origins ="http://localhost:3000")
public class ProiectController {

    private ProiectService proiectService;

    @Autowired
    public ProiectController(ProiectService proiectService){
        this.proiectService = proiectService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Proiect>> getAllProjects(){
        return ResponseEntity.ok(proiectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proiect> getProjectById(@PathVariable Integer id){
        Proiect proiect = proiectService.getByID(id);
        return  ResponseEntity.ok(proiect);
    }

    @PostMapping("/add")
    public ResponseEntity<Proiect> addProject(@RequestBody Proiect proiect){
        Proiect proiect1 = proiectService.addProject(proiect);
        if(proiect1 == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(proiect1);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProjectByID(@PathVariable("id") Integer id){
        proiectService.deleteProjectByID(id);
        return ResponseEntity.ok("Proiectul a fost sters cu succes!");
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Proiect> updateProiect(@PathVariable Integer id, @RequestParam String camp, @RequestParam String valoareCamp) throws ResourceNotFoundException {
        Proiect updatedProiect = proiectService.updateProiect(id, camp, valoareCamp);
        return ResponseEntity.ok(updatedProiect);
    }
}
