package objects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Artista {
    public long id;
    private final String nome;

    private final LocalDate dataDeNascimento;
    private final String localNascimento;

    public Artista(String nome, String dataDeNascimento, String localNascimento){
        this.nome = nome;
        this.dataDeNascimento = LocalDate.parse(dataDeNascimento, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.localNascimento = localNascimento;
    }

    public String getNome(){
        return this.nome;
    }

    public String getLocalNascimento(){
        return this.localNascimento;
    }

    public LocalDate getDataNascimento() {
        return dataDeNascimento;
    }

    public String getDataNascimentoFormatada() { //Por propósitos do save
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return dataDeNascimento.format(formatter);
    }

    public Long getId() {
        return id;
    }
}
