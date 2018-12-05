/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoTP;

import java.util.Scanner;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int opcao = 0, quantum = 5;

		System.out.println("MENU");
		System.out.println("1 - FCFS"
				+ "\n2 - JSF "
				+ "\n3 - Prioridade "
				+ "\n4 - Round Robin "
				+ "\n5 - Sair");
				opcao = sc.nextInt();
		
		if(opcao==5) {
			System.out.println("Simulacao finalizada");
			System.exit(0);
		}
		
		System.out.println("Deseja iniciar com uma lista pre definida? [s/n]");
			char sim = sc.next().charAt(0);
		
		char continuar;
		switch (opcao) {
			case 1:
				FCFS fcfs = new FCFS();
				if(sim == 's' || sim =='S')
					fcfs.listaExemplos();
				else {
					String nome = "", tipo = ""; int tempo = 0; char aux = 65; 
					do {
					nome = String.valueOf(aux++);
					System.out.print("Tempo: ");
						tempo = sc.nextInt();
					System.out.print("Tipo: CPU = c || IO = i: ");
						tipo = sc.next();
						
						if(tipo.equalsIgnoreCase("c")) tipo = "CPU";
						else if(tipo.equalsIgnoreCase("i")) tipo = "IO";
						else System.out.print("Dado incorreto");
						
					if(tipo.equals("CPU") || tipo.equals("IO"))
						fcfs.adicionar(nome, tempo, tipo);
					
					System.out.print("Deseja adiciona mais?[s/n]");
					continuar = sc.next().charAt(0);
					}while(continuar != 'n');
				}
				fcfs.imprime();
				System.out.print("\n\n\nExecutar? [s/n]");
				continuar = sc.next().charAt(0);
				if(continuar != 'n' && continuar != 'N') {
					do {
						fcfs.executar();
						System.out.print("Executar? [s/n]");
						continuar = sc.next().charAt(0);
					}while(continuar != 'n' && continuar != 'N');
				}
				
				break;
			case 2:
				SJF sjf = new SJF(quantum);
				if(sim == 's' || sim =='S')
					sjf.listaExemplos();
				else {
					String nome = "", tipo = ""; int tempo = 0; char aux = 65; 
					do {
					nome = String.valueOf(aux++);
					System.out.print("Tempo: ");
						tempo = sc.nextInt();
					System.out.print("Tipo: CPU = c || IO = i: ");
						tipo = sc.next();
						
						if(tipo.equalsIgnoreCase("c")) tipo = "CPU";
						else if(tipo.equalsIgnoreCase("i")) tipo = "IO";
						else System.out.print("Dado incorreto");
						
					if(tipo.equals("CPU") || tipo.equals("IO"))
						sjf.adicionar(nome, tempo, tipo);
					
					System.out.print("Deseja adiciona mais?[s/n]");
					continuar = sc.next().charAt(0);
					}while(continuar != 'n');
				}
					sjf.imprime();
					System.out.print("\n\n\nExecutar? [s/n]");
					continuar = sc.next().charAt(0);
					if(continuar != 'n' && continuar != 'N') {
						do {
							sjf.executar();
							System.out.print("Executar? [s/n]");
							continuar = sc.next().charAt(0);
						}while(continuar != 'n' && continuar != 'N');
					}
				break;
			case 3: //IMPLEMENTAR
				break;
				
			case 4:
				RoundRobin robin = new RoundRobin(quantum);
				if(sim == 's' || sim =='S')
					robin.listaExemplos();
				else {
					String nome = "", tipo = ""; int tempo = 0; char aux = 65; 
					do {
					nome = String.valueOf(aux++);
					System.out.print("Tempo: ");
						tempo = sc.nextInt();
					System.out.print("Tipo: CPU = c || IO = i: ");
						tipo = sc.next();
						
						if(tipo.equalsIgnoreCase("c")) tipo = "CPU";
						else if(tipo.equalsIgnoreCase("i")) tipo = "IO";
						else System.out.print("Dado incorreto");
						
					if(tipo.equals("CPU") || tipo.equals("IO"))
						robin.adicionar(nome, tempo, tipo);
					
					System.out.print("Deseja adiciona mais?[s/n]");
					continuar = sc.next().charAt(0);
					}while(continuar != 'n');
				}
					robin.imprime();
					System.out.print("\n\n\nExecutar? [s/n]");
					continuar = sc.next().charAt(0);
					if(continuar != 'n' && continuar != 'N') {
						do {
							robin.executar();
							System.out.print("Executar? [s/n]");
							continuar = sc.next().charAt(0);
						}while(continuar != 'n' && continuar != 'N');
					}
			break;
			
		}
		System.out.println("Simulacao finalizada");
    }
}
