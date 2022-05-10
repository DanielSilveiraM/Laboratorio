import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;  
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;


class Lista {
    private filme[] array;
    private int n;
 
 
    public Lista (int tamanho){
       array = new filme[tamanho];
       n = 0;
    }
 
    
    /*Insere um elemento na ultima posicao da lista. */
    public void inserirFim(filme x) throws Exception {

       //validar insercao
       if(n >= array.length){
          throw new Exception("Erro ao inserir!");
       }
 
       array[n] = x.clonar();
       n++;
    }
 
    public void swap(int i, int j) {
        filme temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    public void sort() {
        for (int i = 0; i < (n - 1); i++) {
        int menor=i;
        int k=0;
        //String comparador = array[i+1].getTitulo();
            for (int j = (i + 1); j < n; j++){
                String comparador = array[j].getTitulo();
                String title = array[menor].getTitulo();
                
                if(title.charAt(k) > comparador.charAt(k)){
                    menor = j;
                }
                else if(title.charAt(k) == comparador.charAt(k) && k < title.length()){
                    k++;
                }
            }
            swap(menor, i);
        }
   }


    public void imprimirLista(){
        for(int i=0 ; i<n ; i++){
            array[i].imprimeDados();
        }
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

        String tituloFilme = "";
        filme novoFilme = new filme();
        Lista listaFilme = new Lista(100);
        

        tituloFilme = MyIO.readLine();
        

        //Lendo os filmes e armazenando no array
        while(!tituloFilme.equals("FIM")){

            novoFilme.readDados(tituloFilme);
            try {
                listaFilme.inserirFim(novoFilme);
            } catch (Exception e) {
                e.printStackTrace();
            }

            tituloFilme = MyIO.readLine();
        }

        listaFilme.sort();

        //listaFilme.imprimirLista();
    }
}
