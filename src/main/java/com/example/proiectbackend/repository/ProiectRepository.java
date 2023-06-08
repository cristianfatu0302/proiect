package com.example.proiectbackend.repository;

import com.example.proiectbackend.model.Proiect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProiectRepository extends JpaRepository<Proiect, Integer> {

}
