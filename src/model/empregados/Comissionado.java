package model.empregados;
import java.util.List;
import model.empresa.*;
import java.util.*;

public class Comissionado extends Empregado{
    private int salario;
    private int comissao;
    private List<RelatorioDeVendas> relatorioDeVendas;

    public Comissionado(String name, UUID id,String endereco,int tipoDeEmpregado,Sindicato sindicato, Pagamento pagamento, String diaDoPagamento,int salario, int comissao, List<RelatorioDeVendas> relatorioDeVendas) {
        super(name, id, endereco, tipoDeEmpregado, sindicato, pagamento, diaDoPagamento);
        this.salario = salario;
        this.comissao = comissao;
        this.relatorioDeVendas = new ArrayList<RelatorioDeVendas>();
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public int getComissao() {
        return comissao;
    }

    public void setComissao(int comissao) {
        this.comissao = comissao;
    }

    public List<RelatorioDeVendas> getRelatorioDeVendas() {
        return relatorioDeVendas;
    }

    public void setRelatorioDeVendas(List<RelatorioDeVendas> relatorioDeVendas) {
        this.relatorioDeVendas = relatorioDeVendas;
    }
    
}
