class Interpretador{
	private String arquivo[];
	private Variaveis v[];
	
	public Interpretador(){
		this.v = new Variaveis[20];
	}
	
	public void interpretaCod (String linhas[]){
		int i,pos;
		pos=0;
		this.arquivo = linhas;
		
		for (i=0;i<linhas.length;i++){
			if (linhas[i].contains("var")){
				v[pos].criaVariavel(linhas[i]);
			}
			
			else if (linhas[i].contains("+")||linhas[i].contains("-")||linhas[i].contains("/")||linhas[i].contains("*")){
				
			}
			
			else if (linhas[i].contains("=")){
			
			}
		
		}
		
		
		
		
		
		
		
		
		
	}
	
	
}
