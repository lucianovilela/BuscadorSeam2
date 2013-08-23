package br.com.sandi.core;

/** 
 * Classe que representa as palavras que formam a bow 
 **/
public class Word {
	/** Código da palavra, é atribuído na formação da bow **/
	private long code;
	
	/** A palavra que compõe o documento **/
	private String word;
	
	/** O número de documentos em que a palavra aparece **/
	private double numberDocuments;

	
	/**
	 * @return Returns the code.
	 */
	public long getCode() {
		return code;
	}

	/**
	 * @param code The code to set.
	 */
	public void setCode(long code) {
		this.code = code;
	}

	/**
	 * @return Returns the numberDocuments.
	 */
	public double getNumberDocuments() {
		return numberDocuments;
	}

	/**
	 * @param numberDocuments The numberDocuments to set.
	 */
	public void setNumberDocuments(double numberDocuments) {
		this.numberDocuments = numberDocuments;
	}

	/**
	 * @return Returns the word.
	 */
	public String getWord() {
		return word;
	}

	/**
	 * @param word The word to set.
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * Método que sobrescreve o método equals.
	 * Verifica a igualdade entre WordTO, através do atributo word
	 * @param obj (instancia de WordTO) 
	 * @param boolean ( true se for igual senão retorna false
	 **/
	@Override
	public boolean equals(Object obj) {
		Word wordTO = (Word) obj;		
		return this.getWord().equalsIgnoreCase(wordTO.getWord());
	}
	
	
}
