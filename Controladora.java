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

    public void seleciona_imovel () {
        Scanner s = new Scanner(System.in);
        System.out.println("Digite o ID do imóvel: ");
        Imovel selec = listaDeImoveis.getImovel(Integer.parseInt(s.nextLine()));
        
        if (usuarioLogado.getImoveisFavoritados().contem(selec))
            System.out.println("0 - Favoritar imóvel");
        else
            System.out.println("0 - Desfavoritar imóvel");
        System.out.println("1 - Entrar em contato");
        int opt = Integer.parseInt(s.nextLine());

        // if (opt == 0)
        //     favoritar (selec);
        // else
        //     entrar_em_contato(selec.getDono());

        s.close();
    }

    public void filtrar_imoveis () {

    }
}

