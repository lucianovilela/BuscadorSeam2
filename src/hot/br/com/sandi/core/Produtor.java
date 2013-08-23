package br.com.sandi.core;

public interface Produtor {
	public void startup();
	public Object next();
	public void finish();
}
