package br.com.sandi.session;

import java.util.Arrays;

import javax.persistence.EntityManager;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import br.com.sandi.entity.Projeto;

@Name("projetoList")
public class ProjetoList extends EntityQuery<Projeto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@In
	EntityManager entityManager;

	private static final String EJBQL = "select projeto from br.com.sandi.entity.Projeto projeto";

	private static final String[] RESTRICTIONS = {
			"lower(projeto.nome) like concat(lower(#{projetoList.projeto.nome}),'%')",
			"lower(projeto.separadorArquivoPadroes) like concat(lower(#{projetoList.projeto.separadorArquivoPadroes}),'%')",
			"lower(projeto.separadorClassConsumidor) like concat(lower(#{projetoList.projeto.separadorClassConsumidor}),'%')",
			"lower(projeto.separadorClassProdutor) like concat(lower(#{projetoList.projeto.separadorClassProdutor}),'%')",
			"lower(projeto.separadorDiretorioEntrada) like concat(lower(#{projetoList.projeto.separadorDiretorioEntrada}),'%')",
			"lower(projeto.separadorDiretorioSaida) like concat(lower(#{projetoList.projeto.separadorDiretorioSaida}),'%')",
			"lower(projeto.separadorMascara) like concat(lower(#{projetoList.projeto.separadorMascara}),'%')",
			"lower(projeto.stopListArquivo) like concat(lower(#{projetoList.projeto.stopListArquivo}),'%')",
			"lower(projeto.stopListClassConsumidor) like concat(lower(#{projetoList.projeto.stopListClassConsumidor}),'%')",
			"lower(projeto.stopListClassProdutor) like concat(lower(#{projetoList.projeto.stopListClassProdutor}),'%')",
			"lower(projeto.stopListDiretorioEntrada) like concat(lower(#{projetoList.projeto.stopListDiretorioEntrada}),'%')",
			"lower(projeto.stopListDiretorioSaida) like concat(lower(#{projetoList.projeto.stopListDiretorioSaida}),'%')",
			"lower(projeto.stopListMascara) like concat(lower(#{projetoList.projeto.stopListMascara}),'%')",
			"lower(projeto.taggerClassConsumidor) like concat(lower(#{projetoList.projeto.taggerClassConsumidor}),'%')",
			"lower(projeto.taggerClassProdutor) like concat(lower(#{projetoList.projeto.taggerClassProdutor}),'%')",
			"lower(projeto.taggerDiretorioEntrada) like concat(lower(#{projetoList.projeto.taggerDiretorioEntrada}),'%')",
			"lower(projeto.taggerDiretorioSaida) like concat(lower(#{projetoList.projeto.taggerDiretorioSaida}),'%')",
			"lower(projeto.taggerDiretorioTagger) like concat(lower(#{projetoList.projeto.taggerDiretorioTagger}),'%')",
			"lower(projeto.taggerMascara) like concat(lower(#{projetoList.projeto.taggerMascara}),'%')",};

	private Projeto projeto = new Projeto();

	public ProjetoList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	
	
	public Projeto getProjeto() {
		return projeto;
	}




}
