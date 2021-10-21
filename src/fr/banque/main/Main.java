package fr.banque.main;


import java.sql.Connection;
import java.sql.SQLException;

import fr.banque.models.*;
import fr.banque.bdd.*;

public class Main {

	public static void main(String[] args) throws SQLException{
		Connection co = null;
		try {
			co = AccesBD.Connect("banque");
			
			
			Compte c1 = Requetes.getCompteByNumero(10005);
			System.out.println(c1);
			
			System.out.println(Requetes.getOperationsByCompte(c1));
			
			c1.retrait(1000000, "Tentative d'achat d'une maison");
			System.out.println(c1);
			c1.retrait(10000, "Achat d'une voiture");
			System.out.println(c1);
			System.out.println(Requetes.getOperationsByCompte(c1));
		}		
		catch(Exception e) {
			System.out.println("Erreur dans la main : " + e.getMessage());
		}
		finally {
			co.close();
		}
	}

}
