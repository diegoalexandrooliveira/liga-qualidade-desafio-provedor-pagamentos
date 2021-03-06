package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solucao {

    /**
     * @param infoTransacoes    dados das transações. A String está formatada da seguinte maneira:
     *                          <b>"valor,metodoPagamento,numeroCartao,nomeCartao,validade,cvv,idTransacao"</b>
     *                          <ol>
     *                          <li> Valor é um decimal</li>
     *                          <li> O método de pagamento é 'DEBITO' ou 'CREDITO' </li>
     *                          <li> Validade é uma data no formato dd/MM/yyyy. </li>
     *                          </ol>
     * @param infoAdiantamentos informacao da transacao que pode ser recebida adiantada. A String está formatada da seguinte maneira:
     *                          <b>"idTransacao,taxa"</b>
     *                          <ol>
     *                          <li> Taxa é um decimal </li>
     *                          </ol>
     * @return Uma lista de array de string com as informações na seguinte ordem:
     * [status,valorOriginal,valorASerRecebidoDeFato,dataEsperadoRecebimento].
     * <ol>
     *  <li>O status pode ser 'pago' ou 'aguardando_pagamento'</li>
     *  <li>O valor original e o a ser recebido de fato devem vir no formato decimal. Ex: 50.45</li>
     *  <li>dataEsperadoRecebimento deve ser formatada como dd/MM/yyyy. Confira a classe {@link DateTimeFormatter}</li>
     * </ol>
     * <p>
     * É esperado que o retorno respeite a ordem de recebimento
     */
    public static List<String[]> executa(List<String> infoTransacoes, List<String> infoAdiantamentos) {

        return infoTransacoes
                .stream()
                .map(transacao -> Arrays.asList(transacao.split(",")))
                .map(transacao -> metodoPagamento(transacao.get(1)).pagar(transacao))
                .collect(Collectors.toList());
    }

    private static Pagamento metodoPagamento(String metodoPagamento) {
        switch (metodoPagamento) {
            case "DEBITO":
                return new Debito();
            case "CREDITO":
                return new Credito();
            default:
                throw new IllegalArgumentException("Método de pagamento não encontrado");
        }
    }

}
