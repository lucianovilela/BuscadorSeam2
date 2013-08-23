package br.com.sandi.bow;

public class Formulas {
    
    /**
     * tfidf1 = tf*idf;
       onde:  tf -> é a frequência do termo no documento atual
       idf é o LN(QUANTIDADE DE DOCUMENTOS / QTD DE DOCUMENTOS ONDE O TERMO APARECE)/LN(10) -> Math.log(N/DF) / Math.log(10)
     */
    public static double getTFIDF_v1(double tf,double quantidadeDocumentos,double df){
        double idf = Math.log(quantidadeDocumentos/df) / Math.log(10);
        double tfIdf = tf*idf;
        if(tfIdf >= Double.POSITIVE_INFINITY){
             tfIdf = 0.0;               
        }
        return tfIdf;
    }
    /**
     * tfidf2 = (tf/tot_termos) * idf
       onde:  tf -> é a frequência do termo no documento
       tot_termos -> é a quantidade de termos existentes no documento atual
       idf é o LN(QUANTIDADE DE DOCUMENTOS / QTD DE DOCUMENTOS ONDE O TERMO APARECE) -> Math.log(N/DF)
     */
    public static double getTFIDF_v3(double tf,double quantidadeDocumentos,double df,double totalTermos){
        double idf = Math.log(quantidadeDocumentos/df);
        double tfIdf =  (tf/totalTermos) * idf;
        if(tfIdf >= Double.POSITIVE_INFINITY){
             tfIdf = 0.0;               
        }
        return tfIdf;
    }
    /**
     * tfidf3 = (1/tf) * idf2
       onde:  tf -> é a frequência do termo no documento
       idf2 é o LN(QUANTIDADE DE DOCUMENTOS / QTD DE DOCUMENTOS ONDE O TERMO APARECE)/LN(10) -> Math.log(N/DF) / Math.log(10)

     */
    public static double getTFIDF_v4(double tf,double quantidadeDocumentos,double df){
        double idf2 = Math.log(quantidadeDocumentos/df) / Math.log(10);
        double tfIdf =  (1/tf) * idf2;
        if(tfIdf >= Double.POSITIVE_INFINITY){
             tfIdf = 0.0;               
        }
        return tfIdf;
    }
    /**
     * tfidf4 = (1/(tf*txRelev)) * idf2
       onde:  tf -> é a frequência do termo no documento
       txRelev -> é a taxa de relevância dos termos. Ex. AS_MATRÍCULAS_DOS_FUNCIONÁRIOS@2.7
       idf2 é o LN(QUANTIDADE DE DOCUMENTOS / QTD DE DOCUMENTOS ONDE O TERMO APARECE)/LN(10) -> Math.log(N/DF) / Math.log(10)
     */
    public static double getTFIDF_v6(double tf,double quantidadeDocumentos,double df,double txRelev){
        double idf2 = Math.log(quantidadeDocumentos/df) / Math.log(10);
        double tfIdf =  (1/(tf*txRelev)) * idf2 ;
        if(tfIdf >= Double.POSITIVE_INFINITY){
             tfIdf = 0.0;               
        }
        return tfIdf;
    }
    
    
}
