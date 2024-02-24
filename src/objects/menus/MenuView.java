package objects.menus;

import java.util.List;

public class MenuView {
    public String[] opcoes;
    public String titulo;

    public MenuView(String titulo, String[] opcoes){
        this.titulo = titulo;
        this.opcoes = opcoes;
    }

    public void show_menu(){
        System.out.printf(">>>>> %s <<<<<\n", this.titulo);
        if (this.opcoes==null){
            return;
        }
        for (int i = 0; i < this.opcoes.length; i++){
            System.out.printf("%d - %s\n", i, this.opcoes[i]);
        }
    }

    public void change_menu(String titulo, String[] opcoes){
        this.titulo = titulo;
        this.opcoes = opcoes;
    }

    public void change_menu(String titulo, List<String> opcoes){
        this.titulo = titulo;
        this.opcoes = opcoes.toArray(new String[0]);
    }
   
}
