//Classe Interpretador.
//Esta classe recebe o vetor de strings da Main, e faz a decodificação linha por linha, em busca dos comandos a serem realizados. Ao encontrar algum comando válido
//chama a classe Variáveis, que trata as informações.
class Interpretador{
	private String arquivo[];
	private Variaveis v[]=new Variaveis[20];
		
	//Método que faz a leitura linha por linha.
	public void interpretaCod (String linhas[]){
		this.arquivo = linhas;
		int i,pos;
		pos=0;//Será o indice do vetor de variáveis.		
		
		for (i=0;i<=this.arquivo.length && this.arquivo[i]!= null;i++){
			// Dentro do laço, selecionamos uma linha por vez para interpretar, em busca de Totens que auxiliem nos processos..
			
			//1º SE. Caso encontre o inicio ou fim de uma condição "SE".
			if(this.arquivo[i].contains("Inicio.se")){
				boolean se=false;
				if (arquivo[i].contains("<=")){
					se=Variaveis.expressaoSe("<=",arquivo[i],pos,v);
				}
				else if (arquivo[i].contains(">=")){
					se=Variaveis.expressaoSe(">=",arquivo[i],pos,v);
				}
				else if (arquivo[i].contains(">")){
					se=Variaveis.expressaoSe(">",arquivo[i],pos,v);
				}
				else if (arquivo[i].contains("<")){
					se=Variaveis.expressaoSe("<",arquivo[i],pos,v);
				}				
				else if (arquivo[i].contains("==")){
					se=Variaveis.expressaoSe("==",arquivo[i],pos,v);
				}
				else if (arquivo[i].contains("!=")){
					se=Variaveis.expressaoSe("!=",arquivo[i],pos,v);
				}
				if (se) {continue;}
				else{
					i++;
					for(i=i;i<this.arquivo.length;i++){
						if(this.arquivo[i].contains("Fim.se")){break;}
					}
				}
			}
			else if(this.arquivo[i].contains("Fim.se")){
				continue;
			}		
			
			// 2° SE. Caso a linha contenha a palavra Var, entende-se como criação de variável do tipo Var:nome;
			else if (this.arquivo[i].contains("Var")){
				v[pos]=new Variaveis();
				v[pos].criaVariavel(this.arquivo[i]);
				pos++;
			}
			
			// 3º SE. Caso a linha contenha atribuição de expressões à uma variável.
			else if (this.arquivo[i].contains("=") && (this.arquivo[i].contains("+") || this.arquivo[i].contains("-") ||
			this.arquivo[i].contains("/") || this.arquivo[i].contains("*"))){
				int end;
				end = Variaveis.nomePesquisa(this.arquivo[i],v,pos);
				if (arquivo[i].contains("+")){
					v[end].operacao('+',v,pos,arquivo[i]);
				}
				if (arquivo[i].contains("-")){
					v[end].operacao('-',v,pos,arquivo[i]);
				}
				if (arquivo[i].contains("*")){
					v[end].operacao('*',v,pos,arquivo[i]);
				}
				if (arquivo[i].contains("/")){
					v[end].operacao('/',v,pos,arquivo[i]);
				}
			}	
			
			// 4° SE. Caso a linha possua atribuição de valor do tipo a=2;
			else if (this.arquivo[i].contains("=")){
				int end;
				end = Variaveis.nomePesquisa(this.arquivo[i],v,pos);
				if (end == -1) break;
				v[end].setValor(this.arquivo[i]);
			}
			// 5º SE. Caso encontre a ordem de imprimir.
			else if (this.arquivo[i].contains("Imprime")) {
				int end;
				end = Variaveis.nomePesquisa(this.arquivo[i],v,pos);
				if (end == -1) break;
				v[end].imprimeVariavel(this.arquivo[i]);
			}
			
			
			//Caso seja encontrado fim_do_programa, o interpretador finaliza a leitura do arquivo.
			else if (this.arquivo[i].contains("fim_do_programa")){
				break;
			}
		}			
	}	
}
