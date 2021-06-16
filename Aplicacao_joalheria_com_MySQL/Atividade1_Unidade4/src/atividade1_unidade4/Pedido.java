
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
public class Pedido {
    private Cliente cliente;
    private String data;
    private Funcionario funcionario;
    private Produto produto;
    private int idPed;
    Connection connection;

    public Pedido(int idPed, String data, Cliente cliente, Funcionario funcionario, Produto produto) {
        this.cliente = cliente;
        this.data = data;
        this.funcionario = funcionario;
        this.idPed = idPed;
        this.produto = produto;
    }
    public void conectar() throws ClassNotFoundException{
        String databaseName = "atv04";
        String url = "jdbc:mysql://localhost:3306/"+databaseName+"?useTimezone=true&serverTimezone=UTC";
    
        String username = ConexaoBD.username;
        String pasword = ConexaoBD.password;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,pasword);
            System.out.println("Conectado a base de dados com sucesso!");
            
        }
        catch(SQLException ex){
            System.out.println("Não foi possivelconectar com a base de dados de dados!");
            ex.printStackTrace();
            
        }
    }
    
     public ArrayList<Pedido> retornaDados() throws ClassNotFoundException{
        conectar();
        ArrayList<Pedido> ped = new ArrayList<>();
        int idCli;
        int idFunc;
        int idProd;

        try{
            Statement stmt = connection.createStatement();
            String sql = "select * from pedido";
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
        
            while(rs.next()==true){
               
                idPed = rs.getInt(1);
               
                setData(rs.getString(2));
                
                idCli = rs.getInt(3);
                idFunc = rs.getInt(4);
                idProd = rs.getInt(5);
              
                CadastroFuncionario cadFunc = new CadastroFuncionario();
                funcionario = cadFunc.buscarFuncionarioId(idFunc);
               
                CadastroClientes cadCli = new CadastroClientes();
                cliente = cadCli.buscarClienteId(idCli);
             
                CadastroProdutos cadProd = new CadastroProdutos();
                produto = cadProd.buscaProdutoP(idProd);
                
                Pedido pedi = new Pedido(idPed, data, cliente, funcionario, produto);
                
                ped.add(pedi);
            }
        ps.close();
        rs.close();
        return ped;
        }
        catch(Exception ex){
            System.out.println("Dados inexistentes na base!");
            ex.printStackTrace();
            return null;
        } 
    }
    
    
    public void icluirClienteNome(String nome){
        
    }
    public void icluirClienteCPF(int cpf){
        
    }
    public void icluirFuncionarioNome(String nome){
        
    }
    public void icluirFuncionarioCPF(int cpf){
        
    }
    public void incluirProduto(Produto produto){
        
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getIdPed() {
        return idPed;
    }

    public void setIdPed(int idPed) {
        this.idPed = idPed;
    }
}
