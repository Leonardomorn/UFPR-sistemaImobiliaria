import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ListaDePessoas {
    private List<Pessoa> pessoas;

    // Construtor, getters e setters
    public ListaDePessoas() {
        this.pessoas = new ArrayList<Pessoa>();
    }

    public void adicionar(Pessoa pessoa) {
        this.pessoas.add(pessoa);
    }

    public void remover(Pessoa pessoa) {
        this.pessoas.remove(pessoa);
    }

    public List<String> getNomes() {
        List<String> nomes = new ArrayList<String>();
        Iterator<Pessoa> it = pessoas.iterator();

        while (it.hasNext()) {
            nomes.add(it.next().getNome());
        }

        return nomes;
    }

    public Pessoa getPessoa(String nome) {
        Pessoa p = null;
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getNome().equals(nome))
                p = pessoa;
        }
        return p;
    }

    public void iniciaUsers() {
        this.adicionar(new Pessoa("gustavo", "123", "123456789"));
        this.adicionar(new Pessoa("leonardo", "456", "123456789"));
        this.adicionar(new Pessoa("tulio", "789", "123456789"));
        this.adicionar(new Pessoa("dolzan", "789", "123456789"));
    }

    public List<Pessoa> getPessoas() {
        return this.pessoas;
    }

    public void assertPersistence(String arquivo) {
        // Cria arquivo se nao existe
        try {
            File file = new File(arquivo);

            if (!file.exists()) {
                file.createNewFile();
                iniciaUsers();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setListFromFile(String arquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            Pessoa pessoaAtual = null;
            while ((linha = reader.readLine()) != null) {
                if (linha.equals("---")) {
                    if (pessoaAtual != null) {
                        pessoas.add(pessoaAtual);
                        pessoaAtual = null;
                    }
                } else if (linha.startsWith("--imovel--")) {
                    if (pessoaAtual != null) {
                        String[] imovelDados = linha.substring(10).split(";");
                        if (imovelDados.length >= 4) {
                            // Create Imovel object with pessoaAtual as dono
                            Imovel imovel = new Imovel(Float.parseFloat(imovelDados[0]), imovelDados[1], imovelDados[2], imovelDados[3], pessoaAtual);
                            pessoaAtual.getImoveisAnunciados().adiciona(imovel);
                        }
                    }
                } else if (pessoaAtual == null) {
                    String[] dados = linha.split(";");
                    if (dados.length == 3) {
                        pessoaAtual = new Pessoa(dados[0], dados[1], dados[2]);
                    }
                }
            }
            if (pessoaAtual != null) {
                pessoas.add(pessoaAtual);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writePessoasToFile(String arquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            for (Pessoa pessoa : pessoas) {
                writer.write(pessoa.getNome() + ";" + pessoa.getCpfCnpj() + ";" + pessoa.getContato());
                writer.newLine();
    
                // Write associated Imoveis
                for (Imovel imovel : pessoa.getImoveisAnunciados().getImoveis()) {
                    writer.write("--imovel--" + imovel.getPreco() + ";" + imovel.getDescricao() + ";" + imovel.getEndereco() + ";" + imovel.getBairro());
                    writer.newLine();
                }
    
                // End of Pessoa's data
                writer.write("---");
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void imprimirPessoas() {
        for (Pessoa pessoa : this.pessoas) {
            System.out.println("Nome: " + pessoa.getNome() + ", CPF/CNPJ: " + pessoa.getCpfCnpj() + ", Contato: " + pessoa.getContato());
            for (Imovel imovel : pessoa.getImoveisAnunciados().getImoveis()) {
                System.out.println("\t-> Imovel: " + imovel.getEndereco() + " - " + imovel.getBairro() + " - R$" + imovel.getPreco() + " - " + imovel.getDescricao());
            }
        }
    }

    public ListaDeImoveis unificarListas() {
        ListaDeImoveis listaDeImoveis = new ListaDeImoveis();
        for (Pessoa pessoa : this.pessoas) {
            for (Imovel imovel : pessoa.getImoveisAnunciados().getImoveis()) {
                listaDeImoveis.adiciona(imovel);
            }
        }
        return listaDeImoveis;
    }

}