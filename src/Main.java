import objects.Ator;
import objects.Diretor;
import objects.Filme;
import objects.menus.MenuView;
import utils.RepositorioSingleton;
import utils.ScannerSingleton;
import utils.factory.AtorFactory;
import utils.factory.DiretorFactory;
import utils.factory.FilmeFactory;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;

public class Main {

    static MenuView menu;

    public static void init(){

        Runtime.getRuntime().addShutdownHook(new Thread(() -> ScannerSingleton.instance().getScanner().close())); //Recomendação do IntelliJ
        Locale.setDefault(new Locale("pt", "BR"));
        RepositorioSingleton.instance();
    }
    public static void main(String[] args) {
        init();
        System.out.println("Bem vindo!");
        menu_de_opcao_principal();
        sair_e_salvar();
        System.exit(0); //Costume do C - Desculpa
    }
    public static void menu_de_opcao_principal(){
        final String[] options = {"Sair", "Consultar Informações", "Adicionar Informações", "Editar Informações"};
        menu = new MenuView("Menu Principal", options);
        while (true) {
            menu.change_menu("Menu Principal", options);
            menu.show_menu();
            try {
                int input = ScannerSingleton.instance().getScanner().nextInt();
                switch (input) {
                    case 0:
                        return;
                    case 1:
                        menu_de_consulta();
                        break;
                    case 2:
                        menu_de_adicao();
                        break;
                    case 3:
                        menu_de_edicao();
                        break;
                    default:
                        System.out.println("Opção inválida, por favor, selecione uma dentre as 4 opções.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, insira um número inteiro correspondente à opção do menu.");
                ScannerSingleton.instance().clean_scanner();
            }
        }
    }

    public static void menu_de_consulta(){
        ScannerSingleton.instance().clean_scanner();
        final String[] options = {"Voltar ao Menu Principal", "Consultar Atores", "Consultar Diretores", "Consultar Filmes"};
        menu.change_menu("Menu de Consulta", options);
        String nome;
        while (true) {
            menu.change_menu("Menu de Consulta", options);
            menu.show_menu();
            try {
                int input = ScannerSingleton.instance().getScanner().nextInt();
                ScannerSingleton.instance().clean_scanner();
                switch (input) {
                    case 0:
                        return;
                    case 1:
                        System.out.println("Digite o nome do Ator ou do Filme em que o ator participou para realizar a consulta.");
                        nome = ScannerSingleton.instance().getScanner().nextLine();
                        consulta_binaria("Atores", nome);
                        break;
                    case 2:
                        System.out.println("Digite o nome do Diretor ou do Filme em que o diretor participou para realizar a consulta.");
                        nome = ScannerSingleton.instance().getScanner().nextLine();
                        consulta_binaria("Diretores", nome);
                        break;
                    case 3:
                        System.out.println("Digite o nome do filme para realizar a consulta.");
                        nome = ScannerSingleton.instance().getScanner().nextLine();
                        consultar_filmes(nome);
                        break;
                    default:
                        System.out.println("Opção inválida, por favor, selecione uma dentre as 4 opções.\n");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, insira um número inteiro correspondente à opção do menu.");
                ScannerSingleton.instance().clean_scanner();
            }
        }
    }

    public static void menu_de_adicao(){
        ScannerSingleton.instance().clean_scanner();
        final String[] options = {"Voltar ao Menu Principal", "Adicionar Atores", "Adicionar Diretores", "Adicionar Filmes"};
        menu.change_menu("Menu de Adição", options);
        while (true) {
            menu.change_menu("Menu de Adição", options);
            menu.show_menu();
            try {
                int input = ScannerSingleton.instance().getScanner().nextInt();
                switch (input) {
                    case 0:
                        return;
                    case 1:
                        RepositorioSingleton.instance().getBancoDeAtores().gravar(AtorFactory.create());
                        break;
                    case 2:
                        RepositorioSingleton.instance().getBancoDeDiretores().gravar(DiretorFactory.create());
                        break;
                    case 3:
                        RepositorioSingleton.instance().getBancoDeFilmes().gravar(FilmeFactory.create());
                        break;
                    default:
                        System.out.println("Opção inválida, por favor, selecione uma dentre as 4 opções.\n");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, insira um número inteiro correspondente à opção do menu.");
                ScannerSingleton.instance().clean_scanner();
            }
        }
    }

    public static void menu_de_edicao(){ //Confesso que toda essa última parte fiz na pressa.
                                         //Só não deu completamente ruim porque a criação do RepositorioSingleton me deixou vacinado.
        ScannerSingleton.instance().clean_scanner();
        final String[] options = {"Voltar ao Menu Principal", "Editar Atores", "Editar Diretores", "Editar Filmes"};
        menu.change_menu("Menu de Edição", options);
        while (true) {
            menu.change_menu("Menu de Edição", options);
            menu.show_menu();
            try {
                int input = ScannerSingleton.instance().getScanner().nextInt();
                switch (input) {
                    case 0:
                        return;
                    case 1:
                        editar_ator();
                        break;
                    case 2:
                        editar_diretor();
                        break;
                    case 3:
                        editar_filme();
                        break;
                    default:
                        System.out.println("Opção inválida, por favor, selecione uma dentre as 4 opções.\n");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, insira um número inteiro correspondente à opção do menu.");
                ScannerSingleton.instance().clean_scanner();
            }
        }
    }
    private static void editar_filme() {
        ScannerSingleton.instance().clean_scanner();

        final String[] options = {"Voltar ao Menu de Edição", "Editar Diretor", "Adicionar Ator"};

        menu.change_menu("Menu de Edição de Filme", options);

        while (true) {
            menu.show_menu();
            try {
                int input = ScannerSingleton.instance().getScanner().nextInt();
                switch (input) {
                    case 0:
                        return;
                    case 1:
                        editar_diretor_filme();
                        break;
                    case 2:
                        adicionar_ator_filme();
                        break;
                    default:
                        System.out.println("Opção inválida, por favor, selecione uma das opções do menu.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, insira um número inteiro correspondente à opção do menu.");
                ScannerSingleton.instance().clean_scanner();
            }
        }
    }

    private static void editar_diretor_filme() {
        System.out.println("Digite o nome do filme que deseja editar:");
        String nomeDoFilme = ScannerSingleton.instance().getScanner().nextLine();

        List<String> filmesEncontrados = RepositorioSingleton.instance().getBancoDeFilmes().consultarPorNomeDoFilme(nomeDoFilme);
        if (filmesEncontrados.isEmpty()) {
            System.out.println("Não há filmes com este nome.");
            return;
        }

        menu.change_menu("Lista de filmes", filmesEncontrados);
        menu.show_menu();
        System.out.printf("Escolha um filme de %d - %d\n", 0, filmesEncontrados.size() - 1);
        int escolha = ScannerSingleton.instance().getScanner().nextInt();
        Filme filmeEscolhido = RepositorioSingleton.instance().getBancoDeFilmes().obterFilmePorNome(nomeDoFilme).get(escolha);

        System.out.println("Digite o nome do diretor:");
        String nomeDoDiretor = ScannerSingleton.instance().getScanner().nextLine();

        List<Diretor> diretoresEncontrados = RepositorioSingleton.instance().getBancoDeDiretores().obterDiretorPorNome(nomeDoDiretor);
        List<String> nomes_de_diretores = RepositorioSingleton.instance().getBancoDeDiretores().consultarDiretorPorNome(nomeDoDiretor);
        if (diretoresEncontrados.isEmpty()) {
            System.out.println("Não há diretores com este nome.");
            return;
        }

        menu.change_menu("Lista de diretores", nomes_de_diretores);
        menu.show_menu();
        System.out.printf("Escolha um diretor de %d - %d\n", 0, nomes_de_diretores.size() - 1);
        int diretor_escolhido = ScannerSingleton.instance().getScanner().nextInt();

        Diretor diretorSelecionado = diretoresEncontrados.get(diretor_escolhido);

        filmeEscolhido.setDiretor(diretorSelecionado);
        RepositorioSingleton.instance().getBancoDeFilmes().gravar(filmeEscolhido);
    }

    private static void adicionar_ator_filme() {
        System.out.println("Digite o nome do filme ao qual deseja adicionar um ator:");
        String nomeDoFilme = ScannerSingleton.instance().getScanner().nextLine();

        List<String> filmesEncontrados = RepositorioSingleton.instance().getBancoDeFilmes().consultarPorNomeDoFilme(nomeDoFilme);
        if (filmesEncontrados.isEmpty()) {
            System.out.println("Não há filmes com este nome.");
            return;
        }

        menu.change_menu("Lista de filmes", filmesEncontrados);
        menu.show_menu();
        System.out.printf("Escolha um filme de %d - %d\n", 0, filmesEncontrados.size() - 1);
        int escolha = ScannerSingleton.instance().getScanner().nextInt();
        Filme filmeSelecionado = RepositorioSingleton.instance().getBancoDeFilmes().obterFilmePorNome(nomeDoFilme).get(escolha);

        System.out.println("Digite o nome do ator:");
        String nomeDoAtor = ScannerSingleton.instance().getScanner().nextLine();

        List<Ator> atoresEncontrados = RepositorioSingleton.instance().getBancoDeAtores().obterAtorPorNome(nomeDoAtor);
        List<String> nomes_de_atores = RepositorioSingleton.instance().getBancoDeAtores().consultarAtorPorNome(nomeDoAtor);
        if (atoresEncontrados.isEmpty()) {
            System.out.println("Não há atores com este nome.");
            return;
        }

        menu.change_menu("Lista de atores", nomes_de_atores);
        menu.show_menu();
        System.out.printf("Escolha um ator de %d - %d\n", 0, nomes_de_atores.size() - 1);
        int ator_escolhido = ScannerSingleton.instance().getScanner().nextInt();

        Ator atorSelecionado = atoresEncontrados.get(ator_escolhido);

        filmeSelecionado.addAtor(atorSelecionado);
        RepositorioSingleton.instance().getBancoDeFilmes().gravar(filmeSelecionado);
    }


    private static void editar_ator() {
        ScannerSingleton.instance().clean_scanner();
        final String[] options = {"Voltar ao Menu de Edição", "Editar Pseudônimo"};
        menu.change_menu("Menu de Edição de Ator", options);

        while (true) {
            menu.change_menu("Menu de Edição de Ator", options);
            menu.show_menu();
            try {
                int input = ScannerSingleton.instance().getScanner().nextInt();
                switch (input) {
                    case 0:
                        return;
                    case 1:
                        System.out.println("Digite o nome do ator que deseja editar:");
                        String nomeDoAtor = ScannerSingleton.instance().getScanner().nextLine();

                        List<Ator> atoresEncontrados = RepositorioSingleton.instance().getBancoDeAtores().obterAtorPorNome(nomeDoAtor);
                        List<String> nomes_de_atores = RepositorioSingleton.instance().getBancoDeAtores().consultarAtorPorNome(nomeDoAtor);
                        if (atoresEncontrados.isEmpty()) {
                            System.out.println("Não há atores com este nome.");
                            break;
                        }

                        ScannerSingleton.instance().clean_scanner();
                        System.out.println("Digite o novo pseudônimo:");
                        String novoPseudonimo = ScannerSingleton.instance().getScanner().nextLine();

                        menu.change_menu("Lista de atores", nomes_de_atores);
                        menu.show_menu();
                        System.out.printf("Escolha um atores de %d - %d\n", 0, nomes_de_atores.size() - 1);
                        int ator_escolhido = ScannerSingleton.instance().getScanner().nextInt();
                        RepositorioSingleton.instance().getBancoDeAtores().gravar(atoresEncontrados.get(ator_escolhido).setPseudonimo(novoPseudonimo););
                        break;
                    default:
                        System.out.println("Opção inválida, por favor, selecione uma das opções do menu.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, insira um número inteiro correspondente à opção do menu.");
                ScannerSingleton.instance().clean_scanner();
            }
        }
    }

    private static void editar_diretor() {
        ScannerSingleton.instance().clean_scanner();
        final String[] options = {"Voltar ao Menu de Edição", "Editar ocupação"};
        menu.change_menu("Menu de Edição de Diretor", options);
        while (true) {
            menu.change_menu("Menu de Edição de Diretor", options);
            menu.show_menu();
            try {
                int input = ScannerSingleton.instance().getScanner().nextInt();
                switch (input) {
                    case 0:
                        return;
                    case 1:
                        ScannerSingleton.instance().clean_scanner();
                        System.out.println("Digite o nome do diretor que deseja editar:");
                        String nomeDoDiretor = ScannerSingleton.instance().getScanner().nextLine();

                        List<Diretor> diretoresEncontrados = RepositorioSingleton.instance().getBancoDeDiretores().obterDiretorPorNome(nomeDoDiretor);
                        List<String> nomes_de_diretores = RepositorioSingleton.instance().getBancoDeDiretores().consultarDiretorPorNome(nomeDoDiretor);
                        if (diretoresEncontrados.isEmpty()) {
                            System.out.println("Não há diretores com este nome.");
                            break;
                        }

                        menu.change_menu("Lista de diretores", nomes_de_diretores);
                        menu.show_menu();
                        System.out.printf("Escolha um diretor de %d - %d\n", 0, nomes_de_diretores.size() - 1);
                        int diretor_escolhido = ScannerSingleton.instance().getScanner().nextInt();

                        Diretor diretorSelecionado = diretoresEncontrados.get(diretor_escolhido);


                        final String[] opcoes_edicao_ocupacoes = {"Adicionar ocupação", "Remover ocupação"};
                        menu.change_menu("Selecione a operação desejada:", opcoes_edicao_ocupacoes);
                        menu.show_menu();
                        int operacao = ScannerSingleton.instance().getScanner().nextInt();

                        switch (operacao) {
                            case 0:
                                ScannerSingleton.instance().clean_scanner();
                                System.out.println("Digite a ocupação a ser adicionada:");
                                String ocupacaoAdicionada = ScannerSingleton.instance().getScanner().nextLine();
                                diretorSelecionado.addOcupacao(ocupacaoAdicionada);
                                RepositorioSingleton.instance().getBancoDeDiretores().gravar(diretorSelecionado);
                                break;
                            case 1:
                                ScannerSingleton.instance().clean_scanner();
                                System.out.println("Digite a ocupação a ser removida:");
                                String ocupacaoRemovida = ScannerSingleton.instance().getScanner().nextLine();
                                diretorSelecionado.removeOcupacao(ocupacaoRemovida);
                                RepositorioSingleton.instance().getBancoDeDiretores().gravar(diretorSelecionado);
                                break;
                            default:
                                System.out.println("Opção inválida, por favor, selecione uma das opções do menu.");
                                break;
                        }
                        break;
                    default:
                        System.out.println("Opção inválida, por favor, selecione uma das opções do menu.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, insira um número inteiro correspondente à opção do menu.");
                ScannerSingleton.instance().clean_scanner();
            }
        }
    }

    private static void consulta_binaria(String buscarPor, String nome) {
        List<String> elementos_normais = new ArrayList<>();
        List<String> elementos_por_filme = new ArrayList<>();

        if (buscarPor.equals("Diretores")) {
            List<Diretor> diretores = RepositorioSingleton.instance().getBancoDeDiretores().obterDiretorPorNome(nome);
            for (Diretor diretor : diretores) {
                elementos_normais.add(diretor.toString());
            }

            List<Diretor> filmesPorDiretor = RepositorioSingleton.instance().getBancoDeFilmes().obterDiretorPorNomeDoFilme(nome);
            for (Diretor filme : filmesPorDiretor) {
                elementos_por_filme.add(filme.toString());
            }
        } else {
            List<Ator> atores = RepositorioSingleton.instance().getBancoDeAtores().obterAtorPorNome(nome);
            for (Ator ator : atores) {
                elementos_normais.add(ator.toString());
            }

            List<Ator> filmesPorAtor = RepositorioSingleton.instance().getBancoDeFilmes().obterAtorPorNomeDoFilme(nome);
            for (Ator filme : filmesPorAtor) {
                elementos_por_filme.add(filme.toString());
            }
        }

        if (!elementos_normais.isEmpty()) {
            menu.change_menu(buscarPor + " encontrados", elementos_normais);
        } else {
            menu.change_menu("Não há " + buscarPor + " com este nome", (String[]) null);
        }
        menu.show_menu();

        if (!elementos_por_filme.isEmpty()) {
            menu.change_menu(buscarPor + " encontrados em Filmes", elementos_por_filme);
        } else {
            menu.change_menu("Não há Filmes com este nome", (String[]) null);
        }
        menu.show_menu();
    }

    private static void consultar_filmes(String nome){
        List<String> filmes = RepositorioSingleton.instance().getBancoDeFilmes().consultarPorNomeDoFilme(nome);
        if (!filmes.isEmpty()){
            menu.change_menu("Filmes encontrados", filmes);
        }else{
            menu.change_menu("Não há filmes cadastrados com este nome", (String[]) null);
        }
        menu.show_menu();
    }

    private static void sair_e_salvar() {
        ScannerSingleton.instance().clean_scanner();
        System.out.println("Deseja salvar as mudanças na base de dados? (s/n)");
        String save_input = ScannerSingleton.instance().getScanner().nextLine().toLowerCase();
        if (save_input.equals("s")){RepositorioSingleton.instance().salvar_dados();}
    }
}