package br.com.sandi.core;

import br.com.sandi.entity.Projeto;

/*
 * Created on 08/06/2005
 *
 */

/**
 * @author BT024318
 *
 */
public interface Consumidor {
	public void startup(Projeto projeto);
	public void execute(Object obj);
	public void finish();

}
