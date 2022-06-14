import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;  
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;


//-------------------- Criação do nó 1 --------------------
class No {
	public char elemento; // Conteudo do no.
	public No esq; // No da esquerda.
	public No dir; // No da direita.
    public No2 outro;
	

	//Construtor da classe.
	No(char elemento) {
		this(elemento, null, null);
	}

	
	//Construtor da classe.
	No(char elemento, No esq, No dir) {
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
        this.outro = null;
	}
}


//-------------------- Criação do nó 2 --------------------
class No2 {
	public filme elemento; // Conteudo do no.
	public No2 esq; // No da esquerda.
	public No2 dir; // No da direita.
	
   
	//Construtor da classe.
	No2(filme elemento) {
		this(elemento, null, null);
	}

	
	//Construtor da classe.
	No2(filme elemento, No2 esq, No2 dir) {
		this.elemento = elemento.clonar();
		this.esq = esq;
		this.dir = dir;
	}
}


//-------------------- Arvore de Arvore --------------------
class ArvoreArvore {
	private No raiz; // Raiz da arvore.

	
	//Construtor da classe.
	public ArvoreArvore()throws Exception{
		raiz = null;
        inserir('D');
        inserir('R');
        inserir('Z');
        inserir('X');
        inserir('V');
        inserir('B');
        inserir('F');
        inserir('P');
        inserir('U');
        inserir('I');
        inserir('G');
        inserir('E');
        inserir('J');
        inserir('L');
        inserir('H');
        inserir('T');
        inserir('A');
        inserir('W');
        inserir('S');
        inserir('O');
        inserir('M');
        inserir('N');
        inserir('K');
        inserir('C');
        inserir('Y');
        inserir('Q');
	}


    //Inserir nó 1
    public void inserir(char letra)throws Exception{
       raiz = inserir(letra, raiz);
    }

    private No inserir(char x, No i) throws Exception {
		if (i == null) {
            i = new No(x);
        } 
        else if (x < i.elemento) {
            i.esq = inserir(x, i.esq);
        } 
        else if (x > i.elemento) {
            i.dir = inserir(x, i.dir);
        } 
        else {
            throw new Exception("Erro ao inserir!");
        }

		return i;
	}



    //Inserir nó 2
    public void inserir(filme x)throws Exception{ 
		inserir(x, raiz);
	}

	//Metodo privado recursivo para inserir elemento.
	private void inserir(filme x, No i) throws Exception{
		if (i == null) {
            throw new Exception("Erro ao inserir: caractere invalido!");
        } 
        else if(x.getTitulo().charAt(0) < i.elemento){
            inserir(x, i.esq);
        }   
        else if (x.getTitulo().charAt(0) > i.elemento){
            inserir(x, i.dir);
        }      
        else {
            i.outro = inserir(x, i.outro);
        }	
	}
   


	private No2 inserir(filme s, No2 i) throws Exception {
		if (i == null) {
            i = new No2(s);
        } 
        else if (s.getTitulo().compareTo(i.elemento.getTitulo()) < 0) {
            i.esq = inserir(s, i.esq);
        } 
        else if (s.getTitulo().compareTo(i.elemento.getTitulo()) > 0) {
            i.dir = inserir(s, i.dir);
        } 
        else {
            throw new Exception("Erro ao inserir: elemento existente!");
        }

		return i;
	}
	
	//Metodo publico iterativo para pesquisar elemento.
	public void pesquisar(String elemento){
        boolean resp = false;
        MyIO.println("=> " + elemento);
        MyIO.print("raiz");
		resp = pesquisar(raiz, elemento);
        if(resp == false){
            MyIO.println(" NAO ");
        }
        else if(resp == true){
            MyIO.println(" SIM ");
        }
	}

	private boolean pesquisar(No no, String x) {
        boolean resp = false;
		// if (no == null){
        //     MyIO.println("  NAO"); 
        //     resp = false;
        // } 
        // else if (x.charAt(0) < no.elemento){
        //     MyIO.print(" esq"); 
        //     resp = pesquisar(no.esq, x); 
        // } 
        // else if (x.charAt(0) > no.elemento){
        //     MyIO.print(" dir"); 
        //     resp = pesquisar(no.dir, x); 
        // } 
        // else { 
        //     resp = pesquisarSegundaArvore(no.outro, x); 
        // }
        
        if (no != null) {
			resp = pesquisarSegundaArvore(no.outro, x);// Conteudo do no.
			if(resp == false){
                resp =pesquisar(no.esq, x); // Elementos da esquerda.
            }
            if(resp == false){
                resp = pesquisar(no.dir, x); // Elementos da direita.
            }
			
		}

        return resp;
	}

	private boolean pesquisarSegundaArvore(No2 no, String x) {
        boolean resp;
		if (no == null) { 
            resp = false;
        } 
        else if (x.compareTo(no.elemento.getTitulo()) < 0){
            MyIO.print(" esq"); 
            resp = pesquisarSegundaArvore(no.esq, x); 
        } 
        else if (x.compareTo(no.elemento.getTitulo()) > 0){
            MyIO.print(" dir"); 
            resp = pesquisarSegundaArvore(no.dir, x); 
        } 
        else { 
            resp = true; 
        }
        return resp;
	}
}


class filme{
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String nome;
    private String titulo;
    private Date dataLancamento;
    private int duracao;
    private String genero;
    private String idioma;
    private String situacao;
    private float orcamento;
    private ArrayList<String> palavraChave;


    /* Construtores */
    public filme () {
        this.palavraChave = new ArrayList<String>();
    }

    public filme(String nome, String titulo, Date dataLancamento, int duracao, String genero, String idioma, String situacao, float orcamento){
        this.nome = nome;
        this.titulo = titulo;
        this.dataLancamento = dataLancamento;
        this.duracao = duracao;
        this.genero = genero;
        this.idioma = idioma;
        this.orcamento = orcamento;
        this.palavraChave = new ArrayList<String>();
    }

    /* gets e sets */
    public void setNome(String nome) {this.nome = nome;}
    public String getNome() {return nome;}

    public void setTitulo(String titulo) {this.titulo = titulo;}
    public String getTitulo() {return titulo;}

    public void setData(Date dataLancamento) {this.dataLancamento = dataLancamento;}
    public Date getData() {return dataLancamento;}
    public String getDataString() {return sdf.format(this.dataLancamento);}

    public void setDuracao(int duracao) {this.duracao = duracao;}
    public int getDuracao() {return duracao;}

    public void setGenero(String genero) {this.genero = genero;}
    public String getGenero() {return genero;}

    public void setIdioma(String idioma) {this.idioma = idioma;}
    public String getIdioma() {return idioma;}
    
    public void setSituacao(String situacao) {this.situacao = situacao;}
    public String getSituacao() {return situacao;}

    public void setOrcamento(float orcamento) {this.orcamento = orcamento;}
    public float getOrcamento() {return orcamento;}
    
    public void setPalavraChave(ArrayList<String> palavraChave) {this.palavraChave = palavraChave;}
    public ArrayList<String> getPalavraChave() {return palavraChave;}


    
    /*Clone*/
    public filme clonar(){
        filme clone = new filme();
        clone.nome = this.nome;
        clone.titulo = this.titulo; 
        clone.dataLancamento = this.dataLancamento;
        clone.duracao = this.duracao;
        clone.genero = this.genero;
        clone.idioma = this.idioma;
        clone.situacao = this.situacao;
        clone.orcamento = this.orcamento;
        clone. palavraChave = this.palavraChave;
        return clone;
    }


    /* Remove Tags */ 
    static String removeTags(String entrada){
        String resp = "";
        for(int i=0 ; i<entrada.length() ; i++){
            if(entrada.charAt(i) == '<'){
                while(entrada.charAt(i) != '>'){
                    i++;
                }
            }
            else if(entrada.charAt(i) == '&'){ 
                i++;
                while(entrada.charAt(i) != ';') i++;
                }
            else{
                resp += entrada.charAt(i);
            }
        }
        return resp;
    }
    
    


    /* Abrindo o arquivo  */
    public void readDados(String file){

        BufferedReader br;
        try{
            br = new BufferedReader(new FileReader("/tmp/filmes/" + file));   
            String linha = br.readLine();

            int titulo = 0;

            //Enquanto for diferente de null continua lendo o arquivo
            while(linha != null){


                //Achando o nome
                if(linha.contains("h2 class")){
                    linha = br.readLine().trim();
                    linha = removeTags(linha);
                    setNome(linha);
                }



                //Achando o titúlo
                if(linha.contains("class=\"wrap\"")){
                    linha = removeTags(linha.trim());
                    setTitulo(linha.substring(16, linha.length()).trim());
                    titulo ++;
                }

                //Achando a data
                if(linha.contains("span class=\"release\"")){
                    linha = br.readLine().trim();
                    linha = removeTags(linha);
                    try{
                    setData(sdf.parse(linha));
                    }
                    catch(Exception e){
                        
                    }
                }
                
            

                //Achando a duracao
               if(linha.contains("span class=\"runtime\"")){

                    //Leio a string e trato
                    linha = br.readLine();
                    linha = br.readLine().trim();
                    linha = removeTags(linha.trim());
                    linha = linha.replace("h","");
                    linha = linha.replace("m","");

                    int numberH;
                    int numberM;

                    //Declaro cada parte da String para hora e minuto
                    if(linha.length() > 3){
                        String minuto = "";
                        char hora = linha.charAt(0);
                        for(int i=2 ; i<4 ; i++){
                            minuto += linha.charAt(i);
                        }

                        //Transformo eles em inteiro
                        try{
                            numberH = Character.getNumericValue(hora);
                            numberM = Integer.parseInt(minuto);
                        }finally{}
                        numberH *= 60;
                        numberM += numberH;
                    }
                    

                    else if(linha.length() == 3){
                        String minuto = "";
                        char hora = linha.charAt(0);
                        for(int i=2 ; i<3 ; i++){
                            minuto += linha.charAt(i);
                        }
                        try{
                            numberH = Character.getNumericValue(hora);
                            numberM = Integer.parseInt(minuto);
                        }finally{}
                        numberH *= 60;
                        numberM += numberH;
                    }

                    else if(linha.length() == 2){
                        String minuto = "";
                        for(int i=0 ; i<2 ; i++){
                            minuto += linha.charAt(i);
                        }
                        try{
                            numberM = Integer.parseInt(minuto);
                        }finally{}

                    }
                    else{
                        String minuto = "";
                        for(int i=0 ; i<1 ; i++){
                            minuto += linha.charAt(i);
                        }
                        try{
                            numberM = Integer.parseInt(minuto);
                        }finally{}
                    }
            
                    setDuracao(numberM);
                }



                //Achando Genero
                if(linha.contains("span class=\"genres\"")){
                    linha = br.readLine();
                    linha = br.readLine().trim();
                    linha = removeTags(linha);
                    setGenero(linha);
                }



                //Achando idioma
                if(linha.contains("Idioma original")){
                    linha = removeTags(linha.trim());
                    setIdioma(linha.substring(16, linha.length()).trim());
                }



                //Achando situação
                if(linha.contains("<strong><bdi>Situação")){
                    linha = removeTags(linha.trim());
                    setSituacao(linha.substring(9, linha.length()).trim());
                }



                //Achando orcamento
                if(linha.contains("Orçamento")){
                    linha = removeTags(linha.trim());
                    linha = linha.replace("$","");
                    linha = linha.replace(",","");
                    float orcamento;
                    if(linha.charAt(10) == '-'){
                        orcamento = 0;
                    }
                    else{
                    orcamento = Float.parseFloat(linha.substring(10, linha.length()).trim());
                    }
                    setOrcamento(orcamento);
                }



                //Achando Palavra chave
                if(linha.contains("<section class=\"keywords right_column\">")){
                    this.palavraChave = new ArrayList<String>();
                    linha = br.readLine();
                    linha = br.readLine();

                    while(!linha.contains("</ul")){
                        linha = br.readLine();
                        if(linha==null){
                            break;
                        }
                        if(linha.contains("/keyword/")){
                            linha = removeTags(linha.trim());
                            this.palavraChave.add(linha);
                        }
                    }
                }

            linha = br.readLine();
            }
            if(titulo == 0){
                setTitulo(this.nome);
            }
            else{
                titulo --;
            }

        }

        catch(IOException e){e.printStackTrace();}
    }

    /* Criar vetor nomes */
    public void criaVetor(String file[], int i){

    }

    public void imprimeDados(){

        MyIO.println(getNome() + " " + getTitulo() + " "  + getDataString() + " "  + getDuracao() + " "  + getGenero() + " "  + getIdioma() + " "  + getSituacao() + " "  + getOrcamento() + " " + this.palavraChave);
    }

    


    public static void main (String args[]) throws Exception{
        MyIO.setCharset("utf-8");
        String nomeFilme = "";
        filme novoFilme = new filme();
        ArvoreArvore listaFilme = new ArvoreArvore();
        
        nomeFilme = MyIO.readLine();

        //Lendo os filmes e armazenando no array
        while(!nomeFilme.equals("FIM")){

            novoFilme.readDados(nomeFilme);
            listaFilme.inserir(novoFilme);

            novoFilme = new filme();
            nomeFilme = MyIO.readLine();
        }

        String tamanhoS = "";
        tamanhoS = MyIO.readLine();
        int tamanhoI = Integer.parseInt(tamanhoS);


        //Fazendo as aleterações 
        for(int i=0 ; i<tamanhoI ; i++){

            nomeFilme = MyIO.readLine();

            //Inserir I
            if(nomeFilme.charAt(0) == 'I'){
                nomeFilme = nomeFilme.substring(2);
            
                filme nome = new filme();
                nome.readDados(nomeFilme);
                listaFilme.inserir(nome);
            }
        }

        String pesquisaString = MyIO.readLine();

        while(!pesquisaString.equals("FIM")){
            
            listaFilme.pesquisar(pesquisaString);
            pesquisaString = MyIO.readLine();
        }
    }

}