package objects;

import utils.RepositorioSingleton;

public class Ator extends Artista {
    private String pseudonimo = "";

    public Ator(String nome, String dataDeNascimento, String localDeNascimento, String pseudonimo) {
        super(nome, dataDeNascimento, localDeNascimento);
        this.pseudonimo = pseudonimo;
        this.id = RepositorioSingleton.instance().getBancoDeAtores().getNextID();
    }

    public Ator(String nome, String dataDeNascimento, String localDeNascimento) {
        super(nome, dataDeNascimento, localDeNascimento);
        this.id = RepositorioSingleton.instance().getBancoDeAtores().getNextID();
    }

    public String getPseudonimo(){
        if (this.pseudonimo.isEmpty()){
            return "Não possui pseudonimos.";
        }
        return this.pseudonimo;
    }

    @Override
    public String toString() {
        return this.getNome() + "|" +
                this.getDataNascimento() + " | " +
                this.getLocalNascimento() + " | " +
                this.getPseudonimo();
    }

    public void setPseudonimo(String new_pseudonimo){
        this.pseudonimo = new_pseudonimo;
    }
}
