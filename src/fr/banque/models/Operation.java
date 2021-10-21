package fr.banque.models;

import java.sql.Date;

/**
 * 
 * @author William Noris
 *
 */
public class Operation {

	private int numero;
	private Compte compte;
	private Date date;
	private String libelle;
	private float montant;
	private TypeOp typeOp;
	
	public Operation() {}

	public Operation(Compte compte, Date date, String libelle, float montant, TypeOp typeOp) {
		super();
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

	public TypeOp getTypeOp() {
		return typeOp;
	}

	public void setTypeOp(TypeOp typeOp) {
		this.typeOp = typeOp;
	}

	@Override
	public String toString() {
		return String.format("%-11d%-7d%-15s%-30s%-11.2f%-2s", numero, compte.getNumero(), date.toString(), libelle, montant, typeOp);
//		return "Operation " + typeOp +  " n°" + numero + "\n compte : " + 
//	compte + " montant : " + montant +  "\ndate : " + date + " libelle : " + libelle + "\n";
	}
	
}
