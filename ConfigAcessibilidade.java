public class ConfigAcessibilidade {
    private float contraste;
    private boolean fonteNegrito;
    private boolean modoDaltonico;
    private float tamanhoTexto;

    // Construtor, getters e setters
    public ConfigAcessibilidade(float contraste, boolean fonteNegrito, boolean modoDaltonico, float tamanhoTexto) {
        this.contraste = contraste;
        this.fonteNegrito = fonteNegrito;
        this.modoDaltonico = modoDaltonico;
        this.tamanhoTexto = tamanhoTexto;
    }

    public float getContraste() {
       return contraste;
    }
    public float getTamanhoTexto() {
       return tamanhoTexto;
    }

    public boolean getFonteNegrito() {
        return fonteNegrito;
    }

    public boolean getModoDaltonico() {
        return modoDaltonico;
    }

    public void setContraste(float contraste) {
       this.contraste = contraste;
    }
    
    public void setFonteNegrito(boolean fonteNegrito) {
       this.fonteNegrito = fonteNegrito;
    }

    public void setModoDaltonico(boolean modoDaltonico) {
       this.modoDaltonico = modoDaltonico;
    }

    public void setTamanhoTexto(float tamanhoTexto) {
       this.tamanhoTexto = tamanhoTexto;
    }
}

