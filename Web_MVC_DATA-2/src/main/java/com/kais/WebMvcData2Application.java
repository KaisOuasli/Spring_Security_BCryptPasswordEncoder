package com.kais;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.kais.dao.ClientRepository;
import com.kais.dao.CompteRepository;
import com.kais.dao.OperationRepository;
import com.kais.entities.Client;
import com.kais.entities.Compte;
import com.kais.entities.CompteCourant;
import com.kais.entities.CompteEpargne;
import com.kais.entities.Retrait;
import com.kais.entities.Versement;
import com.kais.metier.IBanqueMetier;

@SpringBootApplication
public class WebMvcData2Application implements CommandLineRunner{

	
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	CompteRepository compteRepository;
	@Autowired
	OperationRepository operationRepository;
	@Autowired
	IBanqueMetier banqueMetier;
	
	public static void main(String[] args) {
		SpringApplication.run(WebMvcData2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Client c1 = clientRepository.save(new Client("Hassan", "hassan@gmail.com"));
		Client c2 = clientRepository.save(new Client("Rachid", "Rachid@gmail.com"));
		
		Compte cp1 = compteRepository.save(new CompteCourant("c1", new Date(), 90000, c1, 6000));
		Compte cp2 = compteRepository.save(new CompteEpargne("c2", new Date(), 6000, c2, 5.5));
		
		operationRepository.save(new Versement(new Date(), 9000, cp1));
		operationRepository.save(new Versement(new Date(), 6000, cp1));
		operationRepository.save(new Retrait(new Date(), 2300, cp1));
		operationRepository.save(new Versement(new Date(), 9000, cp1));
		
		operationRepository.save(new Versement(new Date(), 2300, cp2));
		operationRepository.save(new Retrait(new Date(), 400, cp2));
		operationRepository.save(new Versement(new Date(), 2300, cp2));
		operationRepository.save(new Versement(new Date(), 3000, cp2));
		
		banqueMetier.verser("c1", 11111);
		
		/*
		 * String encodedPassword = passwordEncoder.encode("user");
		 * System.out.println("****user: " + encodedPassword); encodedPassword =
		 * passwordEncoder.encode("admin"); System.out.println("****admin: " +
		 * encodedPassword);
		 */
	}

}
