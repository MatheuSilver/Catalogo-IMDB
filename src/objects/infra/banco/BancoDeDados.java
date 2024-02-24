package objects.infra.banco;

import objects.Ator;
import objects.Diretor;
import objects.Filme;
import utils.RepositorioSingleton;

import java.io.*;
import java.util.*;

public class BancoDeDados {
    private static final Map OBJETOS = new HashMap();

    private static final Long[] id_list = {0L,0L,0L}; //Isso implica que os ID's serão reiniciados a cada inicio do programa.
                                                      //Considerando que o ID é puramente pra diferenciar cada objeto na base de dados
                                                      //Não vejo tanto problema assim nisso.

    private final String[] files = {"atores", "diretores", "filmes"}; //Filmes obrigatoriamente deve ser o último.
    private final Class[] classes = {Ator.class, Diretor.class, Filme.class};

    public BancoDeDados(){}

    public void carregar_dados(){
        for (int i = 0; i < files.length; i++) {
            carregarDadosDoArquivo(files[i]);
        }
    }

    private void carregarDadosDoArquivo(String arquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader("assets\\"+arquivo+".csv"))) {
            br.lines().forEach(linha -> {
                String[] dados = linha.split(",");
                switch(arquivo){
                    case ("atores"):
                        Ator ator = new Ator(dados[1], dados[2], dados[3], dados[4]);
                        inserirObjeto(ator);
                        break;

                    case ("diretores"):
                        List<String> ocupacoes = new ArrayList<>(Arrays.asList(dados[4].split(";")));
                        Diretor diretor = new Diretor(dados[1], dados[2], dados[3], ocupacoes);
                        inserirObjeto(diretor);
                        break;

                    case ("filmes"):
                        Filme filme = new Filme(dados[1], dados[2], dados[3], dados[4], dados[5]);
                        Diretor diretor_filme = RepositorioSingleton.instance().getBancoDeDiretores().obterDiretorPorNome(dados[6]).get(0);
                        for (String ator_do_filme : dados[7].split(";")){
                            filme.addAtor(RepositorioSingleton.instance().getBancoDeAtores().obterAtorPorNome(ator_do_filme).get(0));
                        }
                        filme.setDiretor(diretor_filme);
                        inserirObjeto(filme);
                        break;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void salvarDados(){
        for (int i = 0; i< files.length; i++){
            this.salvarDadosParaArquivo(files[i], classes[i]);
        }
    }

    private void salvarDadosParaArquivo(String arquivo, Class clazz) {
        Set objetos = colecaoDeObjetos(clazz);
        try (PrintWriter writer = new PrintWriter(new FileWriter("assets\\" + arquivo + ".csv"))) {
            for (Object objeto : objetos) {
                if (objeto instanceof Ator) {
                    Ator ator = (Ator) objeto;
                    writer.println(ator.getId()+"," + ator.getNome() + "," + ator.getDataNascimentoFormatada() + "," + ator.getLocalNascimento() + "," + ator.getPseudonimo());
                } else if (objeto instanceof Diretor) {
                    Diretor diretor = (Diretor) objeto;
                    writer.println(diretor.getId()+"," + diretor.getNome() + "," + diretor.getDataNascimentoFormatada() + "," + diretor.getLocalNascimento() + "," + String.join(";", diretor.getOcupacoes())+";");
                } else if (objeto instanceof Filme) {
                    Filme filme = (Filme) objeto;
                    StringBuilder atores = new StringBuilder();
                    for (Ator ator : filme.getAtores()) {
                        atores.append(ator.getNome()).append(";");
                    }
                    writer.println(filme.getId()+"," + filme.getNome() + "," + filme.getAno() + "," + filme.getGenero()+"," + filme.getDuracao() + "," + filme.getClassificacao() + "," + filme.getDiretor().getNome() + "," + atores);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inserirObjeto(Object objeto) {
        Set objetos = colecaoDeObjetos(objeto.getClass());
        objetos.add(objeto);
    }

    public long get_next_ID(Class clazz){
        for (int i=0;i<classes.length;i++){
            if (clazz.equals(classes[i])){
                long free_id = id_list[i];
                id_list[i]++;
                return free_id;
            }
        }
        return 0L;
    }

    public void excluirObjeto(Object objeto) {
        Set objetos = colecaoDeObjetos(objeto.getClass());
        objetos.remove(objeto);
    }

    public List buscarObjetosPorTipo(Class clazz) {
        Set objetos = colecaoDeObjetos(clazz);
        return new ArrayList(objetos);
    }

    private Set colecaoDeObjetos(Class clazz) {
        Set objetos = (Set) BancoDeDados.OBJETOS.get(clazz);
        if (objetos == null){
            objetos = new HashSet();
            BancoDeDados.OBJETOS.put(clazz, objetos);
        }
        return objetos;
    }

}
