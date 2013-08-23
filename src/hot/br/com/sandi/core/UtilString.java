package br.com.sandi.core;

import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * Classe que implementa diversas funcionalidades úteis para tratamento de Strings
 * @author Júnior
 */
public class UtilString {

  /**
   * Conjunto de vogais acentuadas
   */
  private static final String VOGAIS_ACENTUADAS[] = {"á", "à", "â", "ã", "é",
      "ê", "í", "î", "ó", "ô", "õ", "ú", "û", "Á", "À", "Â", "Ã", "É", "Ê",
      "Í", "Î", "Ó", "Ô", "Õ", "Ú", "Û"};
  
  /**
   * Conjunto de vogais não acentuadas
   */
  private static final String VOGAIS_NAO_ACENTUADAS[] = {"a", "a", "a", "a",
      "e", "e", "i", "i", "o", "o", "o", "u", "u", "A", "A", "A", "A", "E",
      "E", "I", "I", "O", "O", "O", "U", "U"};

  /**
   * Método que retorna uma cópia à direita da string inicial com determinado tamanho 
   * @param texto String inicial
   * @param numeroDeCaracteres Tamanho do retorno
   * @return Substring à direita
   */
  public static String right(String texto, int numeroDeCaracteres) {
    if (numeroDeCaracteres > texto.length()) {
      return texto.toString();
    }
    return texto.substring(texto.length() - numeroDeCaracteres);
  }

  /**
   * Método que retorna uma cópia à esquerda da string inicial com determinado tamanho 
   * @param texto String inicial
   * @param numeroDeCaracteres Tamanho do retorno
   * @return Substring à esquerda
   */
  public static String left(String texto, int numeroDeCaracteres) {
    if (numeroDeCaracteres > texto.length()) {
      return texto.toString();
    }
    return texto.substring(0, numeroDeCaracteres);
  }

  /**
   * Método que retorna um pedaço da string inicial identificado pela ordem e marcado por um separador  
   * @param texto String inicial
   * @param separador Caracter que será marcado como separador de pedaços
   * @param pos Ordem do pedaço
   * @return Pedaço selecionado
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
   * Método que remove determinado caracter de uma string
   * @param caracter Caracter que será removido
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
   * Método que transforma uma string em um vetor de substrings
   * @param linhasConcatenadas String inicial
   * @param separador Caracter que será marcado como separador de strings
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
   * Método que avalia se uma string é composta exclusivamente por números
   * @param numero String inicial
   * @return Verdadeiro ou falso para indicar se trata-se de um número ou não respectivamente
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
   * Método que avalia se uma string é composta exclusivamente de caracteres alfanuméricos
   * @param numero String inicial
   * @return Verdadeiro ou falso para indicar se trata-se de uma string alfanumérica ou não respectivamente
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
   * Método que avalia se um caracter é uma letra
   * @param letra Caracter a ser avaliado
   * @return Verdadeiro ou falso para indicar se o caracter é uma letra ou não respectivamente
   */
  public static boolean isLetra(char letra) {
    return Character.isLetter(letra);
  }


  /**
   * Método que cria um vetor de strings a partir de uma coleção de valores
   * @param colecaoDeValores Coleção inicial
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
    * Método que transforma uma string em um vetor de substrings
    * @param texto String inicial
    * @param separador Caracter que será marcado como separador de strings
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
   * Método que remove os acentos de um texto
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
   * Método que transforma uma coleção em uma string separada
   * @param col Coleção inicial
   * @param separador Caracter que será marcado como separador de strings
   * @return String separada
   */
  public static String criaStringComPieces(Collection col, String separador) {
    return criaStringComPieces(col.iterator(), separador);
  }

  /**
   * Método que transforma um iterador de uma coleção em uma string separada
   * @param iter Iterador inicial
   * @param separador Caracter que será marcado como separador de strings
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
   * Método que retorna o nome de uma classe sem o pacote
   * @param classe Classe que será analisada
   * @return Nome da classe sem o pacote
   */
  public static String extraiNomeDaClasse(Class classe) {
    return extraiNomeDaClasse(classe.getName());
  }

  /**
   * Método que retorna o nome de uma classe sem o pacote
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
   * Método que retorna o nome do pacote de uma classe
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
   * Método que retorna o nome do pacote de uma classe
   * @param classe Classe que será analisada
   * @return Nome do pacote
   */
  public static String extraiNomeDoPacote(Class classe) {
    return extraiNomeDoPacote(classe.getName());
  }

  /**
   * Método que avalia se dois textos são iguais independente de maiúsculas e minúsculas
   * @param palavra1 Primeiro texto
   * @param palavra2 Segundo texto
   * @return Verdadeiro ou falso para indicar se os textos são iguais ou não respectivamente
   */
  public static boolean equalsCanonico(String palavra1, String palavra2) {
    return palavra1.trim().equalsIgnoreCase(palavra2.trim());
  }

  /**
   * Método que retorna uma palavra com a inicial em maiúscula
   * @param palavra String inicial
   * @return String com inicial em maiúscula
   */
  public static String capitula(String palavra) {
    return Character.toUpperCase(palavra.charAt(0)) + palavra.substring(1);
  }

  /**
   * Método que retorna todas as palavras de uma frase com a inicial em maiúscula
   * @param frase String inicial
   * @return String com todas as iniciais das palavras em maiúsculas
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
   * Método que transforma uma coleção em uma string separada por vírgulas
   * @param lista Coleção inicial
   * @return String separada
   */
  public static String criaStringComOsValoresDaColecao(Collection lista) {
    return criaStringComOsValoresDaColecao(lista, ",");
  }

  /**
   * Método que transforma uma coleção em uma string separada
   * @param lista Coleção inicial
   * @param separador Caracter que será marcado como separador de strings
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
   * Método que remove determinados caracteres especiais de um texto
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
	palavra = palavra.replace("´", "");
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
	palavra = palavra.replace("¨", "");
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