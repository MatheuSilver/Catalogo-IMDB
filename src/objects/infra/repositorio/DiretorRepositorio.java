package objects.infra.repositorio;

import objects.Diretor;
import objects.infra.banco.BancoDeDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DiretorRepositorio extends AbstractRepositorio<Diretor> {

    public DiretorRepositorio(BancoDeDados bancoDeDados) {
        super(bancoDeDados);
    }

    @Override
    protected Class<Diretor> classeModelo() {
        return Diretor.class;
    }

    public List<String> consultarDiretorPorNome(String nome_do_diretor) {
        List<Diretor> todos_os_diretores = listar();
        List<String> diretores = new ArrayList<>();
        for (Diretor diretor : todos_os_diretores) {
            if (diretor.getNome() != null && diretor.getNome().contains(nome_do_diretor)) {
                diretores.add(diretor.getNome());
            }
        }
        return diretores;
    }

    public List<Diretor> obterDiretorPorNome(String nome_do_diretor) {
        List<Diretor> todos_os_diretores = listar();
        List<Diretor> diretores = new ArrayList<>();
        for (Diretor diretor : todos_os_diretores) {
            if (diretor.getNome() != null && diretor.getNome().contains(nome_do_diretor)) {
                diretores.add(diretor);
            }
        }
        return diretores;
    }
    @Override
    protected Boolean filtraPorId(Diretor diretor, Long id) {
        return Objects.equals(diretor.getId(), id);
    }
}
