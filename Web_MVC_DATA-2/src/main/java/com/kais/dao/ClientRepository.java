package com.kais.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kais.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
