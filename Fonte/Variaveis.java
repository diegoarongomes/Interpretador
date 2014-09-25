class Variaveis{
	
	private String nome;
	private double valor;
	
	public Variaveis(){
		nome = "0";
		valor = 0;
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
	public int valorPesquisa(Variaveis v[],int p,double x){
		int i;
		for(i=0;i<p;i++){
			if (v[i].valor = x){
				return i;
			}
		}
		return -1;
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
}
