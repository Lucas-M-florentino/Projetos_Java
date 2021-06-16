
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

public class CadastroClientes {
    Connection connection = null;
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
    
    ////BancoDados banco;
    public CadastroClientes(/*BancoDados banco*/) {
    }
    public void adicionarCliente(Cliente cli) throws ClassNotFoundException{
        conectar();
        try{
            String sql = "INSERT into pessoa(nome,cpf,telefone,tipo,endereco,numCarteira,numTrabalhos,disponivel) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setString(1, cli.getNome());
            ps.setString(2, ""+cli.getCpf());
            ps.setString(3, cli.getTelefone());
            ps.setString(4,"0");
            ps.setString(5,cli.getEndereco());
            ps.setString(6, null);
            ps.setString(7, null);
            ps.setString(8, null);
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
    
    public void removerClienteNom(String nome) throws ClassNotFoundException{
        conectar();
        System.out.println("Nome Cliente: "+nome);
        try{
            String sql = "DELETE from pessoa WHERE nome =?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,nome);
            ps.executeUpdate();
            
            System.out.println("Valores Removidos com sucesso");
            ps.close();
        }
        catch(Exception ex){
            System.out.println("Falha ao remover valores, dados inexistentes");
            ex.printStackTrace();
       
        }
        
    }
    public void removerClienteCPF(int cpf) throws ClassNotFoundException{
        conectar();
        System.out.println("CPF: "+cpf);
        try{
            String sql = "DELETE from pessoa WHERE cpf =?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,""+cpf);
            ps.executeUpdate();
            
            System.out.println("Valores Removidos com sucesso");
            ps.close();
        }
        catch(Exception ex){
            System.out.println("Falha ao remover valores, dados inexistentes");
           // ex.printStackTrace();
        }
        
    }
    
    public void removerCliente(Cliente cli) throws ClassNotFoundException{
        conectar();
        try{
            int id;
            Statement stmt = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement("select * from pessoa WHERE nome=?");
            ps.setString(1, cli.getNome());
            ResultSet rs = ps.executeQuery();

            id = rs.getInt(1);
            
            
            String sql = "DELETE from pessoa WHERE nome =?";
            
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
    
    public Cliente buscarClienteNome(String nome) throws ClassNotFoundException{
        conectar();
        Cliente cli = new Cliente(0,"", 0, "", "");
        try{
            int idCli =0;
            //Statement stmt = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement("select * from pessoa WHERE nome=?");
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            
            rs.next();
            cli.setIdCli(rs.getInt(1));
            cli = new Cliente(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5));
         
            
            rs.close();
            return cli;
        }
        catch(Exception ex){
            System.out.println("Dados inexistentes na base!");
            ex.printStackTrace();
            return null;
        }
        
        
    }
    public int buscarClienteCPF(int CPF) throws ClassNotFoundException{
        conectar();
        Cliente cli;
        int idCli;
        try{
            //conectar();
            Statement stmt = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement("select * from pessoa WHERE cpf=?");
            ps.setString(1, ""+CPF);
            ResultSet rs = ps.executeQuery();
            
            rs.next();
            idCli = rs.getInt(1);
            cli = new Cliente(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(6));

            
            rs.close();
            return idCli;

        }
        catch(Exception ex){
            System.out.println("Dados inexistentes na base!");
            ex.printStackTrace();
            return -1;
        }
        
    }
    
    public Cliente buscarClienteId(int idCli) throws ClassNotFoundException{
        conectar();
        Cliente cli = new Cliente(0,"", 0, "", "");
        try{
            PreparedStatement ps = connection.prepareStatement("select * from pessoa WHERE idPessoa=?");
            ps.setString(1, ""+idCli);
            ResultSet rs = ps.executeQuery();
            
            rs.next();
            cli.setIdCli(rs.getInt(1));
            cli = new Cliente(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5));
            
            rs.close();
            return cli;
        }
        catch(Exception ex){
            System.out.println("Dados inexistentes na base!");
            ex.printStackTrace();
            return null;
        }
        
        
    }
    
}
