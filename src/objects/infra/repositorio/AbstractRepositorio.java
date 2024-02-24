package objects.infra.repositorio;

import objects.infra.banco.BancoDeDados;

import java.util.Collections;
import java.util.List;

public abstract class AbstractRepositorio<T> {

    protected BancoDeDados bancoDeDados;

    public AbstractRepositorio(BancoDeDados bancoDeDados) {
        this.bancoDeDados = bancoDeDados;
    }

    public void gravar(T objeto) {
        this.bancoDeDados.inserirObjeto(objeto);
    }

    public T buscarPorId(Long id) {
        List<T> objetos = listar();
        for (T objeto : objetos) {
            if (objeto != null && filtraPorId(objeto, id)) {
                return objeto;
            }
        }
        return null; // Retorna null se não encontrado
    }

    public List<T> listar() {
        List<T> objetosPresentesNoBanco = this.bancoDeDados.buscarObjetosPorTipo(classeModelo());
        return Collections.unmodifiableList(objetosPresentesNoBanco);
    }

    public Boolean excluir(T objeto) {
        if (objeto != null) {
            this.bancoDeDados.excluirObjeto(objeto);
            return true;
        }
        return false; // Retorna false se o objeto for nulo
    }

    public long getNextID() {
        return this.bancoDeDados.get_next_ID(classeModelo());
    }

    protected abstract Class<T> classeModelo();

    protected abstract Boolean filtraPorId(T objeto, Long id);
}
