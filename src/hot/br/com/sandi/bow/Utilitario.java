package br.com.sandi.bow;

public class Utilitario {
 
    public static double stringToDouble(String param){       
        try{
            if(isPreenchido(param)) {
                return Double.valueOf(param);
            }
        }catch(NumberFormatException e ){
            Logger.imprime(e,e.getMessage());
        }
        return 0;
    }
    
    public static int stringToInt(String param){       
        try{
            if(isPreenchido(param)) {
                return Integer.valueOf(param);
            }
        }catch(NumberFormatException e ){
            Logger.imprime(e,e.getMessage());
        }
        return 0;
    }
    
    public static boolean isPreenchido(String param){
        if(param!=null || !"".equals(param.trim())) 
            return true;
        return false;
    }
    
    
    public static String removeCaracteresInvalidos(String param){
       if(param!=null){
           for (int i=0;i<param.length();i++)
           {
             char c = param.charAt(i);
             if ((c <= 47 && c!=10 && c!=13 && c!=64 && c>=91 && c<=96 && c>=123 && c<=191)){                     
                 param = param.substring(0,i)+param.substring(i+1,param.length());
                 i--;
             }       
                 
            
           }      
       }
       return param;
    }
    
    public static void imprimeCodigoASCII(String param){
            if(param!=null){
                for (int i=0;i<param.length();i++)
                {
                  int c = param.charAt(i);
                  System.out.println(c);
                }      
            }
            
    }
    
}
