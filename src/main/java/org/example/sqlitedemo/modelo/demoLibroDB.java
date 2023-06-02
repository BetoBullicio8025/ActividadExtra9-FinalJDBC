package org.example.sqlitedemo.modelo;

import org.example.sqlitedemo.persistencia.ConexionSingleton;

import java.sql.*;
import java.util.ArrayList;

public class demoLibroDB {

    public demoLibroDB() {

    }

    public void insertarStatement() {
        String elTitulo = "Arrancame la vida";
        String elAutor = "Ángeles Matreta";
        try {
            Statement stm = ConexionSingleton.getInstance("librosDB2.db").getConnection().createStatement();
            String sqlInsert = "INSERT INTO libros(titulo, autor) VALUES ('" + elTitulo + "','" + elAutor + "')";
            int rowCount = stm.executeUpdate(sqlInsert);
            System.out.println("Se insertaron" + rowCount + "registros");
        } catch (SQLException sqle) {
            System.out.println("Error al conectar" + sqle.getMessage());
        }

    }

    public void insertarPreparedStatement() {
        String elTitulo = "Mit huele a popo";
        String elAutor = "<3";
        String sqlInsert = "INSERT INTO libros(titulo,autor) VALUES(?, ?)";
        try {
            PreparedStatement pstm = ConexionSingleton.getInstance("librosDB2.db").getConnection().prepareStatement(sqlInsert);
            pstm.setString(1, elTitulo);
            pstm.setString(2, elAutor);
            pstm.executeUpdate();
            int rowCount = pstm.executeUpdate();
            System.out.println("Se insertaron" + rowCount + "registros");

        } catch (SQLException sqle) {
            System.out.println("Error en preparedStatement" + sqle.getMessage());
        }
    }

    public boolean insertarLibro(libro libro) {
        String sqlInsert = "INSERT INTO libros(titulo,autor) VALUES(?, ?)";
        int rowCount = 0;
        try {
            PreparedStatement pstm = ConexionSingleton.getInstance("librosDB2.db").getConnection().prepareStatement(sqlInsert);
            pstm.setString(1, libro.getTitulo());
            pstm.setString(2, libro.getAutor());
            pstm.executeUpdate();
            rowCount = pstm.executeUpdate();
            System.out.println("Se insertaron" + rowCount + "registros");

        } catch (SQLException sqle) {
            System.out.println("Error en preparedStatement" + sqle.getMessage());
        }
        return rowCount > 0;
    }

    public libro buscarLibroPorId(int id) {
        String sql = "SELECT * FROM libros WHERE id= ? ;";
        libro libro2 = null;
        try {
            PreparedStatement pstm = ConexionSingleton.getInstance("librosDB2.db").getConnection().prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rst = pstm.executeQuery();
            if (rst.next()) {
                libro2 = new libro(rst.getInt(1), rst.getString(2), rst.getString(3));
            }

        } catch (SQLException sqle) {
            System.out.println("Error al buscar");
        }
        return libro2;
    }

    public ArrayList<libro> obtenerTodos() {
        String sql = "SELECT * FROM libros ";
        ArrayList<libro> resultado = new ArrayList<>();

        try {
            Statement stm = ConexionSingleton.getInstance("librosDB2.db").getConnection().createStatement();
            ResultSet rst = stm.executeQuery(sql);
            while (rst.next()){
                resultado.add(new libro(rst.getInt(1),rst.getString(2), rst.getString(3)));

            }
        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
        }
        return resultado;

    }



}
