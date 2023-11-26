public class Mensagem {
    private String texto;
    private String horaEnvio;

    // Construtor, getters e setters
    public Mensagem(String texto, String hora){
        this.texto = texto;
        this.horaEnvio = hora;
    }

    public String getHoraEnvio() {
       return horaEnvio;
    }

    public String getTexto() {
       return texto;
    }

    public void setHoraEnvio(String horaEnvio) {
       this.horaEnvio = horaEnvio;
    }

    public void setTexto(String texto) {
       this.texto = texto;
    }
}

