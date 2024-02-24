package utils.factory;

import objects.Ator;
import objects.Diretor;
import objects.Filme;
import objects.menus.MenuView;
import utils.RepositorioSingleton;
import utils.ScannerSingleton;

import java.util.ArrayList;
import java.util.List;

public class FilmeFactory {
private static final String[] PARAMETROS_OBRIGATORIOS = {"Título", "Ano de Lançamento", "Genero", "Duração em Minutos", "Classificação Indicativa"};
    public static Filme create(){
        List<String> data = new ArrayList<>();
        List<Ator> atores = new ArrayList<>();
        Diretor diretor;
        ScannerSingleton.instance().clean_scanner();
        
        for (String parametro : PARAMETROS_OBRIGATORIOS){
            System.out.println("Informe o " + parametro + " do Filme: ");
            data.add(ScannerSingleton.instance().getScanner().nextLine());
        }
        String query = "";
        while (!query.equals("0")){
            System.out.println("Escreva o nome de um ator para consultar a base de dados ou '0' para encerrar a adição de atores: ");
            query = ScannerSingleton.instance().getScanner().nextLine();
            if (!query.equals("0")) {
                atores.add(ator_helper(query));
            }
        }
        System.out.println("Escreva o nome de um diretor para consultar a base de dados");
        query = ScannerSingleton.instance().getScanner().nextLine();
        diretor = diretor_helper(query);
        Filme filme = new Filme(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4));
        for (Ator ator : atores){
            filme.addAtor(ator);
        }
        filme.setDiretor(diretor);
        return filme;
    }

    private static Ator ator_helper(String nome){
        MenuView menu = new MenuView(null, null);
        List<Ator> atores = RepositorioSingleton.instance().getBancoDeAtores().obterAtorPorNome(nome);
        List<String> ator_nomes = new ArrayList<>();
        for (Ator ator : atores){
            ator_nomes.add(ator.getNome());
        }
        if (ator_nomes.isEmpty()){
            System.out.println("O ator não está cadastrado, deseja fazer o cadastro agora? (s/n)");
            String prompt = ScannerSingleton.instance().getScanner().nextLine();
            if (prompt.equalsIgnoreCase("s")){
                Ator ator = AtorFactory.create();
                RepositorioSingleton.instance().getBancoDeAtores().gravar(ator);
                return ator;
            }else {
                System.out.println("Escreva o nome de um ator para realizar a busca novamente: ");
                String query = ScannerSingleton.instance().getScanner().nextLine();
                return ator_helper(query);
            }
        }
        menu.change_menu("Lista de atores", ator_nomes);
        menu.show_menu();
        System.out.printf("Escolha um ator de %d - %d\n", 0, atores.size()-1);
        return atores.get(ScannerSingleton.instance().getScanner().nextInt());
    }

    private static Diretor diretor_helper(String nome){
        MenuView menu = new MenuView(null, null);
        List<Diretor> diretores = RepositorioSingleton.instance().getBancoDeDiretores().obterDiretorPorNome(nome);
        List<String> diretor_nomes = new ArrayList<>();
        for (Diretor diretor : diretores){
            diretor_nomes.add(diretor.getNome());
        }
        if (diretor_nomes.isEmpty()){
            System.out.println("O diretor não está cadastrado, deseja fazer o cadastro agora? (s/n)");
            String prompt = ScannerSingleton.instance().getScanner().nextLine();
            if (prompt.equalsIgnoreCase("s")){
                Diretor diretor = DiretorFactory.create();
                RepositorioSingleton.instance().getBancoDeDiretores().gravar(diretor);
                return DiretorFactory.create();
            }else {
                System.out.println("Escreva o nome de um diretor para realizar a busca novamente: ");
                String query = ScannerSingleton.instance().getScanner().nextLine();
                return diretor_helper(query);
            }
        }
        menu.change_menu("Lista de diretores", diretor_nomes);
        menu.show_menu();
        System.out.printf("Escolha um diretor de %d - %d\n", 0, diretor_nomes.size() - 1);
        return diretores.get(ScannerSingleton.instance().getScanner().nextInt());
    }
}
