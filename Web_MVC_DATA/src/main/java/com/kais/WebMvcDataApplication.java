package com.kais;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.kais.dao.ProduitRepository;
import com.kais.entities.Produit;

@SpringBootApplication
public class WebMvcDataApplication implements CommandLineRunner {

	@Autowired
	ProduitRepository produitRepository;

	public static void main(String[] args) {
		SpringApplication.run(WebMvcDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		produitRepository.save(new Produit("imprimante hp 230", 120, 10));
		produitRepository.save(new Produit("ordinateur dell t82", 840, 8));
		produitRepository.save(new Produit("imprimante lx330", 95, 14));
		produitRepository.save(new Produit("imprimante bx 230", 90, 10));
		produitRepository.save(new Produit("ordinateur bell t22", 890, 8));
		produitRepository.save(new Produit("imprimante lx430", 91, 14));
		produitRepository.save(new Produit("imprimante asus 230", 120, 10));
		produitRepository.save(new Produit("ordinateur versus t82", 840, 8));
		produitRepository.save(new Produit("imprimante lx330", 95, 14));
		produitRepository.save(new Produit("imprimante brother 230", 130, 10));
		produitRepository.save(new Produit("ordinateur compaq t22", 890, 8));
		produitRepository.save(new Produit("imprimante lx430", 91, 14));
		produitRepository.save(new Produit("imprimante fd 230", 120, 10));
		produitRepository.save(new Produit("ordinateur fx t82", 840, 8));
		produitRepository.save(new Produit("imprimante lx310", 90, 12));
		produitRepository.save(new Produit("imprimante fd 830", 190, 10));
		produitRepository.save(new Produit("ordinateur acer 982", 240, 8));
		produitRepository.save(new Produit("imprimante lf310", 190, 12));
	}
}
