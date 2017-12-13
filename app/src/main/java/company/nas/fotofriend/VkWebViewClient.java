package company.nas.fotofriend;


import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import company.nas.fotofriend.mvp.friendList.view.FriendFragment;
import company.nas.fotofriend.mvp.IChangeFragment;

/**
 * Created by admin on 06.12.2017.
 */

public class VkWebViewClient extends WebViewClient {




    public VkWebViewClient() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onPageFinished(final WebView view, String url)
    {
        Log.i("onPageFinished",url);
        if (url.contains("oauth.vk.com/blank.html#")) {
            if (url.contains("error")) {
                // Error
            }
            else
            {

                String ahrore = url.substring(url.indexOf("#")+1);
                String token = ahrore.split("&")[0].split("=")[1];
                String userID = ahrore.split("user_id")[1].split("=")[1];
                Log.i("Ahrore",  ahrore);
                Log.i("token",  token);
                Log.i("user",  userID);

                saveToken(token);

                ((IChangeFragment)view.getContext()).onReplaceFragment(FriendFragment.Instance(token, Constant.LIST), Constant.FRIEND_LIST_TAG);


               // Fragment fr = new AuthFragment();
                //((IChangeFragment)view.getContext()).onReplaceFragment(fr, "1");


            }
        }
    }

    private void saveToken(String token)
    {
      /*  SharedPreferences sPref = getSharedPreferences("token", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString("token", token);
        editor.apply();*/
    }

}
