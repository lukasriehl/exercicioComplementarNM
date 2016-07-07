/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente_conta;

import enumerator.Status;
import java.math.BigDecimal;

/**
 *
 * @author Lukas
 */
public class ClientePremium extends Cliente {

    public void criaContaPremium(int numero, String digito, int numeroCCVinculada) {
        Conta contaVinculada = getContaPorNumero(numero);

        if (contaVinculada != null) {
            ContaPremium novaContaPremium = new ContaPremium(numero, digito, BigDecimal.ZERO, contaVinculada.getAgencia(), Status.ATIVO);

            this.adicionaConta(novaContaPremium);

            System.out.println("Processo de criação de conta Premium concluído com sucesso!");
        } else {
            System.out.println("Não foi possível criar a Conta Premium! A conta-corrente selecionada p/ vínculo não existe.");
        }
    }

}
