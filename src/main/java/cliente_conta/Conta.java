/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente_conta;

import agencia_banco.Agencia;
import enumerator.Status;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;

/**
 *
 * @author Lukas
 */
public abstract class Conta implements Cloneable {

    private int numero;
    private String digito;
    private BigDecimal saldo;    
    private Agencia agencia;
    private Status status;
    private HashMap<String, BigDecimal> agendamento = new HashMap<>();

    protected Conta() {

    }

    protected Conta(int numero, String digito, BigDecimal saldo, Agencia agencia, Status status) {
        this.numero = String.valueOf(numero).length() > 7
                ? Integer.parseInt(String.valueOf(numero).substring(0, 7)) : numero;
        this.digito = digito.length() > 2
                ? digito.substring(0, 2) : digito;
        this.saldo = saldo;
        //this.chequeEspecial = chequeEspecial;
        this.agencia = agencia;
        this.status = status;
    }

    protected abstract BigDecimal realizaDeposito(BigDecimal valorDeposito);

    protected abstract BigDecimal realizaSaque(BigDecimal valorSaque);

//    BigDecimal realizaDeposito(Cliente cliente, BigDecimal valor) {
//
//        if (cliente != null) {
//            Calendar diaAtual = Calendar.getInstance();
//
//            //Efetua o agendamento para o próximo dia útil caso o dia atual seja Sábado ou Domingo
//            if ((diaAtual.get(Calendar.DAY_OF_WEEK) == 1) || (diaAtual.get(Calendar.DAY_OF_WEEK) == 7)) {
//                String novaDataAgendamento;
//                int qtdeDias = diaAtual.get(Calendar.DAY_OF_WEEK) == 1 ? 1 : 2;
//
//                diaAtual.add(Calendar.DAY_OF_YEAR, qtdeDias);
//
//                novaDataAgendamento = String.valueOf(diaAtual.get(Calendar.DAY_OF_MONTH)).length() < 2
//                        ? "0".concat(String.valueOf(diaAtual.get(Calendar.DAY_OF_MONTH)))
//                        : String.valueOf(diaAtual.get(Calendar.DAY_OF_MONTH));
//                novaDataAgendamento = novaDataAgendamento.concat("/").concat(String.valueOf(diaAtual.get(Calendar.MONTH)))
//                        .concat(String.valueOf(diaAtual.get(Calendar.YEAR)));
//
//                agendamento.put(novaDataAgendamento, valor);
//
//            } else {
//                this.saldo = this.saldo.add(valor);
//            }
//        }
//
//        return this.saldo;
//    }
//
//    BigDecimal realizaSaque(Cliente cliente, BigDecimal valor) {
//
//        int comparaSaldoEValor = this.saldo.compareTo(valor);
//
//        if ((cliente != null) && (comparaSaldoEValor != -1)) {
//            this.saldo = this.saldo.subtract(valor);
//        }
//
//        return this.saldo;
//    }
    void realizaAgendamento(BigDecimal valor) {

        Calendar diaAtual = Calendar.getInstance();

        String novaDataAgendamento;
        int qtdeDias = diaAtual.get(Calendar.DAY_OF_WEEK) == 1 ? 1 : 2;

        diaAtual.add(Calendar.DAY_OF_YEAR, qtdeDias);

        novaDataAgendamento = String.valueOf(diaAtual.get(Calendar.DAY_OF_MONTH)).length() < 2
                ? "0".concat(String.valueOf(diaAtual.get(Calendar.DAY_OF_MONTH)))
                : String.valueOf(diaAtual.get(Calendar.DAY_OF_MONTH));
        novaDataAgendamento = novaDataAgendamento.concat("/").concat(String.valueOf(diaAtual.get(Calendar.MONTH)))
                .concat(String.valueOf(diaAtual.get(Calendar.YEAR)));

        this.agendamento.put(novaDataAgendamento, valor);
    }

    public int getNumero() {
        return numero;
    }

    public String getDigito() {
        return digito;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    Conta getClone() {
        try {
            // call clone in Object.
            return (Conta) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(" Não é permitido clonar o objeto! ");
            return this;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.numero;
        hash = 67 * hash + Objects.hashCode(this.digito);
        hash = 67 * hash + Objects.hashCode(this.agencia);
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
        final Conta other = (Conta) obj;
        if (this.numero != other.numero) {
            return false;
        }
        if (!Objects.equals(this.digito, other.digito)) {
            return false;
        }
        return Objects.equals(this.agencia, other.agencia);
    }

}
