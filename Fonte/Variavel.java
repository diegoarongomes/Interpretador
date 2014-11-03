/* Classe Variáveis, responsável por criar cada variável, setar e pegar valores do objeto variaveis,
 * além de possuir os metodos que executam os comandos do código interpretado.*/
class Variavel{
	
	private String nome;
	private double valor;
	
	public Variavel(){
		this.nome = new String();
		this.valor = 0.0;
	}
	
	public String getNome(){
		//Retorna nome do objeto que roda o código.
		return this.nome;
	}
	
	
	public void criaVariavel(String s1){
		//Método de criação de nova variável. Recebe a linha para ser interpretada e nela faz a leitura do nome da nova variável.
		String s = s1;
		int i,k;
		i=s.lastIndexOf(";");
		k=s.lastIndexOf(":");
		this.nome = s.substring(k+1,i);
 	}
	
	
	public void setValor(String s1){
		/* Método para inserir valores em uma variável. O método recebe uma string que contém 
		* o nome da variável e o valor a ser colocado nela.
		* Ele encontra o numero na string, transforma em double, e coloca na variável valor do objeto v[i]*/
		int j;
		double k;
		String s = s1;
		j = s.indexOf("=");			
		k = Double.parseDouble(s.substring(j+1,s.indexOf(";")));
		this.valor = k;
	}
	//Médodo para inserir valor no objeto que roda o código.
	public void setValor(double d){
		this.valor = d;
	}	
	
	public static int nomePesquisa(String s1,Variavel v[],int p){
		/* Método de pesquisa, recebe um vetor de variáveis, um int com o tamanho do vetor, 
		* e uma string a ser procurada. Retorna o indice do vetor em que esta a variável
		* ou -1 caso não encontre a string no vetor.*/
		int i,j=0,k=0;
		String s = s1;
		if(s.contains("Imprime") && s.contains("[")){
			j= s.indexOf(")");
			k= s.indexOf(",")+1;
		}		
		else if(s.contains("Imprime")||s.contains("Digite")||s.contains("Inicio.se")||s.contains("Senao")){
			j=s.indexOf(")");
			k=s.indexOf("(")+1;
		}
		else if (s.contains("=")){
			j = s.indexOf("=");
			k=0;
		}
		else if(s.contains("++")){
			j = s.indexOf("+");
			k=0;
		}
		else if(s.contains("--")){
			j = s.indexOf("-");
			k=0;
		}
		
			
		String n_busca = new String();
		n_busca = s.substring(k,j);
		for (i=0;i<p;i++){
			if (v[i].getNome().equals(n_busca)){
				return i;
			}
		}
		return -1;
	}
		
	public void operacao(char op,Variavel[] v1,int pos,String st){
		int i,achou;
		String s = st;
		double d[]= new double[2];
		achou=0;
		if (s.contains("++")){
			this.valor = this.valor + 1;
		}
		else if (s.contains("--")){
			this.valor = this.valor - 1;
		}
		else{
			for(i=0;i<pos;i++){
				if(s.substring(s.indexOf("=")+1,s.indexOf(op)).contains(v1[i].nome)){
					d[0]=v1[i].valor;
					achou++;
					break;
				}
			}
			if (achou==0) d[0]=Double.parseDouble(s.substring(s.indexOf("=")+1,s.indexOf(op)));
			
			achou=0;
			for(i=0;i<pos;i++){
				if(s.substring(s.indexOf(op)+1,s.indexOf(";")).contains(v1[i].nome)){
					d[1]=v1[i].valor;
					achou++;
					break;
				}
			}
			if (achou==0){ d[1]=Double.parseDouble(s.substring(s.indexOf(op)+1,s.indexOf(";")));}
			switch(op){
				case '+':
					this.valor=d[0]+d[1];
					break;
				case '-':
					this.valor=d[0]-d[1];
					break;
				case '*':
					this.valor=d[0]*d[1];
					break;
				case '/':
					this.valor=d[0]/d[1];
					break;
				case '%':
					int a=(int)d[0];
					int b=(int)d[1];
					this.valor=a%b;
					break;
			}
		}
	}
	public static boolean expressaoSe(String op,String s1,int pos,Variavel v1[]){
		int i,achou;
		double d[]= new double[2];
		achou=0;
	
		for(i=0;i<pos;i++){
			if(s1.substring(s1.indexOf("(")+1,s1.indexOf(op)).contains(v1[i].nome)){
				d[0]=v1[i].valor;
				achou++;
				break;
			}
		}
		if (achou==0) d[0]=Double.parseDouble(s1.substring(s1.indexOf("(")+1,s1.indexOf(op)));
		
		achou=0;
		for(i=0;i<pos;i++){
			if(s1.contains("=")){
				if(s1.substring(s1.lastIndexOf("=")+1,s1.indexOf(")")).contains(v1[i].nome)){
					d[1]=v1[i].valor;
					achou++;
					break;
				}
			}
			else {
				if(s1.substring(s1.indexOf(op)+1,s1.indexOf(")")).contains(v1[i].nome)){
					d[1]=v1[i].valor;
					achou++;
					break;
				}
			}
		}
		if (achou==0){
			if(s1.contains("=")){d[1]=Double.parseDouble(s1.substring(s1.lastIndexOf("=")+1,s1.indexOf(")")));}
			else {d[1]=Double.parseDouble(s1.substring(s1.indexOf(op)+1,s1.indexOf(")")));}
		}
		if (s1.contains(">=")) {return d[0]>=d[1];}
		else if (s1.contains("<=")) {return d[0]<=d[1];}
		else if (s1.contains(">")){return d[0]>d[1];}
		else if (s1.contains("<")){return d[0]<d[1];}
		else if (s1.contains("==")){return d[0]==d[1];}
		else if (s1.contains("!=")){return d[0]!=d[1];}	

		return false;
	}
	
	public void imprimeVariavel(String s1){
		//Imprissão de variáveis. Pode imprimir texte junto com a variável.
		String s = s1;
		if (s.contains("[")&&s.contains("]")&&s.contains(",")){
			System.out.print(s.substring((s.indexOf("[")+1),s.indexOf("]")));
			System.out.println(this.valor);
		}
		else
			System.out.println(this.valor);		
	}
	public static void imprimeTexto(String s1){
		System.out.println(s1.substring((s1.indexOf("[")+1),s1.indexOf("]")));
	}
}
