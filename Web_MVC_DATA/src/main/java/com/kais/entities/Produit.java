package com.kais.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Produit implements Serializable{
	
private static final long serialVersionUID = 3987431649385046103L;

@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@NotNull
@Size(min = 2, max = 40)
private String designation;
@DecimalMin("10")
private double prix;
@DecimalMin("0")
private int quantite;
public Produit() {
	super();
	// TODO Auto-generated constructor stub
}
public Produit(String designation, double prix, int quantite) {
	super();
	this.designation = designation;
	this.prix = prix;
	this.quantite = quantite;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getDesignation() {
	return designation;
}
public void setDesignation(String designation) {
	this.designation = designation;
}
public double getPrix() {
	return prix;
}
public void setPrix(double prix) {
	this.prix = prix;
}
public int getQuantite() {
	return quantite;
}
public void setQuantite(int quantite) {
	this.quantite = quantite;
}

}