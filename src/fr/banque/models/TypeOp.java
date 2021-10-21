package fr.banque.models;

public enum TypeOp {
	RETRAIT {
		public String toString() {
			return "-";
		}
	},
	DEPOT {
		public String toString() {
			return "+";
		}
	};
	
	/**
	 * Récupère l'ENUM TypeOp à partir d'un String
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public static TypeOp fromString(String type) throws Exception {
		switch(type) {
		case "+":
			return TypeOp.DEPOT;
		case "-":
			return TypeOp.RETRAIT;
		default:
			throw new Exception("Erreur : " + type + " n'est pas un type d'opération valide.");
		}
	}
}
