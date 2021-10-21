package fr.banque.bdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.banque.models.Compte;
import fr.banque.models.Operation;

/**
 * 
 * @author Sana, William
 *
 */
public abstract class Requetes {

	public static ArrayList<Compte> getAllComptes() throws SQLException{
		ArrayList<Compte> comptes = new ArrayList<Compte>();
		
		String requete = "SELECT * FROM compte;";
		ResultSet results = AccesBD.executerQuery(requete);
		
		while(results.next()) {
			Compte compte = new Compte();
			
			compte.setNumero(results.getInt("numero"));
//			compte.setTypeCompte(result.getInt("codeTypeCompte"));
//			compte.setTitulaire(result.getInt("codeTitulaire"));
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
}
