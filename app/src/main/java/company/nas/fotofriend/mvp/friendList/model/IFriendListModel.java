package company.nas.fotofriend.mvp.friendList.model;


import company.nas.fotofriend.mvp.friendList.presenter.ILoadUsers;

/**
 * Created by admin on 13.12.2017.
 */

interface IFriendListModel {
    void loadFriends(String token, ILoadUsers listener);
    void loadFriendGallery(String token,int userID, ILoadUsers listener);
    void loadAlbum(int albumID, String token,int userID, ILoadUsers listener);
}
