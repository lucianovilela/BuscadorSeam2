package br.com.sandi.taggerRMI;

import java.io.PrintStream;
import java.rmi.RemoteException;
import java.util.Date;

import javax.rmi.PortableRemoteObject;

import tagger.TestTagger;
import br.com.sandi.entity.Projeto;
import br.com.sandi.io.ByteArrayOutputStreamTeste;
import br.com.sandi.io.InputStreamTeste;


public class TaggerImpl extends PortableRemoteObject implements Tagger {
	protected TaggerImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public String ok() throws RemoteException {
		System.out.println("Executando");
		return "ok " + new Date();
	}
	
	@Override
	public byte[] execute(Object obj, Projeto projeto)throws RemoteException {
		// System.err.println(this + " " + obj);
		ByteArrayOutputStreamTeste out;
		InputStreamTeste in;
		
		try {
			if (obj != null) {
				synchronized(System.class){
					in = InputStreamTeste.getInstance(projeto.getTaggerDiretorioEntrada() + "/" + obj);
					out = ByteArrayOutputStreamTeste.getInstance();
					
					System.setIn(in);
					System.setOut(new PrintStream(out));
					
				}
				
				TestTagger.main(new String[]{projeto.getTaggerDiretorioTagger()});
				
				
				in.close();
				out.close();
				return out.toByteArray();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
