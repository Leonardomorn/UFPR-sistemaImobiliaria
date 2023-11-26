import java.util.ArrayList;
import java.util.List;

public class ListaDeConversas {
    private List<Conversa> conversas;

    // Construtor, getters e setters
    public ListaDeConversas() {
        this.conversas = new ArrayList<Conversa>();
    }

    public Conversa contem(Pessoa dono) {
        for (Conversa conversa : conversas) {
            if (conversa.getProprietario() == dono)  return conversa;
        }
        return null;
    }
}

