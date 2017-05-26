/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import static client.Conexao.moedas;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ProBook
 */
public class GerenciadorMensagem {
    Conexao conexao;
    
    public GerenciadorMensagem() throws IOException{
    try{
        conexao = new Conexao("172.16.105.97", 8989);
//        pegarMoeda();
        //System.out.println("O cliente conectou ao servidor");
        
        }catch (IOException e){
            //System.out.println("Error -> " + e.getMessage());
        } 
    
    }
    public Jogador retornarPersonagem() throws IOException{
        //System.out.println("CARALHO -> "+conexao.getJogador());
//        System.out.println("Jogador id -> " + jogador.getId());
//                System.out.println("Jogador nome -> " + jogador.getNome());
//                System.out.println("Jogador x -> " + jogador.getX());
//                System.out.println("Jogador y -> " + jogador.getY());
//                System.out.println("Jogador orientacao -> " + jogador.getOrientacao());
            
//        System.out.println("ID -> " + jogador.getId());
        return conexao.getJogador();
    }
//        public void pegarMoeda(){
//		new Thread() {
//			
//			@Override
//			public void run() {
//                            
//                                while(true){
//                                //System.out.println("PEGANDO MOEDA");
//                                for(int i=0;i<moedas.size();i++){
////                                    if (conexao.jogador.getX() <= moedas.get(i).getX()+50){
////                                        if(conexao.jogador.getX() >= moedas.get(i).getX()-50){
////                                          if (conexao.jogador.getY() <= moedas.get(i).getY()+30){
////                                                if(conexao.jogador.getY() >= moedas.get(i).getY()-30){
////                                                    System.out.println("-----------");
////                                                    System.out.println("ID -> " + moedas.get(i).id);
////                                                    System.out.println("Calc X -> " + (conexao.jogador.getX()-moedas.get(i).getX()));
////                                                    System.out.println("Calc X -> " + (conexao.jogador.getY()-moedas.get(i).getY()));
////                      
////                                                    System.out.println("-----------");
//                                                    int aX = conexao.jogador.getX();
//                                                    int aY = conexao.jogador.getY();
//                                                    int ladoDireitoA = aX + 50;
//                                                    int ladoEsquerdoA = aX;
//                                                    int ladoDeBaixoA = aY + 30;
//                                                    int ladoDeCimaA = aY;
//                                                    int bX = moedas.get(i).getX();
//                                                    int bY = moedas.get(i).getY();
//                                                    int ladoDireitoB = bX + 15;
//                                                    int ladoEsquerdoB = bX;
//                                                    int ladoDeBaixoB = bY + 15;
//                                                    int ladoDeCimaB = bY;
//                                                    boolean achou1 = false;
//                                                    boolean achou2 = false;
//                                                    boolean achou3 = false;
//                                                    boolean achou4 = false;
//                                                    if(ladoDireitoA>=ladoEsquerdoB){
//                                                        achou1 = true;
//                                                    }
//                                                    if(ladoDeCimaA<=ladoDeBaixoB){
//                                                        achou2 = true;
//                                                    }
//                                                    if(ladoDeBaixoA>=ladoDeCimaB){
//                                                        achou3 = true;
//                                                    }
//                                                    if(ladoEsquerdoA<=ladoDireitoB){
//                                                        achou4 = true;
//                                                    }
//                                                    
//                                                    if(achou1 && achou2 && achou3 && achou4){
//                                                        //System.out.println("ENTRO NO IF PA APAGA");
//
//                                                        
//                                                    
//                                                            try {
//                                                                conexao.jogador.setScore(conexao.jogador.getScore()+1);
//                                                                pegarMoeda(conexao.jogador,i);
//                                                            } catch (IOException ex) {
//                                                                Logger.getLogger(GerenciadorMensagem.class.getName()).log(Level.SEVERE, null, ex);
//                                                            }
//                                                        }
//                                                    
////                                                }
////                                            }  
////                                        }
////                                    }
//                                }
//                            
//			}
//                        }
//		}.start();
//
//	}
    public void pegarMoeda(Jogador jogador, int i) throws IOException{
        setarPersonagem(jogador);
        String bigstring = "rm+"+i;
        conexao.enviarMensagem(bigstring);
        
    }
    
    public void setarPersonagem(Jogador jogador) throws IOException{
            
            conexao.atualizarJogador(jogador);
            String bigstring = "att+";
            
            bigstring += jogador.getId()+";"+jogador.getNome()+";"+jogador.getX()+";"+jogador.getY()+";"+jogador.getScore();
            conexao.enviarMensagem(bigstring);
//          conexao.
//            for(int i=0;i<jogadores.size();i++){
//                bigstring += jogadores.get(i).getId()+";"+jogadores.get(i).getLocalizacao().x+";"+jogadores.get(i).getLocalizacao().y+"|";
//            }
    }
    public void encerrarSessao() throws IOException{
        //System.out.println("Id do Jogador em Encerrar -> " + conexao.getId());
        conexao.enviarMensagem("FIM+"+conexao.getJogador().getId());
    }
    public ArrayList<Jogador> retornarTodosJogadores() throws IOException{
            ArrayList<Jogador> jogadores =  new ArrayList<Jogador>();
            conexao.enviarMensagem("all");
            String stringona = "";
              stringona= conexao.receberMensagem();
            //System.out.println("STRINGONA -> " + stringona);
            
            String array[] = stringona.split("\\|");
            if(array.length >1){
                for (int i=0;i<array.length;i++){
                    String teste = array[i];
                    String[] info = teste.split(";");
                    Jogador aux = new Jogador(info[0],info[1]);
                    aux.setX(Integer.parseInt(info[2]));
                    aux.setY(Integer.parseInt(info[3]));
                    aux.setScore(Integer.parseInt(info[4]));
                    jogadores.add(aux);
                }
            }
            return jogadores;
    }
    public ArrayList<Moeda> retornarMoedas() throws IOException{
            ArrayList<Moeda> moedas =  new ArrayList<Moeda>();
            conexao.enviarMensagem("allcoins");
            String stringona = "";
              stringona= conexao.receberMensagem();
            //System.out.println("STRINGONA -> " + stringona);
            
            String array[] = stringona.split("\\|");
            if(array.length >1){
                for (int i=0;i<array.length;i++){
                    String teste = array[i];
                    String[] info = teste.split(";");
                    Moeda aux = new Moeda(Integer.parseInt(info[0]));
                    //Jogador aux = new Jogador(info[0],info[1]);
                    aux.setX(Integer.parseInt(info[1]));
                    aux.setY(Integer.parseInt(info[2]));
                    moedas.add(aux);
                }
            }
            return moedas;
    }
    
//    public void tratarMensagem() throws IOException{
//    String recebido = conexao.receberMensagem();
//    if(recebido.contains(";")){
//             //System.out.println("Entrou no contains");
//            String jogadores = recebido;
//            String array[] = jogadores.split("\\|");
//            //System.out.println("Tamanho Array " + array.length);
//                for(int e=0;e<array.length;e++){
//                    //System.out.println("FOR");
//                    String info[] = array[e].split(";");
//                    Jogador aux = new Jogador(info[0]);
//                    aux.setX(Integer.parseInt(info[1]));
//                    aux.setY(Integer.parseInt(info[2]));
////                    System.out.println("----------");
////                    System.out.println("ID -> " + aux.getId());
////                    System.out.println("----------");
//                    players.add(aux);
//
//                }
////                for(int e=0;e<players.size();e++){
////                    System.out.println("----------");
////                    System.out.println("ID -> " + players.get(e).getId());
////                    System.out.println("----------");
////                
////                }
//            }
//    }
}
