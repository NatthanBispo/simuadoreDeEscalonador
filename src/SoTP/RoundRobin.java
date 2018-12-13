/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoTP;

import java.util.LinkedList;
import java.util.Random;

public class RoundRobin {
    private int unidadeTempo;
    private int tempoTrocaContexto = 1;
    private int contContexto=0;
    private int quantum=0;
    private int tempoExecucaoIO = 1;
    private char nome;
    private int tempoMedio=0;
    
    private LinkedList<Processo> listaProntos;
    private LinkedList<Processo> listaBloqueados;
    private LinkedList<Integer> tempoRestanteBloqueados;
    private LinkedList<Processo> listaProntosINTACTO;
    
    Random rd = new Random();
    
    public RoundRobin(int quantum) {
    	this.listaProntos = new LinkedList<Processo>();
    	this.listaBloqueados = new LinkedList<Processo>();
    	this.tempoRestanteBloqueados = new LinkedList<>();
        this.listaProntosINTACTO = new LinkedList<>();
        this.quantum = quantum;
        this.nome = 65;
        this.unidadeTempo = 0;
        this.tempoRestanteBloqueados.add(quantum);
    }
    
    public void listaExemplos() {
    	for(int i=0; i<10; ++i) {
            if(i%4!=0) adicionar(rd.nextInt(20)+5, "CPU");
            else adicionar(rd.nextInt(20)+5, "IO");
    	}
    }
    
    public void adicionar(int tempo, String tipo) {
    	listaProntos.addLast(new Processo(String.valueOf(nome++), tempo, tipo));
        listaProntosINTACTO.addLast(new Processo(String.valueOf(nome), tempo, tipo));
        if(tempoRestanteBloqueados.size()>0)
            tempoRestanteBloqueados.addLast(tempoRestanteBloqueados.getLast()+quantum);
    }
    
    public LinkedList<Processo> retornaListaProntos(){
        return this.listaProntos;
    }
    
    public LinkedList<Processo> retornaListaBloqueados(){
        return this.listaBloqueados;
    }
    
    public LinkedList<Integer> retornaListaTempoRestante(){
        return this.tempoRestanteBloqueados;
    }
    
    public Processo buscaIntacto(Processo processo){
        for(Processo pro : listaProntosINTACTO){
            if(pro.getNome().equals(processo.getNome())){
                processo = pro;
                break;
            }
        }
        return processo;
    }
        
    public Processo executar() {
        Processo processo = null;
    	if(listaBloqueados.size()>0) {
                listaProntos.add(listaBloqueados.remove(0));
    	}
    	
    	//verifica se o processo ainda precisara de tempo MAIOR ou igual ao quantum para ser executado
    	if(listaProntos.size()>0 && listaProntos.get(0).getTempo() > quantum) {
    		/*
    		 * Subtrai quantum e remove do topo e adiciona no final da lista de Processos Prontos
    		 */
	    	if(listaProntos.get(0).getTipo().equals("CPU")) {
	    		//subtrai o tempo de execucao(quantum) do tempo total do processo
	        	listaProntos.get(0).setTempo(listaProntos.get(0).getTempo() - quantum);	
	    		listaProntos.add(listaProntos.remove(0));
                        unidadeTempo += quantum;
	    	}
	    	
	    	/*
	    	 * se precisar de tempo por causa de IO ele é removido do topo de
	    	 * Processos Prontos e é adicionado na lista de Processos Bloqueados
	    	 */
	    	else {
	    		listaProntos.get(0).setTempo(listaProntos.get(0).getTempo() - tempoExecucaoIO);	
                        unidadeTempo += tempoExecucaoIO;
	    		if(listaProntos.size()>1 && listaProntos.get(0).getTempo()>0)
                            listaBloqueados.add(listaProntos.remove(0));                          
                        else if(listaProntos.get(0).getTempo()<=0)
                            processo = listaProntos.remove(0);
	    	}	
    	}
    	//verifica se o processo ainda precisara de tempo MENOR do que o quantum para ser executado
    	else if(listaProntos.size()>0 && listaProntos.get(0).getTempo() >= 0 && listaProntos.get(0).getTempo() <= quantum){
    		
                if(listaProntos.get(0).getTipo().equals("IO") && listaProntos.get(0).getTempo() > 0){
                    listaProntos.get(0).setTempo(listaProntos.get(0).getTempo() - tempoExecucaoIO);
                    
                    if(listaProntos.size()>1 && listaProntos.get(0).getTempo()>0){
                        listaBloqueados.add(listaProntos.remove(0));
                    }
                    	
                    unidadeTempo += tempoExecucaoIO;
                }else{
                    unidadeTempo += listaProntos.get(0).getTempo();
                    processo = listaProntos.remove(0);
                }
    	}
        unidadeTempo += tempoTrocaContexto;
    	// se o processo nao precisar mais de tempo de execução ele é removido da lista
        if(listaProntos.size()>0 && listaProntos.get(0).getTempo() <= 0)
    		processo = listaProntos.remove(0);
        
    	if(listaProntos.size()>0 && listaProntos.get(listaProntos.size()-1).getTempo() <= 0)
    		processo = listaProntos.remove(listaProntos.size()-1);
    	
        if(processo != null)
            processo = buscaIntacto(processo);
        return processo;
    }
    
    public void calculaTempoMedio(){
        this.tempoMedio += this.unidadeTempo;
    }
    
    public int getTempoMedio() {
        return tempoMedio / listaProntosINTACTO.size();
    }

    public void setTempoMedio(int tempoMedio) {
        this.tempoMedio = tempoMedio;
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
}
