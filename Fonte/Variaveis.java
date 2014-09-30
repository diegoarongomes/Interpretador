//Classe Variáveis, responsável por criar cada variável, setar e pegar valores do objeto variaveis,
//além de possuir os metodos que executam os comandos do código interpretado.
class Variaveis{
	
	private String nome;
	private double valor;
	
	public Variaveis(){
		this.nome = "0";
		this.valor = 0.0;
	}
	
	public String getNome(){
		return this.nome;
	}
	
	//Método de criação de nova variável. 
	public void criaVariavel(String l){
		int i,j,k;
		i=l.lastIndexOf(";");
		k=l.lastIndexOf(":");
		this.nome = l.substring(k+1,i);
		System.out.println("Nome:"+this.nome+" Valor:" +this.valor);		
 	}
	
	//Método para inserir valores em uma variável. O método chama o método nomePesquisa para encontrar o indice da variável no vetor,
	//encontra o numero na string, transforma em double, e coloca na variável valor do objeto v[i]
	public void setValor(String s){
		int j;
		double k;
		j = s.indexOf("=");			
		k = Double.parseDouble(s.substring(j+1,s.indexOf(";")));
		this.valor = k;
		System.out.println("Nome:"+this.nome+" Valor:" +this.valor);
	}
	
	//Método de pesquisa, recebe um vetor de variáveis, um int com o tamanho do vetor, 
	//e uma string a ser procurada. Retorna o indice do vetor em que esta a variável
	//ou -1 caso não encontre a string no vetor.
	public static int nomePesquisa(String s,Variaveis v[],int p){
		int i,j;
		j = s.indexOf("=");		
		String n_busca = new String();
		n_busca = s.substring(0,j);
		String nome;
		for (i=0;i<p;i++){
			nome = v[i].getNome();
			if (nome.compareTo(n_busca) == 0){
				break;
			}
		}
		return i;
	}
	
}
