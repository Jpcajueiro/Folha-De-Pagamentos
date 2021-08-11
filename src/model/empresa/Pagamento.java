package model.empresa;

public class Pagamento {
    private int metodoDePagamento;
    private String banco;
    private int agencia;
    private int conta;

    public Pagamento(int metodoDePagamento,String banco,int agencia, int conta) {
        this.metodoDePagamento = metodoDePagamento;
        this.banco = banco;
        this.agencia = agencia;
        this.conta = conta;
    }

    public int getMetodoDePagamento() {
        return metodoDePagamento;
    }

    public void setMetodoDePagamento(int metodoDePagamento) {
        this.metodoDePagamento = metodoDePagamento;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getConta() {
        return conta;
    }
    
    public void setConta(int conta) {
        this.conta = conta;
    }

    public String printMetodoDePagamento() {
        if(this.getMetodoDePagamento() == 1) return "Em mãos";
        else if (this.getMetodoDePagamento() == 2) return "Via correspondência";
        else if (this.getMetodoDePagamento() == 3) return "Déposito em conta";
        else return "Método de pagamento não existente";
    }
}

