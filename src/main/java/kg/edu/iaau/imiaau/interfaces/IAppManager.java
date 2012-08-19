/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kg.edu.iaau.imiaau.interfaces;

/**
 *
 * @author Kantoro
 */
public interface IAppManager {
    
    String getUsername();
    boolean sendMessage(String username, String message);
    
    String authenticateUser(String usernameText, String passwordText); 
    void messageReceived(String message);
    boolean isNetworkConnected();
    boolean isUserAuthenticated();
    String getLastRawFriendList();
    void exit();
    String signUpUser(String usernameText, String passwordText, String email);
    String addNewFriendRequest(String friendUsername);
    String sendFriendsReqsResponse(String approvedFriendNames,String discardedFriendNames);

}
