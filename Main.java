public class Main {
    public static void main(String[] args) {

        // Testes de persistencia
        ListaDePessoas listaDePessoas = new ListaDePessoas();

        // Inicializacoes
        listaDePessoas.iniciaUsers();
        listaDePessoas.assertPersistence("pessoas.txt");

        // Pega as pessoas de um arquivo
        listaDePessoas.setListFromFile("pessoas.txt");

        // Manipular listaDePessoas ...

        // Salva a lista de pessoas em um arquivo
        listaDePessoas.writePessoasToFile(listaDePessoas.getPessoas(), "pessoas.txt");

        // Imprimir as pessoas depois de manipular a lista
        listaDePessoas.imprimirPessoas();

        // Salva a lista de pessoas em um arquivo
        listaDePessoas.writePessoasToFile(listaDePessoas.getPessoas(), "pessoas.txt");
    }
}
