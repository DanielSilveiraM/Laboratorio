import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;  
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;


//-------------------- Criação do nó --------------------
class No {
    public filme elemento; // Conteudo do no.
    public No esq, dir;  // Filhos da esq e dir.

    
    //Construtor da classe.
    public No(filme elemento){
    }

    
    //Construtor da classe.
    public No(filme elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}





//-------------------- Arvore binaria de pesquisa --------------------
public class ArvoreBinaria {
	private No raiz; // Raiz da arvore.

	//Construtor da classe.
	public ArvoreBinaria() {
		raiz = null;
	}


	// Metodo publico iterativo para pesquisar elemento.
	public boolean pesquisar(filme x) {
		return pesquisar(x, raiz);
	}

	
	//Metodo privado recursivo para pesquisar elemento.
	private boolean pesquisar(filme x, No i) {
      boolean resp;
		if (i == null) {
         resp = false;

      } else if (x.getTitulo().compareTo(i.elemento.getTitulo()) == i.elemento.getTitulo().compareTo(x.getTitulo()) {
         resp = true;

      } else if (x.getTitulo().compareTo(i.elemento.getTitulo()) < i.elemento.getTitulo().compareTo(x.getTitulo()) {
         resp = pesquisar(x, i.esq);

      } else {
         resp = pesquisar(x, i.dir);
      }
      return resp;
	}

	
	//Metodo publico iterativo para exibir elementos.
	public void caminharCentral() {
		System.out.print("[ ");
		caminharCentral(raiz);
		System.out.println("]");
	}

	
	//Metodo privado recursivo para exibir elementos.
	private void caminharCentral(No i) {
		if (i != null) {
			caminharCentral(i.esq); // Elementos da esquerda.
			System.out.print(i.elemento.getTitulo() + " "); // Conteudo do no.
			caminharCentral(i.dir); // Elementos da direita.
		}
	}

	
	//Metodo publico iterativo para exibir elementos.
	public void caminharPre() {
		System.out.print("[ ");
		caminharPre(raiz);
		System.out.println("]");
	}

	
	//Metodo privado recursivo para exibir elementos.
	private void caminharPre(No i) {
		if (i != null) {
			System.out.print(i.elemento.getTitulo() + " "); // Conteudo do no.
			caminharPre(i.esq); // Elementos da esquerda.
			caminharPre(i.dir); // Elementos da direita.
		}
	}


	//Metodo publico iterativo para exibir elementos.
	public void caminharPos() {
		System.out.print("[ ");
		caminharPos(raiz);
		System.out.println("]");
	}

	
	//Metodo privado recursivo para exibir elementos.
	private void caminharPos(No i) {
		if (i != null) {
			caminharPos(i.esq); // Elementos da esquerda.
			caminharPos(i.dir); // Elementos da direita.
			System.out.print(i.elemento.getTitulo() + " "); // Conteudo do no.
		}
	}


	
	//Metodo publico iterativo para inserir elemento.
	public void inserir(filme x) throws Exception {
		raiz = inserir(x, raiz);
	}

	
	//Metodo privado recursivo para inserir elemento.
	private No inserir(filme x, No i) throws Exception {
		if (i == null) {
         i = new No(x);

      } else if (x.getTitulo().compareTo(i.elemento.getTitulo()) < i.elemento.getTitulo().compareTo(x.getTitulo()) {
         i.esq = inserir(x, i.esq);

      } else if (x.getTitulo().compareTo(i.elemento.getTitulo()) > i.elemento.getTitulo().compareTo(x.getTitulo()) {
         i.dir = inserir(x, i.dir);

      } else {
         throw new Exception("Erro ao inserir!");
      }

		return i;
	}

	
	//Metodo publico para inserir elemento.
	public void inserirPai(filme x) throws Exception {
      if(raiz == null){
         raiz = new No(x);
      } else if(x.getTitulo().compareTo(raiz.elemento.getTitulo()) < raiz.elemento.getTitulo().compareTo(x.getTitulo()){
		   inserirPai(x, raiz.esq, raiz);
      } else if(x.getTitulo().compareTo(raiz.elemento.getTitulo()) > raiz.elemento.getTitulo().compareTo(x.getTitulo()){
		   inserirPai(x, raiz.dir, raiz);
      } else {
         throw new Exception("Erro ao inserirPai!");
      }
	}

	
	//Metodo privado recursivo para inserirPai elemento.
	private void inserirPai(filme x, No i, No pai) throws Exception {
		if (i == null) {
         if(x.getTitulo().compareTo(pai.elemento.getTitulo()) < pai.elemento.getTitulo().compareTo(x.getTitulo()){
            pai.esq = new No(x);
         } else {
            pai.dir = new No(x);
         }
      } else if (x.getTitulo().compareTo(i.elemento.getTitulo()) < i.elemento.getTitulo().compareTo(x.getTitulo()) {
         inserirPai(x, i.esq, i);
      } else if (x.getTitulo().compareTo(i.elemento.getTitulo()) > i.elemento.getTitulo().compareTo(x.getTitulo()) {
         inserirPai(x, i.dir, i);
      } else {
         throw new Exception("Erro ao inserirPai!");
      }
	}


	
	//Metodo publico iterativo para remover elemento.
	public void remover(filme x) throws Exception {
		raiz = remover(x, raiz);
	}

	
	//Metodo privado recursivo para remover elemento.
	private No remover(filme x, No i) throws Exception {

		if (i == null) {
         throw new Exception("Erro ao remover!");

      } else if (x.getTitulo().compareTo(i.elemento.getTitulo()) < i.elemento.getTitulo().compareTo(x.getTitulo()) {
         i.esq = remover(x, i.esq);

      } else if (x.getTitulo().compareTo(i.elemento.getTitulo()) > i.elemento.getTitulo().compareTo(x.getTitulo()) {
         i.dir = remover(x, i.dir);

      // Sem no a direita.
      } else if (i.dir == null) {
         i = i.esq;

      // Sem no a esquerda.
      } else if (i.esq == null) {
         i = i.dir;

      // No a esquerda e no a direita.
      } else {
         i.esq = maiorEsq(i, i.esq);
		}

		return i;
	}

	
	//Metodo para trocar o elemento "removido" pelo maior da esquerda.
	private No maiorEsq(No i, No j) {

      // Encontrou o maximo da subarvore esquerda.
		if (j.dir == null) {
			i.elemento = j.elemento; // Substitui i por j.
			j = j.esq; // Substitui j por j.ESQ.

      // Existe no a direita.
		} else {
         // Caminha para direita.
			j.dir = maiorEsq(i, j.dir);
		}
		return j;
	}

	
	//Metodo que retorna o maior elemento da árvore
    public filme getMaior(){
      int resp = -1;

      if(raiz != null){
         No i;
         for(i = raiz; i.dir != null; i = i.dir);
         resp = i.elemento.getTitulo();
      }

      return resp;
   }


	
	//Metodo que retorna o menor elemento da árvore
	public filme getMenor(){
      int resp = -1;

      if(raiz != null){
         No i;
         for(i = raiz; i.esq != null; i = i.esq);
         resp = i.elemento.getTitulo();
      }

      return resp;
   }


	
	//Metodo que retorna a altura da árvore
	public int getAltura(){
      return getAltura(raiz, 0);
    }


	
	//Metodo que retorna a altura da árvore
    public int getAltura(No i, int altura){
      if(i == null){
         altura--;
      } else {
         int alturaEsq = getAltura(i.esq, altura + 1);
         int alturaDir = getAltura(i.dir, altura + 1);
         altura = (alturaEsq > alturaDir) ? alturaEsq : alturaDir;
      }
      return altura;
   }


	
	//Metodo publico iterativo para remover elemento.
	public void remover2(filme x) throws Exception {
      if (raiz == null) {
         throw new Exception("Erro ao remover2!");
      } else if(x.getTitulo().compareTo(raiz.elemento.getTitulo()) < raiz.elemento.getTitulo().compareTo(x.getTitulo()){
         remover2(x, raiz.esq, raiz);
      } else if (x.getTitulo().compareTo(raiz.elemento.getTitulo()) > raiz.elemento.getTitulo().compareTo(x.getTitulo()){
         remover2(x, raiz.dir, raiz);
      } else if (raiz.dir == null) {
         raiz = raiz.esq;
      } else if (raiz.esq == null) {
         raiz = raiz.dir;
      } else {
         raiz.esq = maiorEsq(raiz, raiz.esq);
      }
   }

	
	//Metodo privado recursivo para remover elemento.
	private void remover2(filme x, No i, No pai) throws Exception {
		if (i == null) {
         throw new Exception("Erro ao remover2!");
      } else if (x.getTitulo().compareTo(raiz.elemento.getTitulo()) < raiz.elemento.getTitulo().compareTo(x.getTitulo()) {
         remover2(x, i.esq, i);
      } else if (x.getTitulo().compareTo(raiz.elemento.getTitulo()) > raiz.elemento.getTitulo().compareTo(x.getTitulo()) {
         remover2(x, i.dir, i);
      } else if (i.dir == null) {
         pai = i.esq;
      } else if (i.esq == null) {
         pai = i.dir;
      } else {
         i.esq = maiorEsq(i, i.esq);
		}
	}

   public filme getRaiz() throws Exception {
      return raiz.elemento.getTitulo();
   }

   public static boolean igual (ArvoreBinaria a1, ArvoreBinaria a2){
      return igual(a1.raiz, a2.raiz);
   }

   private static boolean igual (No i1, No i2){
      boolean resp;
      if(i1 != null && i2 != null){
         resp = (i1.elemento.getTitulo().compareTo(i2.elemento.getTitulo()) == i2.elemento.getTitulo().compareTo(i1.elemento.getTitulo())) && igual(i1.esq, i2.esq) && igual(i1.dir, i2.dir);
      } else if(i1 == null && i2 == null){
         resp = true;
      } else {
         resp = false; 
      }
      return resp;
   }

   public int soma(){
      return soma(raiz);
   }


   public boolean hasDiv11(){
      return hasDiv11(raiz);
   }

   public boolean hasDiv11(No i){
      boolean resp = false;
      if(i != null){
         resp = (i.elemento % 11 == 0) || hasDiv11(i.esq) || hasDiv11(i.dir);
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
        Lista listaFilme = new Lista(100);
        

        nomeFilme = MyIO.readLine();
        

        //Lendo os filmes e armazenando no array
        while(!nomeFilme.equals("FIM")){

            novoFilme.readDados(nomeFilme);
            try {
                listaFilme.inserirFim(novoFilme);
            } catch (Exception e) {
                e.printStackTrace();
            }

            nomeFilme = MyIO.readLine();
        }

        String tamanhoS = "";
        tamanhoS = MyIO.readLine();
        int tamanhoI = Integer.parseInt(tamanhoS);


        //Fazendo as aleterações 
        for(int i=0 ; i<tamanhoI ; i++){

            nomeFilme = MyIO.readLine();

            //Inserir no Fim I
            if(nomeFilme.charAt(0) == 'I'){
                nomeFilme = nomeFilme.substring(2);
             
                filme nome = new filme();
                nome.readDados(nomeFilme);

                listaFilme.inserirFim(nome);
            }


            //Remover do Fim R
            else if(nomeFilme.charAt(0) == 'R'){
                
                try {
                    filme removido = listaFilme.removerFim();
                    System.out.println("(R) "+removido.getNome());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        listaFilme.imprimirLista();

    }
}
