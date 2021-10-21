package fr.banque.bdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.banque.models.*;

/**
 * 
 * @author Sana, William
 *
 */
public abstract class Requetes {
	
	// CRUD TypeCompte
	
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
	
	public static void addTypeCompte(TypeCompte typeCompte) throws SQLException {
		Object[] params = {
			
			typeCompte.getCode(),
			typeCompte.getIntitule()
		};
		String requete = "INSERT INTO typecompte VALUES (?, ?)";
		AccesBD.executerUpdate(requete, params);
	}
	
	public static void deleteTypeCompte(TypeCompte typeCompte) throws SQLException {
		Object[] params = {
				typeCompte.getCode()
		};
		String requete = "DELETE FROM typecompte WHERE typecompte.code = ?";
		AccesBD.executerUpdate(requete, params);
	}
	
	public static void updateTypeCompte(TypeCompte typeCompte) throws SQLException {
		Object[] params = {
				typeCompte.getCode(),
				typeCompte.getIntitule()
		};
		String requete = "UPDATE typecompte SET code=?, intitule=?";
		AccesBD.executerUpdate(requete, params);
	}
	
	
	//Crud Titulaire
	
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
        String requete = "SELECT * FROM titulaire WHERE titulaire.code=?" ;
        
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
	
	public static void addTitulaire(Titulaire titulaire) throws SQLException {
		Object[] params = {
			
			titulaire.getCode(),
			titulaire.getPrenom(),
			titulaire.getNom(),
			titulaire.getAdresse(),
			titulaire.getCodePostal(),
		};
		String requete = "INSERT INTO typecompte VALUES (?, ?)";
		AccesBD.executerUpdate(requete, params);
	}
	
	public static void deleteTitulaire(Titulaire titulaire) throws SQLException {
		Object[] params = {
				titulaire.getCode()
		};
		String requete = "DELETE FROM titulaire WHERE titulaire.code = ?";
		AccesBD.executerUpdate(requete, params);
	}
	
	public static void updateTitulaire(Titulaire titulaire) throws SQLException {
		Object[] params = {
				titulaire.getCode(),
				titulaire.getPrenom(),
				titulaire.getNom(),
				titulaire.getAdresse(),
				titulaire.getCodePostal(),
		};
		String requete = "UPDATE titulaire SET code=?, prenom=?, nom=?, adresse=?, codePostal=?";
		AccesBD.executerUpdate(requete, params);
	}
	
	//Lister les comptes pour un Titulaire
	
	public static ArrayList<Compte> getCompteOfTitulaire(Titulaire titulaire) throws SQLException, 
	ClassNotFoundException{
		ArrayList<Compte> comptes = new ArrayList<Compte>();
		Object[] params = {
				titulaire.getCode()
		};
		String requete = "SELECT * FROM compte WHERE compte.codeTitulaire=?;";
		ResultSet results = AccesBD.executerQuery(requete, params);
		
		while(results.next()) {
			Compte compte = new Compte();
			
			compte.setNumero(results.getInt("numero"));
			compte.setTypeCompte(Requetes.getTypeCompteByCode(results.getInt("codeTypeCompte")));
			compte.setTitulaire(Requetes.getTitulaireByCode(results.getInt("codeTitulaire")));
			compte.setSolde(results.getFloat("solde"));
						
			comptes.add(compte);
		}
		return comptes;
	}

	// Bonus Virement
	
	
	//CRUD Compte

	public static ArrayList<Compte> getAllComptes() throws SQLException, ClassNotFoundException{
		ArrayList<Compte> comptes = new ArrayList<Compte>();
		
		String requete = "SELECT * FROM compte;";
		ResultSet results = AccesBD.executerQuery(requete);
		
		while(results.next()) {
			Compte compte = new Compte();
			compte.setNumero(results.getInt("numero"));
			compte.setTypeCompte(getTypeCompteByCode(results.getInt("codeTypeCompte")));
			compte.setTitulaire(getTitulaireByCode(results.getInt("codeTitulaire")));
			compte.setSolde(results.getFloat("solde"));
			
			comptes.add(compte);
		}
		
		return comptes;
	}
	
	public static ArrayList<Operation> getAllOperations() throws SQLException{
		ArrayList<Operation> operations = new ArrayList<Operation>();
		
		String requete = "SELECT * FROM operations;";
		ResultSet results = AccesBD.executerQuery(requete);
		
		while(results.next()) {
			Operation operation = new Operation();
			
			operation.setNumero(results.getInt("numero"));
			operation.setCompte(null); //TODO
			operation.setDate(results.getDate("date"));
			operation.setLibelle(results.getString("libelle"));
			operation.setMontant(results.getFloat("montant"));
			operation.setTypeOp(results.getString("typeop").charAt(0));
			
			operations.add(operation);
		}
		return operations;
	}
	
	public static Compte getCompteByNumero(int numero) throws SQLException {
		Compte compte = new Compte();
		Object[] params = {numero};
		String requete = "SELECT * FROM compte WHERE compte.numero = ?;";
		
		ResultSet results = AccesBD.executerQuery(requete, params);
		
		while(results.next()) {
			compte.setNumero(results.getInt("numero"));
//			compte.setTypeCompte(result.getInt("codeTypeCompte"));
//			compte.setTitulaire(result.getInt("codeTitulaire"));
			compte.setSolde(results.getFloat("solde"));
		}
		return compte;
	}
	
	public static Operation getOperationByNumero(int numero) throws SQLException {
		Operation operation = new Operation();
		Object[] params = {numero};
		String requete = "SELECT * FROM operations WHERE operation.numero = ?";
		
		ResultSet results = AccesBD.executerQuery(requete, params);
		
		while(results.next()) {
			operation.setNumero(results.getInt("numero"));
			operation.setCompte(getCompteByNumero(results.getInt("numeroCompte"))); //TODO
			operation.setDate(results.getDate("date"));
			operation.setLibelle(results.getString("libelle"));
			operation.setMontant(results.getFloat("montant"));
			operation.setTypeOp(results.getString("typeop").charAt(0));
		}
		return operation;
	}
	
	public static void addCompte(Compte compte) throws SQLException {
		Object[] params = {
			compte.getNumero(),
			compte.getTypeCompte(),
			compte.getTitulaire(),
			compte.getSolde()
		};
		String requete = "INSERT INTO compte VALUES (?, ?, ?, ?)";
		AccesBD.executerUpdate(requete, params);
	}
	
	public static void deleteCompte(Compte compte) throws SQLException {
		Object[] params = {
				compte.getNumero()
		};
		String requete = "DELETE FROM compte WHERE compte.numero = ?";
		AccesBD.executerUpdate(requete, params);
	}
	
	public static void updateCompte(Compte compte) throws SQLException {
		Object[] params = {
				compte.getNumero(),
				compte.getTypeCompte().getCode(),
				compte.getTitulaire().getCode(),
				compte.getSolde()
		};
		String requete = "UPDATE compte SET numero=?, codeTypeCompte=?, codeTitulaire=?, solde=?";
		AccesBD.executerUpdate(requete, params);
	}
	
	/**
	 * Lister des opérations pour un compte sélectionné.
	 * @param compte
	 * @return Un ArrayList d'Operation
	 * @throws SQLException
	 */
	public static ArrayList<Operation> getOperationsByCompte(Compte compte) throws SQLException{
		ArrayList<Operation> operations = new ArrayList<Operation>();
		Object[] params = {
				compte.getNumero()
		};
		String requete = "SELECT * FROM operations WHERE operation.numeroCompte=?;";
		ResultSet results = AccesBD.executerQuery(requete, params);
		
		while(results.next()) {
			Operation op = new Operation();
			op.setNumero(results.getInt("numero"));
			op.setCompte(compte);
			op.setDate(results.getDate("date"));
			op.setLibelle(results.getString("libelle"));
			op.setMontant(results.getFloat("montant"));
			op.setTypeOp(results.getString("typeop").charAt(0));
			
			operations.add(op);
		}
		return operations;
	}
}


