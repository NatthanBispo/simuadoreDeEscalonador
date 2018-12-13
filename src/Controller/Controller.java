/*
 * 
 * GITHUB LINK: https://github.com/NatthanBispo/simuadoreDeEscalonador.git
 * 
 */
package Controller;

import SoTP.FCFS;
import SoTP.Prioridade;
import SoTP.Processo;
import SoTP.RoundRobin;
import SoTP.SJF;
import Views.ViewMenu;
import java.util.LinkedList;

public class Controller {   
    FCFS fcfs;
    Prioridade prioridade;
    RoundRobin roundRobin;
    SJF sjf;
    
    public void viewMenu(){
        ViewMenu view = new ViewMenu();
        view.startView();
    }
    
    /*
    **
    **  FCFS
    **
    */
    public void instanciaFCFS(){
        fcfs = new FCFS();
    }
    
    public void adicionarFCFS(int tempo, String tipo){
        fcfs.adicionar(tempo, tipo);
    }
    
    public void adicionarFCFS(){
        fcfs.listaExemplos();
    }
    
    public void removerFCFS(){
        fcfs.remover();
    }
    
    public LinkedList<Processo> retornaListaFCFS(){
        return fcfs.retornaLista();
    }
    
    public LinkedList<Integer> retornaListaTempoFCFS(){
        return fcfs.retornaListaTempo();
    }
    
    public void listaSugeridaFCFS(){
        fcfs.listaExemplos();
    }
    
    public Processo executarFCFS(){
        return fcfs.executar();
    }
    
    public int retornaTempoMedioFCFS(){
        return fcfs.getTempoMedio();
    }
    
    /*
    **
    **  SJF
    **
    */
    public void instanciaSJF(int quantum){
        sjf = new SJF(quantum);
    }
    
    public void adicionarSJF(int tempo, String tipo){
        sjf.adicionar(tempo, tipo);
    }
    
    public void removerSJF(){
        sjf.remover();
    }
    
    public LinkedList<Processo> retornaListaProntosSJF(){
        return sjf.retornaListaProntos();
    }
    
    public LinkedList<Processo> retornaListaBloqueadosSJF(){
        return sjf.retornaListaBloqueados();
    }
    
    public Processo executarSJF(){
        return sjf.executar();
    }
    
    public void listaSugeridaSJF(){
        sjf.listaExemplos();
    }
    
    public int retornaTempoTotalSJF(){
        return sjf.getTempoTotal();
    }
    
    public void calculaTempoMedioSJF(){
        sjf.calculaTempoMedio();
    }
    
    public int retornaTempoMedioSJF(){
        return sjf.getTempoMedio();
    }
    /*
    **
    **  ROUND ROBIN
    **
    */
    public void instanciaRR(int quantum){
        roundRobin = new RoundRobin(quantum);
    }
    public void adicionarRR(int tempo, String tipo){
        roundRobin.adicionar(tempo, tipo);
    }
    
    public LinkedList<Processo> retornaListaProntosRR(){
        return roundRobin.retornaListaProntos();
    }
    
    public LinkedList<Processo> retornaListaBloqueadosRR(){
        return roundRobin.retornaListaBloqueados();
    }
    
    public LinkedList<Integer> retornaListaTempoRestanteRR(){
        return roundRobin.retornaListaTempoRestante();
    }
    
    public Processo executarRR(){
        return roundRobin.executar();
    }
    
    public void listaSugeridaRR(){
        roundRobin.listaExemplos();
    }
    
    public int retornaTotalUnidadeDeTempoRR(){
        return roundRobin.getUnidadeTempo();
    }
    
    public void calculaTempoMedioRR(){
        roundRobin.calculaTempoMedio();
    }
    
    public int retornaTempoMedioRR(){
        return roundRobin.getTempoMedio();
    }
    
    /*
    **
    **  PRIORIDADE
    **
    */

    public void instanciaPRIORIDADE(int quantum){
        prioridade = new Prioridade(quantum);
    }
    
    public void adicionarPRIORIDADE(int tempo, String tipo, int prioridade){
        this.prioridade.adicionar(tempo, tipo, prioridade);
    }
    
    public LinkedList<Processo> retornaListaProntosPRIORIDADE(){
        return prioridade.getListaProntos();
    }
    
    public LinkedList<Processo> retornaListaBloqueadosPRIORIDADE(){
        return prioridade.getListaBloqueados();
    }
    
    public void listaSugeridaPRIORIDADE(){
        prioridade.listaExemplos();
    }
    
    public int retornaUnidadeTempoPRIORIDADE(){
        return prioridade.getUnidadeTempo();
    }
    
    public void executarPRIORIDADE(){
        prioridade.executar();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Controller ct = new Controller();
        ct.viewMenu();
    	
    }
}
