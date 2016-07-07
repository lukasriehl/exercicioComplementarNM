/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enumerator;

/**
 *
 * @author Lukas
 */
public enum TipoMovimentacao {

    CREDITO {
        public char getCodigo() {
            return 'C';
        }
    },
    DEBITO {
        public char getCodigo() {
            return 'D';
        }
    }

}
