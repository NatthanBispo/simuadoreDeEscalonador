/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoTP;

import java.util.LinkedList;
import java.util.Random;

public class RoundRobin {
    private int unidadeTempo=0;
    private int tempoContexto=1;
    private int contContexto=0;
    private int [] tempoRestanteBloqueados = {5, 10, 15, 20, 25, 30, 35, 40, 45, 50};
    private int quantum=0;
    
    private LinkedList<Processo> listaProntos;
    private LinkedList<Processo> listaBloqueados;
    
    Random rd = new Random();
    
    public RoundRobin(int quantum) {
    	this.listaProntos = new LinkedList<Processo>();
    	this.listaBloqueados = new LinkedList<Processo>();
    	this.quantum = quantum;
    }
    
    public void listaExemplos() {
    	this.listaProntos = new LinkedList<Processo>();
    	String a ="";
    	char c = 65;
    	for(int i=0; i<10; ++i) {
    			a = String.valueOf(c++);
    		if(i%4!=0) adicionar(a, rd.nextInt(20)+5, "CPU");
    		else adicionar(a, rd.nextInt(20)+5, "IO");
    	}
    }
    
    public void adicionar(String nome, int tempo, String tipo) {
    	listaProntos.add(new Processo(nome, tempo, tipo));
    }
    
    public void imprime() {
    	System.out.println("DESCRICAO");
    	System.out.println("Tempo de execucao: "+unidadeTempo);
    	System.out.println("Troca de contexto: "+contContexto);
    	
    	System.out.println("\nPROCESSOS PRONTOS");
    	System.out.println("NOME | TEMPO | TIPO");
    	for(int i=0; i<listaProntos.size(); i++) {
    		System.out.println(listaProntos.get(i).getNome()+" | "+listaProntos.get(i).getTempo()+" | "+listaProntos.get(i).getTipo());
    	}
    	System.out.println("\nPROCESSOS BLOQUEADOS");
    	System.out.println("NOME | TEMPO | TIPO | Tempo restante IO");
    	for(int i=0; i<listaBloqueados.size(); i++) {
    		System.out.println(listaBloqueados.get(i).getNome()+" | "+listaBloqueados.get(i).getTempo()+" | "+listaBloqueados.get(i).getTipo()+" | "+tempoRestanteBloqueados[i]);
    	}
    	
    }
    
    public void executar() {
    	if(listaBloqueados.size()>0) {
    		listaProntos.add(listaBloqueados.remove(0));
    	}
    	
    	//verifica se o processo ainda precisara de tempo MAIOR ou igual ao quantum para ser executado
    	if(listaProntos.get(0).getTempo() > 0 && listaProntos.size()>0) {
    		/*
    		 * Subtrai quantum e remove do topo e adiciona no final da lista de Processos Prontos
    		 */
	    	if(listaProntos.get(0).getTipo().equals("CPU")) {
	    		//subtrai o tempo de execucao(quantum) do tempo total do processo
	        	listaProntos.get(0).setTempo(listaProntos.get(0).getTempo() - quantum);	
	    		listaProntos.add(listaProntos.remove(0));
	    	}
	    	
	    	/*
	    	 * se precisar de tempo por causa de IO ele é removido do topo de
	    	 * Processos Prontos e é adicionado na lista de Processos Bloqueados
	    	 */
	    	else {
	    		listaProntos.get(0).setTempo(listaProntos.get(0).getTempo() - tempoContexto);	
	    		if(listaProntos.get(0).getTempo()>0)
	    			listaBloqueados.add(listaProntos.remove(0));
	    		else
	    			listaProntos.remove(0);
	    			
	    	}
	    	
	    	unidadeTempo += quantum;	
    	}
    	//verifica se o processo ainda precisara de tempo MENOR do que o quantum para ser executado
    	else if(listaProntos.get(0).getTempo() > 0 && listaProntos.get(0).getTempo() < 5){
    		unidadeTempo += listaProntos.get(0).getTempo();
    		
    		if(!listaProntos.get(0).getTipo().equals("IO"))
	    			listaProntos.add(listaProntos.remove(0));
	    	else
	    		listaBloqueados.add(listaProntos.remove(0));
    	}
    	// se o processo nao precisar mais de tempo de execução ele é removido da lista
    	if(listaProntos.size()>0 && listaProntos.get(listaProntos.size()-1).getTempo() <= 0)
    		listaProntos.remove(listaProntos.size()-1);
    	
    	imprime();
    	fim();
    }
    
    public void setQuantum(int quantum) {
    	this.quantum = quantum;
    }
    
    public int getQuantum() {
    	return this.quantum;
    }
    
    public int getUnidadeTempo() {
    	return this.unidadeTempo;
    }
    
    public void fim() {
    	if(listaProntos.size()==0 && listaBloqueados.size()==0) {
    		System.out.println("\nFim da simulacao");
    		System.exit(0);
    	}
    }
}
