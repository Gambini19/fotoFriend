package company.nas.fotofriend.mvp.friendList.view;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import company.nas.fotofriend.Constant;
import company.nas.fotofriend.R;
import company.nas.fotofriend.adapter.RVAdapter;
import company.nas.fotofriend.mvp.base.Basefragment;
import company.nas.fotofriend.mvp.friendList.presenter.FriendListPresenter;
import io.realm.RealmObject;

/**
 * Created by admin on 07.12.2017.
 */

public class FriendFragment extends Basefragment implements IFriendListView, View.OnClickListener {
    RecyclerView rvFriends;
    ProgressBar pbLoad;
    FriendListPresenter presenter;
    RVAdapter adapter;

    public static FriendFragment Instance(String token, int type) {
        FriendFragment friendFragment = new FriendFragment();
        Bundle args = new Bundle();
        args.putString("token", token);
        args.putInt("type", type);
        friendFragment.setArguments(args);
        return friendFragment;
    }
    public static FriendFragment Instance(String token, int type, int userID) {
        FriendFragment friendFragment = new FriendFragment();
        Bundle args = new Bundle();
        args.putString("token", token);
        args.putInt("type", type);
        args.putInt("id", userID);
        friendFragment.setArguments(args);
        return friendFragment;
    }
    public static FriendFragment Instance(String token, int type, int userID, int albumID) {
        FriendFragment friendFragment = new FriendFragment();
        Bundle args = new Bundle();
        args.putString("token", token);
        args.putInt("type", type);
        args.putInt("id", userID);
        args.putInt("albumID", albumID);
        friendFragment.setArguments(args);
        return friendFragment;
    }

    public String getToken() {
        return getArguments().getString("token", null);
    }
    public int getType() {
        return getArguments().getInt("type", 0 );
    }
    public int getUserId() {
        return getArguments().getInt("id", 0 );
    }
    public int getAlbumID() {
        return getArguments().getInt("albumID", 0 );
    }

    @Override
    protected void onCreateView(View view) {

        initView(view);
        presenter = new FriendListPresenter(this);
        if (getToken() != null && getType()== Constant.LIST || getType()== Constant.GALLERY) {
            presenter.load(getToken(), getType(), getUserId());
        }
        else if(getToken() != null && getType()== Constant.PREVIEW)
        {
            presenter.load(getToken(), getType(), getUserId(), getAlbumID());
        }

        //rvFriends.setAdapter(adapter);


    }

    private void initView(View view) {
        pbLoad = view.findViewById(R.id.ps_load);
        rvFriends = view.findViewById(R.id.rv_friends);
        rvFriends.setLayoutManager(new LinearLayoutManager(getActivity()));

    }


    @Override
    protected int getResourceID() {
        return R.layout.friend_list_fragment;
    }

    @Override
    public void getUserData() {

    }

    @Override
    public void showFriends(List<? extends RealmObject> personList) {
        //if (adapter == null) {
            Log.d("TAG", "type " + getType());
        adapter = new RVAdapter(personList, getActivity(), getToken(), getType());

        switch (getType())
            {
                case Constant.LIST:
                    LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                    rvFriends.setLayoutManager(manager);
                    break;
                case Constant.GALLERY:
                case Constant.PREVIEW:
                    GridLayoutManager gridManager = new GridLayoutManager(getActivity(),2);
                    rvFriends.setLayoutManager(gridManager);
                    adapter.setWidth(getWidthScreen());

                    break;


            }
            rvFriends.setAdapter(adapter);




    }

        private int getWidthScreen() {
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        return width;
    }
    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showProgress() {
        pbLoad.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLoad.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View view) {

    }
}
