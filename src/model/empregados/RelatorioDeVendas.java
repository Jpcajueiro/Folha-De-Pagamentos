package model.empregados;
import java.time.LocalDate;

public class RelatorioDeVendas {
    private LocalDate dataDeVenda;
    private int valorDeVenda;
    
    public RelatorioDeVendas(LocalDate dataDeVenda, int valorDeVenda) {
        this.dataDeVenda = dataDeVenda;
        this.valorDeVenda = valorDeVenda;
    }

    public LocalDate getDataDeVenda() {
        return dataDeVenda;
    }

    public void setDataDeVenda(LocalDate dataDeVenda) {
        this.dataDeVenda = dataDeVenda;
    }

    public int getValorDeVenda() {
        return valorDeVenda;
    }

    public void setValorDeVenda(int valorDeVenda) {
        this.valorDeVenda = valorDeVenda;
    }
}
