import java.util.Scanner;

public class MainMenu {

    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Controle de Ordem de Servico");
            System.out.println("1 - Prestador");
            System.out.println("2 - Unidade da Empresa");
            System.out.println("3 - Tipo de Servico");
            System.out.println("4 - Ordem de Servico");
            System.out.println("0 - Sair");
            String opcao = sc.nextLine();
            if (opcao.equals("1")) {
                PrestadorMenu p = new PrestadorMenu();
                p.menu(sc);
            } else if (opcao.equals("2")) {
                UnidadeEmpresaMenu u = new UnidadeEmpresaMenu();
                u.menu(sc);
            }else if(opcao.equals("3")){
                TipoServicoMenu t = new TipoServicoMenu();
                t.menu(sc);
            }else if(opcao.equals("4")){
                OrdemServicoMenu o = new OrdemServicoMenu();
                o.menu(sc);
            } else if (opcao.equals("0")) {
                break;
            } else {
                System.out.println("Opcao nao conhecida");
            }
        }
        sc.close();
        System.out.println("Programa encerrado.");
    }

}
