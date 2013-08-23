package br.com.sandi.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;


@Entity
@Table(name="projeto")
@Scope(ScopeType.CONVERSATION)

public class Projeto implements Serializable{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	private String nome;
	private Date dataCriacao;
	private String stopListClassProdutor;
	private String stopListClassConsumidor;
	private int stopListNumeroThreads;
	
	private Boolean stopListExec;
	private String stopListDiretorioEntrada;
	private String stopListDiretorioSaida;
	private String stopListMascara;
	private String stopListArquivo;

	private Boolean separadorExec;
	private String separadorClassProdutor;
	private String separadorClassConsumidor;
	private int separadorNumeroThreads;
	private String separadorDiretorioEntrada;
	private String separadorDiretorioSaida;
	private String separadorMascara;    
    
	private String separadorArquivoPadroes;
	private int separadorQuantidadeTermos;
	
	private Boolean taggerExec;
	private String taggerClassProdutor;
	private String taggerClassConsumidor;
	private int taggerNumeroThreads;
	private String taggerDiretorioEntrada;
	private String taggerDiretorioSaida;
	private String taggerMascara;
	private String taggerDiretorioTagger;

	private Boolean bowExec;
	private Integer bowFuncao=1;
	private String bowDiretorioEntrada;
	private String bowDiretorioSaida;
	private Double bowSemelhanca=0d;
	
	
	@Transient
	private String status;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public String getStopListClassProdutor() {
		return stopListClassProdutor;
	}
	public void setStopListClassProdutor(String stopListClassProdutor) {
		this.stopListClassProdutor = stopListClassProdutor;
	}
	public String getStopListClassConsumidor() {
		return stopListClassConsumidor;
	}
	public void setStopListClassConsumidor(String stopListClassConsumidor) {
		this.stopListClassConsumidor = stopListClassConsumidor;
	}
	public int getStopListNumeroThreads() {
		return stopListNumeroThreads;
	}
	public void setStopListNumeroThreads(int stopListNumeroThreads) {
		this.stopListNumeroThreads = stopListNumeroThreads;
	}
	public String getStopListDiretorioEntrada() {
		return stopListDiretorioEntrada;
	}
	public void setStopListDiretorioEntrada(String stopListDiretorioEntrada) {
		this.stopListDiretorioEntrada = stopListDiretorioEntrada;
	}
	public String getStopListDiretorioSaida() {
		return stopListDiretorioSaida;
	}
	public void setStopListDiretorioSaida(String stopListDiretorioSaida) {
		this.stopListDiretorioSaida = stopListDiretorioSaida;
	}
	public String getStopListMascara() {
		return stopListMascara;
	}
	public void setStopListMascara(String stopListMascara) {
		this.stopListMascara = stopListMascara;
	}
	public String getStopListArquivo() {
		return stopListArquivo;
	}
	public void setStopListArquivo(String stopListArquivo) {
		this.stopListArquivo = stopListArquivo;
	}
	public String getSeparadorClassProdutor() {
		return separadorClassProdutor;
	}
	public void setSeparadorClassProdutor(String separadorClassProdutor) {
		this.separadorClassProdutor = separadorClassProdutor;
	}
	public String getSeparadorClassConsumidor() {
		return separadorClassConsumidor;
	}
	public void setSeparadorClassConsumidor(String separadorClassConsumidor) {
		this.separadorClassConsumidor = separadorClassConsumidor;
	}
	public int getSeparadorNumeroThreads() {
		return separadorNumeroThreads;
	}
	public void setSeparadorNumeroThreads(int separadorNumeroThreads) {
		this.separadorNumeroThreads = separadorNumeroThreads;
	}
	public String getSeparadorDiretorioEntrada() {
		return separadorDiretorioEntrada;
	}
	public void setSeparadorDiretorioEntrada(String separadorDiretorioEntrada) {
		this.separadorDiretorioEntrada = separadorDiretorioEntrada;
	}
	public String getSeparadorDiretorioSaida() {
		return separadorDiretorioSaida;
	}
	public void setSeparadorDiretorioSaida(String separadorDiretorioSaida) {
		this.separadorDiretorioSaida = separadorDiretorioSaida;
	}
	public String getSeparadorMascara() {
		return separadorMascara;
	}
	public void setSeparadorMascara(String separadorMascara) {
		this.separadorMascara = separadorMascara;
	}
	public String getSeparadorArquivoPadroes() {
		return separadorArquivoPadroes;
	}
	public void setSeparadorArquivoPadroes(String separadorArquivoPadroes) {
		this.separadorArquivoPadroes = separadorArquivoPadroes;
	}
	public int getSeparadorQuantidadeTermos() {
		return separadorQuantidadeTermos;
	}
	public void setSeparadorQuantidadeTermos(int separadorQuantidadeTermos) {
		this.separadorQuantidadeTermos = separadorQuantidadeTermos;
	}
	public String getTaggerClassProdutor() {
		return taggerClassProdutor;
	}
	public void setTaggerClassProdutor(String taggerClassProdutor) {
		this.taggerClassProdutor = taggerClassProdutor;
	}
	public String getTaggerClassConsumidor() {
		return taggerClassConsumidor;
	}
	public void setTaggerClassConsumidor(String taggerClassConsumidor) {
		this.taggerClassConsumidor = taggerClassConsumidor;
	}
	public int getTaggerNumeroThreads() {
		return taggerNumeroThreads;
	}
	public void setTaggerNumeroThreads(int taggerNumeroThreads) {
		this.taggerNumeroThreads = taggerNumeroThreads;
	}
	public String getTaggerDiretorioEntrada() {
		return taggerDiretorioEntrada;
	}
	public void setTaggerDiretorioEntrada(String taggerDiretorioEntrada) {
		this.taggerDiretorioEntrada = taggerDiretorioEntrada;
	}
	public String getTaggerDiretorioSaida() {
		return taggerDiretorioSaida;
	}
	public void setTaggerDiretorioSaida(String taggerDiretorioSaida) {
		this.taggerDiretorioSaida = taggerDiretorioSaida;
	}
	public String getTaggerMascara() {
		return taggerMascara;
	}
	public void setTaggerMascara(String taggerMascara) {
		this.taggerMascara = taggerMascara;
	}
	public String getTaggerDiretorioTagger() {
		return taggerDiretorioTagger;
	}
	public void setTaggerDiretorioTagger(String taggerDiretorioTagger) {
		this.taggerDiretorioTagger = taggerDiretorioTagger;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status==null?"Não Iniciado":status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getBowFuncao() {
		return bowFuncao;
	}
	public void setBowFuncao(Integer bowFuncacao) {
		this.bowFuncao = bowFuncacao;
	}
	public String getBowDiretorioEntrada() {
		return bowDiretorioEntrada;
	}
	public void setBowDiretorioEntrada(String bowDiretorioEntrada) {
		this.bowDiretorioEntrada = bowDiretorioEntrada;
	}
	public String getBowDiretorioSaida() {
		return bowDiretorioSaida;
	}
	public void setBowDiretorioSaida(String bowDiretorioSaida) {
		this.bowDiretorioSaida = bowDiretorioSaida;
	}
	public Double getBowSemelhanca() {
		return bowSemelhanca;
	}
	public void setBowSemelhanca(Double bowSemelhanca) {
		this.bowSemelhanca = bowSemelhanca;
	}
	public Boolean getStopListExec() {
		return stopListExec;
	}
	public void setStopListExec(Boolean stopListExec) {
		this.stopListExec = stopListExec;
	}
	public Boolean getSeparadorExec() {
		return separadorExec;
	}
	public void setSeparadorExec(Boolean separadorExec) {
		this.separadorExec = separadorExec;
	}
	public Boolean getTaggerExec() {
		return taggerExec;
	}
	public void setTaggerExec(Boolean taggerExec) {
		this.taggerExec = taggerExec;
	}
	public Boolean getBowExec() {
		return bowExec;
	}
	public void setBowExec(Boolean bowExec) {
		this.bowExec = bowExec;
	}
	
}
