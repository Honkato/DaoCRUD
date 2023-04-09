import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Persist�ncia em Arquivo.
 * 
 * @author Mateus Marques
 */

public class UnidadeEmpresaDAO {

    /** Nome do arquivo comum a todas as opera��es. */
    private static final String UNIDADEEMPRESA_TXT = "UnidadeEmpresa.txt";

    /**
     * Retorna todos os Unidade Empresaes em forma de lista.
     * 
     * @return lista.
     */
    public List<UnidadeEmpresa> getList() {
        List<UnidadeEmpresa> list = new ArrayList<UnidadeEmpresa>();
        try {
            FileReader fr = new FileReader(UNIDADEEMPRESA_TXT);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] var = line.split(";");
                UnidadeEmpresa p = new UnidadeEmpresa();
                p.setCodigo(Integer.parseInt(var[0]));
                p.setNome(var[1]);
                p.setResponsaval(var[2]);
                p.setTipo(var[3]);
                list.add(p);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Retorna um prestador para seu c�digo.
     * 
     * @param cod c�digo.
     * @return Prestador se existir.
     */
    public UnidadeEmpresa getUnidadeEmpresa(int cod) {
        List<UnidadeEmpresa> list = getList();
        for (UnidadeEmpresa p : list) {
            if (p.getCodigo() == cod) {
                return p;
            }
        }
        return null;
    }

    /**
     * Insere uma Unidade Empresa.
     * O arquivo � aberto para leitura, por�m, com append=true
     * para escrever no final do arquivo.
     * 
     * @param p novo Unidade Empresa.
     */
    public void inserir(UnidadeEmpresa p) {
        try {
            FileWriter fw = new FileWriter(UNIDADEEMPRESA_TXT, true);
            String line = getLine(p);
            fw.write(line);
            fw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * M�todo auxiliar para manter o padr�o do arquivo.
     * 
     * @param p Unidade Empresa.
     * @return linha do banco.
     */
    private String getLine(UnidadeEmpresa p) {
        String line = p.getCodigo()
                + ";" + p.getNome()
                + ";" + p.getResponsaval()
                + ";" + p.getTipo()
                + "\r\n";
        return line;
    }

    /**
     * Atualiza um Unidade Empresa.
     *
     * Em arquivo, essa opera��o complexa, pois n�o se pode
     * escrever no meio do arquivo. O que se faz � escrever
     * todo o arquivo novamente.
     *
     * @param p novo Unidade Empresa.
     */
    public void atualizar(UnidadeEmpresa pAtualizar) {

        // Recupera todo os Unidade Empresaes.
        List<UnidadeEmpresa> list = getList();

        try {
            // Abrir o arquivo para escrita, reescrevendo tudo.
            FileWriter fw = new FileWriter(UNIDADEEMPRESA_TXT, false);
            for (UnidadeEmpresa p : list) {
                String line = getLine(p);
                if (p.getCodigo() == pAtualizar.getCodigo()) {
                    line = getLine(pAtualizar);
                }
                fw.write(line);
            }
            fw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Excluir um Unidade Empresa.
     *
     * Em arquivo, essa opera��o complexa, pois n�o se pode
     * escrever no meio do arquivo. O que se faz � escrever
     * todo o arquivo novamente.
     *
     * @param p novo Unidade Empresa.
     */
    public void excluir(UnidadeEmpresa pExcluir) {

        // Recupera todo os Unidade Empresaes.
        List<UnidadeEmpresa> list = getList();

        try {
            // Abrir o arquivo para escrita, reescrevendo tudo.
            FileWriter fw = new FileWriter(UNIDADEEMPRESA_TXT, false);
            for (UnidadeEmpresa p : list) {
                String line = getLine(p);
                if (p.getCodigo() == pExcluir.getCodigo()) {
                    continue; // Nada a fazer, somente n�o escreve no arquivo.
                }
                fw.write(line);
            }
            fw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}