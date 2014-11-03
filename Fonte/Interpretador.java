import java.util.Scanner;
//Classe Interpretador.
//Esta classe recebe o vetor de strings da Main, e faz a decodificação linha por linha, em busca dos comandos a serem realizados. Ao encontrar algum comando válido
//chama a classe Variáveis, que trata as informações.
class Interpretador{
	private String arquivo[];
	private Variavel v[]=new Variavel[20];
		
	//Método que faz a leitura linha por linha.
	public void interpretaCod (String linhas[]){
		this.arquivo = linhas;
		int i,pos,j,k,conta_laco;
		int []caso = new int[10];
		int []enquanto = new int[10];
		boolean []se = new boolean[10];
		conta_laco=0;//controla a criação dos Ifs
		k=0;//controla a criação dos Whiles
		j=0;//Controla os laços e condições.
		pos=0;//Será o indice do vetor de variáveis.		
		
		//O laço irá selecionar uma linha por ver para ser interpretada.
		for (i=0;i<=this.arquivo.length && this.arquivo[i]!= null;i++){
			if (this.arquivo[i].contains("//")) continue;
			//Retira os espaços da linha, exceto linhas com a palavra Imprime.
			if (!this.arquivo[i].contains("Imprime")){
				this.arquivo[i]=this.arquivo[i].replaceAll(" ","");
				this.arquivo[i]=this.arquivo[i].replaceAll("\t","");
			}
			// Dentro do laço, selecionamos uma linha por vez para interpretar, em busca de Totens que auxiliem nos processos..
			
			//1º SE. Caso encontre o inicio ou fim de uma condição "SE".
			if(this.arquivo[i].contains("Inicio.se")||this.arquivo[i].contains("Enquanto")){
				
				//Verifica o que está rodando prioritariamente: SE ou ENQUANTO.
				if(this.arquivo[i].contains("Enquanto")&&(k==1)&&(i!=enquanto[j-1])){
					k=0;
				}
				if (this.arquivo[i].contains("Inicio.se")){
					caso[j]=1;
					j++;
				}
				else if(this.arquivo[i].contains("Enquanto")&&k==0){
					caso[j]=2;
					enquanto[j]=i;
					k=1;
					j++;
				}
				//Resolve as expressões:
				if (arquivo[i].contains("<=")){
					se[j-1]=Variavel.expressaoSe("<=",arquivo[i],pos,v);
				}
				else if (arquivo[i].contains(">=")){
					se[j-1]=Variavel.expressaoSe(">=",arquivo[i],pos,v);
				}
				else if (arquivo[i].contains(">")){
					se[j-1]=Variavel.expressaoSe(">",arquivo[i],pos,v);
				}
				else if (arquivo[i].contains("<")){
					se[j-1]=Variavel.expressaoSe("<",arquivo[i],pos,v);
				}				
				else if (arquivo[i].contains("==")){
					se[j-1]=Variavel.expressaoSe("==",arquivo[i],pos,v);
				}
				else if (arquivo[i].contains("!=")){
					se[j-1]=Variavel.expressaoSe("!=",arquivo[i],pos,v);
				}
				if (se[j-1]) {
					continue;
				}
				else if(caso[j-1]==1){
					i++;
					for(i=i;i<this.arquivo.length;i++){
						if(this.arquivo[i].contains("Fim.se")){
							j--;
							break;
						}
					}
					continue;
				}
				else if(caso[j-1]==2){
					conta_laco=0;
					i++;
					for(i=i;i<this.arquivo.length;i++){
						if((this.arquivo[i].contains("Enquanto"))){conta_laco++;}
						else if(this.arquivo[i].contains("Fim.enquanto")&&conta_laco>0){conta_laco--;}
						else if(this.arquivo[i].contains("Fim.enquanto")&&conta_laco==0){
							j--;k=0;
							break;
						}
					}
					continue;
				}			
			}
			
			else if (this.arquivo[i].contains("Senao")&&(!se[j])){
				continue;
			}
			else if(this.arquivo[i].contains("Senao")&&(se[j])){
				i++;
				for(i=i;i<this.arquivo.length;i++){
					if(this.arquivo[i].contains("Fim.senao"))break;
				}
				continue;
			}
			else if(this.arquivo[i].contains("Fim.senao")){
				continue;
			}	
			else if (this.arquivo[i].contains("Fim.se")){
				j--;
			}
			else if(this.arquivo[i].contains("Fim.enquanto")&&(se[j-1])){
				i=enquanto[j-1]-1;
				continue;
			}
			else if(this.arquivo[i].contains("Quebra.laco")){
				conta_laco=0;
				i++;
				for(i=i;i<this.arquivo.length;i++){
					if((this.arquivo[i].contains("Enquanto"))){conta_laco++;}
					else if(this.arquivo[i].contains("Fim.enquanto")&&conta_laco>0){conta_laco--;}
					else if(this.arquivo[i].contains("Fim.enquanto")&&conta_laco==0){
						j--;k=0;
						break;
					}
				}
				continue;
			}
					
			
			// 2° SE. Caso a linha contenha a palavra Var, entende-se como criação de variável do tipo Var:nome;
			else if (this.arquivo[i].contains("Var")){
				v[pos]=new Variavel();
				v[pos].criaVariavel(this.arquivo[i]);
				pos++;
			}
			
			// 3º SE. Caso a linha contenha atribuição de expressões à uma variável.
			else if ((this.arquivo[i].contains("=")||this.arquivo[i].contains("++")) && (this.arquivo[i].contains("+") || this.arquivo[i].contains("-") ||
			this.arquivo[i].contains("/") || this.arquivo[i].contains("*")||this.arquivo[i].contains("%"))){
				int end;
				end = Variavel.nomePesquisa(this.arquivo[i],v,pos);
				if(this.arquivo[i].contains("++")){
					v[end].operacao('I',v,pos,arquivo[i]);
				}
				else if (this.arquivo[i].contains("+")){
					v[end].operacao('+',v,pos,arquivo[i]);
				}
				else if(this.arquivo[i].contains("-")){
					v[end].operacao('-',v,pos,arquivo[i]);
				}
				else if (this.arquivo[i].contains("*")){
					v[end].operacao('*',v,pos,arquivo[i]);
				}
				else if (this.arquivo[i].contains("/")){
					v[end].operacao('/',v,pos,arquivo[i]);
				}
				else if (this.arquivo[i].contains("%")){
					v[end].operacao('%',v,pos,arquivo[i]);
				}
			}	
			
			// 4° SE. Caso a linha possua atribuição de valor do tipo a=2;
			else if (this.arquivo[i].contains("=")){
				int end;
				end = Variavel.nomePesquisa(this.arquivo[i],v,pos);
				if (end == -1) break;
				v[end].setValor(this.arquivo[i]);
			}
			// 5º SE. Caso encontre a ordem de imprimir.
			else if (this.arquivo[i].contains("Imprime")) {
				if (!this.arquivo[i].contains("],")&&this.arquivo[i].contains("[")) {Variavel.imprimeTexto(this.arquivo[i]);}
				else{
					int end;
					end = Variavel.nomePesquisa(this.arquivo[i],v,pos);
					v[end].imprimeVariavel(this.arquivo[i]);	
				}
			}
			 // 6° SE. Caso encontre o comando de entrada de valor pelo teclado;
			else if(this.arquivo[i].contains("Digite")){
				int end;
				end = Variavel.nomePesquisa(this.arquivo[i],v,pos);
				Scanner sc1 = new Scanner(System.in);
				double valor = sc1.nextDouble();
				v[end].setValor(valor);
			}
			
			
			//Caso seja encontrado fim_do_programa, o interpretador finaliza a leitura do arquivo.
			else if (this.arquivo[i].contains("Fim.do.programa")){
				break;
			}
		}			
	}	
}
