package company.nas.fotofriend.mvp.friendList.presenter;

import java.util.List;

import company.nas.fotofriend.Constant;
import company.nas.fotofriend.entity.Person;
import company.nas.fotofriend.mvp.friendList.model.FriendListInteractor;
import company.nas.fotofriend.mvp.friendList.view.IFriendListView;
import io.realm.RealmObject;


public class FriendListPresenter implements IFriendListPresenter, ILoadUsers{
    private IFriendListView view;
    private FriendListInteractor interactor;

    public FriendListPresenter(IFriendListView view) {
        this.view = view;
        this.interactor = new FriendListInteractor();
    }

    @Override
    public void load(String token, int type, int userID) {
        switch (type){
            case Constant.LIST:
                interactor.loadFriends(token, this);
                break;
            case Constant.GALLERY:
                if (userID != 0)
                interactor.loadFriendGallery(token,userID, this);
                else view.showToast("Нет данных по пользователю с ID= " + userID);
                break;

        }


        view.showProgress();

    }

    @Override
    public void load(String token, int type, int userID, int albumID) {
        if (userID != 0) {
            interactor.loadAlbum(albumID, token, userID, this);
            view.showProgress();
        }
    }

    @Override
    public void onLoadSuccess(List<? extends RealmObject> personList) {
        view.hideProgress();
        view.showFriends(personList);

    }

    @Override
    public void onLoadFailed(String message) {
        view.hideProgress();
        view.showToast(message);
    }


}
