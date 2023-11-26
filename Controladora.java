import java.io.Console;
import java.util.List;
import java.util.Scanner;

public class Controladora {
    private ListaDeImoveis listaDeImoveis = new ListaDeImoveis();
    private ListaDePessoas listaDePessoas = new ListaDePessoas();
    private Pessoa usuarioLogado;

    // Métodos para interação front-back
    public List<String> get_usuarios() {
        this.listaDePessoas.iniciaUsers();
        return this.listaDePessoas.getNomes();
    }

    public void login(String usuario) {
        this.usuarioLogado = listaDePessoas.getPessoa(usuario);
        System.out.println("Logado como: " + usuario);
    }

    public void cria_imovel(float preco, String endereco, String descricao, String bairro) {
        listaDeImoveis.adiciona (preco, endereco, descricao, bairro);
    }

    public void pesquisa_endereco (String bairro) {
        listaDeImoveis.pesquisa_endereco (bairro);
    }

    public void favoritar(Imovel i) {
        usuarioLogado.getImoveisFavoritados().adiciona(i);
        System.out.println("-> Imóvel adicionado aos favoritos!");
    }

    public void seleciona_imovel () {
        // Scanner s = new Scanner(System.in);
        Console console = System.console();
        System.out.println("Digite o ID do imóvel: ");
        Imovel selec = listaDeImoveis.getImovel(Integer.parseInt(console.readLine()));
        
        System.out.println("\t-> " + selec.getEndereco() + " - " + selec.getDescricao() + " - R$" + selec.getPreco());

        if (usuarioLogado.getImoveisFavoritados().contem(selec))
            System.out.println("0 - Desfavoritar imóvel");
        else
            System.out.println("0 - Favoritar imóvel");
        System.out.println("1 - Entrar em contato");
        int opt = Integer.parseInt(console.readLine());

        if (opt == 0)
            favoritar (selec);
        // else
        //     entrar_em_contato(selec.getDono());
    }

    public void filtrar_imoveis () {

    }

    public void mostra_favoritos() {
        usuarioLogado.getImoveisFavoritados().print_lista("Imóveis favoritados");
    }
}

