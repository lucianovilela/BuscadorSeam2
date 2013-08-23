package br.com.sandi.bow;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import br.com.sandi.entity.Projeto;

public class Gerador {

    private TreeMap<Termo, List<String>> termos;

    /** nome do arquivo, conteudo */
    private Map<String, String> listaArquivos;

    private TreeMap<String, Double> tabelaDF;

    private StringBuilder conteudoArquivoSaida;
    
    private Projeto projeto;

    public Gerador() {
        this.termos = new TreeMap<Termo, List<String>>();
        this.listaArquivos = new HashMap<String, String>();
        this.tabelaDF = new TreeMap<String, Double>();
        this.conteudoArquivoSaida = new StringBuilder("");
    }

    public static void main(String[] args) {
        Gerador gerador = new Gerador();
        try {
            gerador.iniciar(null);

        } catch (Exception e) {
            e.printStackTrace();
            Logger.imprime(e, e.getMessage());
        }
    }

    public void iniciar(Projeto projeto) throws IOException {
    	Configuracao.init(projeto);
        System.out.println("Montando a Lista de termos...");
        this.montaListaTermos();
        //gerador.imprimeListaTermos();
        System.out.println("Montando a tabela com os valores DF...");
        montaTabelaDF();
        //gerador.imprimeTabelaDF();
        System.out.println("Montando o cabecalho da tabela...");
        this.montaCabecalhoTabela();
        System.out.println("Montando a tabela...");
        this.montaTabelaFrequencia();
        //System.out.println(this.conteudoArquivoSaida.toString());
        this.salvarSaidaEmArquivo();
    }
    
    private void salvarSaidaEmArquivo(){
        try {
            ArquivoUtil.salvar(Configuracao.CAMINHO_ARQUIVOS_SAIDA+
                            System.getProperty("file.separator")+new Date().getTime()+".csv",
                            this.conteudoArquivoSaida.toString(),false);
        } catch (IOException e) {
            Logger.imprime(e,e.getMessage());
        }
    }

    private void montaListaTermos() throws IOException {
        File[] arquivos = 
            ArquivoUtil.recuperarArquivos(Configuracao.CAMINHO_ARQUIVOS_ENTRADA);
        for (File arq: arquivos) {
            String conteudo = ArquivoUtil.getConteudoArquivo(arq);
            this.listaArquivos.put(arq.getName(), conteudo);
            String[] tokens = conteudo.split("\n");
            for (String tk: tokens) {
            	if( tk.length() != 0){
            		Termo termo = 
                    	new Termo(Utilitario.removeCaracteresInvalidos(tk.trim()));
                	this.termos.put(termo, new ArrayList<String>());
            	}
            }
        }
    }

    private void montaTabelaDF() {
        Set<Termo> chaves = termos.keySet();
        for (Termo t: chaves) {
            double df = 1d;
            for (String conteudo: this.listaArquivos.values()) {
                String[] tokens = conteudo.split("\n");
                boolean achou = false;
                for (String tk: tokens) {
                    if (LevenshteinDistance.saoSemelhantes(tk, t.getValor())) {
                        achou = true;
                    }                
                }
                
                if (achou) df++;
                
            }
            this.tabelaDF.put(t.getValor(), df);
        }

    }

    private double calculaTF(String palavra, String texto) {
        double tf = 0;
        try {
            String[] tokens = texto.split("\n");
            for (String tk: tokens) {
                if (LevenshteinDistance.saoSemelhantes(tk, palavra)) {
                    tf++;
                }
            }
        } catch (Exception e) {
            Logger.imprime(e, "calculaTF");
        }
        return tf;
    }

    private void montaCabecalhoTabela() {
        this.conteudoArquivoSaida.append("NomeArquivo");
        for (Termo t: this.termos.keySet()) {
            this.conteudoArquivoSaida.append(",");
            this.conteudoArquivoSaida.append(t.getValor());
        }
        this.conteudoArquivoSaida.append("\n");

    }

    private void montaTabelaFrequencia() {
        Set<String> arquivos = this.listaArquivos.keySet();

        double qtdeDocumentos = this.listaArquivos.size() + 1; //mais 1 porque começa do zero
        double qtdeTermos = this.termos.size() + 1; //mais 1 porque começa do zero;
        for (String nomeArq: arquivos) {
            this.conteudoArquivoSaida.append(nomeArq);
            
            Set<Termo> termos = this.termos.keySet();
            for (Termo t: termos) {
                double tf = 0d;
                double df = 0d;
                double txRelev = 0d;
            
                this.conteudoArquivoSaida.append(",");
            
                tf = calculaTF(t.getValor(), this.listaArquivos.get(nomeArq));
                df = this.tabelaDF.get(t.getValor());
                txRelev = t.getRelevancia();

                double resultadoFormula = 
                    this.getValorTFIdF(tf, qtdeDocumentos, df, txRelev, 
                                       qtdeTermos);
                this.conteudoArquivoSaida.append(resultadoFormula);

            }

            this.conteudoArquivoSaida.append("\n");
        }

        this.conteudoArquivoSaida.append("\n");
    }

    private double getValorTFIdF(double tf, double quantidadeDocumentos, 
                                 double df, double txRelev, 
                                 double totalTermos) {
        double resuldato = 0d;
        switch (Configuracao.FUNCAO) {
        case 1:
            resuldato = Formulas.getTFIDF_v1(tf, quantidadeDocumentos, df);
            break;
        case 2:
            resuldato = 
                    Formulas.getTFIDF_v3(tf, quantidadeDocumentos, df, totalTermos);
            break;
        case 3:
            resuldato = Formulas.getTFIDF_v4(tf, quantidadeDocumentos, df);
            break;
        case 4:
            resuldato = 
                    Formulas.getTFIDF_v6(tf, quantidadeDocumentos, df, txRelev);
            break;
        default:
            resuldato = 0d;
            break;
        }
        return resuldato;
    }

    private void imprimeListaTermos() {
        Set<Termo> chaves = termos.keySet();
        for (Termo t: chaves) {
            System.out.println("" + t.getValor() + " RELEVANCIA:" + 
                               t.getRelevancia());
        }

    }

    private void imprimeTabelaDF() {
        Set<String> chaves = this.tabelaDF.keySet();
        for (String t: chaves) {
            System.out.println("" + t + ", DF:" + this.tabelaDF.get(t));
        }

    }


}

