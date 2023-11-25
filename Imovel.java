public class Imovel {
    private float preco;
    private String descricao;
    private String endereco;
    private String bairro;

    // Construtor, getters e setters
    public Imovel(float preco, String descricao, String endereco, String bairro) {
        this.preco = preco;
        this.descricao = descricao;
        this.endereco = endereco;
        this.bairro = bairro;
    }

    public String getDescricao() {
       return descricao;
    }
    
    public String getEndereco() {
       return endereco;
    }

    public float getPreco() {
       return preco;
    }
    
    public String getBairro() {
       return bairro;
    }

    public void setDescricao(String descricao) {
       this.descricao = descricao;
    }

    public void setEndereco(String endereco) {
       this.endereco = endereco;
    }
    
    public void setPreco(float preco) {
       this.preco = preco;
    }
    
    public void setBairro(String bairro) {
       this.bairro = bairro;
    }
}

