package br.com.sandi.taggerRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

import br.com.sandi.entity.Projeto;

public interface Tagger extends Remote {
	public byte[] execute(Object obj, Projeto projeto)throws RemoteException;
	public String ok() throws RemoteException;
}
