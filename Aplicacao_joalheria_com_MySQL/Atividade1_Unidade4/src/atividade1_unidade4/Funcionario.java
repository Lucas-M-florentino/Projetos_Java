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
public class Funcionario extends Pessoa{
    private int numCarteira;
    private int numTrabalhos;
    private boolean disponivel;
    private String Endereco;
    private int idFunc;

    public Funcionario(int idFunc,String Nome, int cpf, String Telefone,String Endereco,int numCarteira, int numTrabalhos, boolean disponivel) {
        super(Nome, cpf, Telefone);
        this.idFunc = idFunc;
        this.numCarteira = numCarteira;
        this.numTrabalhos = numTrabalhos;
        this.disponivel = disponivel;
        this.Endereco = Endereco;
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
    
    public ArrayList<Funcionario> retornaDados() throws ClassNotFoundException{
        conectar();
        ArrayList<Funcionario> func = new ArrayList<>();
        try{
            Statement stmt = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement("select * from pessoa");
            
            ResultSet rs = ps.executeQuery();
        
            while(rs.next()==true){
               
                idFunc = rs.getInt(1);
               
                setNome(rs.getString(2));
                
                setCpf(rs.getInt(3));
                setTelefone(rs.getString(4));
                int tipo = rs.getInt(5);
                Endereco = rs.getString(6);
                numCarteira = rs.getInt(7);
                numTrabalhos = rs.getInt(8);
                if(rs.getInt(9)==1){
                    disponivel = true;
                }
                else{
                    disponivel = false;
                }
                        
                
                
                if(tipo == 1){
                    Funcionario funcionario = new Funcionario(idFunc,getNome(),getCpf(),getTelefone(), getEndereco(),getNumCarteira(),
                                                                getNumTrabalhos(),isDisponivel());
                    func.add(funcionario);
                }
        }
        
        rs.close();
        return func;
        }
        catch(Exception ex){
            System.out.println("Dados inexistentes na base!");
            ex.printStackTrace();
            return null;
        } 
    }
    
    public void comecarTrabalho(){
        
    }
    public void encerrarTrabalho(){
        
    }

    public int getNumCarteira() {
        return numCarteira;
    }

    public void setNumCarteira(int numCarteira) {
        this.numCarteira = numCarteira;
    }

    public int getNumTrabalhos() {
        return numTrabalhos;
    }

    public void setNumTrabalhos(int numTrabalhos) {
        this.numTrabalhos = numTrabalhos;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String Endereco) {
        this.Endereco = Endereco;
    }

    @Override
    public ArrayList<Pessoa> imprimirDados() {
        
        return null;
    }

    public int getIdFunc() {
        return idFunc;
    }

    public void setIdFunc(int idFunc) {
        this.idFunc = idFunc;
    }
}
