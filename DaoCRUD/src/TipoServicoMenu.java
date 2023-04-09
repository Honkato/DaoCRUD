import java.util.List;
import java.util.Scanner;

public class TipoServicoMenu {

    public void menu(Scanner sc) {
        while(true) {
            System.out.println("Cadastro de um tipo de serviço: ");
            System.out.println("1 - Listar");
            System.out.println("2 - Incluir");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");
            String opcao = sc.nextLine();
            if (opcao.equals("1")) {
                listar();
            } else if (opcao.equals("2")) {
                incluir(sc);
            } else if (opcao.equals("3")) {
                atualizar(sc);
            } else if (opcao.equals("4")) {
                excluir(sc);
            } else if (opcao.equals("0")) {
                break;
            } else {
                System.out.println("Opcao nao conhecida" + opcao);
            }
        }

    }

    private void listar() {
        TipoServicoDAO dao = new TipoServicoDAO();
        List<TipoServico> list = dao.getList();
        for (TipoServico p : list) {
            System.out.println("Codigo:" + p.getCodigo()
                    + " | Nome:" + p.getNome()
                    + " | Valor:" + p.getValor());
        }
    }

    private void incluir(Scanner sc) {
        System.out.println("Incluir um servico:");
        System.out.println("C�digo:");
        String codigo = sc.nextLine();
        System.out.println("Nome:");
        String nome = sc.nextLine();
        System.out.println("Valor:");
        String valor = sc.nextLine();

        if (codigo == null
                || codigo.trim().length() == 0) {
            System.out.println("C�digo inv�lido.");
            return;
        }
        if (nome == null
                || nome.trim().length() == 0) {
            System.out.println("Nome inv�lido.");
            return;
        }
        int cod = 0;
        try {
            cod = Integer.parseInt(codigo);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("C�digo deve ser num�rico");
            return;
        }

        TipoServico p = new TipoServico();
        p.setCodigo(cod);
        p.setNome(nome);
        p.setValor(Float.parseFloat(valor));

        TipoServicoDAO dao = new TipoServicoDAO();
        dao.inserir(p);
    }

    private void atualizar(Scanner sc) {
        System.out.println("Atualizar um prestador:");
        System.out.println("Informe o C�digo:");
        String codigo = sc.nextLine();

        if (codigo == null
                || codigo.trim().length() == 0) {
            System.out.println("C�digo inv�lido.");
            return;
        }
        int cod = 0;
        try {
            cod = Integer.parseInt(codigo);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("C�digo deve ser num�rico");
            return;
        }
        TipoServicoDAO dao = new TipoServicoDAO();
        TipoServico p = dao.getServico(cod);
        if (p == null) {
            System.out.println("Servico nao encontrado:" + cod);
            return;
        }

        System.out.println("Nome (" + p.getNome() +"):");
        String nome = sc.nextLine();
        System.out.println("Codigo(" + p.getCodigo() +"):");
        String codig = sc.nextLine();
        System.out.println("Valor(" + p.getValor() +"):");
        String valor = sc.nextLine();
        p.setNome(nome);
        p.setCodigo(Integer.parseInt(codig));
        p.setValor(Float.parseFloat(valor));
        dao.atualizar(p);
    }

    private void excluir(Scanner sc) {
        System.out.println("Exluir um servico:");
        System.out.println("Informe o C�digo:");
        String codigo = sc.nextLine();

        if (codigo == null
                || codigo.trim().length() == 0) {
            System.out.println("C�digo inv�lido.");
            return;
        }
        int cod = 0;
        try {
            cod = Integer.parseInt(codigo);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("C�digo deve ser num�rico");
            return;
        }
        TipoServicoDAO dao = new TipoServicoDAO();
        TipoServico p = dao.getServico(cod);
        if (p == null) {
            System.out.println("Servico n�o encontrado:" + cod);
            return;
        }
        dao.excluir(p);
    }


}
