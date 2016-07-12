/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package premium;

import contas_e_operacoes.ContaCorrente;
import java.math.BigDecimal;

/**
 *
 * @author Lukas
 */
public class ContaPremium extends ContaCorrente {

    private static final long serialVersionUID = 1L;

    ContaPremium(ContaCorrente contaCorrente) {
        super(contaCorrente.getNumero(), contaCorrente.getDigito(), contaCorrente.getAgencia());
    }

    @Override
    public BigDecimal realizaSaque(BigDecimal valor) {

        if (super.verificaSaldo(valor)) {
            System.out.println("Impossível realizar o saque! Saldo insuficiente.");
            return getSaldo();
        } else {
            System.out.println("Saque efetuado com sucesso! Saldo atualizado: " + this.getSaldo().toString());
            return super.realizaSaque(valor);
        }
    }
}
