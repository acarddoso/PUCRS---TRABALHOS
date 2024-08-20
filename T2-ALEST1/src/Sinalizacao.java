import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Sinalizacao {

    private String local;
    private LocalDate data;
    public Sinalizacao proximo;

    public Sinalizacao(String local, LocalDate data){
        this.data = data;
        this.local = local;
    }

    public String getLocal() {
        return local;
    }

    public Sinalizacao getProximo() {
        return proximo;
    }

    public void setProximo(Sinalizacao proximo) {
        this.proximo = proximo;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
