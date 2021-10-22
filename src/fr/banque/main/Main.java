package fr.banque.main;
import java.lang.String;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import fr.banque.models.*;
import fr.banque.bdd.*;

public class Main {

	public static void main(String[] args) throws SQLException{
		Connection co = null;
		try {
			co = AccesBD.Connect("banque");
			
			System.out.println("Liste des comptes.");
			System.out.println("------------------");
			ArrayList<Compte> cpts = Requetes.getAllComptes();
			for(Compte cpt : cpts) {
				System.out.println(cpt);
			}
			System.out.println();
			
			System.out.println("Liste des opérations.");
			System.out.println("---------------------");
			ArrayList<Operation> ops = Requetes.getAllOperations();
			for(Operation op : ops) {
				System.out.println(op);
			}
			System.out.println();
			
			System.out.println("Liste des types de compte.");
			System.out.println("--------------------------");
			ArrayList<TypeCompte> tdcs = Requetes.getAllTypeCompte();
			for(TypeCompte tdc : tdcs) {
				System.out.println(tdc);
			}
			System.out.println();
			
			System.out.println("Liste des titulaires.");
			System.out.println("---------------------");
			ArrayList<Titulaire> ts = Requetes.getAllTitulaire();
			for(Titulaire t : ts) {
				System.out.println(t);
			}
			System.out.println();
			
			
			System.out.println("Ajout d'un compte :");
			System.out.println("-------------------");
			Compte compte1 = new Compte(20000, Requetes.getTypeCompteByCode(1), Requetes.getTitulaireByCode(1004), (float)567.50);
			System.out.println(compte1);
			Requetes.addCompte(compte1);
			compte1 = Requetes.getCompteByNumero(20000);
			System.out.println(compte1);
			System.out.println();
			
			System.out.println("Modification d'un compte :");
			System.out.println("--------------------------");
			System.out.println(compte1);
			compte1.setTypeCompte(Requetes.getTypeCompteByCode(2));
			System.out.println(compte1);
			Requetes.updateCompte(compte1);
			System.out.println();
			
			System.out.println("Suppression d'un compte : ");
			System.out.println("--------------------------");
			System.out.println(compte1);
			Requetes.deleteCompte(compte1);
			System.out.println();
			
			
			
			System.out.println("Ajout d'un titulaire :");
			System.out.println("----------------------");
			Titulaire titu1 = new Titulaire(2000, "Bob", "Leponge", "2 rue glouglou", 12345);
			System.out.println(titu1);
			Requetes.addTitulaire(titu1);
			titu1 = Requetes.getTitulaireByCode(2000);
			System.out.println(titu1);
			System.out.println();
			
			System.out.println("Modification d'un titulaire :");
			System.out.println("-----------------------------");
			System.out.println(titu1);
			titu1.setAdresse("SDF");
			titu1.setCodePostal(0);
			System.out.println(titu1);
			Requetes.updateTitulaire(titu1);
			System.out.println();
			
			System.out.println("Suppression d'un titulaire : ");
			System.out.println("-----------------------------");
			System.out.println(titu1);
			Requetes.deleteTitulaire(titu1);
			System.out.println();
			
			System.out.println("Ajout d'un type de compte :");
			System.out.println("---------------------------");
			TypeCompte tc1 = new TypeCompte("Compte de ouf");
			System.out.println(tc1);
			Requetes.addTypeCompte(tc1);
			tc1 = Requetes.getTypeCompteByCode(45);
			System.out.println(tc1);
			System.out.println();
			
			System.out.println("Modification d'un type de compte :");
			System.out.println("----------------------------------");
			System.out.println(tc1);
			tc1.setIntitule("BOF");
			System.out.println(tc1);
			Requetes.updateTypeCompte(tc1);
			System.out.println();
			
			System.out.println("Suppression d'un type de compte : ");
			System.out.println("----------------------------------");
			System.out.println(tc1);
			Requetes.deleteTypeCompte(tc1);
			System.out.println();
	
			
			System.out.println("Liste des opérations pour le compte :");
			Compte compte2 = Requetes.getCompteByNumero(10005);
			System.out.println(compte2);
			System.out.println("-------------------------------------");
			ArrayList<Operation> opesCpt2 = Requetes.getOperationsByCompte(compte2);
			for (Operation opCpt2 : opesCpt2) {
				System.out.println(opCpt2);
			}
			System.out.println();
			
			System.out.println("Liste des comptes du titulaire :");
			Titulaire titu2 = Requetes.getTitulaireByCode(1002);
			System.out.println(titu2);
			System.out.println("--------------------------------");
			ArrayList<Compte> cptsTitu2 = Requetes.getCompteOfTitulaire(titu2);
			for(Compte cptTitu2 : cptsTitu2){
				System.out.println(cptTitu2);
			}
			System.out.println();
			
			System.out.println("Depôt sur le compte :");
			System.out.println(compte2);
			System.out.println("---------------------");
			compte2.depot(1000, "Indemnités");
			compte2 = Requetes.getCompteByNumero(compte2.getNumero());
			System.out.println(compte2);
			System.out.println();
			
			System.out.println("Retrait sur le compte :");
			System.out.println(compte2);
			System.out.println("-----------------------");
			compte2.retrait(500, "Cadeau de Noël");
			compte2 = Requetes.getCompteByNumero(compte2.getNumero());
			System.out.println(compte2);
			System.out.println();
			
			System.out.println("Enorme retrait sur le compte :");
			System.out.println(compte2);
			System.out.println("------------------------------");
			compte2.retrait(500000, "Achat d'une maison");
			compte2 = Requetes.getCompteByNumero(compte2.getNumero());
			System.out.println(compte2);
			System.out.println();
			
		}		
		catch(Exception e) {
			System.out.println("Erreur dans la main : " + e.getMessage());
		}
		finally {
			co.close();
		}
	}

}
