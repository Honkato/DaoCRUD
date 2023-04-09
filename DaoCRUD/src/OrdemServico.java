import java.util.List;

public class OrdemServico {

    private int codigo;
    private String data;
    private int idPrestador;
    private List<Integer> tipoServico;
    private int idUnidade;
    private float valor;
    private String status;
    private String dataFechamento;

    public final int getCodigo() {
        return codigo;
    }
    public final void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public final String getData() {
        return data;
    }
    public final void setData(String data) {
        this.data = data;
    }
    public final int getIdPrestador() {
        return idPrestador;
    }
    public final void setIdPrestador(int prestador) {
        this.idPrestador = prestador;
    }
    public final List<Integer> getTipoServico() {
        return tipoServico;
    }
    public final void setTipoServico(List<Integer> tipoServico) {
        this.tipoServico = tipoServico;
    }
    public final int getIdUnidade() {
        return idUnidade;
    }
    public final void setIdUnidade(int unidade) {
        this.idUnidade = unidade;
    }
    public final float getValor() {
        return valor;
    }
    public final void setValor(float valor) {
        this.valor = valor;
    }
    public final String getStatus() {
        return status;
    }
    public final void setStatus(String status) {
        this.status = status;
    }
    public final String getDataFechamento() {
        return dataFechamento;
    }
    public final void setDataFechamento(String dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

}
