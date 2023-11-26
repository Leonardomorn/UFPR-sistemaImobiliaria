public class Conversa {
    private ListaDeMensagens mensagens;
    private Pessoa cliente;
    private Pessoa proprietario;

    // Construtor, getters e setters
    public Conversa(Pessoa cli, Pessoa prop) {
        this.mensagens = new ListaDeMensagens();
        this.cliente = cli;
        this.proprietario = prop;
    }

    public Pessoa getCliente() {
       return cliente;
    }

    public ListaDeMensagens getMensagens() {
       return mensagens;
    }

    public Pessoa getProprietario() {
       return proprietario;
    }

    public void print_mensagens() {
      mensagens.print_mensagens();
    }
}

