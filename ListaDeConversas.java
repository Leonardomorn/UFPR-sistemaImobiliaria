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
            if (conversa.getProprietario() == dono || conversa.getCliente() == dono)  return conversa;
        }
        return null;
    }

    public void adiciona(Pessoa usuario, Pessoa dono) {
        conversas.add(new Conversa(usuario, dono));
    }

    public int print_conversas(Pessoa usuario) {
        int cont = 0;
        for (Conversa conversa : conversas) {
            if (conversa.getCliente() == usuario){
                System.out.println(conversa.getProprietario().getNome());
                cont++;
            }
            else if (conversa.getProprietario() == usuario){
                System.out.println(conversa.getCliente().getNome());
                cont++;
            }
        }
        return cont;
    }
}

