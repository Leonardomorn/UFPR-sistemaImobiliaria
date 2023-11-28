import java.util.ArrayList;
import java.util.List;

public class ListaDeMensagens {
    private List<Mensagem> mensagens;

    // Construtor, getters e setters
    public ListaDeMensagens(){
        this.mensagens = new ArrayList<Mensagem>();
    }

    public List<Mensagem> getMensagens() {
        return mensagens;
    }

    public void adiciona(String texto, String hora, Pessoa escritor) {
        mensagens.add(new Mensagem(texto, hora, escritor));
    }

    public void print_mensagens() {
        for (Mensagem mensagem : mensagens) {
            System.out.println(mensagem.getEscritor().getNome() + ": " + mensagem.getTexto() + " - " + mensagem.getHoraEnvio());
        }
    }
}

