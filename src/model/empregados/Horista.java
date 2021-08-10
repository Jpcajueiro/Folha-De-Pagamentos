package model.empregados;
import java.util.List;
import java.util.*;
import model.empresa.*;

public class Horista extends Empregado{
    private int salarioPorHora;
    private List<CartaoDePonto> cartaoDePonto;
    
    public Horista(String name, UUID id,String endereco,int tipoDeEmpregado,Sindicato sindicato, Pagamento pagamento, String diaDoPagamento,int salarioPorHora) {
        super(name, id, endereco, tipoDeEmpregado, sindicato, pagamento, diaDoPagamento);
        this.salarioPorHora = salarioPorHora;
        this.cartaoDePonto = new ArrayList<CartaoDePonto>();
    }

    public int getSalarioPorHora() {
        return salarioPorHora;
    }

    public void setSalarioPorHora(int salarioPorHora) {
        this.salarioPorHora = salarioPorHora;
    }

    public List<CartaoDePonto> getCartaoDePonto() {
        return cartaoDePonto;
    }

    public void setCartaoDePonto(List<CartaoDePonto> cartaoDePonto) {
        this.cartaoDePonto = cartaoDePonto;
    }


}
