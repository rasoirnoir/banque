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
			
			
      Titulaire t1 = Requetes.getTitulaireByCode(1000);
		  System.out.println(t1);
		  ArrayList<Compte> l1  = Requetes.getCompteOfTitulaire(t1);
		  System.out.println(l1);
      
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
