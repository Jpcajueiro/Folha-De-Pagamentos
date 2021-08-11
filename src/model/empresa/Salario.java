package model.empresa;
import model.empregados.*;

public class Salario extends FolhaDePagamento{
    public int valor;
    public int taxas;
    public int horasExtras;
    public Vendas vendas;

    public Salario(Empresa empresa, Empregado empregado, Salario salario, String cronograma, int valor, int taxas,int horasExtras, Vendas vendas) {
        super(empresa, empregado, salario, cronograma);
        this.valor = valor;
        this.taxas = taxas;
        this.horasExtras = horasExtras;
        this.vendas = vendas;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getTaxas() {
        return taxas;
    }

    public void setTaxas(int taxas) {
        this.taxas = taxas;
    }

    public int getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(int horasExtras) {
        this.horasExtras = horasExtras;
    }

    public Vendas getVendas() {
        return vendas;
    }

    public void setVendas(Vendas vendas) {
        this.vendas = vendas;
    }
    
}
