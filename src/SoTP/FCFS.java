/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoTP;

import java.util.LinkedList;
import java.util.Random;

public class FCFS {
    private int unidadeTempo=0;
    private char nome;
    private int tempoMedio=0;
    private int tamanhoLista=0;
    private boolean first = true;
    
    private LinkedList<Processo> listaExecucao;
    private LinkedList<Integer> tempoDeEspera;
    
    Random rd = new Random();
    
    public FCFS() {
    	this.listaExecucao = new LinkedList<>();
    	this.tempoDeEspera = new LinkedList<>();
    	this.tempoDeEspera.add(0);
        this.nome = 65;
    }
    
    public void listaExemplos() {
    	for(int i=0; i<10; ++i) {
            if(i%4!=0) adicionar(rd.nextInt(20)+5, "CPU");
            else adicionar(rd.nextInt(20)+5, "IO");
    	}
    }
    
    public void adicionar(int tempo, String tipo) {
    	listaExecucao.add(new Processo(String.valueOf(nome++), tempo, tipo));
    	if(tempoDeEspera.size()>0)
    		tempoDeEspera.addLast(tempoDeEspera.get(tempoDeEspera.size()-1) + listaExecucao.get(listaExecucao.size()-1).getTempo());
    }
    
    public void remover(){
        listaExecucao.removeLast();
    }
    
    public LinkedList<Processo> retornaLista(){
        return this.listaExecucao;
    }
    
    public LinkedList<Integer> retornaListaTempo(){
        return this.tempoDeEspera;
    }
    public Processo executar() {
        if(first){
           tamanhoLista = listaExecucao.size();
           first = false; 
        }
    	Processo removido = null;
        if(listaExecucao.size()>0) {
    		unidadeTempo += listaExecucao.get(0).getTempo();
    		int tempo = listaExecucao.get(0).getTempo();
    		removido = listaExecucao.remove(0);
    		tempoDeEspera.remove(0);
    		
                tempoMedio += unidadeTempo;
                
    		LinkedList<Integer> aux = new LinkedList<>();
    		for(Integer t: tempoDeEspera)
    			aux.addLast(t-tempo);
    		tempoDeEspera = aux;
    	}
        return removido;
    }

    public int getTempoMedio() {
        return tempoMedio / tamanhoLista;
    }
    
    public int getUnidadeTempo() {
    	return this.unidadeTempo;
    }
}
