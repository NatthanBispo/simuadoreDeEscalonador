/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoTP;

import java.util.LinkedList;
import java.util.Random;

public class SJF {
    private int tempoTotal = 0;
    private int tempoTrocaContexto=1;
    private int tempoExecucaoIO;
    private int contContexto=0;
    private int quantum;
    private char nome;
    private int tempoMedio;
    
    private LinkedList<Processo> listaProntos;
    private LinkedList<Processo> listaBloqueados;
    private LinkedList<Processo> listaProntosINTACTA;
    
    Random rd = new Random();
    
    public SJF(int quantum) {
    	this.listaProntos = new LinkedList<>();
    	this.listaBloqueados = new LinkedList<>();
        this.listaProntosINTACTA = new LinkedList<>();
    	this.quantum = quantum;
        this.nome = 65;
        this.tempoMedio = 0;
        this.tempoTotal = 0;
        this.tempoExecucaoIO = 1;
    }
    
    public void listaExemplos() {
    	for(int i=0; i<10; ++i) {
            if(i%4!=0)
                adicionar(rd.nextInt(20)+5, "CPU");
            else
                adicionar(rd.nextInt(20)+5, "IO");
    	}
    }
    
    public void adicionar(int tempo, String tipo) {
    	listaProntos.addLast(new Processo(String.valueOf(nome++), tempo, tipo));
        listaProntosINTACTA.addLast(new Processo(String.valueOf(nome), tempo, tipo));
    }
    
    public void remover(){
        listaProntos.removeLast();
    }
    
    public Processo buscaProcessoINTACTO(Processo processo){
        for(Processo pro : listaProntosINTACTA){
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
                        tempoTotal += quantum;
	    	}
	    	
	    	/*
	    	 * se precisar de tempo por causa de IO ele é removido do topo de
	    	 * Processos Prontos e é adicionado na lista de Processos Bloqueados
	    	 */
	    	else {
	    		listaProntos.get(0).setTempo(listaProntos.get(0).getTempo() - tempoExecucaoIO);	
                        tempoTotal += tempoExecucaoIO;
	    		if(listaProntos.size()>1 && listaProntos.get(0).getTempo()>0)
                            listaBloqueados.add(listaProntos.remove(0));                          
                        else if(listaProntos.get(0).getTempo()<=0)
                            processo = listaProntos.remove(0);
	    	}	
    	}
    	//verifica se o processo ainda precisara de tempo MENOR do que o quantum para ser executado
    	else if(listaProntos.size()>0 && listaProntos.get(0).getTempo() >= 0 && listaProntos.get(0).getTempo() <= quantum){
    		
                if(listaProntos.get(0).getTipo().equals("IO") && listaProntos.get(0).getTempo() > 1){
                    listaProntos.get(0).setTempo(listaProntos.get(0).getTempo() - tempoExecucaoIO);
                    
                    if(listaProntos.size()>1 && listaProntos.get(0).getTempo()>0)
                        listaBloqueados.add(listaProntos.remove(0));
                    	
                    tempoTotal += tempoExecucaoIO;
                }else{
                    tempoTotal += listaProntos.get(0).getTempo();
                    processo = listaProntos.remove(0);
                }
    	}
        tempoTotal += tempoTrocaContexto;
    	// se o processo nao precisar mais de tempo de execução ele é removido da lista
        if(listaProntos.size()>0){
            if(listaProntos.get(0).getTempo() <= 0)
    		processo = listaProntos.remove(0);
        
            if(listaProntos.get(listaProntos.size()-1).getTempo() <= 0)
    		processo = listaProntos.remove(listaProntos.size()-1);
        }
    	ordena();
        if(processo != null)
           return buscaProcessoINTACTO(processo);
        else
            return processo;
    }

    public void ordena() {
    	LinkedList<Processo> listaAux = new LinkedList<>();
    	while(listaProntos.size()>0) {
    		int menor = 0;
    		for(int j = 0; j<listaProntos.size(); j++) {
    			if(listaProntos.get(j).getTempo() < listaProntos.get(menor).getTempo())
    				menor = j;
    		}
    			listaAux.addLast(listaProntos.remove(menor));
    	}
    	listaProntos = listaAux;
    }
        
    public void calculaTempoMedio(){
        this.tempoMedio += this.tempoTotal;
    }    
    
    public void setQuantum(int quantum) {
    	this.quantum = quantum;
    }
    
    public int getQuantum() {
    	return this.quantum;
    }
    
    public int getTempoTotal() {
    	return this.tempoTotal;
    }
    
    public LinkedList<Processo> retornaListaProntos(){
        return this.listaProntos;
    }
    
    public LinkedList<Processo> retornaListaBloqueados(){
        return this.listaBloqueados;
    }

    public int getTempoMedio() {
        return this.tempoMedio / this.listaProntosINTACTA.size();
    }
}
