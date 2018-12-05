package SoTP;

public class Processo {
    private String nome;
    private int tempo;
    private String tipo;
    private int prioridade;

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