package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Credito implements Pagamento {

    @Override
    public String[] pagar(List<String> informacoes) {
        var retorno = new String[4];

        retorno[0] = "aguardando_pagamento";

        retorno[1] = informacoes.get(0);

        retorno[2] = aplicaDescontoCincoPorcento(new BigDecimal(informacoes.get(0))).setScale(2, RoundingMode.HALF_EVEN).toString();

        LocalDate trintaDiasAFrente = LocalDate.now().plusDays(30);
        String dataTransacao = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(trintaDiasAFrente);
        retorno[3] = dataTransacao;

        return retorno;
    }

    private BigDecimal aplicaDescontoCincoPorcento(BigDecimal valor) {
        var percentual = BigDecimal.valueOf(5).divide(BigDecimal.valueOf(100));
        var desconto = percentual.multiply(valor);
        return valor.subtract(desconto);
    }
}
