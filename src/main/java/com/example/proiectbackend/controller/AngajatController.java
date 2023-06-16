package com.example.proiectbackend.controller;

import com.example.proiectbackend.model.Angajat;
import com.example.proiectbackend.model.AngajatRequestBody;
import com.example.proiectbackend.service.AngajatService;
import com.example.proiectbackend.service.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin(origins ="http://localhost:3000")
@RequestMapping("/angajati")
public class AngajatController {
    private final AngajatService angajatService;

    public AngajatController(AngajatService angajatService){
        this.angajatService = angajatService;
    }
    //POST
    @PostMapping("/add")
    public ResponseEntity<Angajat> addAngajat(@RequestBody AngajatRequestBody angajatRequestBody){
        Integer proiectID = angajatRequestBody.getProiectID();
        Angajat angajat = angajatRequestBody.getAngajat();

        Angajat angajat1 = angajatService.addAngajat(angajat);
        if(angajat1 == null){
            return ResponseEntity.noContent().build();
        }

        angajatService.setProiectForAngajat(angajat1.getAngajatID(), proiectID);

        return ResponseEntity.ok(angajat1);
    }
    //GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Angajat> getAngajat(@PathVariable Integer id){
        Angajat angajat = angajatService.getAngajat(id);
        return ResponseEntity.ok(angajat);
    }

    //GET ALL
    @GetMapping("/all")
    public ResponseEntity<List<Angajat>> getAllAngajati(){
        return ResponseEntity.ok(angajatService.getAllAngajati());
    }

    //PUT
    @PutMapping("/update/{id}")
    public ResponseEntity<Angajat> updateAngajat(@PathVariable Integer id, @RequestParam String camp, @RequestParam String valoare) throws ResourceNotFoundException {
        Angajat updatedAngajat = angajatService.updateAngajat(id, camp, valoare);
        return ResponseEntity.ok(updatedAngajat);
    }
    //DELETE
    //STERGERE DUPA UN CAMP UNIC
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteAngajatById(@PathVariable("id")Integer id){
        angajatService.deleteAngajatById(id);
        return ResponseEntity.ok("Angajatul cu id-ul " +id+ " a fost sters cu succes.");
    }
}
