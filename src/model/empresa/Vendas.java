package model.empresa;
import model.empregados.*;
import java.time.LocalDate;
import model.empregados.*;

public class Vendas extends FolhaDePagamento{
    private Double percentualDeVendas;
    private LocalDate dataDeVenda;
    private Double valorDeVenda;
    private Double comissao;

    public Vendas(Empresa empresa, Empregado empregado,Salario salario, String cronograma, Double percentualDeVendas,LocalDate dataDeVenda, Double valorDeVenda, Double commissao){
        this.empresa = empresa;
        this.empregado = empregado;
        this.salario = salario;
        this.percentualDeVendas = percentualDeVendas;

    }
}
