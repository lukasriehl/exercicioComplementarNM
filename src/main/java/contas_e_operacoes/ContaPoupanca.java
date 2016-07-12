/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contas_e_operacoes;

import agencia_banco.Agencia;
import java.math.BigDecimal;

/**
 *
 * @author Lukas
 */
public class ContaPoupanca extends OperacoesConta {

    private BigDecimal rendimento = BigDecimal.ZERO;

    public ContaPoupanca(int numero, String digito, Agencia agencia) {
        super(numero, digito, agencia);
    }

    @Override
    public BigDecimal realizaDeposito(BigDecimal valor) {

        this.rendimento = this.rendimento.add(valor.multiply(BigDecimal.valueOf(0.02)));
//        this.saldo = this.saldo.add(valor.multiply(BigDecimal.valueOf(1.02)));
//        
//        return this.saldo;
        return super.realizaDeposito(valor.add(rendimento));
    }

//    @Override
//    public BigDecimal realizaSaque(BigDecimal valor) {
//        int comparaSaldoEValor = this.saldo.compareTo(valor);
//
//        if (comparaSaldoEValor == -1) {
//            System.out.println("Não foi possível efetuar o saque! Saldo abaixo do valor de saque desejado. Saldo atual: " + this.retornaSaldoTotal());
//
//            return this.saldo;
//        } else {
//            this.saldo = this.saldo.subtract(valor);
//        }
//        return this.retornaSaldoTotal();
//    }
    public BigDecimal getRendimento() {
        return rendimento;
    }

}
