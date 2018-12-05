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
    
    private LinkedList<Processo> listaExecucao;
    private LinkedList<Integer> tempoDeEspera;
    
    Random rd = new Random();
    
    public FCFS() {
    	this.listaExecucao = new LinkedList<Processo>();
    	this.tempoDeEspera = new LinkedList<Integer>();
    	this.tempoDeEspera.add(0);
    }
    
    public void listaExemplos() {
    	this.listaExecucao = new LinkedList<Processo>();
    	String a ="";
    	char c = 65;
    	for(int i=0; i<10; ++i) {
    			a = String.valueOf(c++);
    		if(i%4!=0) adicionar(a, rd.nextInt(20)+5, "CPU");
    		else adicionar(a, rd.nextInt(20)+5, "IO");
    	}
    }
    
    public void adicionar(String nome, int tempo, String tipo) {
    	listaExecucao.add(new Processo(nome, tempo, tipo));
    	if(tempoDeEspera.size()>0)
    		tempoDeEspera.addLast(tempoDeEspera.get(tempoDeEspera.size()-1) + listaExecucao.get(listaExecucao.size()-1).getTempo());
    }
    
    public void imprime() {
    	System.out.println("DESCRICAO");
    	System.out.println("Tempo de execucao: "+unidadeTempo);;
    	
    	System.out.println("\nFILA DE PROCESSOS");
    	//System.out.println("NOME | TEMPO | TIPO | TEMPO DE ESPERA");
    	for(int i=0; i<listaExecucao.size(); i++) {
    		System.out.println("NOME: "+listaExecucao.get(i).getNome()
    				+" | "+"TEMPO: "+listaExecucao.get(i).getTempo()
    				+" | "+"TIPO: "+listaExecucao.get(i).getTipo()
    				+" | "+"TEMPO DE ESPERA: "+tempoDeEspera.get(i));
    	}
    	
    }
    
    public void executar() {
    	if(listaExecucao.size()>0) {
    		unidadeTempo += listaExecucao.get(0).getTempo();
    		int tempo = listaExecucao.get(0).getTempo();
    		listaExecucao.remove(0);
    		tempoDeEspera.remove(0);
    		
    		LinkedList<Integer> aux = new LinkedList<>();
    		for(Integer t: tempoDeEspera)
    			aux.addLast(t-tempo);
    		tempoDeEspera = aux;
    	}else{
    		imprime();
    		System.out.println("\nFim da simulacao");
    		System.exit(0);
    	}
    	imprime();
    }
    
    public int getUnidadeTempo() {
    	return this.unidadeTempo;
    }
}
