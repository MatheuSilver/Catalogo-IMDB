package objects;

import utils.Metodos;
import utils.RepositorioSingleton;

import java.util.List;

public class Diretor extends Artista{

    public List<String> ocupacoes;
    public Diretor(String nome, String dataDeNascimento, String localDeNascimento, List<String> ocupacoes){
        super(nome, dataDeNascimento, localDeNascimento);
        this.ocupacoes=ocupacoes;
        this.id = RepositorioSingleton.instance().getBancoDeDiretores().getNextID();
    }

    public void addOcupacao(String ocupacao){
        this.ocupacoes.add(ocupacao);
    }

    public void removeOcupacao(String ocupacao){
        for (int i=0;i<this.ocupacoes.size();i++){
            if (Metodos.contains_ignoring_case(this.ocupacoes.get(i), ocupacao)){
                this.ocupacoes.remove(i);
                return; //Sai do loop quando achar o elemento desejado.
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getNome()).append(" | ")
                .append(this.getDataNascimento()).append(" | ")
                .append(this.getLocalNascimento()).append(" | ");

        for (String ocupacao : this.ocupacoes) {
            builder.append(ocupacao).append(";");
        }

        // Remove o último ponto e vírgula ";"
        if (!this.ocupacoes.isEmpty()) {
            builder.deleteCharAt(builder.length() - 1);
        }

        return builder.toString();
    }

    public List<String> getOcupacoes(){
        return this.ocupacoes;
    }
}
