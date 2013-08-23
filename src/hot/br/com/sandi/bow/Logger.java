package br.com.sandi.bow;

public class Logger {
   
   
   public static void imprime(Throwable t,String mensagem){
       if( Utilitario.isPreenchido(mensagem)) System.out.println(mensagem);
        
       t.printStackTrace();
   }
}
