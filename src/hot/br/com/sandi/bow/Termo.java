package br.com.sandi.bow;

public class Termo implements Comparable{
    
    private String valor;
    private double relevancia;
    /*  
    public Termo(String tk) {
        String[] texto = tk.split("@");
        if(texto[0].length()!=0){
        	this.setValor(texto[0]);
        	this.setRelevancia(Utilitario.stringToDouble(texto[1]));
        }
    }*/
    
    public Termo(String tk) {
            String[] texto = tk.split("@");
            this.setValor(texto[0]);
            if(texto!=null && texto.length>1){
                this.setRelevancia(Utilitario.stringToDouble(texto[1]));
            }else{
                this.setRelevancia(0d);
            }
        }
    
    public Termo(String texto,double relev) {
        this.setValor(texto);
        this.setRelevancia(relev);
    }
    
    public boolean equals(Object o) {
        if( o instanceof String ){
            Termo tk = (Termo)o;
            return  LevenshteinDistance.saoSemelhantes(this.getValor(),tk.getValor());
        }
        return false;
    }
    
    

    public int compareTo(Object o) {
        Termo tk = (Termo)o;
        if( LevenshteinDistance.saoSemelhantes(this.getValor().trim(),tk.getValor().trim()) ){
        	System.out.printf("%s,",this.getValor().trim());
        	System.out.printf("%s\n",tk.getValor().trim());
            return 0;
        }
        return this.getValor().compareTo( tk.getValor().trim() );
    }


    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public void setRelevancia(double relevancia) {
        this.relevancia = relevancia;
    }

    public double getRelevancia() {
        return relevancia;
    }
}
