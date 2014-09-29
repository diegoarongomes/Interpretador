class Variaveis{
	
	private String nome;
	private double valor;
	
	public Variaveis(){
		this.nome = "0";
		this.valor = 0;
	}
	
	public void setNome(String a){
		this.nome = a;
	}
	public String getNome(){
		return this.nome;
	}
	public void setValor(double a){
		this.valor = a;
	}
	public double getValor(){
		return this.valor;
	}
	public int nomePesquisa(Variaveis v[],int p, String n){
		int i;
		for (i=0;i<p;i++){
			if (v[i].nome == n){
				return i;
			}
		}
		return -1;
	}
	public void criaVariavel(String l){
		int i,j,k;
		i=l.lastIndexOf(";");
		for (j=i;(l.charAt(j)) != null ;j--){
			k=j;
		}
		this.nome = l.substring(k,i-1);
 	}
}
