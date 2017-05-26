/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;
    
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ProBook
 */
public class Conexao {
    private Socket cliente;
    private static int id;
    public Jogador jogador;
    public int aa;
    public static ArrayList<Jogador> players = new ArrayList<Jogador>();
    public static ArrayList<Moeda> moedas = new ArrayList<Moeda>();
    
    public Conexao(String ip, int porta) throws IOException{
        cliente = new Socket(ip, porta);
        
        
    }
    
    public void criarJogador(String nome) throws IOException{
            if(jogador == null){
                System.out.println("Criando objeto");
            enviarMensagem("id");
            String id2 = receberMensagem();
            aa = 10;
            jogador = new Jogador(id2, nome);
            atualizarJogador(jogador);
            }
    }
    public void atualizarJogador(Jogador jogador){
        this.jogador = jogador;
    
    }
    
    public String receberMensagem() throws IOException{
        String stringona = "";
        Scanner s = null;
         s = new Scanner(cliente.getInputStream());

             stringona+=s.nextLine();
         
         return stringona;
    }
    
    public void enviarMensagem(String bigstring) throws IOException{
    PrintStream saida;
            saida = new PrintStream(cliente.getOutputStream());
        System.out.println("Enviou -> "+ bigstring);
    saida.println(bigstring);
    }  

    public Socket getCliente() {
        return cliente;
    }

    public void setCliente(Socket cliente) {
        this.cliente = cliente;
    }

    public static ArrayList<Jogador> getPlayers() {
        return players;
    }

    public static void setPlayers(ArrayList<Jogador> players) {
        Conexao.players = players;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public int getId() {
        return id;
    }
    
    
}
