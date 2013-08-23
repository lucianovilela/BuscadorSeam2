#!/usr/bin/perl

$CLASSPATH="/home/msandi/processamento/mxpost/mxpost.jar";
$PORT_PATH="/home/msandi/processamento/mxpost/port";
$ENTRADA="/home/msandi/processamento/entrada";
$SAIDA_TEMP="/home/msandi/processamento/temp";
open(ARQUIVOS, "find $ENTRADA -name \"*.txt\" | ") or die $?;
while(<ARQUIVOS>){
	chomp;
	$arq=$_;
	print "$arq\n";
	open(ARQ, "< $arq") or dir $?;
	$ARQ_TEMP="${arq}.tmp";
	$ARQ_SAIDA="${arq}.tag";
	open(TEMP,  "> $ARQ_TEMP") or die $?;
	while(<ARQ>){
		chomp;
		s/[\.\?\!\"\'\@\#\$\%\¨\&\*\(\)\+\=\_\}\]\{\[\/\\\:\>\<\`\,\´\º\ª]//g;
		print TEMP "$_\n";
		
	}
	close(ARQ);
	close(TEMP);
	qx{/usr/bin/java -Dfile.encoding=ISO-8859-1 -cp $CLASSPATH -Xmx3g tagger.TestTagger $PORT_PATH < $ARQ_TEMP > $ARQ_SAIDA};
	qx{rm -f $ARQ_TEMP};

}
close(ARQUIVOS);