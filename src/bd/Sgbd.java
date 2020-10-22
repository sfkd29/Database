package bd;
import java.sql.*;
public class Sgbd {
	
	static final String driver = "com.mysql.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost:3306/";

    static final String username = "root";
    static final String password = "";

    public static void main(String[] args) {
        Connection conn;
        Statement stmt;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Creation de la base de donn�es");
            stmt = conn.createStatement();
            String s1 = "CREATE DATABASE biblio";
            stmt.executeUpdate(s1);
            System.out.println("Base de donn�es cr��e");
        } catch (SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        }

        try{
        	final String url = "jdbc:mysql://localhost:3306/biblio";
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connexion r�ussie");

            stmt = conn.createStatement();

            String sql = "CREATE TABLE classe " +
                    "(code_cl varchar (6) not NULL , " +
                    " intitule VARCHAR(30), " +
                    " effectif INTEGER , " +
                    " PRIMARY KEY ( code_cl ))";
            stmt.executeUpdate(sql);

            String sql2 = "CREATE TABLE livre " +
                    "(code_liv varchar (15) not NULL , " +
                    " titre VARCHAR(40), " +
                    " auteur VARCHAR(30), " +
                    " genre VARCHAR(20), " +
                    " prix float , " +
                    " PRIMARY KEY ( code_liv ))";
            stmt.executeUpdate(sql2);

            String sql3 = "CREATE TABLE etudiant " +
                    "(matricule varchar (6) not NULL , " +
                    " nom VARCHAR(20), " +
                    " prenoms VARCHAR(50), " +
                    " sexe VARCHAR(1), " +
                    "code_cl VARCHAR(6),"+
                    "PRIMARY KEY ( matricule ),"+
                    "FOREIGN KEY (code_cl) REFERENCES classe (code_cl))";
            stmt.executeUpdate(sql3);

            String sql4 = "CREATE TABLE emprunt " +
                    "(matricule VARCHAR (6)," +
                    " code_liv VARCHAR(15), " +
                    " sortie DATETIME , " +
                    " retour DATETIME , " +
                    "FOREIGN KEY (code_liv) REFERENCES livre (code_liv),"+
                    "FOREIGN KEY (matricule) REFERENCES etudiant (matricule),"+
                    "PRIMARY KEY ( matricule,code_liv,sortie ))";
            stmt.executeUpdate(sql4);

            System.out.println("Tables cr��es");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
