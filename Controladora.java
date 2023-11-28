import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Controladora {
    private ListaDeImoveis listaDeImoveis;
    private ListaDePessoas listaDePessoas;
    private ListaDeConversas listaDeConversas;
    private Pessoa usuarioLogado;

    public Controladora() {
        this.listaDeImoveis = new ListaDeImoveis();
        this.listaDeConversas = new ListaDeConversas();
        this.listaDePessoas = new ListaDePessoas();

        // Persistence
        this.listaDePessoas.assertPersistence("pessoas.txt");
        this.listaDePessoas.setListFromFile("pessoas.txt");
        this.listaDeImoveis = this.listaDePessoas.unificarListas();
        this.listaDeConversas.assertPersistence("conversas.txt");
        this.listaDeConversas.setListFromFile(listaDePessoas, "conversas.txt");
    }

    // Métodos para interação front-back
    public List<String> get_usuarios() {
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
        Conversa convExiste = listaDeConversas.contem(dono);
        Console console = System.console();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        Date date = new Date();

        if (convExiste == null) {
            listaDeConversas.adiciona(usuarioLogado, dono);
            convExiste = listaDeConversas.contem(dono);
        }
        while (true) {
            convExiste.print_mensagens();
            System.out.println("0 - Mandar nova mensagem");
            System.out.println("1 - Retornar");
            System.out.print("=> ");

            int opt = Integer.parseInt(console.readLine());

            if (opt == 0) {
                System.out.print("Digite uma nova mensagem: ");
                String text = console.readLine();
                convExiste.adiciona_mensagem(text, formatter.format(date), usuarioLogado);
            }
            else break;
        }
    }

    public void print_conversas() {
        Console console = System.console();

        int cont = listaDeConversas.print_conversas(usuarioLogado);
        if (cont > 0) {
        System.out.println("0 - Escolher conversa");
            System.out.println("1 - Retornar");
            System.out.print("=> ");
            int opt = Integer.parseInt(console.readLine());

            if (opt == 0) {
                System.out.print("Digite o nome da Pessoa: ");
                String dono = console.readLine();
                entrar_em_contato(listaDePessoas.getPessoa(dono));
            }
        }
    }

    public void seleciona_imovel () {
        Console console = System.console();
        System.out.print("Digite o ID do imóvel: ");
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

    public void salvarPessoas(String arquivo) {
        this.listaDePessoas.writePessoasToFile(arquivo);
    }

    public void salvarConversas(String arquivo) {
        this.listaDeConversas.writeConversasToFile(arquivo);
    }
}