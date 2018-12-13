/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoTP;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Prioridade {
    private int unidadeTempo=0;
    private int tempoContexto=1;
    private int contContexto=0;
    private int quantum=0;
    private int nmr=50;
    private boolean troca=false;
    
    LinkedList<Processo>listaProntos;
    LinkedList<Processo> listaBloqueados;
    private LinkedList<Processo> lista0;
    private LinkedList<Processo> lista1;
    private LinkedList<Processo> lista2;
    private LinkedList<Processo> lista3;
    
    Random rd = new Random();
    
    public Prioridade (int quantum){
        listaProntos= new LinkedList<Processo>();
        listaBloqueados= new LinkedList<Processo>();
        lista0 = new LinkedList<Processo>();
        lista1 = new LinkedList<Processo>();
        lista2 = new LinkedList<Processo>();
        lista3 = new LinkedList<Processo>();
        this.quantum=quantum;
    }
    
    public void adicionar(String nome, int tempo, String tipo, int prioridade) {
        coloca(new Processo(nome, tempo, tipo, prioridade));
    }
    
    private char geraNome = 65;
    public void adicionar(int tempo, String tipo, int prioridade) {
        coloca(new Processo(String.valueOf(geraNome++), tempo, tipo, prioridade));
    }
    
    public void listaExemplos() {
    	for(int i= 0;i<10;i++){
            String tipoProcesso;
            int tempoProcesso = rd.nextInt(50);
            boolean tipo = rd.nextBoolean();
                if(tipo)
                    tipoProcesso = "CPU";
                else
                    tipoProcesso = "IO";
                char nomep = (char)(65+i);
                int prioridade= rd.nextInt(4);

                adicionar((nomep+""), tempoProcesso, tipoProcesso, prioridade);
        }
    }
    
    public void executar() {
        //organiza a lista de pronto
        org();

        if (contContexto==0) contContexto++;
        //confere o contexto
        if(troca){
            troca = false;
            unidadeTempo++;
            contContexto++;
            atualiza(1);
        }
        //se tver algo na lista e o tempo de espera for menor q zero
        if(listaBloqueados.size()>0 && listaBloqueados.peek().getEspera()<=0){
          //então pega o processo
           Processo aux= listaBloqueados.pop();
           //coloca de novo no lugar
           coloca(aux);
           org();
        }
        // se nao vai ver se a lista de prontos esta vazia
        else if(listaProntos.size()>0){
           troca=true;
            //vai setar para mudar de contexto;
            //vai tirar o elemento das duas listas
           Processo aux2 = listaProntos.pop();
           tira(aux2);
           Processo aux = new Processo();
           int tempo;
           //verifica se o objeto tirado é cpu
           //se sim vai tirar um quantum de tempo dele 
           // se nao vai tirar um
            if(aux2.getTipo().equalsIgnoreCase("CPU"))
                tempo = aux2.getTempo()-quantum;
            else
                tempo=aux2.getTempo()-1;
           //se o tempo for mmenor q zero
            if(tempo<0){ 
                //pega o tempo do menino e acrecenta no temporizador
                unidadeTempo += aux2.getTempo();
                atualiza(aux2.getTempo());
            }
           // se ele for cpu
            else if(tempo>0&&aux2.getTipo().equalsIgnoreCase("CPU")){
                aux2.setTempo(tempo);
                unidadeTempo+=quantum;
                atualiza(quantum);
                coloca(aux2);
            }
            else if(tempo>0&&!aux2.getTipo().equalsIgnoreCase("CPU")) {
                aux2.setTempo(tempo);
                unidadeTempo++;
                atualiza(1);
                listaBloqueados.add(new Processo(aux2.getNome(),aux2.getTempo(),aux2.getTipo(),aux2.getPrioridade(),quantum));
           } 
        }
    }
    
    public int getUnidadeTempo() {
        return unidadeTempo;
    }

    public void setUnidadeTempo(int unidadeTempo) {
        this.unidadeTempo = unidadeTempo;
    }

    public int getTempoContexto() {
        return tempoContexto;
    }

    public void setTempoContexto(int tempoContexto) {
        this.tempoContexto = tempoContexto;
    }

    public int getContContexto() {
        return contContexto;
    }

    public void setContContexto(int contContexto) {
        this.contContexto = contContexto;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }
    
    public LinkedList<Processo> getListaBloqueados() {
        return listaBloqueados;
    }
    
    public LinkedList<Processo> getListaProntos(){
        return listaProntos;
    }
    
    public void setListaBloqueados(LinkedList<Processo> listaBloqueados) {
        this.listaBloqueados = listaBloqueados;
    }

    public Random getRd() {
        return rd;
    }

    public void setRd(Random rd) {
        this.rd = rd;
    }

    public void atualiza(int i) {
        for(Processo a: listaBloqueados){
            int s=a.getEspera();
            a.setEspera(s-i);
        }
    }
    
    public void org(){
        LinkedList<Processo> li= new LinkedList<Processo>();
        for(Processo a : lista0)
            li.add(a);
        for(Processo a : lista1)
            li.add(a);
        for(Processo a : lista2)
            li.add(a);
        for(Processo a : lista3)
            li.add(a);
          
        listaProntos= li;
    }
    
    public void coloca(Processo a){
        if(a.getPrioridade()==0) lista0.add(a);
        if(a.getPrioridade()==1) lista1.add(a);
        if(a.getPrioridade()==2) lista2.add(a);
        if(a.getPrioridade()==3) lista3.add(a);
        org();
    }
    
    public void tira(Processo a){
        if(a.getPrioridade()==0) lista0.remove(a);
        if(a.getPrioridade()==1) lista1.remove(a);
        if(a.getPrioridade()==2) lista2.remove(a);
        if(a.getPrioridade()==3) lista3.remove(a);
	org();
    }
}