/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kg.edu.iaau.imiaau.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.HashMap;
import java.util.Iterator;
import kg.edu.iaau.imiaau.interfaces.IAppManager;
import kg.edu.iaau.imiaau.interfaces.ISocketOperator;

/**
 *
 * @author Kantoro
 */
public class SocketOperator implements ISocketOperator {
    
    private static final String AUTHENTICATION_SERVER_ADDRESS = "http://localhost/android_im/";
    
    private int listeningPort = 0;
    
    private static final String HTTP_REQUEST_FAILED = null;
    
    private HashMap<InetAddress, Socket> sockets = new HashMap<InetAddress, Socket>();
    
    private ServerSocket serverSocket = null;
    
    private boolean listening;
    
    private IAppManager appManager;
    
    
    
    public SocketOperator(IAppManager appManager) {
        this.appManager = appManager;
    }
    
    public String sendHttpRequest(String params) {
        URL url;
        String result = new String();
        try {
            url = new URL(AUTHENTICATION_SERVER_ADDRESS);
            HttpURLConnection connection;
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            
            PrintWriter out = new PrintWriter( connection.getOutputStream() );
            
            out.println(params);
            out.close();
            
            BufferedReader in = new BufferedReader( 
                    new InputStreamReader( connection.getInputStream() ) );
            String inputLine;
            
            while ( (inputLine = in.readLine()) != null ) {
                result = result.concat(inputLine);
            }
            in.close();
        } catch (MalformedURLException malEx) {
            System.out.println(malEx);
        } catch (IOException iOException) {
            System.out.println(iOException);
        }
        
        if( result.length()==0 )
            result = HTTP_REQUEST_FAILED;
        
        return result;
    }
    
    public boolean sendMessage(String message, String ip, int port) {
        
        try {
            String[] str = ip.split("\\.");
            
            byte[] IP = new byte[str.length];
            
            for (int i = 0; i < str.length; i++) {
                IP[i] = (byte) Integer.parseInt(str[i]);
            }
            
            Socket socket = getSocket(InetAddress.getByAddress(IP), port);
            
            if( socket == null )
                return false;
            
            PrintWriter out = null;
            out = new PrintWriter(socket.getOutputStream(),true);
            out.println(message);
        } catch(UnknownHostException uhe) {
            return false;
        } catch(IOException ioe) {
            return false;
        }
        return true;
    }

    public int startListening(int port) {
        listening  = true;
        try {
            serverSocket = new ServerSocket(port);
            this.listeningPort = port;            
        } catch (IOException ioe) {
            this.listeningPort = 0;
            return 0;
        }
        while (listening) {            
            try {
                new ReceiveConnection(serverSocket.accept()).start();
            } catch (IOException ioe) {
                return 2;
            }
        }
        try {
            serverSocket.close();
        } catch (IOException ioe) {
            System.out.println("Exception server socket " + " Exception when closing server socket");
            return 3;
        }
        return 1;
    }

    public void stopListening() {
        
        this.listening = false;
        
    }

    public void exit() {
        for (Iterator<Socket> it = sockets.values().iterator(); it.hasNext();) {
            Socket socket = (Socket) it.next();
            try {
                socket.shutdownInput();
                socket.shutdownOutput();
                socket.close();
            } catch (IOException ioe) {
                
            }
        }
        sockets.clear();
        stopListening();
        appManager = null;
    }

    public int getListeningPort() {
        return this.listeningPort;
    }
    
    private Socket getSocket(InetAddress addr, int portNo) {
        Socket socket = null;
        
        if( sockets.containsKey(addr) ) {
            socket = sockets.get(addr);
            if( !socket.isConnected() ||
                    socket.isInputShutdown() ||
                    socket.isOutputShutdown() ||
                    (socket.getPort() != portNo) )
            {
                // if socket is not suitable,  then create a new socket
                sockets.remove(addr);
                try {
                    socket.shutdownInput();
                    socket.shutdownOutput();
                    socket.close();
                    socket = new Socket(addr, portNo);
                    sockets.put(addr, socket);
                } catch (IOException ioe) {
                    System.out.println(ioe);
                }
            }
        }
        else
        {
            try {
                socket = new Socket(addr, portNo);
                sockets.put(addr, socket);                
            } catch (IOException ioe) {
                System.out.println(ioe);
            }
        }
        
        return socket;
    }
    
    private class ReceiveConnection extends Thread {
        Socket clentSocket = null;
        
        public ReceiveConnection( Socket socket ) {
            this.clentSocket = socket;
            SocketOperator.this.sockets.put(socket.getInetAddress(), socket);
        }
        
        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader( clentSocket.getInputStream() ) );
                String inputLine;
                
                while ( (inputLine = in.readLine()) != null ) {
                    if ( !inputLine.equals("exit") ) {
                        appManager.messageReceived(inputLine);
                    }
                    else
                    {
                        this.clentSocket.shutdownInput();
                        this.clentSocket.shutdownOutput();
                        this.clentSocket.close();
                        SocketOperator.this.sockets.remove( clentSocket.getInetAddress() );
                    }
                }
            } catch (IOException ioe) {
                System.out.println(ioe.toString());
            }
        }
    }
}
