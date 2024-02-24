package utils;

import objects.Genero;

public class Metodos {
   public static boolean contains_ignoring_case(String texto, String sentenca){
        texto = texto.toLowerCase();
        sentenca = sentenca.toLowerCase();
        return sentenca.contains(texto);
    }

    public static Genero getGeneroFromString(String genero){
        genero = genero.toLowerCase();
        switch (genero){
            case "terror":
                return Genero.TERROR;
            case "suspense":
                return Genero.SUSPENSE;
            case "acao": case "ação":
                return Genero.ACAO;
            case "aventura":
                return Genero.AVENTURA;
            case "infantil":
                return Genero.INFANTIL;
            default:
                return Genero.DESCONHECIDO;
        }
    }
}
