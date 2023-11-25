import java.util.ArrayList;
import java.util.List;
import java.util.*;

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

    public void iniciaUsers() {
        this.adicionar(new Pessoa("gustavo", "123", "123456789"));
        this.adicionar(new Pessoa("leonardo", "456", "123456789"));
        this.adicionar(new Pessoa("tulio", "789", "123456789"));
        this.adicionar(new Pessoa("dolzan", "789", "123456789"));
    }
}