//Classe Interpretador.
//Esta classe recebe o vetor de strings da Main, e faz a decodificação linha por linha, em busca dos comandos a serem realizados. Ao encontrar algum comando válido
//chama a classe Variáveis, que trata as informações.
class Interpretador{
	private String arquivo[];
	private Variaveis v[]=new Variaveis[20];
	
	//Incialização do objeto
	public Interpretador(){
		int i;
		for (i=0;i<20;i++){
			v[i]=new Variaveis();
		}
	}
	
	//Método que faz a leitura linha por linha.
	public void interpretaCod (String linhas[]){
		int i,pos;
		pos=0;//Será o indice do vetor de variáveis.
		this.arquivo = linhas;
		
		for (i=0;i<=this.arquivo.length;i++){
			
			// Dentro do laço, selecionamos uma linha por vez para interpretar, em busca de Totens que auxiliem nos processos..
			// 1° SE. Caso a inha contenha a palavra Var, entende-se como criação de variável do tipo Var:a;
			if (this.arquivo[i].contains("Var")){
				v[pos].criaVariavel(this.arquivo[i]);
				pos++;
			}
			//2° SE. Caso a linha possua atribuição de valor do tipo a=2;
			else if (this.arquivo[i].contains("=")){
				int end;
				end = Variaveis.nomePesquisa(this.arquivo[i],v,pos);
				v[end].setValor(this.arquivo[i]);
			}
		}	
		
	}
	
	
}
