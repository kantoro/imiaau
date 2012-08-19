/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kg.edu.iaau.imiaau.interfaces;

/**
 *
 * @author Kantoro
 */
public interface ISocketOperator {
    
    String sendHttpRequest(String params);
    boolean sendMessage(String message, String ip, int port);
    int startListening(int port);
    void stopListening();
    void exit();
    int getListeningPort();
}
