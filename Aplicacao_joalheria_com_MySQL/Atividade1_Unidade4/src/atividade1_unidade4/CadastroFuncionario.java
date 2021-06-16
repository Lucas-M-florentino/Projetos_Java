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

/**
 *
 * @author Lucas Matheus
 */
public class CadastroFuncionario {
    private Connection connection = null;
    
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
    public CadastroFuncionario(/*BancoDados banco*/) {
    }
    public void adicionarFuncionario(Funcionario func){
        conectar();
        try{
            String sql = "INSERT into pessoa(nome,cpf,telefone,tipo,endereco,numCarteira,numTrabalhos,disponivel) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setString(1, func.getNome());
            ps.setString(2, ""+func.getCpf());
            ps.setString(3, func.getTelefone());
            ps.setString(4,"1");
            ps.setString(5,func.getEndereco());
            ps.setString(6,""+func.getNumCarteira());
            ps.setString(7,""+func.getNumTrabalhos());
            if(func.isDisponivel()==true){
            ps.setString(8,"1");
            }
            else{
                ps.setString(8,"0");
            }
            ps.execute();
            ps.close();
            
            System.out.println("Valores adicionados com sucesso");
            connection.close();
        }
        catch(Exception ex){
            System.out.println("Não foi possivel adicionar valores na base de dados!");
            ex.printStackTrace();
        }
    }
    
    public void removerFuncionarioNom(String nome) throws ClassNotFoundException{
        conectar();
        try{
            String sql = "DELETE from pessoa WHERE nome =?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,nome);
            ps.executeUpdate();
            ps.close();
            
            System.out.println("Valores Removidos com sucesso");
            connection.close();
        }
        catch(Exception ex){
            System.out.println("Falha ao remover valores, dados inexistentes");
            ex.printStackTrace();
        }
    }
    public void removerFuncNumCart(int numCarteira){
        conectar();
        try{            
            String sql = "DELETE from pessoa WHERE numCarteira =?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,""+numCarteira);
            ps.executeUpdate();
            ps.close();

            System.out.println("Valores Removidos com sucesso");
            connection.close();
        }
        catch(Exception ex){
            System.out.println("Falha ao remover valores, dados inexistentes");
            ex.printStackTrace();
        }
    }
    
    public int buscarFuncNCarteira(int numCarteira) throws ClassNotFoundException{
        conectar();
        int idFunc;
        try{
            
            //Statement stmt = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement("select * from pessoa WHERE numCarteira=?");
            ps.setString(1, ""+numCarteira);
            ResultSet rs = ps.executeQuery();
            
            rs.next();
            idFunc = rs.getInt(1);
            
            rs.close();
            connection.close();
            return idFunc;
        }
        catch(Exception ex){
            System.out.println("Dados inexistentes na base!");
            ex.printStackTrace();
            return -1;
        }
        
        
    }
    public Funcionario buscarFuncionarioNome(String nome) throws ClassNotFoundException{
        Funcionario func = new Funcionario(0,"", 0, "", "", 0, 0, true);
        conectar();
        try{
            int idFunc = 0;
            Statement stmt = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement("select * from pessoa WHERE nome=?");
            ps.setString(1, ""+nome);
            ResultSet rs = ps.executeQuery();
            
            rs.next();
            func.setIdFunc(rs.getInt(1));
            func = new Funcionario(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(6),rs.getInt(7),rs.getInt(8),rs.getBoolean(9));
            rs.close();
            connection.close();
            return func;
        }
        catch(Exception ex){
            System.out.println("Dados inexistentes na base!");
            ex.printStackTrace();
            return null;
        }
        
    }
    
    public Funcionario buscarFuncionarioId(int idFunc) throws ClassNotFoundException{
        Funcionario func = new Funcionario(0,"", 0, "", "", 0, 0, true);
        conectar();
        try{
            Statement stmt = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement("select * from pessoa WHERE idPessoa=?");
            ps.setString(1, ""+idFunc);
            ResultSet rs = ps.executeQuery();
            
            rs.next();
            func.setIdFunc(rs.getInt(1));
            func = new Funcionario(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(6),rs.getInt(7),rs.getInt(8),rs.getBoolean(9));
            
            rs.close();
            connection.close();
            return func;
        }
        catch(Exception ex){
            System.out.println("Dados inexistentes na base!");
            ex.printStackTrace();
            return null;
        }
        
    }
    
}
