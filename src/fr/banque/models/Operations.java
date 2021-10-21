package fr.banque.models;

import java.sql.Date;

public class Operations {

	private int numero;
	private Compte compte;
	private Date date;
	private String libelle;
	private float montant;
	private char typeOp;
	
	public Operations() {}

	public Operations(int numero, Compte compte, Date date, String libelle, float montant, char typeOp) {
		super();
		this.numero = numero;
		this.compte = compte;
		this.date = date;
		this.libelle = libelle;
		this.montant = montant;
		this.typeOp = typeOp;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public float getMontant() {
		return montant;
	}

	public void setMontant(float montant) {
		this.montant = montant;
	}

	public char getTypeOp() {
		return typeOp;
	}

	public void setTypeOp(char typeOp) {
		this.typeOp = typeOp;
	}

	@Override
	public String toString() {
		return "Operations [numero=" + numero + ", compte=" + compte + ", date=" + date + ", libelle=" + libelle
				+ ", montant=" + montant + ", typeOp=" + typeOp + "]";
	}
	
}
