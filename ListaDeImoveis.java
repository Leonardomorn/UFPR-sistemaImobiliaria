import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

    public List<Imovel> getImoveis() {
        return imoveis;
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

    // Persistence

    public void assertPersistence(String arquivo) {
        File file = new File(arquivo);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Erro ao criar arquivo " + arquivo);
                e.printStackTrace();
            }
        }
    }

    private List<Imovel> getListFromFile(String arquivo) {
        List<Imovel> imoveis = new ArrayList<>();
    
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 5) {
                    Pessoa dono = new Pessoa(dados[4], "", ""); // Tem que colocar o dono do arquivo de algum jeito **TODO**
                    Imovel imovel = new Imovel(Float.parseFloat(dados[0]), dados[1], dados[2], dados[3], dono);
                    imoveis.add(imovel);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imoveis;
    }

    public void writeImoveisToFile(List<Imovel> imoveis, String arquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            for (Imovel imovel : imoveis) {
                writer.write(imovel.getPreco() + ";" + imovel.getDescricao() + ";" + imovel.getEndereco() + ";" + imovel.getBairro() + ";" + imovel.getDono().getNome());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void imprimirImoveis(List<Imovel> imoveis) {
        for (Imovel imovel : imoveis) {
            System.out.println("Endereço: " + imovel.getEndereco() + ", Bairro: " + imovel.getBairro() + ", Preço: R$" + imovel.getPreco() + ", Descrição: " + imovel.getDescricao() + ", Dono: " + imovel.getDono().getNome());
        }
    }
}