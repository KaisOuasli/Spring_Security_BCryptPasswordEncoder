package com.kais.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kais.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, String>{

}
