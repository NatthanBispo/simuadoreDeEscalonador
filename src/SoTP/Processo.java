package SoTP;

public class Processo {
    private String nome;
    private int tempo;
    private String tipo;
    private int prioridade;
    private int espera;

    public int getEspera() {
        return espera;
    }

    public void setEspera(int espera) {
        this.espera = espera;
    }

    public Processo() {
    }

    public Processo(String nome, int tempo, String tipo) {
        this.nome = nome;
        this.tempo = tempo;
        this.tipo = tipo;
    }
    
    public Processo(String nome, int tempo, String tipo, int prioridade) {
        this.nome = nome;
        this.tempo = tempo;
        this.tipo = tipo;
        this.prioridade = prioridade;
    }
    
    public Processo(String nome, int tempo, String tipo, int prioridade, int espera) {
        this.nome = nome;
        this.tempo = tempo;
        this.tipo = tipo;
        this.prioridade = prioridade;
        this.espera = espera;

    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }   

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;   
    }   
}