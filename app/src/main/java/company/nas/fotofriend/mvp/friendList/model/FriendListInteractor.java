package company.nas.fotofriend.mvp.friendList.model;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import company.nas.fotofriend.entity.Album;
import company.nas.fotofriend.entity.Person;
import company.nas.fotofriend.entity.Photo;
import company.nas.fotofriend.helper.network.RestManager;
import company.nas.fotofriend.mvp.friendList.presenter.ILoadUsers;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 13.12.2017.
 */

public class FriendListInteractor implements IFriendListModel {

    @Override
    public void loadFriends(String token, final ILoadUsers listener) {

        RestManager.getService().getUserList("city,domain,photo_50", token).enqueue(new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            String text = "";
            Realm realm = Realm.getDefaultInstance();
            try {
                text = response.body().string();
                Log.d("TAG" , "loadFriendGallery err 1 " + text);
                JSONArray array = new JSONObject(text).getJSONArray("response");
                realm.beginTransaction();
                realm.createOrUpdateAllFromJson(Person.class, array);
                realm.commitTransaction();

                if (getPersonList() != null )
                listener.onLoadSuccess(getPersonList());


            } catch (JSONException | IOException e) {
                listener.onLoadFailed(e.getMessage());
            } finally {
                realm.close();
            }

        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            Log.d("TAG", "onResponse: " + t.getMessage());
            listener.onLoadFailed(t.getMessage());

        }
    });
}

    @Override
    public void loadFriendGallery(final String token, final int userID, final ILoadUsers listener) {
        RestManager.getService().getUserAlbums(userID+"","1", token,"0", "0").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String text = "";
                Realm realm = Realm.getDefaultInstance();
                try {
                    text = response.body().string();
                    Log.d("TAG" , "loadFriendGallery err 2 " + text);
                    JSONArray array = new JSONObject(text).getJSONArray("response");
                    realm.beginTransaction();
                    realm.createOrUpdateAllFromJson(Album.class, array);
                    realm.commitTransaction();

                    listener.onLoadSuccess(getPersonGallery(userID));

                } catch (JSONException | IOException e) {
                    Log.d("TAG" , "loadFriendGallery err " + e.getMessage());
                    listener.onLoadFailed(e.getMessage());
                } finally {
                    realm.close();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("TAG" , "loadFriendGallery err " + t.getMessage());
                listener.onLoadFailed(t.getMessage());

            }
        });
    }

    @Override
    public void loadAlbum(final int albumID, String token, final int userID, final ILoadUsers listener) {
        RestManager.getService().getAlbumsPhoto(albumID+"",userID+"","1", token,"0").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String text = "";
                Realm realm = Realm.getDefaultInstance();
                try {
                    text = response.body().string();
                    Log.d("TAG" , "loadFriendGallery err 3 " + text);
                    JSONObject object = new JSONObject(text);
                    if (object.has("error"))
                    {

                        listener.onLoadFailed(object.getJSONObject("error").getString("error_msg"));
                    }
                    else if (object.has("response")) {
                        JSONArray array = new JSONObject(text).getJSONArray("response");
                        realm.beginTransaction();
                        realm.createOrUpdateAllFromJson(Photo.class, array);
                        realm.commitTransaction();
                    }

                    if (getAlbumFoto(userID, albumID) != null)
                    {
                        listener.onLoadSuccess(getAlbumFoto(userID, albumID));
                    }

                } catch (JSONException | IOException e) {
                    listener.onLoadFailed(e.getMessage());
                } finally {
                    realm.close();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("TAG" , "loadFriendGallery err " + t.getMessage());
                listener.onLoadFailed(t.getMessage());
            }
        });
    }

    private List<Photo> getAlbumFoto(int userID, int albumID) {
        List<Photo> fotoList = null;
        Realm realm = Realm.getDefaultInstance();
        try {
            RealmResults<Photo> realmList = realm
                    .where(Photo.class)
                    .equalTo("owner_id", userID)
                    .equalTo("aid", albumID)
                    .findAll();
            fotoList = realm.copyFromRealm(realmList);
            return fotoList;
        }catch (RealmException ex)
        {
            Log.d("TAG", ex.getMessage());

        }finally {
            realm.close();
        }
        return null;

    }

    private List<Album> getPersonGallery(int userID) {
        List<Album> galleryList = null;
        Realm realm = Realm.getDefaultInstance();
        try {
            RealmResults<Album> realmList = realm.where(Album.class).equalTo("owner_id", userID).findAll();
            galleryList = realm.copyFromRealm(realmList);
            return galleryList;
        }catch (RealmException ex)
        {
            Log.d("TAG", ex.getMessage());

        }finally {
            realm.close();
        }

        return null;
    }

    private List<Person> getPersonList() {
        List<Person> personList = null;
        Realm realm = Realm.getDefaultInstance();
        try {
            RealmResults<Person> realmList = realm.where(Person.class).findAll();
            personList = realm.copyFromRealm(realmList);
            return personList;
        }catch (RealmException ex)
        {
            Log.d("TAG", ex.getMessage());

        }finally {
            realm.close();
        }

        return personList;
    }
}
