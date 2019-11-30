package sample;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketProgram {

    public static void sendFile(DataOutputStream output,File file) throws IOException {
        FileInputStream fileIn = new FileInputStream(file);
        byte[] buf = new byte[Short.MAX_VALUE];
        int bytesRead;
        while( (bytesRead = fileIn.read(buf)) != -1 ) {
            output.writeShort(bytesRead);
            output.write(buf,0,bytesRead);
        }
        output.writeShort(-1);
        fileIn.close();
    }



    public static void receiveFile(DataInputStream input,File file) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(file);
        byte[] buf = new byte[Short.MAX_VALUE];
        int bytesSent;
        while( (bytesSent = input.readShort()) != -1 ) {
            input.readFully(buf,0,bytesSent);
            fileOut.write(buf,0,bytesSent);
        }
        fileOut.close();
    }

    static class runSocketServer implements Runnable{
        @Override
        public void run(){
            try {
                SocketServer.mainServer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static public class SocketServer{

        private static ServerSocket server;
        private static int port = 9876;

        public static void mainServer() throws IOException {
            server = new ServerSocket(port);
            while(true){
                System.out.println("Waiting for the client request");
                Socket socket = server.accept();
                DataInputStream ois = new DataInputStream(socket.getInputStream());
                DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
                sendFile(oos,new File("savedUsers.txt"));
                ois.close();
                oos.close();
                socket.close();
                break;
            }
            System.out.println("Shutting down Socket server!!");
            server.close();
        }

    }

    static class runSocketClient implements Runnable{
        @Override
        public void run(){
            try {
                SocketClient.mainClient();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
    static public class SocketClient{

        public static void mainClient() throws IOException {
            Socket socket = null;
            socket = new Socket("192.168.43.182", 9876);
            DataInputStream ois = new DataInputStream(socket.getInputStream());
            receiveFile(ois,new File("savedUsers.txt"));
            System.out.println("savedUsers updated probably");
        }
    }
}