package objects;

import utils.Metodos;
import utils.RepositorioSingleton;

import java.util.ArrayList;
import java.util.List;

public class Filme {

    private Long id;
    private final String titulo;
    private final int ano;
    private final int duracao; //Em minutos.
    private final Genero genero;
    private final int classificacao;
    private Diretor diretor;
    private final List<Ator> atores;

    public Filme(String titulo, String ano, String genero, String duracao, String classificacao){
        this.atores = new ArrayList<>();
        this.titulo = titulo;
        this.ano = Integer.parseInt(ano);
        this.genero = Metodos.getGeneroFromString(genero);
        this.duracao = Integer.parseInt(duracao);
        this.classificacao=Integer.parseInt(classificacao);
        this.id = RepositorioSingleton.instance().getBancoDeFilmes().getNextID();
        RepositorioSingleton.instance().getBancoDeFilmes().gravar(this);
    }

    public String getNome(){
        return this.titulo;
    }

    public Diretor getDiretor(){
        return this.diretor;
    }

    public Genero getGenero(){
        return this.genero;
    }

    public int getAno(){
        return this.ano;
    }

    public int getDuracao(){
        return this.duracao;
    }

    public int getClassificacao(){
        return this.classificacao;
    }

    public void setDiretor(Diretor diretor){ //Para o caso do cadastro ser feito sem ter o diretor cadastrado.
        this.diretor = diretor;
    }

    public void addAtor(Ator ator){
        for (Ator ator_cadastrado : this.atores){
            if (ator.getNome().equalsIgnoreCase(ator_cadastrado.getNome())){
                return;
            }
        }
        this.atores.add(ator);
    }

    public List<Ator> getAtores(){
        return this.atores;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Título: ").append(this.titulo).append(" | ");
        builder.append("Ano: ").append(this.ano).append(" | ");
        builder.append("Duração: ").append(this.duracao).append(" minutos | ");
        builder.append("Gênero: ").append(this.genero).append(" | ");
        builder.append("Classificação: ").append(this.classificacao).append(" | ");
        builder.append("Diretor: ").append(this.diretor.getNome()).append(" | ");
        builder.append("Atores: ");
        for (Ator ator : this.atores) {
            builder.append(ator.getNome()).append(", ");
        }
        builder.delete(builder.length() - 2, builder.length()); //Só pra remover a última virgula.
        return builder.toString();
    }
}