package company.nas.fotofriend.mvp.friendList.presenter;

import java.util.List;

import company.nas.fotofriend.entity.Person;
import io.realm.RealmObject;

/**
 * Created by admin on 13.12.2017.
 */

public interface ILoadUsers {
    void onLoadSuccess(List<? extends RealmObject> personList);
    void onLoadFailed(String message);
}
