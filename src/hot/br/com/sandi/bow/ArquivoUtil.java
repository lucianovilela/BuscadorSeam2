package br.com.sandi.bow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class ArquivoUtil {


    public static File[] recuperarArquivos(String diretorio) {
        File dir = new File(diretorio);
        return  dir.listFiles();
    }
    
    
    public static void salvar(String arquivo, String conteudo, boolean adicionar)
    throws IOException {

            FileWriter fw = new FileWriter(arquivo, adicionar);

            fw.write(conteudo);
            
            fw.close();
    }
    
    
    public static String getConteudoArquivo(File arq) throws IOException {        
        if(!arq.isDirectory()){
            StringBuffer fileData = new StringBuffer(1000);
            BufferedReader reader = new BufferedReader(
                            new FileReader(arq));
            char[] buf = new char[1024];
            int numRead=0;
            while((numRead=reader.read(buf)) != -1){
                  String readData = String.valueOf(buf, 0, numRead);
                  fileData.append(readData);
                  buf = new char[1024];
            }
            reader.close();
            return fileData.toString();
        }        
        return null;
    }

    /*    String[] children = dir.list();
        if (children == null) {
            // Either dir does not exist or is not a directory
        } else {
            for (int i = 0; i < children.length; i++) {
                // Get filename of file or directory
                String filename = children[i];
            }
        }

        // It is also possible to filter the list of returned files.
        // This example does not return any files that start with `.'.
        FilenameFilter filter = new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return !name.startsWith(".");
                }
            };
        children = dir.list(filter);


        // The list of files can also be retrieved as File objects
        File[] files = dir.listFiles();

        // This filter only returns directories
        FileFilter fileFilter = new FileFilter() {
                public boolean accept(File file) {
                    return file.isDirectory();
                }
            };
        files = dir.listFiles(fileFilter);
    }
    */

}
