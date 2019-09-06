package com.kais.metier;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.kais.dao.CompteRepository;
import com.kais.dao.OperationRepository;
import com.kais.entities.Compte;
import com.kais.entities.CompteCourant;
import com.kais.entities.Operation;
import com.kais.entities.Retrait;
import com.kais.entities.Versement;

@Service
@Transactional
public class BanqueMetierImpl implements IBanqueMetier{
	
@Autowired
CompteRepository compteRepository;
@Autowired
OperationRepository operationRepository;

	@Override
	public Compte consulterCompte(String codeCpte) {
		return compteRepository.findById(codeCpte).orElseThrow(()->new RuntimeException("Compte introuvalbe"));
	}

	@Override
	public void verser(String codeCpte, double montant) {
		Compte cp = consulterCompte(codeCpte);
		Versement versement = new Versement(new Date(), montant, cp);
		operationRepository.save(versement);
		cp.setSolde(cp.getSolde()+montant);
		compteRepository.save(cp);
	}

	@Override
	public void retirer(String codeCpte, double montant) {
		Compte cp = consulterCompte(codeCpte);
		double facilitesCaisse = 0;
		if(cp instanceof CompteCourant)
			facilitesCaisse =((CompteCourant) cp).getDecouvert();
		if(montant > facilitesCaisse + cp.getSolde())
			throw new RuntimeException("solde insuffisant");
		Retrait retrait = new Retrait(new Date(), montant, cp);
		operationRepository.save(retrait);
		cp.setSolde(cp.getSolde()-montant);
		compteRepository.save(cp);
		
	}

	@Override
	public void virement(String codeCpte1, String codeCpte2, double montant) {
		if(codeCpte1.equals(codeCpte2))
			throw new RuntimeException("Virement impossible sur le même compte");
		retirer(codeCpte1, montant);
		verser(codeCpte2, montant);
		
	}

	@Override
	public Page<Operation> listOperation(String codeCpte, int page, int size) {
		// TODO Auto-generated method stub
		return operationRepository.listOperation(codeCpte, PageRequest.of(page, size));
	}

}
