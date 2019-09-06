package com.kais.metier;

import org.springframework.data.domain.Page;

import com.kais.entities.Compte;
import com.kais.entities.Operation;

public interface IBanqueMetier {
	
Compte consulterCompte(String codeCpte);
void verser(String codeCompte, double montant);
void retirer(String codeCompte, double montant);
void virement(String codeCpte1, String codeCpte2, double montant);
Page<Operation> listOperation(String codeCpte, int page, int size);

}
