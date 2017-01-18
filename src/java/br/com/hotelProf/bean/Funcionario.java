/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hotelProf.bean;

import java.util.Date;


/**
 *
 * @author Junior
 */
public class Funcionario extends Pessoa{
    private String cracha;
    private Double salario;
    private Funcao funcao;
    private Turno turno;

    public Funcionario() {
    }

    public Funcionario(Integer id, String cracha, Double salario,  String nome, String email,
            String telefone, String cpf, String rg, String celular, Date nascimento, String sexo) {
        super(id, nome, email, telefone, cpf, rg, celular, nascimento, sexo);
        this.cracha = cracha;
        this.salario = salario;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public String getCracha() {
        
        return cracha;
    }

    public void setCracha(String cracha) {
        this.cracha = cracha;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

}
