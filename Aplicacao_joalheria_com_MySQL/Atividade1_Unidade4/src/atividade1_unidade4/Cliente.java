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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author Lucas Matheus
 */
public class Cliente extends Pessoa{
    private String endereco;
    private int idCli;
    
    public Cliente(int idCli,String Nome, int cpf, String Telefone,String endereco) {
        super(Nome, cpf, Telefone);
        this.endereco = endereco;
        this.idCli = idCli;
    }
    Connection connection;
    
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
    
    public ArrayList<Cliente> retornaDados() throws ClassNotFoundException{
        conectar();
        ArrayList<Cliente> cli = new ArrayList<Cliente>();
        try{
            Statement stmt = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement("select * from pessoa");
            
            ResultSet rs = ps.executeQuery();
        
            while(rs.next()==true){
               
                idCli = rs.getInt(1);
               
                setNome(rs.getString(2));
                
                setCpf(rs.getInt(3));
                setTelefone(rs.getString(4));
                int tipo = rs.getInt(5);
                endereco = rs.getString(6);
                
                if(tipo == 0){
                    Cliente cliente = new Cliente(idCli,getNome(),getCpf(),getTelefone(), getEndereco());
                    cli.add(cliente);
                }
        }
        
        rs.close();
        return cli;
        }
        catch(Exception ex){
            System.out.println("Dados inexistentes na base!");
            ex.printStackTrace();
            return null;
        } 
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getIdCli() {
        return idCli;
    }

    public void setIdCli(int idCli) {
        this.idCli = idCli;
    }

    public String getEndereco() {
        return endereco;
    }

    @Override
    public ArrayList<Pessoa> imprimirDados() {
    return null;
    }
    
}
