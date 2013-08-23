#!/bin/bash
JBOSS_LIB=/home/luciano/Documentos/DevTools/jboss-5.1.0.GA/client
CLASSPATH="/home/luciano/Ubuntu One/BuscadorSeam2/WebContent/WEB-INF/classes"
CLASSPATH=$CLASSPATH:"/home/luciano/Ubuntu One/BuscadorSeam2/WebContent/WEB-INF/dev"
CLASSPATH=$CLASSPATH:"/home/luciano/Ubuntu One/BuscadorSeam2/WebContent/WEB-INF/lib/mxpost.jar"
CLASSPATH=$CLASSPATH:"$JBOSS_LIB/jbossall-client.jar"

java -classpath "$CLASSPATH" -Dfile.encoding=ISO8859-1 br.com.sandi.taggerRMI.TaggerServer
