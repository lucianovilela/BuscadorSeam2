package br.com.sandi.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//import logica.UtilString;

//import logica.Tesauro;

/**
 * Classe que descreve a janela cuja funcionalidade � a extra��o dos
 * sintagmas do texto que estejam no padr�o selecionado
 * 
 * @author J�nior
 */
public class ExtrairSintagmasComTxRelevImpl implements ExtrairSintagmas{

	/**
	 * Separador de sintagmas
	 */
	private static final String SEPARADOR = "-----------------------------";

	/**
	 * Classes gramaticais para o MXPost
	 */
//	private static final String CLASSES_GRAMATICAIS_MXPOST[] = {
//			"ADJ - Adjetivo", "ADV-KS - Adv. Conectivo Subord.",
//			"ADV-KS-REL - Adv. Relativo Subord.", "ADV - Advervio",
//			"ART - Artigo", "IN  - Interjeicao",
//			"KC  - Conjuncao Coordenativa",
//			"KS  - Conjuncao Subordinativa", "N   - Substantivo",
//			"NPROP - Substantivo Proprio", "NUM - Numeral",
//			"PCP - Participio", "PDEN - Palavra Denotativa",
//			"PREP - Preposico", "PROADJ - Pronome Adjetivo",
//			"PRO-KS - Pronome Conectivo Subord.", "PROPESS - Pronome Pessoal",
//			"PRO-KS_REL - Pronome Rel. Conec. Subord.",
//			"PROSUB - Pronome Substantivo", "V - Verbo",
//			"VAUX - Verbo Auxiliar", "CUR - S�mbolo Moeda Corrente",
//			"|+ - Contracoes e enclises", "|! - Mesoclises",
//			"|EST - Estrangeirismos", "|AP - Aposto", "|DAD - Dados",
//			"|TEL - Numeros de Telefone", "|DAT - Datas", "|HOR - Horas",
//			"|[ |] - Disjuncao", SEPARADOR, };

	/**
	 * Vetor com o resultado da an�lise do processamento de linguagem natural
	 */
	private String[] resultadoAnalise = null;

	/**
	 * Vetor com todo o conte�do do texto
	 */
	private String[] entradaAnalise = null;
	
	/**
	 * Vetor com os pesos dos Sintagmas
	 * 
	 */
	private List<Double> pesos = new ArrayList<Double>();
	

	/**
	 * /** M�todo construtor da classe
	 */
	public ExtrairSintagmasComTxRelevImpl() {

	}

	public void gerarArquivoSintagmas(String texto, String arqSaida) {
		// System.out.println(txtTexto.getText());
		String saida = texto;
		saida = saida.replace(" ", "-");
		try {
			File arquivo = new File(arqSaida);
			FileOutputStream fos = new FileOutputStream(arquivo);
			fos.write(saida.getBytes());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�todo que implementa a funcionalidade do bot�o Procurar,
	 * selecionando um arquivo para leitura e realizando o processamento de
	 * linguagem natural do texto
	 * 
	 * @param qtdTermos
	 */
	private void buscarSintagmas(String arquivo) {
		try {
			ArrayList<String> vetor = new ArrayList<String>();
			String leitor = "";

			FileInputStream arquivoEntrada = new FileInputStream(arquivo);
			InputStreamReader leitorEntrada = new InputStreamReader(
					arquivoEntrada);
			BufferedReader leitorBuferizado = new BufferedReader(leitorEntrada);
			while ((leitor = leitorBuferizado.readLine()) != null) {
				StringTokenizer objTokenizador = new StringTokenizer(leitor);
				while (objTokenizador.hasMoreTokens()) {
					vetor.add(objTokenizador.nextToken());
				}
			}

			String[][] retorno = taggerMXPost(vetor);
			entradaAnalise = retorno[0];
			resultadoAnalise = retorno[1];

			leitorBuferizado.close();
			leitorEntrada.close();
			arquivoEntrada.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�todo que procura padr�es de estruturas sintagm�ticas no texto
	 * 
	 * @param matriz
	 *            Estrutura bidimensional que cont�m os padr�es que se
	 *            deseja procurar
	 * @param entradaAnalise
	 *            Vetor cada unidade lexical do texto
	 * @param resultadoAnalise
	 *            Vetor com a an�lise morfol�gica de cada unidade lexical do
	 *            texto
	 * @param separador
	 *            Separador que ser� utilizado no retorno do m�todo
	 * @param utilizarTesauro
	 *            Booleano que indica se o tesauro deve ser utilizado para
	 *            permitir os termos
	 * @param objTesauro
	 *            Objeto da classe Tesauro que cont�m todo o esquema de
	 *            representa��o do conhecimento
	 * @param utilizarTermoRelacionado
	 *            Booleano que indica se os termos relacionados no tesauro
	 *            ser�o utilizados
	 * @return String com os padr�es encontrados separados pelo separador
	 */
	private String procuraPadroes(ArrayList<ArrayList<String>> matriz,
			String[] entradaAnalise, String[] resultadoAnalise,
			String separador, int qtd) {
		boolean flag = false;
		String temp = "";
		String saida = "";
		// String use = "";
		String palavra = "";
//		String palavraSingular = "";
		ArrayList<String> linha = new ArrayList<String>();

		for (int i = 0; i < matriz.size(); i++) {
			linha = matriz.get(i);
			for (int j = 0; j < resultadoAnalise.length - linha.size(); j++) {
				flag = true;
				for (int k = 0; k < linha.size(); k++) {
					if (!resultadoAnalise[j + k].equals(linha.get(k))) {
						flag = false;
					}
				}

				if (flag && qtd > 0) {
					qtd--;
					temp = "";
					for (int k = 0; k < linha.size(); k++) {
						palavra = UtilString
								.removeCaracteresEspeciais(entradaAnalise[j + k]
										.trim().toUpperCase());
						//palavraSingular = "";

						//if (palavra.length() > 0) {
						//	if (palavra.charAt(palavra.length() - 1) == 'S') {
						//		palavraSingular = palavra.substring(0, palavra
						//				.length() - 1);
						//	}
						//}

						temp = temp + palavra + "_" ;
					}
					temp = temp.trim() + "@"+ pesos.get(i);
					temp = temp.replaceAll("_@", "@");
					if (!saida.contains(temp)) {
						saida = saida + temp + separador;
					}
				}
			}
		}
		if (saida.length() > 0)
			saida = saida.substring(0, saida.length() - separador.length());

		return saida;
	}

	/**
	 * M�todo que realiza o tagging utilizando o MXPost
	 * 
	 * @param vetor
	 *            Vetor de tokens do texto original j� taggeado
	 * @return Dois vetores com as unidades lexicais e suas respectivas
	 *         classifica��es morfol�gicas
	 */
	private String[][] taggerMXPost(ArrayList<String> vetor) {
		String[] entradaAnalise = null;
		String[] resultadoAnalise = null;

		try {
			entradaAnalise = new String[vetor.size()];
			resultadoAnalise = new String[vetor.size()];
			for (int i = 0; i < vetor.size(); i++) {
				entradaAnalise[i] = UtilString.getPiece(
						vetor.get(i).toString(), "_", 1);
				resultadoAnalise[i] = UtilString.getPiece(vetor.get(i)
						.toString(), "_", 2);
			}
		} catch (Exception e) {

		}

		String[][] retorno = new String[2][vetor.size()];
		retorno[0] = entradaAnalise;
		retorno[1] = resultadoAnalise;

		return retorno;
	}

	/**
	 * M�todo que implementa a funcionalidade do bot�o An�lise, procurando
	 * no texto todas as ocorr�ncias dos sintagmas escolhidos
	 */
	@SuppressWarnings("unchecked")
	public String analisarSintagmas(List dlmClasses, String arquivo, int qtd) {
		ArrayList<ArrayList<String>> matriz = new ArrayList<ArrayList<String>>();
		ArrayList<String> linha = new ArrayList<String>();

		for (int i = 0; i < dlmClasses.size(); i++) {
			if (dlmClasses.get(i).equals(SEPARADOR)) {
				matriz.add(linha);
				linha = new ArrayList<String>();
			} else {
				if(dlmClasses.get(i).toString().startsWith("@")){
					pesos.add(Double.parseDouble(dlmClasses.get(i).toString().substring(1)));
				}
				else{
					linha.add(UtilString.getPiece(dlmClasses.get(i).toString(),
						" ", 1));
				}
			}
		}
		if (linha.size() > 0) {
			matriz.add(linha);
		}
		/**
		 * Este m�todo buscar� a quantidade de termos informados no segundo
		 * par�metro.
		 */
		buscarSintagmas(arquivo);
		return procuraPadroes(matriz, entradaAnalise, resultadoAnalise, "\r\n",
				qtd);
	}
}