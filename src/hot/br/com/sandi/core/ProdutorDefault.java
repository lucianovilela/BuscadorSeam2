package br.com.sandi.core;
import java.io.File;
import java.io.FilenameFilter;




/**
 * @author 
 *
 */
public class ProdutorDefault implements Produtor {

	
	String[] arquivos;
	int posicao=0;
	private String diretorioEntrada;
	private String diretorioEntradaMask;
	
	

	public void finish() {

	}

	public Object next() {
		String retorno = null;
		if(arquivos!= null && posicao<arquivos.length){
			retorno =  arquivos[posicao];
			posicao++;
		}
		return retorno;
	}


	public void startup() {
		//final Configuracao conf = Configuracao.getInstance();
		
		File dir = new File(diretorioEntrada);

		
		arquivos = dir.list(new FilenameFilter(){


			public boolean accept(File dir, String name) {
				return name.matches(diretorioEntradaMask);
			}
			
		});
		

	}

	public String getDiretorioEntrada() {
		return diretorioEntrada;
	}

	public void setDiretorioEntrada(String diretorioEntrada) {
		this.diretorioEntrada = diretorioEntrada;
	}

	public String getDiretorioEntradaMask() {
		return diretorioEntradaMask;
	}

	public void setDiretorioEntradaMask(String diretorioEntradaMask) {
		this.diretorioEntradaMask = diretorioEntradaMask;
	}

}
