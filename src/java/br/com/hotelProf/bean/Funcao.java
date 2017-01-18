/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hotelProf.bean;

import java.io.Serializable;

/**
 *
 * @author Junior
 */
public class Funcao implements Serializable{
    private Integer id;
    private String tipo;
    private String descricao;

    public Funcao() {
    }

    public Funcao(Integer id, String tipo, String descricao) {
        this.id = id;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String nome) {
        this.tipo = nome;
    }
    
}
