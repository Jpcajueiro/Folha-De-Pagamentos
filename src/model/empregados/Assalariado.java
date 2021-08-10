package model.empregados;
import java.util.*;
import model.empresa.*;

public class Assalariado extends Empregado{
    private int salario;

    public Assalariado(String name, UUID id,String endereco,int tipoDeEmpregado,Sindicato sindicato, Pagamento pagamento, String diaDoPagamento,int salario) {
        super(name, id, endereco, tipoDeEmpregado, sindicato, pagamento, diaDoPagamento);
        this.salario = salario;
    }
    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }
}
