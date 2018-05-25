/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Davide
 */
public class Pizzeria extends Thread {
    String nome;
    String [] menuCibo;
    String [] menuBere;
    private Socket socket;
    int [] ordiniCibo;
    int [] ordiniBere;
    
    public Pizzeria(Socket socket) {
        this.socket = socket;
        menuCibo = new String [3];
        menuCibo [0] = "Marherita";
        menuCibo [1] = "Diavola";
        menuCibo [2] = "Capricciosa";
        menuBere = new String [3];
        menuBere [0] ="Acqua";
        menuBere [1] ="Birra";
        menuBere [2] ="Vino";
        ordiniCibo = new int [3];
        ordiniBere = new int [3];
        for (int i = 0; i<3; i++){
            ordiniCibo[i] =0;
            ordiniBere[i] =0;
        }
    }

    public String getNome() {
        return nome;
    }

    public String[] getMenuCibo() {
        return menuCibo;
    }

    public String[] getMenuBere() {
        return menuBere;
    }
    
    public void run(){
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Inserisci il tuo nome! Inserisci exit per uscire");
            String resp ="";
            while(true){
                String input = in.readLine();
                if (input == null||input.equals("exit")){
                    out.println("Arrivederci!");
                    socket.close();
                    break;
                }
                nome = input;
                out.println("Ciao "+nome+"! Vuoi ordinare? (y,n)");
                input = in.readLine();
                if (input == null||input.equals("n")){
                    out.println("Arrivederci "+nome+" !");
                    socket.close();
                    break;
                }
                else{
                    while (true){
                        resp +="Vuoi ordinare da mangiare o da bere? (m,b)";
                        out.println(resp);
                            boolean isCibo;
                            input = in.readLine();
                            if (input.equals("m")) isCibo = true;
                            else isCibo = false;
                            if (isCibo){
                                resp="Cosa vuoi ordinare da mangiare? \n";
                                for (int i = 0; i<3; i++){
                                    resp+=((i+1)+": "+menuCibo[i]+"\n");
                                }
                                out.println(resp);
                                input =in.readLine();
                                if(input.equals("1")|| input.equals("2")|| input.equals("3")){
                                    ordiniCibo[Integer.parseInt(input)-1] +=1;
                                    resp = "";
                                }
                                else{
                                    resp=("input non valido \n");
                                }
                            }
                            if(!isCibo){
                                resp="Cosa vuoi ordinare da bere? \n";
                               for (int i = 0; i<3; i++){
                                    resp+=((i+1)+": "+menuBere[i]+"\n");
                                }
                               out.println(resp);
                               input =in.readLine();
                                if(input.equals("1")|| input.equals("2")|| input.equals("3")){
                                    ordiniBere[Integer.parseInt(input)-1] +=1;
                                    resp = "";
                                }
                                else{
                                    resp=("input non valido \n");
                                }
                            }
                            out.println(resp + "Vuoi ordinare altro? (y,n)");
                            input =in.readLine();
                            if (input.equals("n")){
                                resp = "Hai ordinato: \n";
                                for (int i = 0; i<3; i++){
                                    if(ordiniCibo[i]!=0) resp+=ordiniCibo[i]+" "+menuCibo[i]+"\n";
                                }
                                for (int i = 0; i<3; i++){
                                    if(ordiniBere[i]!=0) resp+=ordiniCibo[i]+" "+menuBere[i];
                                }
                                out.println(resp+"Arrivederci "+nome+"!");
                                socket.close();
                                break;
                            }
                        
                    }
            }
            }
        } catch(IOException e){
            System.out.println("Connessione non riuscita");   
        }finally{
            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(Pizzeria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
    }
    
    
    
}
