package fr.banque.main;
import java.lang.String;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.banque.models.*;
import fr.banque.bdd.*;

public class Main {

	public static void main(String[] args) throws SQLException{
		Connection co = null;
		try {
			co = AccesBD.Connect("banque");
			

			TypeCompte type1 = new TypeCompte("Compte Génial.");
			Requetes.addTypeCompte(type1);
			System.out.println(Requetes.getAllTypeCompte());
			
			TypeCompte type2 = Requetes.getTypeCompteByCode(12);
			type2.setIntitule("Prout");
			Requetes.updateTypeCompte(type2);
			System.out.println(Requetes.getAllTypeCompte());

			Titulaire t0 = new Titulaire(1006, "Bob", "Leponge", "123 allons dans les bois", 12345);
			Requetes.addTitulaire(t0);
			System.out.println(Requetes.getAllTitulaire());
			
			t0 = Requetes.getTitulaireByCode(1006);
			t0.setAdresse("Rue des bulles");
			Requetes.updateTitulaire(t0);
			System.out.println(Requetes.getTitulaireByCode(1006));
			
			Requetes.deleteTitulaire(t0);
			
			Titulaire t1 = Requetes.getTitulaireByCode(1000);
			System.out.println(t1);
			ArrayList<Compte> l1  = Requetes.getCompteOfTitulaire(t1);
			System.out.println(l1);
      
			System.out.println();
			System.out.println("Test des comptes");
			System.out.println("----------------");
			Compte c1 = Requetes.getCompteByNumero(10005);
			System.out.println(c1);
			c1.setTypeCompte(type2);
			Requetes.updateCompte(c1);
			System.out.println(Requetes.getCompteByNumero(10005));
			
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
