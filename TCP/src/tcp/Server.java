/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author Davide
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        System.out.println("The server is running");
        try{
            ServerSocket listener = new ServerSocket(9898);
            try{
                while(true){
                    new Pizzeria(listener.accept()).start();
                }
            }catch(Exception e){
                System.out.println("Connection failed");
            }finally{
                listener.close();
        }
        } catch(Exception e){
            System.out.println("Qui  " + e.getMessage());
        }
        
    }
}
