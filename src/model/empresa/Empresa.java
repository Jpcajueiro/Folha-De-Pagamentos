package model.empresa;
import java.util.*;
import model.empregados.*;

public class Empresa extends FolhaDePagamento{
    public String name;
    private int telefone;
    private UUID id;
    private String endereco;

    public Empresa(Empresa empresa, Empregado empregado, Salario salario, List<String> cronograma) {
        super();
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
