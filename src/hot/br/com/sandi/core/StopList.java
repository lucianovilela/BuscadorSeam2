package br.com.sandi.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class StopList {
    private String arquivoStopWord;

    private String arquivoPontuacao;

    private String arquivoPronome;

    private List listagemPontuacao;

    private List listagemStopWords;
    
    private List listagemPronomes;

    /**
     * @return Returns the arquivoPontuacao.
     */
    public String getArquivoPontuacao() {
        return arquivoPontuacao;
    }

    /**
     * @param _arquivoPontuacao
     *            The arquivoPontuacao to set.
     */
    public void setArquivoPontuacao(String _arquivoPontuacao) {
        arquivoPontuacao = _arquivoPontuacao;
    }

    /**
     * @return Returns the arquivoStopWord.
     */
    public String getArquivoStopWord() {
        return arquivoStopWord;
    }

    /**
     * @param _arquivoStopWord
     *            The arquivoStopWord to set.
     */
    public void setArquivoStopWord(String _arquivoStopWord) {
        arquivoStopWord = _arquivoStopWord;
    }


    /**
     * @return Returns the arquivoPronome.
     */
    public String getArquivoPronome() {
		return arquivoPronome;
	}
    
    /**
     * @param _arquivoPronome
     *            The arquivoPronome to set.
     */
	public void setArquivoPronome(String _arquivoPronome) {
		this.arquivoPronome = _arquivoPronome;
	}

	public void init() {
        this.inicializaListaPontuacao();
        //this.inicializaStopWord();
        //this.inicializaListaPronomes();
    }

    @SuppressWarnings("unchecked")
	public void inicializaStopWord() {
    	listagemStopWords = new ArrayList();
    	if(arquivoStopWord != null && !arquivoStopWord.trim().equalsIgnoreCase("")){
            try {
                BufferedReader in = new BufferedReader(new FileReader(
                        arquivoStopWord));
                String conteudo = "";
                Word word;
                while ((conteudo = in.readLine()) != null) {
                	word = new Word();
                	word.setWord(conteudo.toLowerCase().trim());
                    listagemStopWords.add(word);
                }
                in.close();
            } catch (Exception e) {
                System.out
                        .println("Erro ao executar o método inicializaStopWord.\nMsg: "
                                + e.getMessage());
            }
            System.out.println("Carregada a lista de stop word");
    		
    	}else{
    		System.out.println("Não existe lista de stop words a ser carregada");    		
    	}
    }

    @SuppressWarnings("unchecked")
	public void inicializaListaPontuacao() {
    	listagemPontuacao = new ArrayList();
    	if(arquivoPontuacao != null && !arquivoPontuacao.trim().equalsIgnoreCase("")){
	        try {
	            BufferedReader in = new BufferedReader(new FileReader(
	                    arquivoPontuacao));
	            String conteudo = "";
	            Word word;
	            
	            while ((conteudo = in.readLine()) != null) {
	              	word = new Word();
	            	word.setWord(conteudo.toLowerCase().trim());
	                listagemPontuacao.add(word);
	            }
	            in.close();
	        } catch (Exception e) {
	            System.out
	                    .println("Erro ao executar o método inicializaListalistaPontuacao.\nMsg: "
	                            + e.getMessage());
	        }
	        System.out.println("Carregada a lista de pontuacao");
    	}else{
    		System.out.println("Não existe lista de pontuacao a ser carregada");    		
    	}
    }


    @SuppressWarnings("unchecked")
	public void inicializaListaPronomes(){
    	listagemPronomes = new ArrayList();
     	if(arquivoPronome != null && !arquivoPronome.trim().equalsIgnoreCase("")){

        	try	{
        		BufferedReader in = new BufferedReader(new FileReader(arquivoPronome));
        		String conteudo = "";
        		Word word;
        		while((conteudo = in.readLine()) != null) {
        	      	word = new Word();
                	word.setWord(conteudo.toLowerCase().trim());
        			listagemPronomes.add(word);
        		}
        		in.close();
        	}	catch(Exception e)	{
        		System.out.println("Erro ao executar o m\351todo inicializaListalistaPronomes.\nMsg: " + e.getMessage());
        	}
        	System.out.println("Carregada a lista de pronomes");
     		
    	}else{
    		System.out.println("Não existe lista de pronomes a ser carregada");    		
    	}
    }    
    
    public boolean isStopWord(String _palavra) {
    	if(listagemStopWords == null)
    		return false;
    	Word word = new Word();
    	word.setWord(_palavra.toLowerCase());
        return listagemStopWords.contains(word);
    }

    public boolean isPontuacao(char _character) {
    	if(listagemPontuacao == null)
    		return false;
    	Word word = new Word();
    	word.setWord(String.valueOf(_character));

        return listagemPontuacao.contains(word);
    }
    
    public boolean isNumber(String _palavra) {
    	try{
    		Double.parseDouble(_palavra);
    		return true;
    	}catch(Exception e){
    		//Verifica se começa com número
    		if(_palavra.indexOf("0") > 0)
    			return true;
    		if(_palavra.indexOf("1") > 0)
    			return true;
    		if(_palavra.indexOf("2") > 0)
    			return true;
    		if(_palavra.indexOf("3") > 0)
    			return true;
    		if(_palavra.indexOf("4") > 0)
    			return true;
    		if(_palavra.indexOf("5") > 0)
    			return true;
    		if(_palavra.indexOf("6") > 0)
    			return true;
    		if(_palavra.indexOf("7") > 0)
    			return true;
    		if(_palavra.indexOf("8") > 0)
    			return true;
    		if(_palavra.indexOf("9") > 0)
    			return true;
    		return false;
    	}
    	
    }
    
    public String retiraPronome(String palavra)  {    	
    	int index = palavra.indexOf("-");
    	
    	//Verifica se a palavra possui pronome
    	if(index > 0){
    		//Verifica se é um pronome
    		String pronome = palavra.substring(index + 1);
        	Word word = new Word();
        	word.setWord(pronome);
    		if(listagemPronomes.contains(word)){
    			palavra = palavra.substring(0, index);
    		}
    	}
    	return palavra;
    }

}
