package fr.banque.models;

public class Transfer {
	
	public static void Virement(Compte compteDebiteur, Compte compteCrediteur, 
	float montant){
		
		compteDebiteur.retrait(montant, "");
		compteCrediteur.depot(montant, "");
		
	}
	

}
