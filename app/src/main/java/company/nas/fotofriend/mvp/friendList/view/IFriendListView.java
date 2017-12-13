package company.nas.fotofriend.mvp.friendList.view;

import java.util.List;

import io.realm.RealmObject;

/**
 * Created by admin on 13.12.2017.
 */

public interface IFriendListView {
    void getUserData();
    void showFriends(List<? extends RealmObject> personList);
    void showToast(String message);
    void showProgress();
    void hideProgress();

}
