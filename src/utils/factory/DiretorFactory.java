package utils.factory;

import objects.Diretor;
import utils.ScannerSingleton;

import java.util.ArrayList;
import java.util.List;

public class DiretorFactory {
    private static final String[] PARAMETROS_OBRIGATORIOS = {"Nome", "Data de Nascimento no formato (dd-MM-yyyy)", "Local de Nascimento"};
    public static Diretor create(){
        List<String> data = new ArrayList<>();
        List<String> ocuppation_data = new ArrayList<>();
        for (String parametro : PARAMETROS_OBRIGATORIOS){
            System.out.println("Informe o " + parametro + " do Diretor: ");
            data.add(ScannerSingleton.instance().getScanner().nextLine());
        }
        String query = "";
        while (!query.equals("0")){
            System.out.println("Escreva ocupações adicionais do diretor ou '0' para encerrar a adição de ocupações: ");
            query = ScannerSingleton.instance().getScanner().nextLine();
            if (!query.equals("0")){
                ocuppation_data.add(query);
            }
        }
        return new Diretor(data.get(0), data.get(1), data.get(2), ocuppation_data);
    }
}
