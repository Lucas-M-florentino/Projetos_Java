/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade1_unidade4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Lucas Matheus
 */
public class ConexaoBD {
     static CadastroClientes CadCli = new CadastroClientes();
    static CadastroFuncionario CadFunc = new CadastroFuncionario();
    static CadastroProdutos CadProd = new CadastroProdutos();
    static Connection connection = null;
    static String username = "root";
    static String password = "server2020POO";
    public static Connection connection() throws ClassNotFoundException{
        // função que estabelece conexão com banco de dados
        String databaseName = "atv04";
        String url = "jdbc:mysql://localhost:3306/"+databaseName+"?useTimezone=true&serverTimezone=UTC";
    
     
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
            System.out.println("Conectado a base de dados com sucesso!");
            return connection;
        }
        catch(SQLException ex){
            System.out.println("Não foi possivelconectar com a base de dados de dados!");
            ex.printStackTrace();
            return connection;
        } 
        
    }
    
    public static void resetarTabela(){
        try{
            //Statement stmt = connection.createStatement();
            String sql ="DELETE from pedido where idPedido >= 1";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate(sql);
            System.out.println("Tabela pedido limpa com sucesso");
            
            sql ="DELETE from produto where idProduto >= 1";
            ps = connection.prepareStatement(sql);
            ps.executeUpdate(sql);
            System.out.println("Tabela produto limpa com sucesso");
            
            sql ="DELETE from pessoa where idPessoa >= 1";
            ps = connection.prepareStatement(sql);
            ps.executeUpdate(sql);
            System.out.println("Tabela pessoa limpa com sucesso");
          
        }
        catch(Exception ex){
            System.out.println("Falha ao limpar tabela, Tabela vazia");
            ex.printStackTrace();
        } 
    }
    public static void addDadosBase() throws ClassNotFoundException, SQLException{
        //função para limpar tabela
        resetarTabela();
        Cliente cliente;
        Funcionario funcionario;
        Produto produto;
        Pedido pedido;
        // add dados de base para mostrar no programa
        cliente = new Cliente(0,"Lucas Matheus", 324317, "988543244", "avenida das palmeiras, 22");
        CadCli.adicionarCliente(cliente);
        cliente = new Cliente(0,"Marcia Lima", 495352, "995693733", "rua pedro Matias, 1355");
        CadCli.adicionarCliente(cliente);
        funcionario = new Funcionario(0,"João Marcos", 364798, "991234567", "Rua Goiás, 78", 2324, 3, true);
        CadFunc.adicionarFuncionario(funcionario);
        funcionario = new Funcionario(0,"Fernando Gabriel Mendes", 478342, "981536401", "Rua Manoel Oliveira, 274", 5631, 2, true);
        CadFunc.adicionarFuncionario(funcionario);
        produto = new Produto(0,"Pulseira", "Ouro");
        CadProd.adicionarProduto(produto);
        produto = new Produto(0,"Anel", "Ouro");
        CadProd.adicionarProduto(produto);
        Date datOrig = new Date();
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");// converter formato de data atual
        String data = ""+formatador.format(datOrig);
        pedido = new Pedido(1, ""+data, cliente, funcionario, produto);
        CadastroPedidos cadPed = new CadastroPedidos();
        cadPed.adicionarPedido(pedido);
    }
    //função para receber dados
    public static String recebeData(){
        String data;
        Date dat = new Date(System.currentTimeMillis());
        data = ""+dat;
        return data;
    }

    /**
     * @param args the command line arguments
     */

    
}
