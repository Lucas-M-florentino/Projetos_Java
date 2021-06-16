/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade1_unidade4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Lucas Matheus
 */
public class Produto {
    private  String nome;
    private String categoria;
    private int idProd;
    Connection connection;
    public Produto(int idProd,String nome, String categoria) {
        this.idProd = idProd;
        this.nome = nome;
        this.categoria = categoria;
        
    }
    public void conectar(){
        String databaseName = "atv04";
        String url = "jdbc:mysql://localhost:3306/"+databaseName+"?useTimezone=true&serverTimezone=UTC";
        String username = ConexaoBD.username;
        String pasword = ConexaoBD.password;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,pasword);
        }
        catch(Exception ex){
            System.out.println("Falha ao conectar ao banco de dados");
            ex.printStackTrace();
        }
    }
    
    public ArrayList<Produto> imprimirDados(){
        conectar();
        ArrayList<Produto> prod = new ArrayList<>();
        
        try{
            Statement stmt = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement("select * from produto");
            
            ResultSet rs = ps.executeQuery();
        
            while(rs.next()==true){
               
                idProd = rs.getInt(1);
               
                nome = rs.getString(2);
                
                categoria = rs.getString(3);
            Produto produto = new Produto(idProd,nome,categoria);
          prod.add(produto);
        }
        
        rs.close();
        return prod;
        }
        catch(Exception ex){
            System.out.println("Dados inexistentes na base!");
            ex.printStackTrace();
            return null;
        } 
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }
    
}
