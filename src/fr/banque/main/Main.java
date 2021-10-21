package fr.banque.main;


import java.sql.Connection;
import java.sql.SQLException;
import fr.banque.models.*;
import fr.banque.bdd.*;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Connection co = AccesBD.Connect("banque");
		
		
		Compte c1 = Requetes.getCompteByNumero(10005);
		System.out.println(c1);
		
		co.close();
	}

}
