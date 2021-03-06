package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Debito implements Pagamento {

    @Override
    public List<String> pagar(List<String> informacoes) {
        var retorno = new ArrayList<String>();

        retorno.add("pago");

        retorno.add(informacoes.get(0));

        retorno.add(aplicaDesconteTresPorcento(new BigDecimal(informacoes.get(0))).setScale(2, RoundingMode.HALF_EVEN).toString());

        String dataTransacao = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now());
        retorno.add(dataTransacao);


        return retorno;
    }

    private BigDecimal aplicaDesconteTresPorcento(BigDecimal valor) {
        var percentual = BigDecimal.valueOf(3).divide(BigDecimal.valueOf(100));
        var desconto = percentual.multiply(valor);
        return valor.subtract(desconto);
    }
}
