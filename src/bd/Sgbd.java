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
        //Creation de la base de donn�es
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
        
        	//Creation des tables
        
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
        
        //Insertion de donn�es
        
        try{
        	final String url = "jdbc:mysql://localhost:3306/biblio";
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connection � la base de donn�es");
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected database successfully...");

            System.out.println("Insertion de donn�es");
            stmt = conn.createStatement();

            String sql = "INSERT INTO classe " +
                    "VALUES ('class1', 'first class', 18)";
            stmt.executeUpdate(sql);
            System.out.println("Donn�es enregistr�es classe");

            String sql2 = "INSERT INTO livre " +
                    "VALUES ('miage_livre001', 'LA VIE A MIAGE', 'ASSALE', 'Recit',5000)";
            stmt.executeUpdate(sql2);
            System.out.println("Donn�es enregistr�es livre");

            String sql3 = "INSERT INTO etudiant " +
                    "VALUES('etu001', 'BOUEDIRO', 'STEVE', 'M','class1')";
            stmt.executeUpdate(sql3);
            System.out.println("Donn�es enregistr�es etudiant");

            String sql1 = "INSERT INTO emprunt  " +
                    "VALUES ('etu001', 'miage_livre001', '2020-10-20 13:00:00', '2020-10-27 13:00:00')";
            stmt.executeUpdate(sql1);
            System.out.println("Donn�es enregistr�es emprunt");

            System.out.println("Donn�es enregistr�es");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try{
            final String url = "jdbc:mysql://localhost:3306/biblio";
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connection � la base de donn�es");
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected database successfully...");

            System.out.println("Affichage de donn�es");
            stmt = conn.createStatement();

            String sql = "SELECT code_cl, intitule, effectif FROM classe";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String code_cl  = rs.getString("code_cl");
                String intitule = rs.getString("intitule");
                int effectif = rs.getInt("effectif");
                System.out.println("CLASSE");
                System.out.print("\n CODE: " + code_cl);
                System.out.print(", INTITULE: " + intitule);
                System.out.print(", EFFECTIF: " + effectif);
            }
            rs.close();

            String sql1 = "SELECT code_liv, titre, auteur, genre, prix  FROM livre";
            ResultSet rs1 = stmt.executeQuery(sql1);
            while(rs1.next()){
                String code_liv  = rs1.getString("code_liv");
                String titre = rs1.getString("titre");
                String auteur = rs1.getString("auteur");
                String genre = rs1.getString("genre");
                float prix = rs1.getFloat("prix");
                System.out.println("LIVRE");
                System.out.print("\n CODE: " + code_liv);
                System.out.print(", TITRE: " + titre);
                System.out.print(", AUTEUR: " + auteur);
                System.out.print(", GENRE: " + genre);
                System.out.print(", PRIX: " + prix);
            }
            rs1.close();

            String sql2 = "SELECT matricule, nom, prenoms, sexe,code_cl FROM etudiant";
            ResultSet rs2 = stmt.executeQuery(sql2);
            while(rs2.next()){
                String matricule  = rs2.getString("matricule");
                String nom = rs2.getString("nom");
                String prenoms = rs2.getString("prenoms");
                String sexe = rs2.getString("sexe");
                String code_cl  = rs2.getString("code_cl");

                System.out.println("ETUDIANT");
                System.out.print(" \n MATRICULE: " + matricule);
                System.out.print(", NOM: " + nom);
                System.out.print(", PRENOMS: " + prenoms);
                System.out.print(", SEXE: " + sexe);
                System.out.print(",CLASSE: " +code_cl );
            }
            rs2.close();

            String sql3 = "SELECT matricule,code_liv, sortie, retour FROM emprunt";
            ResultSet rs3 = stmt.executeQuery(sql3);
            while(rs3.next()){
                String matricule  = rs3.getString("matricule");
                String code_liv = rs3.getString("code_liv");
                String sortie = rs3.getString("sortie");
                String retour = rs3.getString("retour");

                System.out.println("EMPRUNT");
                System.out.print("\n MATRICULE: " + matricule);
                System.out.print(", CODE: " + code_liv);
                System.out.print(", SORTIE: " + sortie);
                System.out.print(", RETOUR: " + retour);
            }
            rs3.close();

            System.out.println("\n Donn�es affich�es");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
