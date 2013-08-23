package br.com.sandi.taggerRMI;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;
@Name("taggerServer")
@Scope(ScopeType.APPLICATION)
@Startup()
@AutoCreate
public class TaggerServer {
	@Create
	public void server() throws Exception{
		Hashtable environment = new Hashtable();
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
        environment.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
        environment.put(Context.PROVIDER_URL, "jnp://localhost:1099"); // remote machine IP
        		
        //System.setProperty("java.security.policy", "client.policy");
        //System.setSecurityManager(new RMISecurityManager());
		Tagger t = new TaggerImpl();
		//System.out.println("Criando Tagger");
		Context c = new InitialContext(environment);
		//System.out.println("Registrando Tagger");
		c.rebind("taggerService", t);

		
		//System.out.println("Esperando Conexoes");
	}

	
	public static void main(String[] args) throws Exception{
		new TaggerServer().server();
	}
}
