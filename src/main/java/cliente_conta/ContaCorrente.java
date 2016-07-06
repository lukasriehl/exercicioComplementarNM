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

/**
 *
 * @author Lukas
 */
public class ContaCorrente extends Conta {
    
    private BigDecimal chequeEspecial = new BigDecimal(5000);
    
    public ContaCorrente(int numero, String digito, BigDecimal saldo, BigDecimal chequeEspecial, Agencia agencia, Status status){
        super(numero, digito, saldo, agencia, status);
    }
    
    @Override
    public BigDecimal realizaDeposito(BigDecimal valor) {

        BigDecimal saldoAtualizado;

        Calendar diaAtual = Calendar.getInstance();

        //Efetua o agendamento para o próximo dia útil caso o dia atual seja Sábado ou Domingo
        if ((diaAtual.get(Calendar.DAY_OF_WEEK) == 1) || (diaAtual.get(Calendar.DAY_OF_WEEK) == 7)) {

            saldoAtualizado = this.getSaldo();

            realizaAgendamento(valor);

        } else {
            saldoAtualizado = this.getSaldo().add(valor.multiply(BigDecimal.valueOf(1.02)));
        }

        return saldoAtualizado;
    }

    @Override
    public BigDecimal realizaSaque(BigDecimal valor) {
        int comparaSaldoEValor = this.getSaldo().compareTo(valor);

        return comparaSaldoEValor != -1 ? this.getSaldo().subtract(valor) : this.getSaldo();
    }

}
