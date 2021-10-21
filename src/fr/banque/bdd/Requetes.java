package fr.banque.bdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.banque.models.Compte;
import fr.banque.models.Operations;

public abstract class Requetes {

	public ArrayList<Compte> getAllComptes() throws SQLException{
		ArrayList<Compte> comptes = new ArrayList<Compte>();
		
		String requete = "SELECT * FROM compte;";
		ResultSet result = AccesBD.executerQuery(requete);
		
		while(result.next()) {
			Compte compte = new Compte();
			
			compte.setNumero(result.getInt("numero"));
//			compte.setTypeCompte(result.getInt("codeTypeCompte"));
//			compte.setTitulaire(result.getInt("codeTitulaire"));
			compte.setSolde(result.getFloat("solde"));
			
			comptes.add(compte);
		}
		
		return comptes;
	}
	
//	public ArrayList<Operations> getAllOperations(){
//		
//	}
}
