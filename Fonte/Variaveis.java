//Classe Variáveis, responsável por criar cada variável, setar e pegar valores do objeto variaveis,
//além de possuir os metodos que executam os comandos do código interpretado.
class Variaveis{
	
	private String nome;
	private double valor;
	
	public Variaveis(){
		this.nome = "0";
		this.valor = 0.0;
	}
	
	public void setNome(String a){
		this.nome = a;
	}
	
	public String getNome(){
		return this.nome;
	}
	//Método para inserir valores em uma variável. O método chama o método nomePesquisa para encontrar o indice da variável no vetor,
	//encontra o numero na string, transforma em double, e coloca na variável valor do objeto v[i]
	public void setValor(String s,Variaveis vet[],int p){
		int j;
		double k;
		String nome = new String();
		j = s.indexOf("=");		
		nome = s.substring(0,j);		
		int i = this.nomePesquisa(vet,p,nome);
		k = Double.parseDouble(s.substring(j+1,s.indexOf(";")));
		this[i].valor = k;
	}

	public double getValor(){
		return this.valor;
	}
	//Método de pesquisa, recebe um vetor de variáveis, um int com o tamanho do vetor, 
	//e uma string a ser procurada. Retorna o indice do vetor em que esta a variável
	//ou -1 caso não encontre a string no vetor.
	public int nomePesquisa(Variaveis v[],int p, String s){
		int i;
		for (i=0;i<p;i++){
			if (v[i].nome.compareTo(s) == 0){
				return i;
			}
		}
		return -1;
	}
	//Método de criação de nova variável. 
	public void criaVariavel(String l,Variaveis v[],int pos){
		int i,j,k;
		i=l.lastIndexOf(";");
		k=l.lastIndexOf(":");
		this.nome = l.substring(k+1,i);		
 	}
}
