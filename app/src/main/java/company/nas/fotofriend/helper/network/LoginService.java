package company.nas.fotofriend.helper.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by admin on 08.12.2017.
 */

public  interface LoginService {

    @GET("photos.getAlbums")
    Call<ResponseBody> getUserAlbums(@Query("owner_id") String ownerId,
                                     @Query("need_covers") String needCovers,
                                     @Query("access_token") String accessToken,
                                     @Query("photo_sizes") String needPhotoSizes,
                                     @Query("need_system") String needSystem);

    @GET("friends.get")
    Call<ResponseBody> getUserList   (@Query("fields") String nickname,
                                      @Query("access_token") String accessToken);

    @GET("photos.get")
    Call<ResponseBody> getAlbumsPhoto(@Query("album_id") String albumId,
                                                  @Query("owner_id") String ownerId,
                                                  @Query("need_covers") String needCovers,
                                                  @Query("access_token") String accessToken,
                                                  @Query("photo_sizes") String needPhotoSizes);
}
