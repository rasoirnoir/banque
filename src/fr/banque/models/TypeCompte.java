package fr.banque.models;

public class TypeCompte {
	private int code;
	private String intitule;
	private String prenom;
	private String nom;
	private String adresse;
	private int codePostal;
	
	
	public TypeCompte(int code, String intitule, String prenom, String nom, String adresse, int codePostal) {
		super();
		this.code = code;
		this.intitule = intitule;
		this.prenom = prenom;
		this.nom = nom;
		this.adresse = adresse;
		this.codePostal = codePostal;
	}


	public TypeCompte() {
		
	}


	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public String getIntitule() {
		return intitule;
	}


	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public int getCodePostal() {
		return codePostal;
	}


	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}


	@Override
	public String toString() {
		return "TypeCompte [code=" + code + ", intitule=" + intitule + ", prenom=" + prenom + ", nom=" + nom
				+ ", adresse=" + adresse + ", codePostal=" + codePostal + "]";
	}
	
	
	
	
	
	
}
