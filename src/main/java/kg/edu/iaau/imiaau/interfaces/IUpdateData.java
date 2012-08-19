/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kg.edu.iaau.imiaau.interfaces;

import kg.edu.iaau.imiaau.types.FriendInfo;

/**
 *
 * @author Kantoro
 */
public interface IUpdateData {
    
    void updateData(FriendInfo[] friends, FriendInfo[] unApprovedFriends, String userKey);
    
}
