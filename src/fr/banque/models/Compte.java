package fr.banque.models;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import fr.banque.bdd.Requetes;

/**
 * 
 * @author William Noris
 *
 */
public class Compte {

	private int numero;
	private TypeCompte typeCompte;
	private Titulaire titulaire;
	private float solde;
	
	public Compte(int numero, TypeCompte typeCompte, Titulaire titulaire, float solde) {
		super();
		this.numero = numero;
		this.typeCompte = typeCompte;
		this.titulaire = titulaire;
		this.solde = solde;
	}
	
	public Compte() {}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public TypeCompte getTypeCompte() {
		return typeCompte;
	}

	public void setTypeCompte(TypeCompte typeCompte) {
		this.typeCompte = typeCompte;
	}

	public Titulaire getTitulaire() {
		return titulaire;
	}

	public void setTitulaire(Titulaire titulaire) {
		this.titulaire = titulaire;
	}

	public float getSolde() {
		return solde;
	}

	public void setSolde(float solde) {
		this.solde = solde;
	}
	
	/**
	 * Enregistrer une opération sur un compte (dépôt ou retrait).
	 * 1) Enregistrer une opération dans la base
	 * 2) Débiter le compte du montant de l'opération
	 * @param montant
	 * @throws SQLException 
	 */
	public void depot(float montant, String libelle) {
		Operation ope = new Operation(this, Date.valueOf(LocalDate.now()), libelle, montant, TypeOp.DEPOT);
		try {
			Requetes.addOperation(ope);
			this.solde += montant;
			Requetes.updateCompte(this);
		}
		catch(SQLException sqlE) {
			System.out.println("Erreur lors du depôt. Operation annulée.");
		}		
	}
	
	/**
	 * Enregistrer une opération sur un compte (dépôt ou retrait).
	 * @param montant
	 */
	public void retrait(float montant, String libelle) {
		Operation ope = new Operation(this, Date.valueOf(LocalDate.now()), libelle, montant, TypeOp.RETRAIT);

		if(montant <= this.getSolde()) {
			try {
				Requetes.addOperation(ope);
				this.solde -= montant;
				Requetes.updateCompte(this);
			}
			catch(SQLException sqlEx) {
				System.out.println("Erreur lors du retait. Operation annulée.");
			}
		}
	}

	@Override
	public String toString() {
		return String.format("%-7d%-5d%-7d%-10.2f", numero, typeCompte.getCode(), titulaire.getCode(), solde);
//		return "Compte n°" + numero + " " + typeCompte + 
//		"\ntitulaire : " + titulaire + " solde : "+ solde + "\n";
	}
	
	
}
