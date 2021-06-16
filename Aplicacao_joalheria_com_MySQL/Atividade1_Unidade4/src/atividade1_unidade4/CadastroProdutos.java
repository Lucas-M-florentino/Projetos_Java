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
public class CadastroProdutos {
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
    public CadastroProdutos(/*BancoDados banco*/) {
        
    }
    public void adicionarProduto(Produto produto){
        conectar();
        try{
            String sql = "INSERT into produto(nome,categoria) VALUES(?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setString(1, produto.getNome());
            ps.setString(2, ""+produto.getCategoria());
            ps.execute();
            
            ps = connection.prepareStatement("select * from produto WHERE nome=?");
            ps.setString(1, produto.getNome());
            ResultSet rs = ps.executeQuery();
            rs.next();
            produto.setIdProd(rs.getInt(1));
         
            ps.close();
            
            
            System.out.println("Valores adicionados com sucesso");
        }
        catch(Exception ex){
            System.out.println("Não foi possivel adicionar valores na base de dados!");
            ex.printStackTrace();
        }
    }
    
    public void adicionarProdutoNCat(String Nome,int categoria){
        conectar();
        try{
            String sql = "INSERT into produto(nome,categoria) VALUES(?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setString(1, Nome);
            ps.setString(2, ""+categoria);
            ps.execute();
            ps.close();
            
            System.out.println("Valores adicionados com sucesso");
        }
        catch(Exception ex){
            System.out.println("Não foi possivel adicionar valores na base de dados!");
            ex.printStackTrace();
        }
    }
    
    public void removerProduto(Produto produto){
        conectar();
        try{
            int id;
            Statement stmt = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement("select * from produto WHERE idProduto=?");
            ps.setString(1, ""+produto.getIdProd());
            ResultSet rs = ps.executeQuery();
            
            
            id = rs.getInt(1);
            System.out.println("idPessoa: "+rs.getInt(1));
            System.out.println("Nome: "+rs.getString(2));
            System.out.println("CPF: "+rs.getCharacterStream(3));
            
            
            String sql = "DELETE from produto WHERE nome =?";
            
            ps.setString(1,""+id);
            int DeleteCount = ps.executeUpdate();
            ps.close();
            rs.close();
            
            System.out.println("Valores Removidos com sucesso");
        }
        catch(Exception ex){
            System.out.println("Falha ao remover valores, dados inexistentes");
            ex.printStackTrace();
        }
        
    }
    public void removerProdutoNome(String nome){
        
        System.out.println("Nome do Produto: "+nome);
        conectar();
        try{
            String sql = "DELETE from produto WHERE nome =?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,nome);
            int DeleteCount = ps.executeUpdate();
            ps.close();
            
            
            System.out.println("Valores Removidos com sucesso");
           
        }
        catch(Exception ex){
            System.out.println("Falha ao remover valores, dados inexistentes");
            ex.printStackTrace();
            
        }
        
        
    }
    public Produto buscaProdutoNome(String nome){
        Produto produto = new Produto(0,"", "");
        try{
            String sql = "select * from produto WHERE nome=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, ""+nome);
            ResultSet rs = ps.executeQuery();
            
            rs.next();
            produto.setIdProd(rs.getInt(1));
            produto = new Produto(rs.getInt(1),rs.getString(2), rs.getString(3));
            System.out.println("idProduto: "+produto.getIdProd());
            System.out.println("Nome: "+rs.getString(2));
            System.out.println("Categoria: "+rs.getString(3));

            rs.close();
            return produto;
        }
        catch(Exception ex){
            System.out.println("Dados inexistentes na base!");
            ex.printStackTrace();
            return produto;
        }
        
        
    }
    public Produto buscaProdutoP(int idproduto){
        conectar();
        Produto produto = new Produto(0,"", "");
        try{
            String sql = "select * from produto WHERE idProduto=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, ""+idproduto);
            ResultSet rs = ps.executeQuery();
            
            rs.next();
            produto = new Produto(rs.getInt(1),rs.getString(2), rs.getString(3));

            rs.close();
            return produto;
        }
        catch(Exception ex){
            System.out.println("Dados inexistentes na base!");
            ex.printStackTrace();
            return null;
        }
    }
}
