/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agencia_banco;

import java.io.Serializable;

/**
 *
 * @author Lukas
 */
public class Banco implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    private int codigo;
    private String nome;

    public Banco(int codigo, String nome) {
        this.codigo = String.valueOf(codigo).length() > 3
                ? Integer.parseInt(String.valueOf(codigo).substring(0, 3)) : codigo;
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    Banco getClone() {
        try {
            // call clone in Object.
            return (Banco) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(" Não é permitido clonar o objeto! ");
            return this;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.codigo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Banco other = (Banco) obj;
        return this.codigo == other.codigo;
    }

}
