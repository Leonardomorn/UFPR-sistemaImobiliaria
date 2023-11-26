import java.util.ArrayList;
import java.util.List;

public class ListaDeImoveis {
    private List<Imovel> imoveis;

    // Construtor
    public ListaDeImoveis() {
        this.imoveis = new ArrayList<Imovel>();
    }

    // Métodos
    public void adiciona(float preco, String endereco, String descricao, String bairro) {
        this.imoveis.add(new Imovel(preco, descricao, endereco, bairro));
        System.out.println("-> Imóvel cadastrado com sucesso!");
        System.out.println("Seus imóveis: ");
        for (Imovel i: imoveis) {
            System.out.println("\t" + i.getEndereco() + " - " + i.getBairro() + " - " + "R$" + i.getPreco());
        }
    }

    public void adiciona(Imovel i) {
        this.imoveis.add(i);
    }

    public void pesquisa_endereco (String bairro) {
        for (Imovel imovel : imoveis) {
            if (imovel.getBairro().equals(bairro)) {
                System.out.println("-> " + imoveis.indexOf(imovel) + " - "  + imovel.getEndereco() + " - " + imovel.getDescricao() + " - R$" + imovel.getPreco());
            }
        }
    }

    public Imovel getImovel(int index) {
        return imoveis.get(index);
    }

    public boolean contem (Imovel i) {
        return imoveis.contains(i);
    }

    public void print_lista(String titulo) {
        System.out.println(titulo);
        for (Imovel imovel : imoveis) {
            System.out.println("\t-> " + imoveis.indexOf(imovel) + " - "  + imovel.getEndereco() + " - " + imovel.getDescricao() + " - R$" + imovel.getPreco());
        }
    }
}

