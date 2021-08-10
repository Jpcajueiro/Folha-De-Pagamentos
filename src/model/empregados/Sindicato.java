package model.empregados;
import java.util.*;

public class Sindicato {
    private int taxaMensal;
    private int taxaDeServico;
    private UUID idSindicato;
    private int membro;

    public Sindicato(int taxaMensal,int taxaDeServico, UUID idSindicato,int membro) {
        this.taxaMensal = taxaMensal;
        this.taxaDeServico = taxaDeServico;
        this.idSindicato= idSindicato;
        this.membro = membro;
    }

    public int getTaxaMensal() {
        return taxaMensal;
    }

    public void setTaxaMensal(int taxaMensal) {
        this.taxaMensal = taxaMensal;
    }

    public int getTaxaDeServico() {
        return taxaDeServico;
    }

    public void setTaxaDeServico(int taxaDeServico) {
        this.taxaDeServico = taxaDeServico;
    }

    public UUID getIdSindicato() {
        return idSindicato;
    }

    public void setIdSindicato(UUID idSindicato) {
        this.idSindicato = idSindicato;
    }

    public int getMembro() {
        return membro;
    }

    public void setMembro(int membro) {
        this.membro = membro;
    }

}
