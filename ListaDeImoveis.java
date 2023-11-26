import java.util.ArrayList;
import java.util.List;

public class ListaDeImoveis {
    private List<Imovel> imoveis;
    private int tam;

    // Construtor
    public ListaDeImoveis() {
        this.imoveis = new ArrayList<Imovel>();
        this.tam = 0;
    }

    // Getter
    public Imovel getImovel(int index) {
        return imoveis.get(index);
    }

    public int getTam() {
       return tam;
    }

    // Métodos
    public void adiciona(float preco, String endereco, String descricao, String bairro, Pessoa dono) {
        Imovel im = new Imovel(preco, descricao, endereco, bairro, dono);
        this.imoveis.add(im);
        dono.getImoveisAnunciados().adiciona(im);

        System.out.println("-> Imóvel cadastrado com sucesso!");
        System.out.println("Seus imóveis: ");
        for (Imovel i: imoveis) {
            if (i.getDono() == dono)
                System.out.println("\t" + i.getEndereco() + " - " + i.getBairro() + " - " + "R$" + i.getPreco());
        }
        this.tam++;
    }

    public void adiciona(Imovel i) {
        this.imoveis.add(i);
        this.tam++;
    }

    public void remove(Imovel i) {
        this.imoveis.remove(i);
        this.tam--;
    }

    public int getIndex(Imovel i) {
        for (Imovel imovel : imoveis) {
            if (imovel == i) return imoveis.indexOf(imovel);
        }
        return -1;
    }

    public void print_lista(String titulo, ListaDeImoveis listaInd) {
        System.out.println(titulo);
        for (Imovel imovel : imoveis) {
            System.out.println("\t-> " + listaInd.getIndex(imovel) + " - "  + imovel.getEndereco() + " - " + imovel.getDescricao() + " - R$" + imovel.getPreco());
        }
    }

    public void pesquisa_endereco (String bairro) {
        for (Imovel imovel : imoveis) {
            if (imovel.getBairro().equals(bairro)) {
                System.out.println("\t-> " + imoveis.indexOf(imovel) + " - "  + imovel.getEndereco() + " - " + imovel.getDescricao() + " - R$" + imovel.getPreco());
            }
        }
    }

    public boolean contem (Imovel i) {
        return imoveis.contains(i);
    }

    public int filtrar (float preco) {
        int count = 0;
        for (Imovel imovel : imoveis) {
            if (imovel.getPreco() <= preco) {
                count++;
                System.out.println("\t-> " + imoveis.indexOf(imovel) + " - "  + imovel.getEndereco() + " - " + imovel.getDescricao() + " - R$" + imovel.getPreco());
            }
        }

        return count;
    }
}

