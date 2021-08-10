package model.empresa;
import model.empregados.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class FolhaDePagamento {
    public Empresa empresa;
    public Empregado empregado;
    public Salario salario;
    public List<String> cronograma;

    // função sem argumento só pra criar a lista do cronograma
    public FolhaDePagamento(){
        this.cronograma = new ArrayList<String>();
        this.cronograma.add("Mensal");
        this.cronograma.add("Quinzenal");
        this.cronograma.add("Semanal");
        }

    public FolhaDePagamento(Empresa empresa, Empregado empregado, Salario salario, String cronograma){
        this.empresa = empresa;
        this.empregado = empregado;
        this.salario = salario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void setEmpregado(Empregado empregado) {
        this.empregado = empregado;
    }

    public Empregado getEmpregado() {
        return empregado;
    }

    public Salario getSalario() {
        return salario;
    }

    public void setSalario(Salario salario) {
        this.salario = salario;
    }

    public List<String> getCronograma() {
        return cronograma;
    }

    public void setCronograma(List<String> cronograma) {
        this.cronograma = cronograma;
    }
}
