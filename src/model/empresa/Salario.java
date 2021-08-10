package model.empresa;
import java.util.List;

import model.empregados.*;

public class Salario extends FolhaDePagamento{
    public Double valor;
    public Double taxas;
    public Double horasExtras;
    public Vendas vendas;

    public Salario(Empresa empresa, Empregado empregado, Salario salario, String cronograma, Double valor, Double taxas,Double horasExtras, Vendas vendas) {
        super(empresa, empregado, salario, cronograma);
        this.valor = valor;
        this.taxas = taxas;
        this.horasExtras = horasExtras;
        this.vendas = vendas;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getTaxas() {
        return taxas;
    }

    public void setTaxas(Double taxas) {
        this.taxas = taxas;
    }

    public Double getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(Double horasExtras) {
        this.horasExtras = horasExtras;
    }

    public Vendas getVendas() {
        return vendas;
    }

    public void setVendas(Vendas vendas) {
        this.vendas = vendas;
    }
    
}
