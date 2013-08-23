package br.com.sandi.tagger;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.sandi.core.Consumidor;
import br.com.sandi.entity.Projeto;
import br.com.sandi.taggerRMI.Tagger;

@Name("taggerConsumidor")
@Scope(ScopeType.BUSINESS_PROCESS)
public class TaggerConsumidor implements Consumidor {
	@SuppressWarnings("unchecked")
	List padroes = null;
	// Configuracao config = null;
	Projeto projeto;

	Context ic;
	Tagger t;

	public void execute(Object obj) {
		// System.err.println(this + " " + obj);

		try {
			byte[] status = t.execute(obj, projeto);
			if (status != null) {
				FileOutputStream f = new FileOutputStream(
						projeto.getSeparadorDiretorioEntrada() + "/" + obj);
				f.write(status);
				f.flush();
				f.close();
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
			// config = Configuracao.getInstance();
			projeto = p;
			ic = new InitialContext();
			Object objref = ic.lookup("taggerService");
			t = (Tagger) PortableRemoteObject.narrow(objref, Tagger.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private List loadArquivo(String arq) throws IOException {
		List result = new ArrayList();
		BufferedReader in = new BufferedReader(new FileReader(arq));
		String linha = null;
		// int qtdTermos=20, contador=0;
		while ((linha = in.readLine()) != null) {
			result.add(linha);
		}
		/*
		 * while ((linha = in.readLine()) != null) { StringTokenizer
		 * objTokenizador = new StringTokenizer(linha); while
		 * (objTokenizador.hasMoreTokens()) {
		 * result.add(objTokenizador.nextToken()); if(contador == qtdTermos){
		 * break; } contador++; } //result.add(linha); }
		 */

		return result;
	}

}
