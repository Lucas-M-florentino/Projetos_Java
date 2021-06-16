/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Atividade4_Janelas;
import atividade1_unidade4.CadastroClientes;
import atividade1_unidade4.CadastroFuncionario;
import atividade1_unidade4.CadastroPedidos;
import atividade1_unidade4.CadastroProdutos;
import atividade1_unidade4.Cliente;
import atividade1_unidade4.Pedido;
import atividade1_unidade4.Produto;
import atividade1_unidade4.ConexaoBD;
import atividade1_unidade4.Funcionario;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lucas Matheus
 */
public class TelaInicio extends javax.swing.JFrame {
    Connection connection = null;
    ArrayList<Cliente> ListCliente;
    ArrayList<Funcionario> ListaFuncionario;
    ArrayList<Produto> ListProduto;
    ArrayList<Pedido> ListPedido;
    
    public void LoadTableCli(){
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"id","Nome","CPF","Telefone","Endereco"},0);
        for(int i=0;i<ListCliente.size();i++){
            Object linha [] = new Object[]{ListCliente.get(i).getIdCli(),ListCliente.get(i).getNome(),ListCliente.get(i).getCpf(),
                ListCliente.get(i).getTelefone(),ListCliente.get(i).getEndereco()};
            
            modelo.addRow(linha);
        }
        TB_ListCli.setModel(modelo);
    }
    public void LoatTableCPedido(){
        Cliente cliente = new Cliente(0, "", 0, "", "");
        try {
            ListCliente = cliente.retornaDados();
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(TelaInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        LoadTableCli();        
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"id","Nome"},0);
        for(int i=0;i<ListCliente.size();i++){
            Object linha [] = new Object[]{ListCliente.get(i).getIdCli(),ListCliente.get(i).getNome()};
            
            modelo.addRow(linha);
        }
        TB_CPedidos.setModel(modelo);
    }
     public void LoatTableFPedido(){
        Funcionario funcionario = new Funcionario(0, "", 0, "", "",0,0,true);
        try {
            ListaFuncionario = funcionario.retornaDados();
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(TelaInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        LoadTableFunc();        
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"id","Nome"},0);
        for(int i=0;i<ListaFuncionario.size();i++){
            Object linha [] = new Object[]{ListaFuncionario.get(i).getIdFunc(),ListaFuncionario.get(i).getNome()};
            
            modelo.addRow(linha);
        }
        TB_FPedidos.setModel(modelo);
    }
      public void LoatTableProPedido(){
        Produto produto = new Produto(0, "", "");
        ListProduto = produto.imprimirDados();
        
        LoadTableProd();        
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"id","Nome"},0);
        for(int i=0;i<ListProduto.size();i++){
            Object linha [] = new Object[]{ListProduto.get(i).getIdProd(),ListProduto.get(i).getNome()};
            
            modelo.addRow(linha);
        }
        TB_ProPedidos.setModel(modelo);
    }
      
    public void LoadTableFunc(){
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"id","Nome","CPF","Telefone","Endereco","Num. Carteira", "Num. Trabalhos", "Disponivel"},0);
        String Disponivel = "";
        for(int i=0;i<ListaFuncionario.size();i++){
            if(ListaFuncionario.get(i).isDisponivel() == true){
                Disponivel = "Sim";
            }
            else{
                Disponivel = "Não";
            }
            Object linha [] = new Object[]{ListaFuncionario.get(i).getIdFunc(),ListaFuncionario.get(i).getNome(),ListaFuncionario.get(i).getCpf(),
                ListaFuncionario.get(i).getTelefone(),ListaFuncionario.get(i).getEndereco(),ListaFuncionario.get(i).getNumCarteira(),
                ListaFuncionario.get(i).getNumTrabalhos(),Disponivel};
            
            modelo.addRow(linha);
        }
        TB_ListFunc.setModel(modelo);
    }
    public void LoadTableProd(){
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"id","Nome","Categoria"},0);
        for(int i=0;i<ListProduto.size();i++){
            Object linha [] = new Object[]{ListProduto.get(i).getIdProd(),ListProduto.get(i).getNome(),ListProduto.get(i).getCategoria()};
            
            modelo.addRow(linha);
        }
        TB_ListProdutos.setModel(modelo);
    }
    public void LoadTablePed(){
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"id","Data","idCliente","idFuncionario","idProduto"},0);
        for(int i=0;i<ListPedido.size();i++){
            Object linha [] = new Object[]{ListPedido.get(i).getIdPed(),ListPedido.get(i).getData(),ListPedido.get(i).getCliente().getIdCli(),
                                           ListPedido.get(i).getFuncionario().getIdFunc(),ListPedido.get(i).getProduto().getIdProd()};
            modelo.addRow(linha);
        }
        TB_ListPedidos.setModel(modelo);
    }
    
    
    /**
     * Creates new form TelaInicio
     */
    public TelaInicio() throws ClassNotFoundException, SQLException {
        initComponents();
        setLocationRelativeTo(null);
        connection = ConexaoBD.connection();
        ConexaoBD.addDadosBase();
        Confirm_CadCli.setText("");
        Btn_CadCli.setEnabled(false);
        Btn_CancelCadCli.setEnabled(false);
        Btn_CancelPedidos.setEnabled(false);
        Cad_Produtos.setEnabled(false);
        Box_CategoriaPedidos.setEnabled(false);
        Confirm_CadProd.setText("");
        Btn_CadProdutos.setEnabled(false);
        Btn_CancelCadProdutos.setEnabled(false);
        Cad_NomeCli.setEnabled(false);
        Cad_CPFCli.setEnabled(false);
        Cad_EnderecoCli.setEnabled(false);
        Cad_TelCli.setEnabled(false);
        Confirm_CadFunc.setText("");
        Cad_NomeFunc.setText("");
        Cad_CPFFunc.setText("");
        Cad_EndeFunc.setText("");
        Cad_TelFunc.setText("");
        Cad_NumCarFunc.setText("");
        Cad_NumTrabFunc.setText("");
        Cad_TelFunc.setEnabled(false);
        Cad_EndeFunc.setEnabled(false);
        Btn_CadFunc.setEnabled(false);
        Btn_CancelCadFunc.setEnabled(false);
        Cad_NomeFunc.setEnabled(false);
        Cad_CPFFunc.setEnabled(false);
        Cad_NumCarFunc.setEnabled(false);
        Cad_NumTrabFunc.setEnabled(false);
        Confirm_CadPed.setText("");
        Btn_CancelPedidos.setEnabled(false);
        Btn_RMPedidos.setEnabled(false);
        RM_idPedido.setEnabled(false);
        Btn_CadPedidos.setEnabled(false);
        Cad_idCliente.setEnabled(false);
        Cad_idFuncionario.setEnabled(false);
        Cad_idProd.setEnabled(false);
        
        
        if(connection != null){
            lblStatus.setText("Conectado\n");
            lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Atividade4_Janelas/iconBDOK.png")));
        }
        else if(connection == null){
            lblStatus.setText("Não Conectado\n");
            lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Atividade4_Janelas/iconBDERR.png")));
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable11 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        Btn_RMFunc1 = new javax.swing.JButton();
        jDialog1 = new javax.swing.JDialog();
        NomeLoja = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        Btn_ListPedidos = new javax.swing.JButton();
        jScrollPane16 = new javax.swing.JScrollPane();
        TB_ListPedidos = new javax.swing.JTable();
        RM_idPedido = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        Btn_RMPedidos = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Btn_ListCeFPedidos = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        TB_CPedidos = new javax.swing.JTable();
        jScrollPane12 = new javax.swing.JScrollPane();
        TB_FPedidos = new javax.swing.JTable();
        Btn_CancelPedidos = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        TB_ProPedidos = new javax.swing.JTable();
        Btn_CadPedidos = new javax.swing.JButton();
        Cad_idCliente = new javax.swing.JTextField();
        Cad_idFuncionario = new javax.swing.JTextField();
        Cad_idProd = new javax.swing.JTextField();
        Confirm_CadPed = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        Btn_ListProdutos = new javax.swing.JButton();
        Btn_CadProdutos = new javax.swing.JButton();
        Btn_RMProdutos = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        TB_ListProdutos = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Cad_Produtos = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        RM_Produtos = new javax.swing.JTextField();
        Box_CategoriaPedidos = new javax.swing.JComboBox<>();
        Btn_CancelCadProdutos = new javax.swing.JButton();
        Btn_NovoProdutos = new javax.swing.JButton();
        Confirm_CadProd = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        Btn_CancelCadCli = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Cad_CPFCli = new javax.swing.JTextField();
        Cad_TelCli = new javax.swing.JTextField();
        Cad_NomeCli = new javax.swing.JTextField();
        Cad_EnderecoCli = new javax.swing.JTextField();
        Btn_NovoCli = new javax.swing.JButton();
        Btn_CadCli = new javax.swing.JButton();
        Confirm_CadCli = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TB_ListCli = new javax.swing.JTable();
        Btn_ListCli = new javax.swing.JButton();
        Btn_RMCliCPF = new javax.swing.JButton();
        RM_CPFCli = new javax.swing.JTextField();
        RM_NomeCli = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Btn_RMCliNome = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jButton15 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        Cad_CPFFunc = new javax.swing.JTextField();
        Cad_TelFunc = new javax.swing.JTextField();
        Cad_NomeFunc = new javax.swing.JTextField();
        Cad_EndeFunc = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        Cad_NumCarFunc = new javax.swing.JTextField();
        Cad_NumTrabFunc = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        Btn_NovoFunc = new javax.swing.JButton();
        Btn_CadFunc = new javax.swing.JButton();
        Btn_CancelCadFunc = new javax.swing.JButton();
        Confirm_CadFunc = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TB_ListFunc = new javax.swing.JTable();
        Btn_ListFunc = new javax.swing.JButton();
        Btn_RMFuncNome = new javax.swing.JButton();
        RM_NumCartFunc = new javax.swing.JTextField();
        RM_NomeFunc = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        Btn_RMFuncNumCart = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        jMenuItem1.setText("jMenuItem1");

        jMenu5.setText("jMenu5");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        jTable11.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Nome"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane11.setViewportView(jTable11);

        jButton3.setText("Remover");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel17.setText("Telefone");

        jLabel18.setText("CPF");

        jLabel19.setText("Telefone");

        jTextField17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField17ActionPerformed(evt);
            }
        });

        jLabel20.setText("CPF");

        Btn_RMFunc1.setText("Remover");

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        NomeLoja.setText("Joalheria Bright Dream");

        lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Atividade4_Janelas/iconBDERR.png"))); // NOI18N

        Btn_ListPedidos.setText("Listar");
        Btn_ListPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ListPedidosActionPerformed(evt);
            }
        });

        TB_ListPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Data", "id Cliente", "Id Funcionario", "id Produto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane16.setViewportView(TB_ListPedidos);

        jLabel12.setText("Id pedido");

        Btn_RMPedidos.setText("Remover");
        Btn_RMPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_RMPedidosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(Btn_ListPedidos))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(RM_idPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Btn_RMPedidos))
                            .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Btn_ListPedidos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RM_idPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(Btn_RMPedidos))
                .addGap(54, 54, 54))
        );

        jTabbedPane4.addTab("Lista", jPanel10);

        jLabel1.setText("Cliente");

        Btn_ListCeFPedidos.setText("Novo");
        Btn_ListCeFPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ListCeFPedidosActionPerformed(evt);
            }
        });

        jLabel2.setText("Funcionario");

        TB_CPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nome"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TB_CPedidos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TB_CPedidosFocusGained(evt);
            }
        });
        TB_CPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TB_CPedidosMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(TB_CPedidos);
        if (TB_CPedidos.getColumnModel().getColumnCount() > 0) {
            TB_CPedidos.getColumnModel().getColumn(0).setPreferredWidth(1);
        }

        TB_FPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nome"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane12.setViewportView(TB_FPedidos);

        Btn_CancelPedidos.setText("Cancelar");
        Btn_CancelPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_CancelPedidosActionPerformed(evt);
            }
        });

        jLabel25.setText("Produto");

        TB_ProPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nome"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TB_ProPedidos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TB_ProPedidosFocusGained(evt);
            }
        });
        TB_ProPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TB_ProPedidosMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(TB_ProPedidos);
        if (TB_ProPedidos.getColumnModel().getColumnCount() > 0) {
            TB_ProPedidos.getColumnModel().getColumn(0).setPreferredWidth(1);
        }

        Btn_CadPedidos.setText("Cadastrar");
        Btn_CadPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_CadPedidosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(Btn_ListCeFPedidos)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Btn_CancelPedidos))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(Cad_idFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Btn_CadPedidos))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(Cad_idCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Cad_idProd, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Confirm_CadPed, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(134, 134, 134)))
                .addContainerGap())
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(jLabel1)
                .addGap(320, 320, 320)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 214, Short.MAX_VALUE)
                .addComponent(jLabel25)
                .addGap(112, 112, 112))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(Btn_ListCeFPedidos)
                .addGap(17, 17, 17)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel25))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Cad_idFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cad_idCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Cad_idProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Btn_CadPedidos)))
                .addGap(18, 18, 18)
                .addComponent(Btn_CancelPedidos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Confirm_CadPed, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Cadastro", jPanel9);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane4)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jTabbedPane4)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Pedidos", jPanel2);

        Btn_ListProdutos.setText("Listar");
        Btn_ListProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ListProdutosActionPerformed(evt);
            }
        });

        Btn_CadProdutos.setText("Cadastrar");
        Btn_CadProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_CadProdutosActionPerformed(evt);
            }
        });

        Btn_RMProdutos.setText("Remover");
        Btn_RMProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_RMProdutosActionPerformed(evt);
            }
        });

        TB_ListProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nome", "Categoria"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(TB_ListProdutos);

        jLabel3.setText("Nome");

        jLabel4.setText("Categoria");

        Cad_Produtos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cad_ProdutosActionPerformed(evt);
            }
        });

        jLabel5.setText("Nome");

        Box_CategoriaPedidos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ouro", "Prata", "Semi Joia", "Acessório" }));
        Box_CategoriaPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Box_CategoriaPedidosActionPerformed(evt);
            }
        });

        Btn_CancelCadProdutos.setText("Cancelar");
        Btn_CancelCadProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_CancelCadProdutosActionPerformed(evt);
            }
        });

        Btn_NovoProdutos.setText("Novo");
        Btn_NovoProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_NovoProdutosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Btn_ListProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Cad_Produtos, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addGap(60, 60, 60)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(226, 226, 226))
                                .addComponent(RM_Produtos, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Btn_RMProdutos)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(Box_CategoriaPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Confirm_CadProd, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(Btn_CadProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(61, 61, 61)
                                        .addComponent(Btn_CancelCadProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(208, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 908, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(201, 201, 201)
                    .addComponent(Btn_NovoProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(641, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Btn_ListProdutos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 207, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cad_Produtos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_CadProdutos)
                    .addComponent(Box_CategoriaPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_CancelCadProdutos))
                .addGap(18, 18, 18)
                .addComponent(Confirm_CadProd, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RM_Produtos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_RMProdutos))
                .addGap(50, 50, 50))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(36, 36, 36)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(265, Short.MAX_VALUE)))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap(217, Short.MAX_VALUE)
                    .addComponent(Btn_NovoProdutos)
                    .addGap(198, 198, 198)))
        );

        jTabbedPane1.addTab("Produtos", jPanel3);

        Btn_CancelCadCli.setText("Cancelar");
        Btn_CancelCadCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_CancelCadCliActionPerformed(evt);
            }
        });

        jLabel6.setText("*CPF");

        jLabel7.setText("*Nome");

        jLabel8.setText("*Endereco");

        jLabel9.setText("*Telefone");

        Cad_TelCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cad_TelCliActionPerformed(evt);
            }
        });

        Cad_EnderecoCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cad_EnderecoCliActionPerformed(evt);
            }
        });

        Btn_NovoCli.setText("Novo");
        Btn_NovoCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_NovoCliActionPerformed(evt);
            }
        });

        Btn_CadCli.setText("Cadastrar");
        Btn_CadCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_CadCliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Btn_CadCli)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Confirm_CadCli, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(202, 202, 202)
                .addComponent(Btn_CancelCadCli)
                .addGap(110, 110, 110))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Cad_NomeCli, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Cad_EnderecoCli, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Cad_CPFCli, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(283, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Cad_TelCli, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addComponent(Btn_NovoCli)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(Btn_NovoCli)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(Cad_NomeCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Cad_CPFCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(Cad_TelCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(Cad_EnderecoCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(96, 96, 96)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Btn_CancelCadCli)
                        .addComponent(Btn_CadCli))
                    .addComponent(Confirm_CadCli, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane2.addTab("Cadastro", jPanel1);

        TB_ListCli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nome", "CPF", "Telefone", "Endereco"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(TB_ListCli);

        Btn_ListCli.setText("Listar");
        Btn_ListCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ListCliActionPerformed(evt);
            }
        });

        Btn_RMCliCPF.setText("Remover");
        Btn_RMCliCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_RMCliCPFActionPerformed(evt);
            }
        });

        RM_CPFCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RM_CPFCliActionPerformed(evt);
            }
        });

        RM_NomeCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RM_NomeCliActionPerformed(evt);
            }
        });

        jLabel10.setText("Nome");

        jLabel11.setText("CPF");

        Btn_RMCliNome.setText("Remover");
        Btn_RMCliNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_RMCliNomeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(366, 366, 366)
                        .addComponent(Btn_ListCli)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(Btn_RMCliNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Btn_RMCliCPF))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RM_NomeCli, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RM_CPFCli, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(44, 44, 44))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btn_ListCli)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RM_CPFCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RM_NomeCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_RMCliCPF)
                    .addComponent(Btn_RMCliNome))
                .addGap(16, 16, 16))
        );

        jTabbedPane2.addTab("Lista", jPanel6);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Clientes", jPanel4);

        jButton15.setText("Cadastrar");

        jLabel13.setText("*CPF");

        jLabel14.setText("*Nome");

        jLabel15.setText("*Endereco");

        jLabel16.setText("*Telefone");

        Cad_TelFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cad_TelFuncActionPerformed(evt);
            }
        });

        Cad_EndeFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cad_EndeFuncActionPerformed(evt);
            }
        });

        jLabel21.setText("*Num. Trabalhos");

        Cad_NumTrabFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cad_NumTrabFuncActionPerformed(evt);
            }
        });

        jLabel22.setText("*Num. Carteira");

        Btn_NovoFunc.setText("Novo");
        Btn_NovoFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_NovoFuncActionPerformed(evt);
            }
        });

        Btn_CadFunc.setText("Cadastrar");
        Btn_CadFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_CadFuncActionPerformed(evt);
            }
        });

        Btn_CancelCadFunc.setText("Cancelar");
        Btn_CancelCadFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_CancelCadFuncActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Cad_NumCarFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(719, 719, 719))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Cad_NumTrabFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(Btn_CadFunc)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Confirm_CadFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(209, 209, 209)
                                .addComponent(Btn_CancelCadFunc)
                                .addGap(137, 137, 137)))
                        .addComponent(jButton15)
                        .addGap(110, 110, 110))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Cad_CPFFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Cad_EndeFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Cad_NomeFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Cad_TelFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(Btn_NovoFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(Btn_NovoFunc)
                .addGap(30, 30, 30)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(Cad_NomeFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(Cad_CPFFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Cad_TelFunc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(Cad_EndeFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(Cad_NumCarFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Cad_NumTrabFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Confirm_CadFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton15)
                                .addComponent(Btn_CancelCadFunc)
                                .addComponent(Btn_CadFunc)))))
                .addContainerGap())
        );

        jTabbedPane3.addTab("Cadastro", jPanel7);

        TB_ListFunc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nome", "CPF", "Telefone", "Tipo", "Endereço", "Num. Carteira", "Num. Trabalhos", "Disponivel"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(TB_ListFunc);

        Btn_ListFunc.setText("Listar");
        Btn_ListFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ListFuncActionPerformed(evt);
            }
        });

        Btn_RMFuncNome.setText("Remover");
        Btn_RMFuncNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_RMFuncNomeActionPerformed(evt);
            }
        });

        RM_NumCartFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RM_NumCartFuncActionPerformed(evt);
            }
        });

        RM_NomeFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RM_NomeFuncActionPerformed(evt);
            }
        });

        jLabel23.setText("Nome");

        jLabel24.setText("Num. Carteira");

        Btn_RMFuncNumCart.setText("Remover");
        Btn_RMFuncNumCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_RMFuncNumCartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel24)
                                .addGap(310, 310, 310))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(Btn_RMFuncNome)
                                        .addGap(760, 760, 760)
                                        .addComponent(Btn_RMFuncNumCart))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(RM_NomeFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(78, 78, 78)
                                        .addComponent(RM_NumCartFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 14, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(406, 406, 406)
                        .addComponent(Btn_ListFunc)
                        .addGap(446, 446, 446))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btn_ListFunc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RM_NumCartFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RM_NomeFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_RMFuncNome)
                    .addComponent(Btn_RMFuncNumCart))
                .addGap(23, 23, 23))
        );

        jTabbedPane3.addTab("Lista", jPanel8);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 908, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Funcionarios", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(350, 350, 350)
                .addComponent(NomeLoja)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblStatus)
                .addGap(22, 22, 22))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(NomeLoja)
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblStatus)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_ListProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ListProdutosActionPerformed
        
        Produto produto = new Produto(0, "", "");
        ListProduto = produto.imprimirDados();
        
        LoadTableProd();
    }//GEN-LAST:event_Btn_ListProdutosActionPerformed

    private void RM_NomeCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RM_NomeCliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RM_NomeCliActionPerformed

    private void Cad_TelCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cad_TelCliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Cad_TelCliActionPerformed

    private void Cad_EnderecoCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cad_EnderecoCliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Cad_EnderecoCliActionPerformed

    private void Cad_ProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cad_ProdutosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Cad_ProdutosActionPerformed

    private void Cad_TelFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cad_TelFuncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Cad_TelFuncActionPerformed

    private void Cad_EndeFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cad_EndeFuncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Cad_EndeFuncActionPerformed

    private void jTextField17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField17ActionPerformed

    private void Cad_NumTrabFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cad_NumTrabFuncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Cad_NumTrabFuncActionPerformed

    private void RM_NomeFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RM_NomeFuncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RM_NomeFuncActionPerformed

    private void RM_NumCartFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RM_NumCartFuncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RM_NumCartFuncActionPerformed

    private void Btn_ListPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ListPedidosActionPerformed
       Cliente cliente = new Cliente(0, "", 0, "", "");
       Funcionario funcionario = new Funcionario(0, "", 0, "", "",0,0,true);
       Produto produto = new Produto(0, "", "");
       Pedido pedido = new Pedido(0, "", cliente, funcionario, produto);
       
        try {
            ListPedido = pedido.retornaDados();
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(TelaInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Btn_RMPedidos.setEnabled(true);
        RM_idPedido.setEnabled(true);
        LoadTablePed();
    }//GEN-LAST:event_Btn_ListPedidosActionPerformed
    
    
    private void Btn_NovoCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_NovoCliActionPerformed
        Cad_NomeCli.setText("");
        Cad_CPFCli.setText("");
        Cad_EnderecoCli.setText("");
        Cad_TelCli.setText("");
        Confirm_CadCli.setText("");
        Btn_CadCli.setEnabled(true);
        Btn_CancelCadCli.setEnabled(true);
        Cad_NomeCli.setEnabled(true);
        Cad_CPFCli.setEnabled(true);
        Cad_EnderecoCli.setEnabled(true);
        Cad_TelCli.setEnabled(true);
    }//GEN-LAST:event_Btn_NovoCliActionPerformed

    private void Btn_ListCeFPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ListCeFPedidosActionPerformed
        LoatTableCPedido();
        LoatTableFPedido();
        LoatTableProPedido();
        Btn_CancelPedidos.setEnabled(true);
        Btn_CadPedidos.setEnabled(true);
        Cad_idCliente.setEnabled(true);
        Cad_idFuncionario.setEnabled(true);
        Cad_idProd.setEnabled(true);
        Confirm_CadPed.setText("");
        
    }//GEN-LAST:event_Btn_ListCeFPedidosActionPerformed

    private void Btn_CancelPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_CancelPedidosActionPerformed
        Btn_CancelPedidos.setEnabled(true);
        Cad_idCliente.setText("");
        Cad_idFuncionario.setText("");
        Cad_idProd.setText("");
    }//GEN-LAST:event_Btn_CancelPedidosActionPerformed

    private void Btn_CadProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_CadProdutosActionPerformed
        String categoria = "";
        
        if(Box_CategoriaPedidos.getVerifyInputWhenFocusTarget()==true){
            switch (Box_CategoriaPedidos.getSelectedIndex()){
                case 0 -> categoria = "Ouro";
                case 1 -> categoria = "Prata";
                case 2 -> categoria = "Semi Joia";
                case 3 -> categoria = "Acessorio";
            }
            
        }
        
        Produto prod = new Produto(0,Cad_Produtos.getText(),categoria);
        CadastroProdutos cadProd = new CadastroProdutos();
        cadProd.adicionarProduto(prod);
        Produto produto = new Produto(0, "", "");
        ListProduto = produto.imprimirDados();
        
        Cad_Produtos.setText("");
        Confirm_CadProd.setText("Cadastrado");
        LoadTableProd();
    }//GEN-LAST:event_Btn_CadProdutosActionPerformed

    private void Btn_CancelCadProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_CancelCadProdutosActionPerformed
        
        Cad_Produtos.setEnabled(false);
        Box_CategoriaPedidos.setEnabled(false);
        Btn_CancelCadProdutos.setEnabled(false);
    }//GEN-LAST:event_Btn_CancelCadProdutosActionPerformed

    private void Btn_NovoProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_NovoProdutosActionPerformed
        Cad_Produtos.setEnabled(true);
        Box_CategoriaPedidos.setEnabled(true);
        Btn_CadProdutos.setEnabled(true);
        Btn_CancelCadProdutos.setEnabled(true);
        Confirm_CadProd.setText("");
    }//GEN-LAST:event_Btn_NovoProdutosActionPerformed

    private void Box_CategoriaPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Box_CategoriaPedidosActionPerformed
        
    }//GEN-LAST:event_Box_CategoriaPedidosActionPerformed

    private void Btn_RMProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_RMProdutosActionPerformed
        CadastroProdutos cadProd = new CadastroProdutos();
        cadProd.removerProdutoNome(RM_Produtos.getText());
        Produto produto = new Produto(0, "", "");
        ListProduto = produto.imprimirDados();
        LoadTableProd();
        
    }//GEN-LAST:event_Btn_RMProdutosActionPerformed

    private void Btn_CancelCadCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_CancelCadCliActionPerformed
        Cad_NomeCli.setText("");
        Cad_CPFCli.setText("");
        Cad_EnderecoCli.setText("");
        Cad_TelCli.setText("");
        RM_NomeCli.setText("");
        RM_CPFCli.setText("");
        Btn_CadCli.setEnabled(false);
        Btn_CancelCadCli.setEnabled(false);
        Cad_NomeCli.setEnabled(false);
        Cad_CPFCli.setEnabled(false);
        Cad_EnderecoCli.setEnabled(false);
        Cad_TelCli.setEnabled(false);
    }//GEN-LAST:event_Btn_CancelCadCliActionPerformed

    private void Btn_CadCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_CadCliActionPerformed
        if(Cad_NomeCli.getText() != ""){
            int cpf = Integer.parseInt(Cad_CPFCli.getText());
            
            Cliente cli = new Cliente(0,Cad_NomeCli.getText(),cpf,Cad_TelCli.getText(),Cad_EnderecoCli.getText());
            CadastroClientes  cadCli = new CadastroClientes();
            try {
                cadCli.adicionarCliente(cli);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TelaInicio.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                ListCliente = cli.retornaDados();
            } catch (ClassNotFoundException ex) {
                //Logger.getLogger(TelaInicio.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Cad_NomeCli.setText("");
            Cad_CPFCli.setText("");
            Cad_EnderecoCli.setText("");
            Cad_TelCli.setText("");
            Confirm_CadCli.setText("Cadastrado");
            LoadTableCli();
        }
        
    }//GEN-LAST:event_Btn_CadCliActionPerformed

    private void Btn_ListCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ListCliActionPerformed
        Cliente cliente = new Cliente(0, "", 0, "", "");
        try {
            ListCliente = cliente.retornaDados();
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(TelaInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        LoadTableCli();
    }//GEN-LAST:event_Btn_ListCliActionPerformed

    private void Btn_RMCliCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_RMCliCPFActionPerformed
        CadastroClientes cadCli = new CadastroClientes();
        if(RM_CPFCli.getText()!=""){
            
            int cpf = Integer.parseInt(RM_CPFCli.getText());
            try {
                cadCli.removerClienteCPF(cpf);
            } catch (ClassNotFoundException ex) {
                //Logger.getLogger(TelaInicio.class.getName()).log(Level.SEVERE, null, ex);
            }
            Cliente cliente = new Cliente(0, "", 0,"","");
            try {
                ListCliente = cliente.retornaDados();
            } catch (ClassNotFoundException ex) {
                //Logger.getLogger(TelaInicio.class.getName()).log(Level.SEVERE, null, ex);
            }
            LoadTableCli();
        }
        
        RM_NomeCli.setText("");
        
    }//GEN-LAST:event_Btn_RMCliCPFActionPerformed

    private void RM_CPFCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RM_CPFCliActionPerformed

    }//GEN-LAST:event_RM_CPFCliActionPerformed

    private void Btn_RMCliNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_RMCliNomeActionPerformed
        CadastroClientes cadCli = new CadastroClientes();
        if(RM_NomeCli.getText()!=""){
            
            try {
                cadCli.removerClienteNom(RM_NomeCli.getText());
            } catch (ClassNotFoundException ex) {
                //Logger.getLogger(TelaInicio.class.getName()).log(Level.SEVERE, null, ex);
            }
            Cliente cliente = new Cliente(0, "", 0,"","");
            try {
                ListCliente = cliente.retornaDados();
            } catch (ClassNotFoundException ex) {
                //Logger.getLogger(TelaInicio.class.getName()).log(Level.SEVERE, null, ex);
            }
            LoadTableCli();
        }
        
        RM_NomeCli.setText("");
    }//GEN-LAST:event_Btn_RMCliNomeActionPerformed

    private void Btn_ListFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ListFuncActionPerformed
        Funcionario funcionario = new Funcionario(0, "", 0, "", "",0,0,true);
        try {
            ListaFuncionario = funcionario.retornaDados();
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(TelaInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        LoadTableFunc();
    }//GEN-LAST:event_Btn_ListFuncActionPerformed

    private void Btn_RMFuncNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_RMFuncNomeActionPerformed
        CadastroFuncionario cadFunc = new CadastroFuncionario();
        if(RM_NomeFunc.getText()!=""){
            
            try {
                cadFunc.removerFuncionarioNom(RM_NomeFunc.getText());
            } catch (ClassNotFoundException ex) {
                //Logger.getLogger(TelaInicio.class.getName()).log(Level.SEVERE, null, ex);
            }
            Funcionario funcionario = new Funcionario(0, "", 0,"","",0,0,true);
            try {
                ListaFuncionario = funcionario.retornaDados();
            } catch (ClassNotFoundException ex) {
                //Logger.getLogger(TelaInicio.class.getName()).log(Level.SEVERE, null, ex);
            }
            LoadTableFunc();
        }
        
        RM_NomeFunc.setText("");
    }//GEN-LAST:event_Btn_RMFuncNomeActionPerformed

    private void Btn_RMFuncNumCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_RMFuncNumCartActionPerformed
        CadastroFuncionario cadFunc = new CadastroFuncionario();
        if(RM_NumCartFunc.getText()!=""){
            int NumCart = Integer.parseInt(RM_NumCartFunc.getText());
            cadFunc.removerFuncNumCart(NumCart);
            Funcionario funcionario = new Funcionario(0, "", 0,"","",0,0,true);
            try {
                ListaFuncionario = funcionario.retornaDados();
            } catch (ClassNotFoundException ex) {
                //Logger.getLogger(TelaInicio.class.getName()).log(Level.SEVERE, null, ex);
            }
            LoadTableFunc();
        }
        
        RM_NumCartFunc.setText("");
    }//GEN-LAST:event_Btn_RMFuncNumCartActionPerformed

    private void Btn_NovoFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_NovoFuncActionPerformed
        Cad_NomeFunc.setText("");
        Cad_CPFFunc.setText("");
        Cad_EndeFunc.setText("");
        Cad_TelFunc.setText("");
        Cad_NumCarFunc.setText("");
        Cad_NumTrabFunc.setText("");
        Confirm_CadFunc.setText("");
        Btn_CadFunc.setEnabled(true);
        Btn_CancelCadFunc.setEnabled(true);
        Cad_NomeFunc.setEnabled(true);
        Cad_CPFFunc.setEnabled(true);
        Cad_NumCarFunc.setEnabled(true);
        Cad_NumTrabFunc.setEnabled(true);
        Cad_TelFunc.setEnabled(true);
        Cad_EndeFunc.setEnabled(true);
    }//GEN-LAST:event_Btn_NovoFuncActionPerformed

    private void Btn_CancelCadFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_CancelCadFuncActionPerformed
        
        Cad_NomeFunc.setText("");
        Cad_CPFFunc.setText("");
        Cad_EndeFunc.setText("");
        Cad_TelFunc.setText("");
        Cad_NumCarFunc.setText("");
        Cad_NumTrabFunc.setText("");
        Cad_TelFunc.setEnabled(false);
        Cad_EndeFunc.setEnabled(false);
        Btn_CadFunc.setEnabled(false);
        Btn_CancelCadFunc.setEnabled(false);
        Cad_NomeFunc.setEnabled(false);
        Cad_CPFFunc.setEnabled(false);
        Cad_NumCarFunc.setEnabled(false);
        Cad_NumTrabFunc.setEnabled(false);
    }//GEN-LAST:event_Btn_CancelCadFuncActionPerformed

    private void Btn_CadFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_CadFuncActionPerformed
        if(Cad_NomeFunc.getText()!=""){
            int cpf = Integer.parseInt(Cad_CPFFunc.getText());
            int NumCarteira = Integer.parseInt(Cad_NumCarFunc.getText());
            int NumTrabalhos = Integer.parseInt(Cad_NumTrabFunc.getText());
            
            Funcionario func = new Funcionario(0,Cad_NomeFunc.getText(),cpf,Cad_TelFunc.getText(),Cad_EndeFunc.getText(),NumCarteira,NumTrabalhos,true);
            CadastroFuncionario  cadFunc = new CadastroFuncionario();
            cadFunc.adicionarFuncionario(func);
            try {
                ListaFuncionario = func.retornaDados();
            } catch (ClassNotFoundException ex) {
                //Logger.getLogger(TelaInicio.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Cad_NomeFunc.setText("");
            Cad_CPFFunc.setText("");
            Cad_EndeFunc.setText("");
            Cad_TelFunc.setText("");
            Cad_NumCarFunc.setText("");
            Cad_NumTrabFunc.setText("");
            Confirm_CadFunc.setText("Cadastrado");
            LoadTableFunc();
            
        }
        
    }//GEN-LAST:event_Btn_CadFuncActionPerformed

    private void Btn_RMPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_RMPedidosActionPerformed
        
        CadastroPedidos cadPed = new CadastroPedidos();
        if(RM_idPedido.getText()!=""){
            int idPed = Integer.parseInt(RM_idPedido.getText());
            try {
                cadPed.removerPedido(idPed);
            } catch (ClassNotFoundException ex) {
                //Logger.getLogger(TelaInicio.class.getName()).log(Level.SEVERE, null, ex);
            }
            Cliente cliente = new Cliente(0, "", 0, "", "");
            Funcionario funcionario = new Funcionario(0, "", 0, "", "",0,0,true);
            Produto produto = new Produto(0, "", "");
            Pedido pedido = new Pedido(0, "", cliente, funcionario, produto);            
            try {
                ListPedido = pedido.retornaDados();
            } catch (ClassNotFoundException ex) {
                //Logger.getLogger(TelaInicio.class.getName()).log(Level.SEVERE, null, ex);
            }
            RM_idPedido.setText("");
            LoadTablePed();
        }     
    }//GEN-LAST:event_Btn_RMPedidosActionPerformed

    private void TB_CPedidosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TB_CPedidosFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_TB_CPedidosFocusGained

    private void TB_CPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TB_CPedidosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TB_CPedidosMouseClicked

    private void TB_ProPedidosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TB_ProPedidosFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_TB_ProPedidosFocusGained

    private void TB_ProPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TB_ProPedidosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TB_ProPedidosMouseClicked

    private void Btn_CadPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_CadPedidosActionPerformed
        ArrayList<Pedido> Lped = new ArrayList<>();
        Date datOrig = new Date();
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        String data = ""+formatador.format(datOrig);
        CadastroClientes cadCli = new CadastroClientes();
        CadastroFuncionario cadFun = new CadastroFuncionario();
        CadastroProdutos cadProd = new CadastroProdutos();
        
        CadastroPedidos cadPed = new CadastroPedidos();
        
        try {
            Pedido pedido = new Pedido(0, data, cadCli.buscarClienteId(Integer.parseInt(Cad_idCliente.getText())),
            cadFun.buscarFuncionarioId(Integer.parseInt(Cad_idFuncionario.getText())),
            cadProd.buscaProdutoP(Integer.parseInt(Cad_idProd.getText())));
            cadPed.adicionarPedido(pedido);
            Lped.add(pedido);
        
            ListPedido = Lped;
            Cad_idCliente.setText("");
            Cad_idFuncionario.setText("");
            Cad_idProd.setText("");
            Confirm_CadPed.setText("Cadastrado");
            LoadTablePed();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }//GEN-LAST:event_Btn_CadPedidosActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
        * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
        */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                try {
                    new TelaInicio().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    //Logger.getLogger(TelaInicio.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    //Logger.getLogger(TelaInicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Box_CategoriaPedidos;
    private javax.swing.JButton Btn_CadCli;
    private javax.swing.JButton Btn_CadFunc;
    private javax.swing.JButton Btn_CadPedidos;
    private javax.swing.JButton Btn_CadProdutos;
    private javax.swing.JButton Btn_CancelCadCli;
    private javax.swing.JButton Btn_CancelCadFunc;
    private javax.swing.JButton Btn_CancelCadProdutos;
    private javax.swing.JButton Btn_CancelPedidos;
    private javax.swing.JButton Btn_ListCeFPedidos;
    private javax.swing.JButton Btn_ListCli;
    private javax.swing.JButton Btn_ListFunc;
    private javax.swing.JButton Btn_ListPedidos;
    private javax.swing.JButton Btn_ListProdutos;
    private javax.swing.JButton Btn_NovoCli;
    private javax.swing.JButton Btn_NovoFunc;
    private javax.swing.JButton Btn_NovoProdutos;
    private javax.swing.JButton Btn_RMCliCPF;
    private javax.swing.JButton Btn_RMCliNome;
    private javax.swing.JButton Btn_RMFunc1;
    private javax.swing.JButton Btn_RMFuncNome;
    private javax.swing.JButton Btn_RMFuncNumCart;
    private javax.swing.JButton Btn_RMPedidos;
    private javax.swing.JButton Btn_RMProdutos;
    private javax.swing.JTextField Cad_CPFCli;
    private javax.swing.JTextField Cad_CPFFunc;
    private javax.swing.JTextField Cad_EndeFunc;
    private javax.swing.JTextField Cad_EnderecoCli;
    private javax.swing.JTextField Cad_NomeCli;
    private javax.swing.JTextField Cad_NomeFunc;
    private javax.swing.JTextField Cad_NumCarFunc;
    private javax.swing.JTextField Cad_NumTrabFunc;
    private javax.swing.JTextField Cad_Produtos;
    private javax.swing.JTextField Cad_TelCli;
    private javax.swing.JTextField Cad_TelFunc;
    private javax.swing.JTextField Cad_idCliente;
    private javax.swing.JTextField Cad_idFuncionario;
    private javax.swing.JTextField Cad_idProd;
    private javax.swing.JLabel Confirm_CadCli;
    private javax.swing.JLabel Confirm_CadFunc;
    private javax.swing.JLabel Confirm_CadPed;
    private javax.swing.JLabel Confirm_CadProd;
    private javax.swing.JLabel NomeLoja;
    private javax.swing.JTextField RM_CPFCli;
    private javax.swing.JTextField RM_NomeCli;
    private javax.swing.JTextField RM_NomeFunc;
    private javax.swing.JTextField RM_NumCartFunc;
    private javax.swing.JTextField RM_Produtos;
    private javax.swing.JTextField RM_idPedido;
    private javax.swing.JTable TB_CPedidos;
    private javax.swing.JTable TB_FPedidos;
    private javax.swing.JTable TB_ListCli;
    private javax.swing.JTable TB_ListFunc;
    private javax.swing.JTable TB_ListPedidos;
    private javax.swing.JTable TB_ListProdutos;
    private javax.swing.JTable TB_ProPedidos;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTable jTable11;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JLabel lblStatus;
    // End of variables declaration//GEN-END:variables
}
