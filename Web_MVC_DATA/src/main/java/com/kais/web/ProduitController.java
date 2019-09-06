package com.kais.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kais.dao.ProduitRepository;
import com.kais.entities.Produit;

@Controller
public class ProduitController {
	@Autowired
	ProduitRepository produitRepository;

	@GetMapping("/index")
	public String index(@RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "size", defaultValue = "5") int s,
			@RequestParam(name = "motCle", defaultValue = "") String mc, Model model) {
		Page<Produit> pageProduits = produitRepository.chercher("%" + mc + "%", PageRequest.of(p, s));
		model.addAttribute("listProduits", pageProduits.getContent());
		int[] pages = new int[pageProduits.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);
		return "index";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam(name = "id") Long id, @RequestParam(name = "page") int page,
			@RequestParam(name = "size") int size, @RequestParam(name = "motCle") String motCle, Model model) {
		produitRepository.deleteById(id);
		return "redirect:/index?page=" + page + "&size=" + size + "&motCle=" + motCle;
	}

	@GetMapping("/form")
	public String formProduit(Model model) {
		model.addAttribute("produit", new Produit());
		return "formProduit";
	}

	@PostMapping("/save")
	public String save(@Valid Produit produit, BindingResult bidingResult) {
		if (bidingResult.hasErrors())
			return "formProduit";
		produitRepository.save(produit);
		return "confirmation";
	}

	@GetMapping("/edit")
	public String formProduit(Model model, Long id) {
		Optional<Produit> p = produitRepository.findById(id);
		p.ifPresent(x -> {
			model.addAttribute("produit", x);
			produitRepository.save(x);
		});
		return "editProduit";
	}

	@GetMapping("/")
	public String home() {
		return "redirect:/index";
	}
	@GetMapping("/403")
	public String errorHandling() {
		return "403";
	}
}
