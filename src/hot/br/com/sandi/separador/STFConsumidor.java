package br.com.sandi.separador;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
import br.com.sandi.core.ExtrairSintagmas;
import br.com.sandi.core.ExtrairSintagmasImpl;
import br.com.sandi.entity.Projeto;

@Name("separadorConsumidor")
@Scope(ScopeType.BUSINESS_PROCESS)

public class STFConsumidor implements Consumidor {
	@SuppressWarnings("unchecked")
	List padroes = null;
//	Configuracao config = null;
	
	Projeto projeto;


	public void execute(Object obj) {
		// System.err.println(this + " " + obj);
		try {
			if (obj != null) {
				ExtrairSintagmas extrator = new ExtrairSintagmasImpl();
				String resultado = extrator.analisarSintagmas(padroes, projeto.getSeparadorDiretorioEntrada() + "/" + obj,
						projeto.getSeparadorQuantidadeTermos());
				extrator.gerarArquivoSintagmas(resultado, projeto.getSeparadorDiretorioSaida() + "/" + obj);
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
			padroes = loadArquivo(projeto.getSeparadorArquivoPadroes());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private List loadArquivo(String arq) throws IOException {
		List result = new ArrayList();
		BufferedReader in = new BufferedReader(new FileReader(arq));
		String linha = null;
		
		while ((linha = in.readLine()) != null) {
			result.add(linha);
		}
		/*int qtdTermos=20, contador=0;
		while ((linha = in.readLine()) != null) {
			StringTokenizer objTokenizador = new StringTokenizer(linha);
			while (objTokenizador.hasMoreTokens()) {
				result.add(objTokenizador.nextToken());
				if(contador == qtdTermos){
					break;
				} 
				contador++;
			}
			//result.add(linha);
		}*/
		return result;
	}



}
