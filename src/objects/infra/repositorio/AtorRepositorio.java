package objects.infra.repositorio;

import objects.infra.banco.BancoDeDados;
import objects.Ator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AtorRepositorio extends AbstractRepositorio<Ator> {

    public AtorRepositorio(BancoDeDados bancoDeDados) {
        super(bancoDeDados);
    }

    @Override
    protected Class<Ator> classeModelo() {
        return Ator.class;
    }

    public List<String> consultarAtorPorNome(String nome_do_ator) {
        List<Ator> todos_os_atores = listar();
        List<String> atores = new ArrayList<>();
        for (Ator ator : todos_os_atores) {
            if (ator.getNome() != null && ator.getNome().contains(nome_do_ator)) {
                atores.add(ator.getNome());
            }
        }
        return atores;
    }

    public List<Ator> obterAtorPorNome(String nome_do_ator) {
        List<Ator> todos_os_atores = listar();
        List<Ator> atores = new ArrayList<>();
        for (Ator ator : todos_os_atores) {
            if (ator.getNome() != null && ator.getNome().contains(nome_do_ator)) {
                atores.add(ator);
            }
        }
        return atores;
    }

    @Override
    protected Boolean filtraPorId(Ator ator, Long id) {
        return Objects.equals(ator.getId(), id);
    }
}