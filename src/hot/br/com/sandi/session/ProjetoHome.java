package br.com.sandi.session;

import br.com.sandi.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("projetoHome")
public class ProjetoHome extends EntityHome<Projeto> {

	public void setProjetoId(Integer id) {
		setId(id);
	}

	public Integer getProjetoId() {
		return (Integer) getId();
	}

	@Override
	protected Projeto createInstance() {
		Projeto projeto = new Projeto();
		return projeto;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public Projeto getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
