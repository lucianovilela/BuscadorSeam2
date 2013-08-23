package br.com.sandi.io;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

public class ByteArrayOutputStreamTeste extends OutputStream {
	private static ThreadLocal<ByteArrayOutputStreamTeste> local = new ThreadLocal<ByteArrayOutputStreamTeste>();
	private ByteArrayOutputStream instance;
	public static synchronized ByteArrayOutputStreamTeste getInstance() throws IOException{
		ByteArrayOutputStreamTeste obj = new ByteArrayOutputStreamTeste();
		obj.instance = new ByteArrayOutputStream();
		
		local.set(obj);
		return local.get();
	}
	
	protected ByteArrayOutputStreamTeste() throws FileNotFoundException {
		super();
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		local.get().instance.close();
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

	public synchronized void writeTo(OutputStream out) throws IOException {
		// TODO Auto-generated method stub
		local.get().instance.writeTo(out);
	}

	public synchronized void reset() {
		// TODO Auto-generated method stub
		local.get().instance.reset();
	}

	public synchronized byte[] toByteArray() {
		// TODO Auto-generated method stub
		return local.get().instance.toByteArray();
	}

	public synchronized int size() {
		// TODO Auto-generated method stub
		return local.get().instance.size();
	}
	


}
