/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import static client.Conexao.players;
import static client.Conexao.moedas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author ProBook
 */
public class Interface extends JPanel implements ActionListener, KeyListener{
    Timer tm = new Timer(5, this);
    GerenciadorMensagem gerenciador = null;
    int x=0, y=0, velX=0, velY=0, vel = 10;
    String nome;
    boolean mapa = false;
    
    
    public Interface(String nome) throws IOException{
        tm.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        this.nome = nome;
         gerenciador = new GerenciadorMensagem();
         gerenciador.conexao.criarJogador(nome);
         }
    public BufferedImage convertImage(String caminho) throws IOException{
            BufferedImage img = ImageIO.read(new File(caminho));
            return img;
    }
    @Override
    public void paintComponent(Graphics g){
        try {
            super.paintComponent(g);
            g.setColor(Color.decode("#378ad1"));
            Jogador jogador = gerenciador.retornarPersonagem();
            g.fillRect(0, 0, 810, 610);
            g.drawImage(convertImage("C:\\Users\\ProBook\\Documents\\NetBeansProjects\\Client\\src\\img\\campo.png"), 210, 5, this);
            
            g.fillRect(jogador.getX(),jogador.getY(),50,30);
            g.drawString(jogador.getNome(), jogador.getX(), jogador.getY());
            
            //g.fillRect(x,y,50,30);
            // System.out.println("Tamanho de Players -> " + players.size());
            g.setColor(Color.yellow);
            for(int i=0;i<moedas.size();i++){
                g.drawImage(convertImage("C:\\Users\\ProBook\\Documents\\NetBeansProjects\\Client\\src\\img\\moeda.png"), moedas.get(i).x, moedas.get(i).y, this);
            
                //g.fillArc(moedas.get(i).x, moedas.get(i).y, 15,15,0,360);
                //g.drawString(i, moedas.get(i).x+1, moedas.get(i).y+1);
            }
            g.setColor(Color.BLACK);
            for(int i=0;i<players.size();i++){
                //System.out.println(""+i);
                if(players.get(i).getId() == null ? jogador.getId() != null : !players.get(i).getId().equals(jogador.getId())){
                g.fillRect(players.get(i).getX(),players.get(i).getY(),50,30);
                g.drawString(players.get(i).getNome(), players.get(i).getX(), players.get(i).getY());
                }
            }
            //g.fillRect(5, 5,200, 300);
            g.drawImage(convertImage("C:\\Users\\ProBook\\Documents\\NetBeansProjects\\Client\\src\\img\\rank.png"), 5, 5, this);
            
            g.setColor(Color.white);
            g.drawString("Nome", 20, 30);
            g.drawString("Score", 140, 30);
            g.drawString("________________________", 20, 34);
            int yy = 50;
            players.sort(Comparator.comparing(Jogador::getScore).reversed());
            for(int i=0;i<players.size();i++){
                g.drawString(players.get(i).getNome(), 20, yy);
                g.drawString(players.get(i).getScore()+"", 140, yy);
                yy+=16;
            }
        } catch (IOException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void iniciarInterface() throws IOException{
        
        Interface t = new Interface(this.nome);
        JFrame jf = new JFrame();
        jf.addWindowListener( new WindowListener()
        {
            @Override
            public void windowClosing( WindowEvent e )
            {
                try {
                    gerenciador.encerrarSessao();
                } catch (IOException ex) {
                    Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println( "Fechando!!!" );
            }
            public void windowClosed( WindowEvent e )
            {
                System.out.println( "Fechado!!!" );
            }
            public void windowOpened( WindowEvent e ) {}
            public void windowIconified( WindowEvent e ) {}
            public void windowDeiconified( WindowEvent e ) {}
            public void windowActivated( WindowEvent e ) {}
            public void windowDeactivated( WindowEvent e ) {}
        } );
        jf.setTitle("Game");
        jf.setSize(820,650);
        jf.setVisible(true);
        //jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setResizable(false);
        jf.add(t);
        
        pegarJogadoresMoedas();
        
        
        //gerenciador.setarPersonagem(gerenciador.retornarPersonagem());
    
    }
    public void attJogador() throws IOException{
        Jogador jogador = null;
            jogador = gerenciador.retornarPersonagem();
            jogador.setX(jogador.getX()+ velX);
            jogador.setY(jogador.getY()+ velY);
            for(int i=0;i<moedas.size();i++){
                                                    int aX = gerenciador.conexao.jogador.getX();
                                                    int aY = gerenciador.conexao.jogador.getY();
                                                    int ladoDireitoA = aX + 50;
                                                    int ladoEsquerdoA = aX;
                                                    int ladoDeBaixoA = aY + 30;
                                                    int ladoDeCimaA = aY;
                                                    int bX = moedas.get(i).getX();
                                                    int bY = moedas.get(i).getY();
                                                    int ladoDireitoB = bX + 15;
                                                    int ladoEsquerdoB = bX;
                                                    int ladoDeBaixoB = bY + 15;
                                                    int ladoDeCimaB = bY;
                                                    boolean achou1 = false;
                                                    boolean achou2 = false;
                                                    boolean achou3 = false;
                                                    boolean achou4 = false;
                                                    if(ladoDireitoA>=ladoEsquerdoB){
                                                        achou1 = true;
                                                    }
                                                    if(ladoDeCimaA<=ladoDeBaixoB){
                                                        achou2 = true;
                                                    }
                                                    if(ladoDeBaixoA>=ladoDeCimaB){
                                                        achou3 = true;
                                                    }
                                                    if(ladoEsquerdoA<=ladoDireitoB){
                                                        achou4 = true;
                                                    }
                                                    
                                                    if(achou1 && achou2 && achou3 && achou4){
                                                        //System.out.println("ENTRO NO IF PA APAGA");

                                                        
                                                    
                                                            try {
                                                                gerenciador.conexao.jogador.setScore(gerenciador.conexao.jogador.getScore()+1);
                                                                gerenciador.pegarMoeda(gerenciador.conexao.jogador,moedas.get(i).getId());
                                                            } catch (IOException ex) {
                                                                Logger.getLogger(GerenciadorMensagem.class.getName()).log(Level.SEVERE, null, ex);
                                                            }
                                                        }
                                                    
                                }
            gerenciador.setarPersonagem(jogador);
    }
    public void pegarJogadoresMoedas(){
		new Thread() {
			
			@Override
			public void run() {
                            try {
                                while(true){
                                ArrayList<Jogador> aux = gerenciador.retornarTodosJogadores();
                                if(!players.equals(aux)){
                                    players = aux;
                                    
                                }
                                ArrayList<Moeda> aux2 = gerenciador.retornarMoedas();
                                    //System.out.println("Tamanho da array moedas -> " + aux.size());
                                if(!moedas.equals(aux2)){
                                    moedas = aux2;
                                    
                                }
                                
                            }
			}   catch (IOException ex) {
                                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
		}.start();

	}
    @Override
    public void actionPerformed(ActionEvent e) {
                    repaint();

            
            
//            public static final long TEMPO = (1000 * 60); // atualiza o site a cada 1 minuto
//        //****INICIA A TAREFA ELE VERIFICA A CADA UM MINUTO****//
//        System.out.println("inicio");  
//         Timer timer = null;  
//         if (timer == null) {  
//             timer = new Timer();  
//             TimerTask tarefa = new TimerTask() {  
//                 public void run() {  
//                     try {  
//                         System.out.println("Teste Agendador");  
//                        //chamar metodo  
//                     } catch (Exception e) {  
//                         e.printStackTrace();  
//                     }  
//                 }  
//             };  
//             timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);  
//         }
            
      
    }
  
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        
        if(c == KeyEvent.VK_LEFT){
            velX = -1 * vel;
            velY = 0;
            try {
                attJogador();
            } catch (IOException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            }
            //System.out.println("ANDO ESQUERDA");
        }
        if(c == KeyEvent.VK_UP){
            velX = 0;
            velY = -1 * vel;
            //System.out.println("ANDO CIMA");
            try {
                attJogador();
            } catch (IOException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(c == KeyEvent.VK_RIGHT){
            velX = 1 * vel;
            velY = 0;
            //System.out.println("ANDO DIREITA");
            try {
                attJogador();
            } catch (IOException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(c == KeyEvent.VK_DOWN){
            velX = 0;
            velY = 1 * vel;
           // System.out.println("ANDO BAIXO");
            try {
                attJogador();
            } catch (IOException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        velX = 0;
        velY = 0;
    }
}
