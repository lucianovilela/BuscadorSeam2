package br.com.sandi.core;

import java.util.List;

public interface ExtrairSintagmas {

	public abstract void gerarArquivoSintagmas(String texto, String arqSaida);

	/**
	 * M�todo que implementa a funcionalidade do bot�o An�lise, procurando
	 * no texto todas as ocorr�ncias dos sintagmas escolhidos
	 */
	@SuppressWarnings("unchecked")
	public abstract String analisarSintagmas(List dlmClasses, String arquivo,
			int qtd);

}