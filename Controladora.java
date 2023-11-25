import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class Controladora {
    private ListaDeImoveis listaDeImoveis = new ListaDeImoveis();
    private ListaDePessoas listaDePessoas = new ListaDePessoas();
    private Pessoa usuarioLogado;

    // Métodos para interação front-back
    public List<String> getUsuarios() {
        this.listaDePessoas.iniciaUsers();
        return this.listaDePessoas.getNomes();
    }

    public void Login(String usuario) {
        this.usuarioLogado = listaDePessoas.getPessoa(usuario);
        System.out.println("Logado como: " + usuario);
    }
}

