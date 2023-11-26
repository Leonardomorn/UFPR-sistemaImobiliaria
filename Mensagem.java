public class Mensagem {
    private String texto;
    private String horaEnvio;
    private Pessoa escritor;

    // Construtor, getters e setters
    public Mensagem(String texto, String hora, Pessoa escritor){
        this.texto = texto;
        this.horaEnvio = hora;
        this.escritor = escritor;
    }

    public String getHoraEnvio() {
       return horaEnvio;
    }

    public String getTexto() {
       return texto;
    }

    public Pessoa getEscritor() {
       return escritor;
    }

    public void setHoraEnvio(String horaEnvio) {
       this.horaEnvio = horaEnvio;
    }

    public void setTexto(String texto) {
       this.texto = texto;
    }
}

