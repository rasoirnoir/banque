package fr.banque.bdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.banque.models.*;

public abstract class Requetes {
	
	
	public static ArrayList<TypeCompte> getAllTypeCompte() throws ClassNotFoundException,
	SQLException{
		ArrayList<TypeCompte> typesComptes = new ArrayList<TypeCompte>();
		
		String requete = "SELECT * FROM typecompte;";
		ResultSet result = AccesBD.executerQuery(requete);
		
		while(result.next()) {
			TypeCompte typeCompte = new TypeCompte();
			
			typeCompte.setCode(result.getInt("code"));
			typeCompte.setIntitule(result.getString("intitule"));
			
			typesComptes.add(typeCompte);
		}
		
		return typesComptes;
	}
	
	public static TypeCompte getTypeCompteByCode(int code) throws ClassNotFoundException,
	SQLException {
        TypeCompte typeCompte = new TypeCompte();
        Object[] params = {code};
        String requete = "SELECT * FROM typecompte WHERE typecompte.code = ?";
        
        ResultSet results = AccesBD.executerQuery(requete, params);
        
        while(results.next()) {
        	typeCompte.setCode(results.getInt("code"));
			typeCompte.setIntitule(results.getString("intitule"));
        }
        return typeCompte;
    }
	
	public static ArrayList<Titulaire> getAllTitulaire() throws ClassNotFoundException,
	SQLException{
		ArrayList<Titulaire> titulaires = new ArrayList<Titulaire>();
		
		String requete = "SELECT * FROM titulaire;";
		ResultSet result = AccesBD.executerQuery(requete);
		
		while(result.next()) {
			Titulaire titulaire = new Titulaire();
			
			titulaire.setCode(result.getInt("code"));
			titulaire.setPrenom(result.getString("prenom"));
			titulaire.setNom(result.getString("nom"));
			titulaire.setAdresse(result.getString("adresse"));
			titulaire.setCodePostal(result.getInt("codePostal"));
			
			
			
			titulaires.add(titulaire);
		}
		
		return titulaires;
	}
	
	public static Titulaire getTitulaireByCode(int code) throws ClassNotFoundException,
	SQLException {
        Titulaire titulaire = new Titulaire();
        Object[] params = {code};
        String requete = "SELECT * FROM titulaire WHERE titulaire.code = ?";
        
        ResultSet result = AccesBD.executerQuery(requete, params);
        
        while(result.next()) {
        	titulaire.setCode(result.getInt("code"));
			titulaire.setPrenom(result.getString("prenom"));
			titulaire.setNom(result.getString("nom"));
			titulaire.setAdresse(result.getString("adresse"));
			titulaire.setCodePostal(result.getInt("codePostal"));
        }
        return titulaire;
    }

	public static ArrayList<Compte> getAllComptes() throws ClassNotFoundException,
	SQLException{
		ArrayList<Compte> comptes = new ArrayList<Compte>();
		
		String requete = "SELECT * FROM compte;";
		ResultSet result = AccesBD.executerQuery(requete);
		
		while(result.next()) {
			Compte compte = new Compte();
			
			compte.setNumero(result.getInt("numero"));
			compte.setTypeCompte(result.getInt("codeTypeCompte"));
			compte.setTitulaire(result.getInt("codeTitulaire"));
			compte.setSolde(result.getFloat("solde"));
			
			comptes.add(compte);
		}
		
		return comptes;
	}
	
	public ArrayList<Operations> getAllOperations(){
		
	}
	
	
	
}
