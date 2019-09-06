package com.kais.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.kais.entities.Compte;
import com.kais.entities.Operation;
import com.kais.metier.IBanqueMetier;

@Controller
public class BanqueController {

	@Autowired
	IBanqueMetier banqueMetier;

	@GetMapping("/")
	public String home() {
		return "redirect:/operations";
	}

	
	@GetMapping("/operations")
	public String index() {
		return "comptes";
	}

	@GetMapping("/consultercompte")
	public String consulter(String codeCompte,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "4") int size,
			Model model) {
		model.addAttribute("codeCompte", codeCompte);
		try {
			Page<Operation> pageOperations = banqueMetier.listOperation(codeCompte, page, size);
			model.addAttribute("listOperations", pageOperations.getContent());
			int[] pages = new int[pageOperations.getTotalPages()];
			model.addAttribute("pages", pages);
			model.addAttribute("pageCourante", page);
			Compte cp = banqueMetier.consulterCompte(codeCompte);
			model.addAttribute("compte", cp);

		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		return "comptes";
	}
	
	@PostMapping("/saveOperation")
	public String saveOperation( String codeCompte, String codeCompte2, double montant, String typeOperation, Model model) {
try {
			if(typeOperation.equals("VERS"))
				banqueMetier.verser(codeCompte, montant);
			else if(typeOperation.equals("RETR"))
				banqueMetier.retirer(codeCompte, montant);
			if(typeOperation.equals("VIR"))
				banqueMetier.virement(codeCompte, codeCompte2, montant);

		}   catch(Exception e) {
		model.addAttribute("error", e);
		return "redirect:/consultercompte?codeCompte="+codeCompte+"&error="+e.getMessage();
	}
return "redirect:/consultercompte?codeCompte="+codeCompte;
}
}