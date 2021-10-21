package fr.banque.models;

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

	@Override
	public String toString() {
		return "Compte [numero=" + numero + ", typeCompte=" + typeCompte + ", titulaire=" + titulaire + ", solde="
				+ solde + "]";
	}
	
	
}
