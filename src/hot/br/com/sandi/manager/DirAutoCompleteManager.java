package br.com.sandi.manager;

import java.io.File;
import java.io.FileFilter;

import org.jboss.seam.annotations.Name;

@Name("dirAutoComplete")
public class DirAutoCompleteManager {
	public String[] sugestionDir(Object obj){
		final String str = ((String)obj).trim();
		File f = new File(str);
		File parent = f.getParentFile();
		final String nome = f.getName();
		if(parent== null){
			parent = new File(File.separator);
		}
		File[] a = new File[0];
		try{
		a=  parent.listFiles(new FileFilter(){
			@Override
			public boolean accept(File arg0) {
				if(arg0.isDirectory()){
					
					return arg0.getName().matches(nome+".*$");
				}
				return false;
			}
			
			
		});
		}
		catch(Exception e){
			
		}
		String[] result;
		if(a.length>0){
			result = new String[a.length];
			int i=0;
			for(File s : a){
				result[i++]=s.getAbsolutePath();
			}
		}
		else{
			result = new String[1];
			result[0] = str;
		}

		return result;
		
	}
	public String[] sugestionFile(Object obj){
		final String str = ((String)obj).trim();
		File f = new File(str);
		File parent = f.getParentFile();
		final String nome = f.getName();
		if(parent== null){
			parent = new File(File.separator);
		}
		File[] a = new File[0];
		try{
		a=  parent.listFiles(new FileFilter(){
			@Override
			public boolean accept(File arg0) {
				if(arg0.isFile()){
					
					return arg0.getName().matches(nome+".*$");
				}
				return false;
			}
			
			
		});
		}
		catch(Exception e){
			
		}
		String[] result;
		if(a.length>0){
			result = new String[a.length];
			int i=0;
			for(File s : a){
				result[i++]=s.getAbsolutePath();
			}
		}
		else{
			result = new String[1];
			result[0] = str;
		}

		return result;
		
	}	
	
}
