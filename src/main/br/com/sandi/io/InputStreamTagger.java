package br.com.sandi.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

public class InputStreamTagger extends InputStream {
	private FileInputStream instance;
	private static ThreadLocal<InputStreamTagger> local = new ThreadLocal<InputStreamTagger>();
	protected InputStreamTagger(String file) throws FileNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static synchronized InputStreamTagger getInstance(String file) throws IOException{
		InputStreamTagger obj = new InputStreamTagger(null);
		obj.instance = new FileInputStream(file);
		
		local.set(obj);
		return local.get();
	}
	


	
	
	@Override
	public int read() throws IOException {
		
		return local.get().instance.read();
	}

	@Override
	public void close() throws IOException {

		local.get().instance.close();
	}

	@Override
	public int available() throws IOException {
		return local.get().instance.available();
	}


	public FileChannel getChannel() {
		// TODO Auto-generated method stub
		return local.get().instance.getChannel();
	}

	@Override
	public  int read(byte[] b, int off, int len) throws IOException {

		return local.get().instance.read(b, off, len);
	}

	@Override
	public int read(byte[] b) throws IOException {
		// TODO Auto-generated method stub
		return local.get().instance.read(b);
	}

	@Override
	public long skip(long n) throws IOException {
		// TODO Auto-generated method stub
		return local.get().instance.skip(n);
	}

	@Override
	public synchronized void mark(int readlimit) {
		// TODO Auto-generated method stub
		local.get().instance.mark(readlimit);
	}

	@Override
	public boolean markSupported() {
		// TODO Auto-generated method stub
		return local.get().instance.markSupported();
	}

	@Override
	public synchronized void reset() throws IOException {
		// TODO Auto-generated method stub
		local.get().instance.reset();
	}

}
