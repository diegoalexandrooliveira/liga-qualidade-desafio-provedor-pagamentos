package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique;

import java.util.List;

public interface Pagamento {

    List<String> pagar(List<String> informacoes);

}
