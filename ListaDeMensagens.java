import java.util.ArrayList;
import java.util.List;

public class ListaDeMensagens {
    private List<Mensagem> mensagens;

    // Construtor, getters e setters
    public ListaDeMensagens(){
        this.mensagens = new ArrayList<Mensagem>();
    }

    public void adiciona(String texto, String hora) {
        mensagens.add(new Mensagem(texto, hora));
    }

    public void print_mensagens() {
        for (Mensagem mensagem : mensagens) {
            
        }
    }
}

