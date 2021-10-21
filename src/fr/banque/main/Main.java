package fr.banque.main;
import java.lang.String;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.banque.models.*;
import fr.banque.bdd.*;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		
		Connection co = AccesBD.Connect("banque");
		Titulaire t1 = Requetes.getTitulaireByCode(1000);
		System.out.println(t1);
		ArrayList<Compte> l1  = Requetes.getCompteOfTitulaire(t1);
		System.out.println(l1);
		co.close();
		
		
	}

}
