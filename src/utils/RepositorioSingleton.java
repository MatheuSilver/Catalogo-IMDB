package utils;

import objects.infra.banco.BancoDeDados;
import objects.infra.repositorio.AtorRepositorio;
import objects.infra.repositorio.DiretorRepositorio;
import objects.infra.repositorio.FilmeRepositorio;

public class RepositorioSingleton {
    private static BancoDeDados banco_de_dados;
    private static FilmeRepositorio banco_de_filmes;
    private static AtorRepositorio banco_de_atores;
    private static DiretorRepositorio banco_de_diretores;

    private static final RepositorioSingleton SINGLETON = new RepositorioSingleton();
    private RepositorioSingleton() {}

    public static RepositorioSingleton instance() {
        if (banco_de_dados==null){
            banco_de_dados = new BancoDeDados();
            banco_de_dados.carregar_dados();
        }
        return SINGLETON;
    }

    public void salvar_dados(){
        banco_de_dados.salvarDados();
    }
    public FilmeRepositorio getBancoDeFilmes() {
        if (banco_de_filmes == null) {
            banco_de_filmes = new FilmeRepositorio(banco_de_dados);
        }
        return banco_de_filmes;
    }

    public AtorRepositorio getBancoDeAtores() {
        if (banco_de_atores == null) {
            banco_de_atores = new AtorRepositorio(banco_de_dados);
        }
        return banco_de_atores;
    }

    public DiretorRepositorio getBancoDeDiretores() {
        if (banco_de_diretores == null) {
            banco_de_diretores = new DiretorRepositorio(banco_de_dados);
        }
        return banco_de_diretores;
    }
}