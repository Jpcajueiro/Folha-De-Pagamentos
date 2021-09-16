package model.empregados;
import java.time.LocalDate;
import java.time.LocalTime;

public class CartaoDePonto {
    private LocalDate data;
    private LocalTime horarioEntrada;
    private LocalTime horarioSaida;

    public CartaoDePonto(LocalDate data,LocalTime horarioEntrada, LocalTime horarioSaida) {
        this.data = data;
        this.horarioEntrada = horarioEntrada;
        this.horarioSaida = horarioSaida;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHorarioEntrada() {
        return horarioEntrada;
    }

    public void setHorarioEntrada(LocalTime horarioEntrada) {
        this.horarioEntrada = horarioEntrada;
    }

    public LocalTime getHorarioSaida() {
        return horarioSaida;
    }

    public void setHorarioSaida(LocalTime horarioSaida) {
        this.horarioSaida = horarioSaida;
    }
}