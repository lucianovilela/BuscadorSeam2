package br.com.sandi.bow;

import br.com.sandi.entity.Projeto;

 class Configuracao {
    
    public static String CAMINHO_ARQUIVOS_ENTRADA;
    public static String CAMINHO_ARQUIVOS_SAIDA;
    public static double SEMELHANCA;
    public static int FUNCAO;
    

    
    public static void init(Projeto p){

        CAMINHO_ARQUIVOS_ENTRADA = p.getBowDiretorioEntrada();
        CAMINHO_ARQUIVOS_SAIDA = p.getBowDiretorioSaida();
        SEMELHANCA = p.getBowSemelhanca();
        FUNCAO = p.getBowFuncao();
    }
    
//    static {
//        
//        ResourceBundle bundle = ResourceBundle.getBundle("config");
//        CAMINHO_ARQUIVOS_ENTRADA = bundle.getString("param.entrada");
//        CAMINHO_ARQUIVOS_SAIDA = bundle.getString("param.saida");
//        SEMELHANCA = Utilitario.stringToDouble(bundle.getString("param.semelhanca"));
//        FUNCAO = Utilitario.stringToInt(bundle.getString("param.funcao"));
//        
//    }
    
    public Configuracao() {
         
    }

}
