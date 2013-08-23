#!/usr/bin/perl

while(<>){
	chomp;
	if($. == 1){
		@cab = split(",");
		
	}
	else{
		print "nomearquivo,termo,indice\n" if($. == 2);
		@col = split(",");
		for( $i=1; $i< @col; $i++){
			printf("%s,%s,%s\n", $col[0], $cab[$i], $col[$i]==0?"?": "1");
		}
	}

}