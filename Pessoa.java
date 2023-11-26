public class Pessoa{
    private String nome;
    private String cpfCnpj;
    private String contato;
    private ListaDeImoveis imoveisFavoritados;
    private ListaDeImoveis imoveisAnunciados;
    // private ListaDeConversas conversas;
    private ConfigAcessibilidade configAcessibilidade;

    // Construtor
    public Pessoa (String nome, String cpfCnpj, String contato) {
      this.nome = nome;
      this.cpfCnpj = cpfCnpj;
      this.contato = contato;
      this.imoveisFavoritados = new ListaDeImoveis();
      this.imoveisAnunciados = new ListaDeImoveis();
      // this.conversas = new ListaDeConversas();
      this.configAcessibilidade = new ConfigAcessibilidade(50, false, false, 50);
    }

    // Getters
    public String getNome() {
      return nome;
    }

    public ConfigAcessibilidade getConfigAcessibilidade() {
      return configAcessibilidade;
    }

    public String getContato() {
      return contato;
    }

    // public ListaDeConversas getConversas() {
    //   return conversas;
    // }

    public String getCpfCnpj() {
      return cpfCnpj;
    }

    public ListaDeImoveis getImoveisFavoritados() {
      return imoveisFavoritados;
    }

    public ListaDeImoveis getImoveisAnunciados() {
      return imoveisAnunciados;
    }

    // Setters
    public void setConfigAcessibilidade(ConfigAcessibilidade configAcessibilidade) {
      this.configAcessibilidade = configAcessibilidade;
    }
    
    public void setContato(String contato) {
      this.contato = contato;
    }

    // public void setConversas(ListaDeConversas conversas) {
    //   this.conversas = conversas;
    // }

    public void setCpfCnpj(String cpfCnpj) {
      this.cpfCnpj = cpfCnpj;
    }

    public void setImoveisFavoritados(ListaDeImoveis imoveisFavoritados) {
      this.imoveisFavoritados = imoveisFavoritados;
    }
    public void setNome(String nome) {
      this.nome = nome;
    }
}

