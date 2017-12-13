package company.nas.fotofriend.mvp.friendList.presenter;

/**
 * Created by admin on 13.12.2017.
 */

interface IFriendListPresenter {
    void load(String token, int type, int id);
    void load(String token, int type, int id, int albumID);
}
