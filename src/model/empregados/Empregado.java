package model.empregados;
import java.util.*;

import model.empresa.Pagamento;


public class Empregado{
    private String name;
    private UUID id;
    private String endereco;
    private Sindicato sindicato;
    private Pagamento pagamento;
    // 1 Assalariado - 2 Comissionado - 3 Horista
    private int tipoDeEmpregado;
    private String diaDoPagamento;
    

    public Empregado(String name, UUID id,String endereco,int tipoDeEmpregado,Sindicato sindicato, Pagamento pagamento, String diaDoPagamento){
        this.name = name;
        this.id = id;
        this.endereco = endereco;
        this.tipoDeEmpregado = tipoDeEmpregado;
        this.sindicato = sindicato;
        this.pagamento = pagamento;
        this.diaDoPagamento = diaDoPagamento;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setTipoDeEmpregado(int tipoDeEmpregado) {
        this.tipoDeEmpregado = tipoDeEmpregado;
    }

    public int getTipoDeEmpregado() {
        return tipoDeEmpregado;
    }

    public void setDiaDoPagamento(String diaDoPagamento) {
        this.diaDoPagamento = diaDoPagamento;
    }

    public String getDiaDoPagamento() {
        return diaDoPagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setSindicato(Sindicato sindicato) {
        this.sindicato = sindicato;
    }

    public Sindicato getSindicato() {
        return sindicato;
    }

    public String checarMembro() {
        if(this.getSindicato().getMembro() == 0) return "Não é membro";
        else return "É membro";
    }

    public String checarTipoDeEmpregado(){
        if(getTipoDeEmpregado()==1) return "Assalariado";
        else if (getTipoDeEmpregado() == 2) return "Comissionado";
        else if (getTipoDeEmpregado() == 3) return "Horista";
        else return "Tipo de empregado não informado ou não existente";
    }

    public String printInfoBasica() {
        return "Nome: " + getName() +
        "\n--------------------------------------------\n"+
        "Tipo de Empregado: " + checarTipoDeEmpregado()+
        "\n--------------------------------------------\n"+
        "Sindicato: " + checarMembro() +
        "\n--------------------------------------------\n";
        }

    public String printInfoPagamento(){
        return "Nome: " +getName() +
        "\n--------------------------------------------\n"+
        "Tipo de empregado: " + checarTipoDeEmpregado()+
        "\n--------------------------------------------\n"+
        "Método de pagamento: " + pagamento.printMetodoDePagamento()+
        "\n--------------------------------------------\n"+
        "Dia do pagamento: " + getDiaDoPagamento()+
        "\n--------------------------------------------\n";
        }
    
    public String printEmpregadoInfo(){
        return "============================================\n"+
        "======== Informações do Empregado ==========\n"+
        "--------------------------------------------\n"+
        "Nome: " + getName() +
        "\n--------------------------------------------\n"+
        "Endereço: " + getEndereco() +
        "\n--------------------------------------------\n"+
        "ID: " + getId() +
        "\n--------------------------------------------\n"+
        "Tipo de Empregado: " + checarTipoDeEmpregado()+
        "\n--------------------------------------------\n"+
        "Sindicato: " + checarMembro() +
        "\n--------------------------------------------\n"+
        "ID do sindicato:\n"+ sindicato.getIdSindicato() +
        "\n--------------------------------------------\n"+
        "Taxa de serviço do sindicato: " + sindicato.getTaxaDeServico() +
        "\n--------------------------------------------\n"+
        "Método de Pagamento: " + pagamento.printMetodoDePagamento() +
        "\n--------------------------------------------\n"+
        "Dia do pagamento: " + diaDoPagamento +
        "\n--------------------------------------------\n";   
    }
}
