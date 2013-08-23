package br.com.sandi.core;

import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * Classe que implementa diversas funcionalidades �teis para tratamento de Strings
 * @author J�nior
 */
public class UtilString {

  /**
   * Conjunto de vogais acentuadas
   */
  private static final String VOGAIS_ACENTUADAS[] = {"�", "�", "�", "�", "�",
      "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�",
      "�", "�", "�", "�", "�", "�", "�"};
  
  /**
   * Conjunto de vogais n�o acentuadas
   */
  private static final String VOGAIS_NAO_ACENTUADAS[] = {"a", "a", "a", "a",
      "e", "e", "i", "i", "o", "o", "o", "u", "u", "A", "A", "A", "A", "E",
      "E", "I", "I", "O", "O", "O", "U", "U"};

  /**
   * M�todo que retorna uma c�pia � direita da string inicial com determinado tamanho 
   * @param texto String inicial
   * @param numeroDeCaracteres Tamanho do retorno
   * @return Substring � direita
   */
  public static String right(String texto, int numeroDeCaracteres) {
    if (numeroDeCaracteres > texto.length()) {
      return texto.toString();
    }
    return texto.substring(texto.length() - numeroDeCaracteres);
  }

  /**
   * M�todo que retorna uma c�pia � esquerda da string inicial com determinado tamanho 
   * @param texto String inicial
   * @param numeroDeCaracteres Tamanho do retorno
   * @return Substring � esquerda
   */
  public static String left(String texto, int numeroDeCaracteres) {
    if (numeroDeCaracteres > texto.length()) {
      return texto.toString();
    }
    return texto.substring(0, numeroDeCaracteres);
  }

  /**
   * M�todo que retorna um peda�o da string inicial identificado pela ordem e marcado por um separador  
   * @param texto String inicial
   * @param separador Caracter que ser� marcado como separador de peda�os
   * @param pos Ordem do peda�o
   * @return Peda�o selecionado
   */
  public static String getPiece(String texto, String separador, int pos) {
    StringTokenizer token = new StringTokenizer(texto, separador);
    String pedaco = "";
    for (; pos > 0 && token.hasMoreTokens(); pos--) {
      pedaco = token.nextToken();
    }
    if (pos == 0) {
      return pedaco;
    }
    return "";
  }

  /**
   * M�todo que remove determinado caracter de uma string
   * @param caracter Caracter que ser� removido
   * @param texto String inicial
   * @return Substring com caracteres removidos
   */
  public static String trocaCaracter(String caracter, String texto) {
    StringBuilder retorno = new StringBuilder();
    for (int i = 0; i < texto.length(); i++) {
      if (texto.charAt(i) != caracter.charAt(0)) {
        retorno.append(texto.charAt(i));
      }
    }
    return retorno.toString();
  }

  /**
   * M�todo que transforma uma string em um vetor de substrings
   * @param linhasConcatenadas String inicial
   * @param separador Caracter que ser� marcado como separador de strings
   * @return Vetor de substrings
   */
  public static String[] stringToArray(String linhasConcatenadas,
      String separador) {
    StringTokenizer to = new StringTokenizer(linhasConcatenadas, separador);
    int posicoes = to.countTokens();
    String[] linhas = new String[posicoes];
    for (int i = 0; i < posicoes; i++) {
      linhas[i] = to.nextToken();
    }
    return linhas;
  }

  /**
   * M�todo que avalia se uma string � composta exclusivamente por n�meros
   * @param numero String inicial
   * @return Verdadeiro ou falso para indicar se trata-se de um n�mero ou n�o respectivamente
   */
  public static boolean isNumerico(String numero) {
    for (int i = 0; i < numero.length(); i++) {
      if (Character.isDigit(numero.charAt(i)) == false) {
        return false;
      }
    }
    return true;
  }

  /**
   * M�todo que avalia se uma string � composta exclusivamente de caracteres alfanum�ricos
   * @param numero String inicial
   * @return Verdadeiro ou falso para indicar se trata-se de uma string alfanum�rica ou n�o respectivamente
   */
  public static boolean isAlfaNumerico(String numero) {
    for (int i = 0; i < numero.length(); i++) {
      if (Character.isDigit(numero.charAt(i)) == false
          && Character.isLetter(numero.charAt(i)) == false) {
        return false;
      }
    }
    return true;
  }

  /**
   * M�todo que avalia se um caracter � uma letra
   * @param letra Caracter a ser avaliado
   * @return Verdadeiro ou falso para indicar se o caracter � uma letra ou n�o respectivamente
   */
  public static boolean isLetra(char letra) {
    return Character.isLetter(letra);
  }


  /**
   * M�todo que cria um vetor de strings a partir de uma cole��o de valores
   * @param colecaoDeValores Cole��o inicial
   * @return Vetor de strings
   */public static String[] criaArrayDeString(Collection colecaoDeValores) {
    String[] array;
    if (colecaoDeValores.size() == 0) {
      array = new String[1];
    } else {
      array = new String[colecaoDeValores.size()];
    }
    Iterator iter = colecaoDeValores.iterator();
    for (int i = 0; i < colecaoDeValores.size(); i++) {
      array[i] = (String) iter.next();
    }
    return array;
  }

   /**
    * M�todo que transforma uma string em um vetor de substrings
    * @param texto String inicial
    * @param separador Caracter que ser� marcado como separador de strings
    * @return Vetor de substrings
    */
  public static String[] criaArrayDeString(String texto, String separador) {
    StringTokenizer token = new StringTokenizer(texto, separador);
    String[] array = new String[token.countTokens()];
    for (int i = 0; i < array.length; i++) {
      array[i] = token.nextToken();
    }
    return array;
  }

  /**
   * M�todo que remove os acentos de um texto
   * @param palavra String inicial
   * @return String alterada sem os acentos
   */
  public static String retiraAcentos(String palavra) {
    String palavraAlterada = palavra;
    for (int i = 0; i < VOGAIS_ACENTUADAS.length; i++) {
      palavraAlterada = palavraAlterada.replaceAll(VOGAIS_ACENTUADAS[i],
          VOGAIS_NAO_ACENTUADAS[i]);
    }
    return palavraAlterada;
  }

  /**
   * M�todo que transforma uma cole��o em uma string separada
   * @param col Cole��o inicial
   * @param separador Caracter que ser� marcado como separador de strings
   * @return String separada
   */
  public static String criaStringComPieces(Collection col, String separador) {
    return criaStringComPieces(col.iterator(), separador);
  }

  /**
   * M�todo que transforma um iterador de uma cole��o em uma string separada
   * @param iter Iterador inicial
   * @param separador Caracter que ser� marcado como separador de strings
   * @return String separada
   */
  public static String criaStringComPieces(Iterator iter, String separador) {
    String html = "";
    while (iter.hasNext()) {
      if (html.length() > 0) {
        html = html + separador;
      }
      html = (String) iter.next();
    }
    return html;
  }

  /**
   * M�todo que retorna o nome de uma classe sem o pacote
   * @param classe Classe que ser� analisada
   * @return Nome da classe sem o pacote
   */
  public static String extraiNomeDaClasse(Class classe) {
    return extraiNomeDaClasse(classe.getName());
  }

  /**
   * M�todo que retorna o nome de uma classe sem o pacote
   * @param nomeDaClasse Nome da classe
   * @return Nome da classe sem o pacote
   */
  public static String extraiNomeDaClasse(String nomeDaClasse) {
    int posPonto = nomeDaClasse.lastIndexOf(".");
    if (posPonto >= 0) {
      return nomeDaClasse.substring(posPonto + 1);
    }
    return nomeDaClasse;
  }

  /**
   * M�todo que retorna o nome do pacote de uma classe
   * @param nomeDaClasse Nome da classe
   * @return Nome do pacote
   */
  public static String extraiNomeDoPacote(String nomeDaClasse) {
    int posPonto = nomeDaClasse.lastIndexOf(".");
    if (posPonto >= 0) {
      return nomeDaClasse.substring(0, posPonto);
    }
    return nomeDaClasse;
  }

  /**
   * M�todo que retorna o nome do pacote de uma classe
   * @param classe Classe que ser� analisada
   * @return Nome do pacote
   */
  public static String extraiNomeDoPacote(Class classe) {
    return extraiNomeDoPacote(classe.getName());
  }

  /**
   * M�todo que avalia se dois textos s�o iguais independente de mai�sculas e min�sculas
   * @param palavra1 Primeiro texto
   * @param palavra2 Segundo texto
   * @return Verdadeiro ou falso para indicar se os textos s�o iguais ou n�o respectivamente
   */
  public static boolean equalsCanonico(String palavra1, String palavra2) {
    return palavra1.trim().equalsIgnoreCase(palavra2.trim());
  }

  /**
   * M�todo que retorna uma palavra com a inicial em mai�scula
   * @param palavra String inicial
   * @return String com inicial em mai�scula
   */
  public static String capitula(String palavra) {
    return Character.toUpperCase(palavra.charAt(0)) + palavra.substring(1);
  }

  /**
   * M�todo que retorna todas as palavras de uma frase com a inicial em mai�scula
   * @param frase String inicial
   * @return String com todas as iniciais das palavras em mai�sculas
   */
  public static String capitulaFrase(String frase) {
    StringBuilder texto = new StringBuilder();
    StringTokenizer token = new StringTokenizer(frase.toLowerCase(), " ");
    while (token.hasMoreTokens()) {
      texto.append(capitula(token.nextToken()));
      if (token.hasMoreTokens()) {
        texto.append(" ");
      }
    }
    return texto.toString();
  }

  /**
   * M�todo que transforma uma cole��o em uma string separada por v�rgulas
   * @param lista Cole��o inicial
   * @return String separada
   */
  public static String criaStringComOsValoresDaColecao(Collection lista) {
    return criaStringComOsValoresDaColecao(lista, ",");
  }

  /**
   * M�todo que transforma uma cole��o em uma string separada
   * @param lista Cole��o inicial
   * @param separador Caracter que ser� marcado como separador de strings
   * @return String separada
   */
  public static String criaStringComOsValoresDaColecao(Collection lista,
      String separador) {
    Iterator ite = lista.iterator();
    if (!ite.hasNext()) {
      return "";
    }
    StringBuilder retorno = new StringBuilder();
    while (ite.hasNext()) {
      retorno.append(ite.next() + separador);
    }
    return retorno.substring(0, retorno.length() - 1);
  }

  /**
   * M�todo que remove determinados caracteres especiais de um texto
   * @param palavra String inicial
   * @return Substring com caracteres removidos
   */
  public static String removeCaracteresEspeciais(String palavra) {
	palavra = palavra.replace("*", "");
	palavra = palavra.replace("@", "");
	palavra = palavra.replace("!", "");
	palavra = palavra.replace("?", "");
	palavra = palavra.replace(".", "");
	palavra = palavra.replace(",", "");
	palavra = palavra.replace("'", "");
	palavra = palavra.replace("�", "");
	palavra = palavra.replace("`", "");
	palavra = palavra.replace(";", "");
	palavra = palavra.replace(":", "");
	palavra = palavra.replace("=", "");
	palavra = palavra.replace("_", "");
	palavra = palavra.replace("\"", "");
	palavra = palavra.replace("(", "");
	palavra = palavra.replace(")", "");
	palavra = palavra.replace("[", "");
	palavra = palavra.replace("]", "");
	palavra = palavra.replace("#", "");
	palavra = palavra.replace("~", "");
	palavra = palavra.replace("^", "");
	palavra = palavra.replace("�", "");
	palavra = palavra.replace("+", "");
	palavra = palavra.replace("<", "");
	palavra = palavra.replace(">", "");
	palavra = palavra.replace("|", "");
	palavra = palavra.replace("\\", "");
	palavra = palavra.replace("\r", "");
	palavra = palavra.replace("\n", "");
	palavra = palavra.replace("\t", "");
	
	return palavra;
  }
}