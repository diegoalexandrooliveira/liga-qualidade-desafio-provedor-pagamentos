package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Debito implements Pagamento {

    @Override
    public String[] pagar(List<String> informacoes) {
        var retorno = new String[4];

        retorno[0] = "pago";

        retorno[1] = informacoes.get(0);

        retorno[2] = aplicaDescontoTresPorcento(new BigDecimal(informacoes.get(0))).setScale(2, RoundingMode.HALF_EVEN).toString();

        String dataTransacao = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now());
        retorno[3] = dataTransacao;

        return retorno;
    }

    private BigDecimal aplicaDescontoTresPorcento(BigDecimal valor) {
        var percentual = BigDecimal.valueOf(3).divide(BigDecimal.valueOf(100));
        var desconto = percentual.multiply(valor);
        return valor.subtract(desconto);
    }
}
