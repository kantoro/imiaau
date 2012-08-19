/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kg.edu.iaau.services;

import kg.edu.iaau.imiaau.interfaces.IAppManager;
import kg.edu.iaau.imiaau.interfaces.IUpdateData;
import kg.edu.iaau.imiaau.types.FriendInfo;

/**
 *
 * @author Kantoro
 */
public class IMService implements IAppManager, IUpdateData {
    
    
    
    public String getUsername() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean sendMessage(String username, String message) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String authenticateUser(String usernameText, String passwordText) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void messageReceived(String message) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isNetworkConnected() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isUserAuthenticated() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getLastRawFriendList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void exit() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String signUpUser(String usernameText, String passwordText, String email) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String addNewFriendRequest(String friendUsername) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String sendFriendsReqsResponse(String approvedFriendNames, String discardedFriendNames) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateData(FriendInfo[] friends, FriendInfo[] unApprovedFriends, String userKey) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
