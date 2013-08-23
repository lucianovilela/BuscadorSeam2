package br.com.sandi.core;

/** 
 * Classe que representa as palavras que formam a bow 
 **/
public class Word {
	/** C�digo da palavra, � atribu�do na forma��o da bow **/
	private long code;
	
	/** A palavra que comp�e o documento **/
	private String word;
	
	/** O n�mero de documentos em que a palavra aparece **/
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
	 * M�todo que sobrescreve o m�todo equals.
	 * Verifica a igualdade entre WordTO, atrav�s do atributo word
	 * @param obj (instancia de WordTO) 
	 * @param boolean ( true se for igual sen�o retorna false
	 **/
	@Override
	public boolean equals(Object obj) {
		Word wordTO = (Word) obj;		
		return this.getWord().equalsIgnoreCase(wordTO.getWord());
	}
	
	
}
