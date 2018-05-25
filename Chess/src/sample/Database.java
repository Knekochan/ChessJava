package sample;

import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    private String conString = "jdbc:sqlite:database.db";
    private Connection con = null;

    private static Database instance;

    public static Database getInstance() {
        if(instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Database() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.initTables();
        } catch (Exception ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
    }

    public Connection getConnection() {
        try {
            con = DriverManager.getConnection(conString);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null,
                    ex);
            con = null;
        }
        return con;
    }

    public void initTables() {
        try {
            Statement st = getConnection().createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS scores(name TEXT, score INT)");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
    }

    public ArrayList<Pair<String, Integer>> getScores() {
        try {
            ArrayList<Pair<String, Integer>> list = new ArrayList<>();
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT name, score FROM scores");
            while(rs.next()) {
                list.add(new Pair<>(rs.getString("name"), rs.getInt("score")));
            }
            return list;
        }
        catch(SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
        return null;
    }

    public void addScore(String name, int score) {
        try {
            Statement st = getConnection().createStatement();
            st.execute("INSERT INTO scores (name,score) VALUES('" + name + "','" + score + "')");
        }
        catch(SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
    }
}
