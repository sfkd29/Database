package bd;
import  java.sql.*;

public class Sgdb {
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
            System.out.println("Creation de la base de données");
            stmt = conn.createStatement();
            String s1 = "CREATE DATABASE biblio";
            stmt.executeUpdate(s1);
            System.out.println("Base de données créée");
        } catch (SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        }

        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connexion réussie");

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
                    "code_cl VARCHAR(6)"+
                    "FOREIGN KEY (code_cl) REFERENCES classe(code_cl)"+
                    "PRIMARY KEY ( matricule ))";
            stmt.executeUpdate(sql3);

            String sql4 = "CREATE TABLE emprunt " +
                    "(matricule VARCHAR (6)," +
                    " code_liv VARCHAR(15), " +
                    " sortie DATETIME , " +
                    " retour DATETIME , " +
                    "FOREIGN KEY (code_liv) REFERENCES livre (code_liv)"+
                    "FOREIGN KEY (matricule) REFERENCES etudiant (matricule)"+
                    "PRIMARY KEY ( matricule,code_liv,sortie ))";
            stmt.executeUpdate(sql4);

            System.out.println("Tables créées");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try{
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected database successfully...");

            System.out.println("Insertion de données");
            stmt = conn.createStatement();

            String sql = "INSERT INTO classe " +
                    "VALUES ('class1', 'first class', 18)";
            stmt.executeUpdate(sql);

            String sql2 = "INSERT INTO livre " +
                    "VALUES ('miage_livre0001', 'LA VIE A MIAGE', 'Recit', 5000)";
            stmt.executeUpdate(sql2);

            String sql3 = "INSERT INTO etudiant " +
                    "VALUES('etu001', 'BOUEDIRO', 'STEVE', 'M','class1')";
            stmt.executeUpdate(sql3);

            String sql1 = "INSERT INTO emprunt  " +
                    "VALUES ('etu001', 'miage_livre0001', '2020-10-20 13:00:00', '2020-10-27 13:00:00')";
            stmt.executeUpdate(sql1);

            System.out.println("Données enregistrées");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
