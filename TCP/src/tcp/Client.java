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
import java.util.stream.Collectors;

/**
 *
 * @author Davide
 */




public class Client {
    
    private BufferedReader in;
    private PrintWriter out;
    private BufferedReader stdIn;
    
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.connectToServer();
    }
    
    public String readAll(BufferedReader input){
        String s="";
        try{
            char [] buff = new char [4096];
            int size = 0;
            while ((size=input.read(buff))!=-1){
                s = new String(buff,0,size);
            }
        }
        catch(Exception e){
            System.out.println("Error reading from buffer");
        }
        return s;
    }
    public  void connectToServer() throws IOException{
        
        String serverAddress ="127.0.0.1";
        String userInput;
        
        Socket socket = new Socket(serverAddress, 9898);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        stdIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(in.readLine());
        
        while((userInput = stdIn.readLine())!= null){
           char [] buffOut=new char[1000]; 
           out.println(userInput);
           //System.out.println("prima di read");
           // System.out.println(in.readLine());
           in.read(buffOut);
           //System.out.println("dopo read");
           String output="";
           for(int i=0;i<buffOut.length;i++){
               output+=buffOut[i];
           }
           System.out.println(output);
         // String s=in.lines().collect(Collectors.joining());
           // out.println("Reading out");
            //out.println(readAll(in));
/*            Object [] s = in.lines().toArray();
            for (int i = 0; i< s.length; i++){
                System.out.println(i);
                System.out.println(s[i].toString());
            }*/
        }
        
    }
    
}
