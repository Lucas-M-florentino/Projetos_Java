/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade1_unidade4;

import java.util.ArrayList;

/**
 *
 * @author Lucas Matheus
 */
public abstract class Pessoa {
    private String Nome;
    private int cpf;
    private String Telefone;

    public Pessoa(String Nome, int cpf, String Telefone) {
        this.Nome = Nome;
        this.cpf = cpf;
        this.Telefone = Telefone;
    }
    
    
    public abstract ArrayList<Pessoa> imprimirDados();

    
    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }
    
}
