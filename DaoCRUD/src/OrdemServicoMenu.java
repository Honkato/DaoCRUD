import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrdemServicoMenu {

    public void menu(Scanner sc){
        while(true){
//            System.out.println("""
//                    Cadastro de Ordem de Serviço
//                    1 - Lista
//                    2 - Incluir
//                    3 - Atualizar
//                    4 - Excluir
//                    0 - Sair
//                    """);
            System.out.println("Cadastro de Ordem de Servico");
            System.out.println("1 - Lista");
            System.out.println("2 - Incluir");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Sair");
            String opcao = sc.nextLine();
            if (opcao.equals("1")){
                listar();
            } else if (opcao.equals("2")) {
                incluir(sc);
            } else if (opcao.equals("3")) {
                atualizar(sc);
            } else if (opcao.equals("4")) {
                excluir(sc);
            } else if (opcao.equals("0")) {
                break;
            }else {
                System.out.println("Opção não conhecida: " + opcao);
            }
        }
    }

    private void listar(){
        OrdemServicoDAO dao = new OrdemServicoDAO();
        List<OrdemServico> list = dao.getList();
        for (OrdemServico o : list){
            System.out.println(
                      "Codigo: " + o.getCodigo()
                    + " | Data: " + o.getData()
                    + " | Prestador: " + o.getIdPrestador()
                    + " | TipoServico: " + o.getTipoServico()
                    + " | Unidade: " + o.getIdUnidade()
                    + " | Valor: " + o.getValor()
                    + " | Status: " + o.getStatus()
                    + " | DataFechamento: " + o.getDataFechamento());
        }
    }
    private void incluir(Scanner sc){
        int cod = 0;
        int prest = 0;
        int uni = 0;
        float val = 0;
        System.out.println("INCLUIR UMA ORDEM DE SERVIÇO:");
        System.out.println("Codigo:");
        try{
            String codigo = sc.nextLine();
            System.out.println("cod");
            if (validarStr(codigo, "Codigo")){
                return;
            }
            cod = Integer.parseInt(codigo);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Codigo deve ser um numero");
            return;
        }
        System.out.println("Data:");
        String data = sc.nextLine();
        if (validarStr(data, "Data")){
            return;
        }
        System.out.println("Prestador:");
        try{
            String prestador = sc.nextLine();
            if (validarStr(prestador, "Prestador")){
                return;
            }
            prest = Integer.parseInt(prestador);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Prestador deve ser um numero");
            return;
        }

        System.out.println("TipoServico:");
        System.out.println("Digite ao menos 1 tipo de serviço");
        List<Integer> listaServicos = new ArrayList<>();
        while (true) {
            System.out.println("Para parar digite um caracter não numerico");
            try {
                String tipoServico = sc.nextLine();
                if (validarStr(tipoServico, "Tipo Serviço")) {
                    return;
                }
                int tipoServ = Integer.parseInt(tipoServico);
                listaServicos.add(tipoServ);
            } catch (Exception e) {
                if (listaServicos.size() == 0){
                    System.out.println("Adicione ao menos 1 serviço");
                    continue;
                }
                // e.printStackTrace();
                // System.out.println("Codigo deve ser um numero");
                break;
            }
        }
        System.out.println("Unidade:");
        try{
            String unidade = sc.nextLine();
            if (validarStr(unidade, "Unidade")){
                return;
            }
            uni = Integer.parseInt(unidade);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Unidade deve ser um numero");
            return;
        }
        System.out.println("Valor:");
        try{
            String valor = sc.nextLine();
            if (validarStr(valor, "Valor")){
                return;
            }
            val = Float.parseFloat(valor);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Unidade deve ser um numero");
            return;
        }
        System.out.println("Status:");
        String status = sc.nextLine();
        System.out.println("DataFechamento:");
        String dataFechamento = sc.nextLine();

        OrdemServico o = new OrdemServico();
        o.setCodigo(cod);
        o.setData(data);
        o.setDataFechamento(dataFechamento);
        o.setStatus(status);
        o.setIdUnidade(uni);
        o.setValor(val);
        o.setIdPrestador(prest);
        o.setTipoServico(listaServicos);

        OrdemServicoDAO dao = new OrdemServicoDAO();
        System.out.println(o.getCodigo());
        dao.inserir(o);
    }
    private Boolean validarStr(String val, String nome){
        if (val == null || val.trim().length() == 0){
            System.out.println(nome + "inválido.");
            return true;
        }
        return false;
    }

    private void atualizar(Scanner sc){
        int cod = 0;
        int prest = 0;
        int uni = 0;
        float val = 0;
        System.out.println("ATUALIZAR UMA ORDEM DE SERVIÇO");
        System.out.println("Informe o Código:");
        String codigo = sc.nextLine();

        if (codigo == null
                || codigo.trim().length() == 0) {
            System.out.println("Codigo invalido.");
            return;
        }
        try {
            cod = Integer.parseInt(codigo);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Codigo deve ser numerico");
            return;
        }
        OrdemServicoDAO dao = new OrdemServicoDAO();
        OrdemServico o = dao.getOrdemServico(cod);
        if (o==null){
            System.out.println("Ordem de serviço não encontrada");
            return;
        }
        System.out.println("Data ("+o.getData()+"):");
        String data = sc.nextLine();
        if (validarStr(data, "Data")){
            return;
        }
        System.out.println("Prestador ("+o.getIdPrestador()+"):");
        try{
            String prestador = sc.nextLine();
            if (validarStr(prestador, "Prestador")){
                return;
            }
            prest = Integer.parseInt(prestador);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Prestador deve ser um numero");
            return;
        }

        System.out.println("TipoServico ("+o.getTipoServico()+"):");
        System.out.println("Digite ao menos 1 tipo de serviço");
        List<Integer> listaServicos = new ArrayList<>();
        while (true) {
            System.out.println("Para parar digite um caracter não numerico");
            try {
                String tipoServico = sc.nextLine();
                if (validarStr(tipoServico, "Tipo Serviço")) {
                    return;
                }
                int tipoServ = Integer.parseInt(tipoServico);
                listaServicos.add(tipoServ);
            } catch (Exception e) {
                if (listaServicos.size() == 0){
                    System.out.println("Adicione ao menos 1 serviço");
                    continue;
                }
                // e.printStackTrace();
                // System.out.println("Codigo deve ser um numero");
                break;
            }
        }
        System.out.println("Unidade ("+o.getIdUnidade()+")");
        try{
            String unidade = sc.nextLine();
            if (validarStr(unidade, "Unidade")){
                return;
            }
            uni = Integer.parseInt(unidade);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Unidade deve ser um numero");
            return;
        }
        System.out.println("Valor ("+o.getValor()+")");
        try{
            String valor = sc.nextLine();
            if (validarStr(valor, "Valor")){
                return;
            }
            val = Float.parseFloat(valor);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Unidade deve ser um numero");
            return;
        }
        System.out.println("Status ("+o.getStatus()+")");
        String status = sc.nextLine();
        System.out.println("DataFechamento ("+o.getDataFechamento()+")");
        String dataFechamento = sc.nextLine();

        o.setCodigo(cod);
        o.setData(data);
        o.setDataFechamento(dataFechamento);
        o.setStatus(status);
        o.setIdUnidade(uni);
        o.setValor(val);
        o.setIdPrestador(prest);
        o.setTipoServico(listaServicos);

        dao.atualizar(o);
    }

    private void excluir(Scanner sc) {
        System.out.println("Atualizar uma ordem de serviço:");
        System.out.println("Informe o Codigo:");
        String codigo = sc.nextLine();

        if (codigo == null
                || codigo.trim().length() == 0) {
            System.out.println("Codigo invalido.");
            return;
        }
        int cod = 0;
        try {
            cod = Integer.parseInt(codigo);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Codigo deve ser numerico");
            return;
        }
        OrdemServicoDAO dao = new OrdemServicoDAO();
        OrdemServico o = dao.getOrdemServico(cod);
        if (o == null) {
            System.out.println("Prestador nao encontrado:" + cod);
            return;
        }
        dao.excluir(o);
    }

}
