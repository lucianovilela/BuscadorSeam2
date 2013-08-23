package br.com.sandi.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

public class OutputStreamTagger extends OutputStream {
	private static ThreadLocal<OutputStreamTagger> local = new ThreadLocal<OutputStreamTagger>();
	private FileOutputStream instance;
	public static synchronized OutputStreamTagger getInstance(String file) throws IOException{
		OutputStreamTagger obj = new OutputStreamTagger();
		obj.instance = new FileOutputStream(file);
		
		local.set(obj);
		return local.get();
	}
	
	protected OutputStreamTagger() throws FileNotFoundException {
		super();
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		local.get().instance.close();
	}


	public FileChannel getChannel() {
		// TODO Auto-generated method stub
		return local.get().instance.getChannel();
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		// TODO Auto-generated method stub
		local.get().instance.write(b, off, len);
	}

	@Override
	public void write(byte[] b) throws IOException {
		// TODO Auto-generated method stub
		local.get().instance.write(b);
	}

	@Override
	public void write(int b) throws IOException {
		// TODO Auto-generated method stub
		local.get().instance.write(b);
	}

	@Override
	public void flush() throws IOException {
		// TODO Auto-generated method stub
		local.get().instance.flush();
	}
	


}
