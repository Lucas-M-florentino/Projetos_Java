/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade1_unidade4;

import com.mysql.cj.jdbc.PreparedStatementWrapper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Lucas Matheus
 */

public class CadastroPedidos {
    CadastroClientes CadCli = new CadastroClientes();
    CadastroFuncionario CadFunc = new CadastroFuncionario();
    CadastroProdutos CadProd = new CadastroProdutos();
    public CadastroPedidos(){
        
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
 
    public void adicionarPedido(Pedido pedido) throws ClassNotFoundException{
        conectar();
        
        Cliente cli = new Cliente(0, "", 0, "", "");
        Funcionario func = new Funcionario(0, "", 0, "", "", 0, 0, true);
        Produto prod = new Produto(0, "", "");
        cli = CadCli.buscarClienteNome(pedido.getCliente().getNome());
        func = CadFunc.buscarFuncionarioNome(pedido.getFuncionario().getNome());
        prod = CadProd.buscaProdutoP(pedido.getProduto().getIdProd());
        try{
            
            String sql = "INSERT into pedido(data,Cliente_idPessoa,Funcionario_idPessoa,Produto_idProduto) VALUES(?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setString(1, pedido.getData());
            ps.setString(2, ""+cli.getIdCli());
            ps.setString(3, ""+func.getIdFunc());
            ps.setString(4, ""+prod.getIdProd());
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
    
    public void removerPedido(int idpedido) throws ClassNotFoundException{
    conectar();
        try{
            String sql = "DELETE from pedido WHERE idPedido =?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,""+idpedido);
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
    public void removerPedidoCliente(Cliente cliente) throws ClassNotFoundException{
        conectar();
        CadastroClientes CadCli = new CadastroClientes();
        try{
            int id;
            int idCliente = CadCli.buscarClienteCPF(cliente.getCpf());
            Statement stmt = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement("select * from pedido WHERE Cliente_idPessoa=?");
            ps.setString(1, ""+idCliente);
            ResultSet rs = ps.executeQuery();
            
            id = rs.getInt(1);
            String sql = "DELETE from pedido WHERE nome =?";
            
            ps.setString(1,""+id);
            int DeleteCount = ps.executeUpdate();
            ps.close();
            rs.close();
            
            System.out.println("Valores Removidos com sucesso");
            connection.close();
        }
        catch(Exception ex){
            System.out.println("Falha ao remover valores, dados inexistentes");
            ex.printStackTrace();
        }
    }
  
}
