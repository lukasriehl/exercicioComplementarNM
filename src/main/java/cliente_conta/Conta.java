/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente_conta;

import agencia_banco.Agencia;
import enumerator.Status;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Lukas
 */
public abstract class Conta implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

//    private int numero;
//    private String digito;
//    protected BigDecimal saldoChequeEspecial;
//    private Agencia agencia;
//    private Status status;
    protected Conta() {
    }

//    protected Conta(int numero, String digito, Agencia agencia, Status status) {
//        this.numero = String.valueOf(numero).length() > 7
//                ? Integer.parseInt(String.valueOf(numero).substring(0, 7)) : numero;
//        this.digito = digito.length() > 2
//                ? digito.substring(0, 2) : digito;
//        this.agencia = agencia;
//        this.status = status;
//    }
    protected abstract BigDecimal realizaDeposito(BigDecimal valorDeposito);

    protected abstract BigDecimal realizaSaque(BigDecimal valorSaque);

//    protected BigDecimal retornaSaldoTotal() {
//        return this.saldo.add(this.saldoChequeEspecial);
//    }
    public abstract int getNumero();

    public abstract String getDigito();

    public abstract Agencia getAgencia();

    public abstract Status getStatus();

    public abstract BigDecimal getSaldo();

    @Override
    public Conta clone() {
        try {
            // call clone in Object.
            return (Conta) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(" Não é permitido clonar o objeto! ");
            return this;
        }
    }

//    @Override
//    public int hashCode() {
//        int hash = 3;
//        hash = 67 * hash + this.numero;
//        hash = 67 * hash + Objects.hashCode(this.digito);
//        hash = 67 * hash + Objects.hashCode(this.agencia);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final Conta other = (Conta) obj;
//        if (this.numero != other.numero) {
//            return false;
//        }
//        if (!Objects.equals(this.digito, other.digito)) {
//            return false;
//        }
//        return Objects.equals(this.agencia, other.agencia);
//    }
}
