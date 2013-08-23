package br.com.sandi.core;

import java.io.Serializable;
import java.util.EventListener;
import java.util.EventObject;
import java.util.List;
import java.util.logging.Logger;

import org.ajax4jsf.event.PushEventListener;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.FlushModeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;

import br.com.sandi.bow.Gerador;
import br.com.sandi.entity.Projeto;
import br.com.sandi.separador.STFConsumidor;
import br.com.sandi.session.ProjetoList;
import br.com.sandi.stopList.StopListConsumidor;
import br.com.sandi.tagger.TaggerConsumidor;

@Name("delegateProdutorConsumidor")
@Scope(ScopeType.BUSINESS_PROCESS)
@AutoCreate
public class DelegateProdutorConsumidor implements Serializable{
    /**
	 * 
	 */
	
	
	
	@DataModel("listaProjetos")
	public List<Projeto> listaProjetos=null;
	
	@Out(required=false, value="projeto")
	@DataModelSelection("listaProjetos")
	private Projeto projeto;
	
	

	StopListConsumidor stopListConsumidor;

	
	@In(create=true)
	private ProjetoList projetoList;
	
	
	
	private static final long serialVersionUID = 1L;

	private Logger log ;
	
	

    private long contGeral = 0;
    private long contPeriodo = 0;
    private boolean continua = true;

    private Produtor produtor;
    
    private PushEventListener listener;
    
    @Factory("listaProjetos")
    public void returnListaProjetos(){
    	projetoList.setEjbql("select projeto from br.com.sandi.entity.Projeto projeto");
    	listaProjetos= projetoList.getResultList();
    }
    
    
    
    
    private synchronized Object next() {
		contGeral++;
		contPeriodo++;
		return produtor.next();
    }
    private synchronized void reset(){
		contPeriodo =0;
    }
    
    
    private Thread factoryThread(ThreadGroup tg, final Consumidor consumidor){
		Thread th =null;
		try {
			th = new Thread(tg, new Runnable() {
				//Consumidor consumidor =  consumidorClass.newInstance();
				public void run() {
					consumidor.startup(projeto);
					Object obj = null;
					while ((obj = next()) != null && continua) {
						consumidor.execute(obj);
						
					}
					consumidor.finish();
				}
			});
		} catch(Exception e){
			log.severe(e.getMessage());
    	}	
		return th;
    	 
    }
    
    public void exec(final int qtdConsumidor, Produtor produtor, Consumidor consumidor) throws Exception{
		long t0 = System.currentTimeMillis();
		log = Logger.getLogger(this.getClass().getName());
		this.produtor = produtor;
		
		produtor.startup();
        
        ThreadGroup tg = new ThreadGroup("Runners");
        for(int i=0; i< qtdConsumidor; i++){
                log.info("criado threads");
				Thread th = factoryThread(tg,  consumidor);
				th.start();
        }
//		Thread th = new Thread(new Runnable(){
//			public void run(){
//				while(true){
//					log.info("Geral : " + contGeral + " Periodo : " + contPeriodo);
//					reset();
//					try{
//						Thread.sleep(1000);
//						}catch(Exception e){};
//				}
//			}
//		});
//		th.setDaemon(true);
//		th.start();
//		
		
        while(tg.activeCount() > 0){
            try{
            	Thread.sleep(500);
            	}catch(Exception e){};
        }
        produtor.finish();
        log.info("Tempo : " + (System.currentTimeMillis() - t0));

    }
    
    private void runStopList() throws Exception{
    	//DelegateProdutorConsumidor sbp = new DelegateProdutorConsumidor();
		produtor = new ProdutorDefault();
		((ProdutorDefault) produtor).setDiretorioEntrada(projeto.getStopListDiretorioEntrada());
		((ProdutorDefault) produtor).setDiretorioEntradaMask(projeto.getStopListMascara());
		Consumidor consumidor =  new StopListConsumidor();
        exec(projeto.getStopListNumeroThreads(), produtor, consumidor);
    }
    
    
    private void runTagger() throws Exception{
    	//DelegateProdutorConsumidor sbp = new DelegateProdutorConsumidor();
		produtor = new ProdutorDefault();
		((ProdutorDefault) produtor).setDiretorioEntrada(projeto.getTaggerDiretorioEntrada());
		((ProdutorDefault) produtor).setDiretorioEntradaMask(projeto.getTaggerMascara());
		Consumidor consumidor =  new TaggerConsumidor();
        exec(projeto.getTaggerNumeroThreads(), produtor, consumidor);
    }
 
    private void runSeparador() throws Exception{
    	//DelegateProdutorConsumidor sbp = new DelegateProdutorConsumidor();
		produtor = new ProdutorDefault();
		((ProdutorDefault) produtor).setDiretorioEntrada(projeto.getSeparadorDiretorioEntrada());
		((ProdutorDefault) produtor).setDiretorioEntradaMask(projeto.getSeparadorMascara());
		Consumidor consumidor =  new STFConsumidor();
        exec(projeto.getSeparadorNumeroThreads(), produtor, consumidor);
    }
    
    private void runGerarBow() throws Exception{
    	Gerador gerador  = new Gerador();
    	gerador.iniciar(projeto);
    
    } 
    @SuppressWarnings("unchecked")
    @Begin(nested=true, flushMode=FlushModeType.AUTO)
    @End
    public void main(){
    	try{
	        projeto.setStatus("Stop List<br/>");
 	        runStopList();


	        projeto.setStatus(projeto.getStatus() + "Tagger<br/>");
//    		listener.onEvent(new EventObject(this));
	        runTagger();

	        projeto.setStatus(projeto.getStatus() + "Separador<br/>");
//    		listener.onEvent(new EventObject(this));
	        runSeparador();

	        projeto.setStatus(projeto.getStatus() + "Gerar Bow<br/>");
//    		listener.onEvent(new EventObject(this));
	        runGerarBow();
	        projeto.setStatus(projeto.getStatus() + "Finalizado<br/>");
//    		listener.onEvent(new EventObject(this));
	        
    	}
        catch(Exception e){
        	projeto.setStatus("Error");
        }
    	
    }

//
//    public void addListener(EventListener listener) {
//        synchronized (listener) {
//            if (this.listener != listener) {
//                this.listener = (PushEventListener) listener;
//            }
//        }
//    }




	public long getContGeral() {
		return contGeral;
	}




	public void setContGeral(long contGeral) {
		this.contGeral = contGeral;
	}




	public long getContPeriodo() {
		return contPeriodo;
	}




	public void setContPeriodo(long contPeriodo) {
		this.contPeriodo = contPeriodo;
	}


}