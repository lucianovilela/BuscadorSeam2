package br.com.sandi.stopList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.sandi.core.Consumidor;
import br.com.sandi.entity.Projeto;

@Name("stopListConsumidor")
@Scope(ScopeType.BUSINESS_PROCESS)

public class StopListConsumidor implements Consumidor {

	List<String> padroes = null;
	//Configuracao config = null;
	Projeto projeto;

	public void execute(Object obj) {
		// System.err.println(this + " " + obj);
		try {
			if (obj != null) {
				BufferedReader in = new BufferedReader(new FileReader(projeto.getStopListDiretorioEntrada()+"/"+(String)obj));
				PrintWriter out = new PrintWriter( new FileWriter(projeto.getStopListDiretorioSaida()+"/"+(String)obj));
				String linha;
				while((linha = in.readLine())!= null){
					for(String padrao : padroes){
						if(padrao.length() == 1){
							linha = linha.replaceAll("\\"+padrao, " ");
						}
						else{
							linha = linha.replaceAll(padrao, " ");
						}
					}
					out.println(linha);
					
				}
				
				in.close();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void finish() {
		// TODO Auto-generated method stub

	}


	public void startup(Projeto p) {

		try {
			projeto = p;
			//config = Configuracao.getInstance();
			padroes = loadArquivo(projeto.getStopListArquivo());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private List<String> loadArquivo(String arq) throws IOException {
		List<String> result = new ArrayList<String>();
		BufferedReader in = new BufferedReader(new FileReader(arq));
		String linha = null;
		
		while ((linha = in.readLine()) != null) {
			result.add(linha);
		}

		return result;
	}
	

}
