package objects.infra.repositorio;

import objects.Ator;
import objects.Diretor;
import objects.Filme;
import objects.infra.banco.BancoDeDados;
import utils.Metodos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FilmeRepositorio extends AbstractRepositorio<Filme> {

    public FilmeRepositorio(BancoDeDados bancoDeDados) {
        super(bancoDeDados);
    }

    @Override
    protected Class<Filme> classeModelo() {
        return Filme.class;
    }

    public List<String> consultarPorNomeDoFilme(String nome_do_filme) {
        List<Filme> filmes = listar();
        List<String> filmes_encontrados = new ArrayList<>();
        for (Filme filme : filmes) {
            if (filme.getNome() != null && Metodos.contains_ignoring_case(nome_do_filme, filme.getNome())) {
                filmes_encontrados.add(filme.toString());
            }
        }
        return filmes_encontrados;
    }

    public List<String> consultarDiretorPorNomeDoFilme(String nome_do_filme) {
        List<Filme> filmes = listar();
        List<String> diretores = new ArrayList<>();
        for (Filme filme : filmes) {
            if (filme.getNome() != null && Metodos.contains_ignoring_case(nome_do_filme, filme.getNome())) {
                if (filme.getDiretor() != null) {
                    diretores.add(filme.getDiretor().getNome());
                }
            }
        }
        return diretores;
    }

    public List<Diretor> obterDiretorPorNomeDoFilme(String nome_do_filme) {
        List<Filme> filmes = listar();
        List<Diretor> diretores = new ArrayList<>();
        for (Filme filme : filmes) {
            if (filme.getNome() != null && Metodos.contains_ignoring_case(nome_do_filme, filme.getNome())) {
                if (filme.getDiretor() != null) {
                    diretores.add(filme.getDiretor());
                }
            }
        }
        return diretores;
    }

    public List<Ator> obterAtorPorNomeDoFilme(String nome_do_filme) {
        List<Filme> filmes = listar();
        List<Ator> atores = new ArrayList<>();
        for (Filme filme : filmes) {
            if (filme.getNome() != null && Metodos.contains_ignoring_case(nome_do_filme, filme.getNome())) {
                if (filme.getDiretor() != null) {
                    atores.addAll(filme.getAtores());
                }
            }
        }
        return atores;
    }

    public List<Filme> obterFilmePorNome(String nome_do_filme) {
        List<Filme> filmes = listar();
        List<Filme> filmes_encontrados = new ArrayList<>();
        for (Filme filme : filmes) {
            if (filme.getNome() != null && Metodos.contains_ignoring_case(nome_do_filme, filme.getNome())) {
                filmes_encontrados.addAll(filmes);
            }
        }
        return filmes_encontrados;
    }

    public List<String> consultarAtorPorNomeDoFilme(String nome_do_filme) {
        List<Filme> filmes = listar();
        List<String> atores = new ArrayList<>();
        for (Filme filme : filmes) {
            if (filme.getNome() != null && Metodos.contains_ignoring_case(nome_do_filme, filme.getNome())) {
                for (Ator ator : filme.getAtores()) {
                    if (ator != null) {
                        atores.add(ator.getNome());
                    }
                }
            }
        }
        return atores;
    }
    @Override
    protected Boolean filtraPorId(Filme filme, Long id) {
        return Objects.equals(filme.getId(), id);
    }
}
