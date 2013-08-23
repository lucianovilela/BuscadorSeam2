package br.com.sandi.taggerRMI;

import java.rmi.RemoteException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.international.StatusMessage.Severity;
import org.jboss.seam.international.StatusMessages;



@Name("taggerClient")
@Scope(ScopeType.CONVERSATION)
public class TaggerClient {
	
	@In
	private StatusMessages statusMessages;
	private String status;
	@Begin(join=true)
	public void run() {
            
			Context ic;
			try {
				ic = new InitialContext();
	            Object objref = ic.lookup("taggerService");
	            //System.out.println("Client: Obtained a ref. to Hello server.");
	            
	            Tagger t = (Tagger) PortableRemoteObject.narrow(objref, Tagger.class);
	            status = t.ok();

			} catch (NamingException e) {
				statusMessages.add(Severity.ERROR, e.getMessage());
				e.printStackTrace();
			} catch (RemoteException e) {
				
				statusMessages.add(Severity.ERROR, e.getMessage());
				e.printStackTrace();
			}
 	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
