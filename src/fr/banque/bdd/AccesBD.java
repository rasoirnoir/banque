package fr.banque.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;

public class AccesBD {

	private static String DB_URL = "";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "password";
	private static Connection connexion = null;

	/**
	 * Permet de se connecter à la base de données
	 * 
	 * @return
	 * @throws SQLException 
	 */
	public static Connection Connect(String nomBDD) throws SQLException {
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
		connexion.setAutoCommit(false);
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
		connexion.setAutoCommit(true);
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
		finally {
			connexion.setAutoCommit(false);
		}
		return resultat; // retourne un ResultSet
	}

	public static ResultSet executerQuery(String requete, Object[] parametres) throws SQLException {
		PreparedStatement statement = null;
		connexion.setAutoCommit(true);
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
		finally {
			connexion.setAutoCommit(false);
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
		connexion.setAutoCommit(true);
		try {

			statement = connexion.createStatement();

			int i = statement.executeUpdate(requete);
			connexion.commit();

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
			connexion.setAutoCommit(false);
			statement.close();
			connexion.close();

		}

	}

	public static void executerUpdate(String requete, Object[] params) throws SQLException {
		connexion.setAutoCommit(true);
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
			connexion.setAutoCommit(false);
			statement.close();
		}
	}
	
	
	public static void transactionUpdate(String[] requetes, Object[][] params) throws SQLException {
		connexion.setAutoCommit(false);
		Savepoint save = connexion.setSavepoint();
		try {
			ArrayList<PreparedStatement> statements = new ArrayList<PreparedStatement>();
			for(int i = 0; i < requetes.length; i++) {
				PreparedStatement st = connexion.prepareStatement(requetes[i]);
				Object[] tabParam = params[i];
				for (int j = 0; j < tabParam.length; j++) {
					st.setObject(j + 1,tabParam[j]);
				}
				statements.add(st);
			}

			for(PreparedStatement statement : statements) {
				statement.executeUpdate();
			}

			connexion.commit();
		}
		catch(SQLException sqlE) {
			connexion.rollback(save);
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
