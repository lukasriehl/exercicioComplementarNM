/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agencia_banco;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Lukas
 */
public class Agencia implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    private int numero;
    private String digito;
    private String nome;
    private Banco banco;

    public Agencia(int numero, String digito, String nome, Banco banco) {
        this.numero = String.valueOf(numero).length() > 4
                ? Integer.parseInt(String.valueOf(numero).substring(0, 4)) : numero;
        this.digito = digito.length() > 2 ? digito.substring(0, 2) : digito;
        this.nome = nome;
        this.banco = banco;
    }

    public int getNumero() {
        return numero;
    }

    public String getDigito() {
        return digito;
    }

    public String getNome() {
        return nome;
    }

    public Banco getBanco() {
        return banco;
    }

    @Override
    public Agencia clone() {
        try {
            // call clone in Object.
            Agencia a = (Agencia) super.clone();
            a.banco = a.banco.clone();
            return a;
        } catch (CloneNotSupportedException e) {
            System.out.println(" Não é permitido clonar o objeto! ");
            return this;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.numero;
        hash = 47 * hash + Objects.hashCode(this.digito);
        hash = 47 * hash + Objects.hashCode(this.banco);
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
        final Agencia other = (Agencia) obj;
        if (this.numero != other.numero) {
            return false;
        }
        if (!Objects.equals(this.digito, other.digito)) {
            return false;
        }
        return Objects.equals(this.banco, other.banco);
    }

}
