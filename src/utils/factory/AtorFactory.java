package utils.factory;

import objects.Ator;
import utils.ScannerSingleton;

import java.util.ArrayList;
import java.util.List;

public class AtorFactory {
    private static final String[] PARAMETROS_OBRIGATORIOS = {"Nome", "Data de Nascimento no formato (dd-MM-yyyy)", "Local de Nascimento"};
    public static Ator create(){
        List<String> data = new ArrayList<>();
        for (String parametro : PARAMETROS_OBRIGATORIOS){
            System.out.println("Informe o " + parametro + " do Ator: ");
            data.add(ScannerSingleton.instance().getScanner().nextLine());
        }
        System.out.println("O ator possui um pseudônimo? (s/n): ");
        String query = ScannerSingleton.instance().getScanner().nextLine();
        if (query.equalsIgnoreCase("s")){
            System.out.println("Digite o pseudônimo do Ator: ");
            String pseudonimo = ScannerSingleton.instance().getScanner().nextLine();
            data.add(pseudonimo);
        }else{
            data.add("");
        }
        return new Ator(data.get(0), data.get(1), data.get(2), data.get(3));
    }
}
