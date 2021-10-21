package fr.banque.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

public class AccesBD {

	private static String DB_URL = "";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "password";
	private static Connection connexion = null;

	/**
	 * Permet de se connecter à la base de données
	 * 
	 * @return
	 */
	public static Connection Connect(String nomBDD) {
		setDB_URL(nomBDD);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			if (connexion == null)
				connexion = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (ClassNotFoundException cnfE) {
			System.out.println("Problème de pilote :\n" + cnfE);
			System.exit(1);
		} catch (SQLException sqlE) {
			System.out.println("Problème de connexion à la base :\n" + sqlE);
			System.exit(2);
		}
		return connexion;
	}

	/**
	 * Execute une requête SELECT
	 * 
	 * @param requete La requete select à executer
	 * @return Le ResultSet correspondant au resultat de la requête
	 * @throws SQLException
	 */
	public static ResultSet executerQuery(String requete) throws SQLException

	{
		/*
		 * On déclare un objet de type Statement que l'on nomme instruction. Cet objet
		 * soumet la requête à la base de données dans MySQL. On déclare un objet de
		 * type ResultSet que l'on nomme resultat. cet objet est retourné pour
		 * encapsuler les résultats de la requête. Il va nous permettre de manipuler les
		 * résultats de la requête. Les afficher, les stocker dans des objets,...
		 *
		 */

		// Déclaration de nos variables
		Statement statement = null;
		ResultSet resultat = null;

		try {
			/*
			 * Paramètres ajouté pour la gestion des curseurs pour la navigation dans un
			 * ResultSet TYPE_SCROLL_INSENSITIVE : Cette valeur indique que le curseur peut
			 * être déplacé dans les deux sens, mais aussi arbitrairement (de manière
			 * absolue ou relative). Le terme insensitive indique que le ResultSet est
			 * insensible aux modifications des valeurs dans la base de données. Cela
			 * définit en fait une vue statique des données contenues dans le ResultSet.
			 *
			 * CONCUR_UPDATABLE : Cette valeur indique que l'on peut modifier les données de
			 * la base via le ResultSet.
			 */

			int type = ResultSet.TYPE_SCROLL_SENSITIVE;
			int mode = ResultSet.CONCUR_UPDATABLE;

			/*
			 * On peut traduire Statement par ordre ou instruction. La méthode
			 * createStatement() nous retourne un objet de type Statement. Nous l'avons
			 * appelé avec la méthode getConnection() qui nous renvoie un objet de type
			 * Connection nommé connexion. Dés lors, nous pouvons utiliser l'objet
			 * instruction pour interroger la base de données.
			 *
			 */
			statement = connexion.createStatement(type, mode);
			/*
			 * Pour cela, il nous suffit d'appeler la méthode executeQuery() en lui passant
			 * comme paramètre, la requete que nous voulons exécuter. L'objet resultat
			 * contient le résultat de l'exécution de la requête.
			 */
			resultat = statement.executeQuery(requete);

		} catch (SQLException sqle) {
			System.out.println("Problème dans la requête SQL !");
			sqle.printStackTrace();
		}
		return resultat; // retourne un ResultSet
	}

	public static ResultSet executerQuery(String requete, Object[] parametres) {
		PreparedStatement statement = null;
		ResultSet resultat = null;
		try {

			statement = connexion.prepareStatement(requete);
			for (int i = 0; i < parametres.length; i++) {
				statement.setObject(i + 1, parametres[i]);
			}

			resultat = statement.executeQuery();
		} catch (SQLException sqle) {
			System.out.println("Problème dans la requête SQL !");
			sqle.printStackTrace();
		}
		return resultat;
	}

	/**
	 * Méthode d'exécution d'une requete Update (UPDATE, INSERT, DELETE ou CREATE ou
	 * DROP). Elle ne renvoie rien
	 * 
	 * @param requete String
	 */
	public static void executerUpdate(String requete) throws SQLException

	{
		Statement statement = null;
		try {

			statement = connexion.createStatement();

			int i = statement.executeUpdate(requete);

			if (i == 1) // on affiche un message d'information sur l'opération pour le plaisir !

			{
				System.out.println("L'opération a réussie !");
			} else {
				System.out.println("L'opération a échoué !");
			}

		}

		catch (SQLException sqle) {
			System.out.println("Problème dans la requête SQL !");
			sqle.printStackTrace();
		} finally {

			statement.close();
			connexion.close();

		}

	}

	public static void executerUpdate(String requete, Object[] params) throws SQLException {
		PreparedStatement statement = null;
		try {

			statement = connexion.prepareStatement(requete);

			for (int i = 0; i < params.length; i++) {
				statement.setObject(i + 1, params[i]);
			}
			int i = statement.executeUpdate();

			if (i == 1) // on affiche un message d'information sur l'opération pour le plaisir !

			{
				System.out.println("L'opération a réussie !");
			} else {
				System.out.println("L'opération a échoué !");
			}	
		}
		catch(SQLIntegrityConstraintViolationException sqlicvE) {
			System.out.println("Requete échouée : Violation de contrainte d'intégrité.");
		}
		catch (SQLException sqle) {
			System.out.println("Problème dans la requête SQL !");
			sqle.printStackTrace();
		} finally {

			statement.close();
		}
	}

	/**
	 * Met à jour le nom de la base de données à laquelle se connecter
	 * 
	 * @param nomBDD Le nom de la base de données
	 */
	private static void setDB_URL(String nomBDD) {
		DB_URL = "jdbc:mysql://localhost:3306/" + nomBDD
				+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	}
}
