import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListaDeConversas {
    private List<Conversa> conversas;

    // Construtor, getters e setters
    public ListaDeConversas() {
        this.conversas = new ArrayList<Conversa>();
    }

    public Conversa contem(Pessoa dono) {
        for (Conversa conversa : conversas) {
            if (conversa.getProprietario() == dono || conversa.getCliente() == dono)  return conversa;
        }
        return null;
    }

    public void adiciona(Pessoa usuario, Pessoa dono) {
        conversas.add(new Conversa(usuario, dono));
    }

    public int print_conversas(Pessoa usuario) {
        int cont = 0;
        for (Conversa conversa : conversas) {
            if (conversa.getCliente() == usuario){
                System.out.println(conversa.getProprietario().getNome());
                cont++;
            }
            else if (conversa.getProprietario() == usuario){
                System.out.println(conversa.getCliente().getNome());
                cont++;
            }
        }
        return cont;
    }

    // PERSISTENCE

    public void assertPersistence(String arquivo) {
        // Cria arquivo se nao existe
        try {
            File file = new File(arquivo);

            if (!file.exists()) {
                file.createNewFile();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setListFromFile(ListaDePessoas listaPessoas, String arquivo) {    
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            Pessoa cliente = null;
            Pessoa proprietario = null;
            Conversa conversaAtual = null;
            while ((linha = reader.readLine()) != null) {
                if (linha.equals("--pessoas--")) {
                    String[] dados = reader.readLine().split(";");
                    if (dados.length == 6) {
                        for (Pessoa pessoa : listaPessoas.getPessoas()) {
                            if (pessoa.getNome().equals(dados[0]) && pessoa.getCpfCnpj().equals(dados[1]) && pessoa.getContato().equals(dados[2])) {
                                cliente = pessoa;
                            }
                            if (pessoa.getNome().equals(dados[3]) && pessoa.getCpfCnpj().equals(dados[4]) && pessoa.getContato().equals(dados[5])) {
                                proprietario = pessoa;
                            }
                        }
                        conversaAtual = new Conversa(cliente, proprietario);
                    }
                } else if (linha.startsWith("--msg--")) {
                    if (conversaAtual != null) {
                        String[] mensagem = linha.substring(7).split(";");
                        if (mensagem.length == 3) {
                            Pessoa escritor = mensagem[2].equals(cliente.getNome()) ? cliente : proprietario;
                            conversaAtual.adiciona_mensagem(mensagem[0], mensagem[1], escritor);
                        }
                    }
                } else if (linha.equals("---")) {
                    if (conversaAtual != null) {
                        conversas.add(conversaAtual);
                        conversaAtual = null;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print the list
        for (Conversa conversa : conversas) {
            System.out.println(conversa.getCliente().getNome() + " - " + conversa.getProprietario().getNome());
            conversa.print_mensagens();
        }

    }

    public void writeConversasToFile(String arquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            for (Conversa conversa : conversas) {
                // Print Pessoa info
                writer.write("--pessoas--\n");
                writer.write(conversa.getCliente().getNome() + ";" + conversa.getCliente().getCpfCnpj() + ";" + conversa.getCliente().getContato() + ";" + conversa.getProprietario().getNome() + ";" + conversa.getProprietario().getCpfCnpj() + ";" + conversa.getProprietario().getContato() + "\n");
                // Print Mensagens
                for (Mensagem mensagem : conversa.getMensagens().getMensagens()) {
                    writer.write("--msg--" + mensagem.getTexto() + ";" + mensagem.getHoraEnvio() + ";" + mensagem.getEscritor().getNome() + "\n");
                }
                writer.write("---\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // print all Conversas __DEBUG__
    public void print_conversas_debug() {
        for (Conversa conversa : conversas) {
            System.out.println(conversa.getCliente().getNome() + " - " + conversa.getProprietario().getNome());
            conversa.print_mensagens();
        }
    }

}

