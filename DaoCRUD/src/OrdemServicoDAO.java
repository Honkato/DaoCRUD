import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;


public class OrdemServicoDAO {

    private static final String ORDEM_SERVICO_TXT = "OrdemServico.txt";

    public List<OrdemServico> getList(){
        List<OrdemServico> list = new ArrayList<>();
        try {
            FileReader fr = new FileReader(ORDEM_SERVICO_TXT);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
                String[] var = line.split(";");
                OrdemServico o = new OrdemServico();
                o.setCodigo(Integer.parseInt(var[0]));
                o.setData(var[1]);
                o.setIdPrestador(Integer.parseInt(var[2]));
                String[] servicos = var[3].replace(" ","").replace("[","").replace("]","").split(",");
                List<Integer> listaServicos = new ArrayList<>();
                for(String i : servicos){
                    listaServicos.add(Integer.parseInt(i));
                }
                o.setTipoServico(listaServicos);
                o.setIdUnidade(Integer.parseInt(var[4]));
                o.setValor(Float.parseFloat(var[5]));
                o.setStatus(var[6]);
                o.setDataFechamento(var[7]);
                list.add(o);
            }
            br.close();
            fr.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public OrdemServico getOrdemServico(int cod){
        List<OrdemServico> list = getList();
        for (OrdemServico o : list){
            if (o.getCodigo() == cod){
                return o;
            }
        }
        return null;
    }
    public void inserir(OrdemServico o){
        try{
            FileWriter fw =
                    new FileWriter(ORDEM_SERVICO_TXT, true);
            String line = getLine(o);
            fw.write(line+"\n");
            fw.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private String getLine(OrdemServico o){
        String line = o.getCodigo()
                + ";" + o.getData()
                + ";" + o.getIdPrestador()
                + ";" + o.getTipoServico()
                + ";" + o.getIdUnidade()
                + ";" + o.getValor()
                + ";" + o.getStatus()
                + ";" + o.getDataFechamento();
        return line;
    }

    public void atualizar(OrdemServico oAtualizar){
        List<OrdemServico> list = getList();

        try{
            FileWriter fw =
                    new FileWriter(ORDEM_SERVICO_TXT, false);
            for (OrdemServico o : list){
                String line = getLine(o);
                if (o.getCodigo() == oAtualizar.getCodigo()){
                    line = getLine(oAtualizar);
                }
                fw.write(line+"\n");
            }
            fw.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void excluir(OrdemServico oExcluir){
        List<OrdemServico> list = getList();

        try{
            FileWriter fw =
                    new FileWriter(ORDEM_SERVICO_TXT, false);
            for (OrdemServico o : list){
                String line = getLine(o);
                if (o.getCodigo() == oExcluir.getCodigo()){
                    continue;
                }
                fw.write(line+"\n");
            }
            fw.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
