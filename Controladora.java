import java.io.Console;
import java.util.List;

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
        listaDeImoveis.adiciona (preco, endereco, descricao, bairro, usuarioLogado);
    }

    public void pesquisa_endereco (String bairro) {
        listaDeImoveis.pesquisa_endereco (bairro);
    }

    public void favoritar(Imovel i) {
        boolean contem = usuarioLogado.getImoveisFavoritados().contem(i);
        if (!contem) {
            usuarioLogado.getImoveisFavoritados().adiciona(i);
            System.out.println("-> Imóvel adicionado aos favoritos!");
        }
        else{
            usuarioLogado.getImoveisFavoritados().remove(i);
            System.out.println("-> Imóvel removido dos favoritos!");
        }
    }

    public void entrar_em_contato(Pessoa dono) {
        Conversa convExiste = usuarioLogado.getConversas().contem(dono);
        Console console = System.console();
        if (convExiste != null) {
            while (true) {
                convExiste.print_mensagens();
                System.out.println("0 - Mandar nova mensagem");
                System.out.println("1 - Retornar");

                int opt = Integer.parseInt(console.readLine());

                if (opt == 0) {
                    System.out.println("Digite uma nova mensagem: ");
                    System.out.print("=> ");
                }
                else break;
            }
        }
        else {

        }
    }

    public void seleciona_imovel () {
        Console console = System.console();
        System.out.println("Digite o ID do imóvel: ");
        System.out.print("=> ");
        Imovel selec = listaDeImoveis.getImovel(Integer.parseInt(console.readLine()));
        
        System.out.println("\t-> " + selec.getEndereco() + " - " + selec.getDescricao() + " - R$" + selec.getPreco());

        if (usuarioLogado.getImoveisFavoritados().contem(selec))
            System.out.println("0 - Desfavoritar imóvel");
        else
            System.out.println("0 - Favoritar imóvel");
        System.out.println("1 - Entrar em contato");
        System.out.println("2 - Retornar");
        System.out.print("=> ");
        int opt = Integer.parseInt(console.readLine());

        if (opt == 2) return;
        else if (opt == 0)
            favoritar (selec);
        else
            entrar_em_contato(selec.getDono());
    }

    public void filtrar_imoveis () {
        Console console = System.console();
        System.out.println("Digite o preço máximo do imóvel: ");
        System.out.print("=> ");
        float preco = Float.parseFloat(console.readLine().replaceAll(",","."));

        int tam = listaDeImoveis.filtrar(preco);
        if (tam > 0){
            System.out.println("0 - Escolher imóvel");
            System.out.println("1 - Retornar");

            int opt = Integer.parseInt(console.readLine());
            
            if (opt == 0) seleciona_imovel();
        }
        else {
            System.out.println("-> Nenhum imóvel encontrado com esse preço!");
        }
    }

    public void mostra_favoritos() {
        ListaDeImoveis l = usuarioLogado.getImoveisFavoritados();
        if (l.getTam() > 0){
            l.print_lista("Imóveis favoritados", listaDeImoveis);
            System.out.println("0 - Escolher imóvel");
            System.out.println("1 - Retornar");
            System.out.print("=> ");

            Console console = System.console();
            int opt = Integer.parseInt(console.readLine());
            
            if (opt == 0) seleciona_imovel();
        }
    }

    public void personalizar_vizualizacao(float contraste, boolean negrito, boolean daltonico, float tamTexto) {
        usuarioLogado.getConfigAcessibilidade().setContraste(contraste);
        usuarioLogado.getConfigAcessibilidade().setFonteNegrito(negrito);
        usuarioLogado.getConfigAcessibilidade().setModoDaltonico(daltonico);
        usuarioLogado.getConfigAcessibilidade().setTamanhoTexto(tamTexto);
    }
}