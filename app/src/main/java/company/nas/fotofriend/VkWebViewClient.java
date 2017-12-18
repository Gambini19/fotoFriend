package company.nas.fotofriend;


import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import company.nas.fotofriend.mvp.friendList.view.FriendFragment;
import company.nas.fotofriend.mvp.IChangeFragment;

public class VkWebViewClient extends WebViewClient {
    public VkWebViewClient() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.d("TAG", "shouldOverrideUrlLoading: " + url);
        if (url.contains("oauth.vk.com/blank.html#")) {
            if (url.contains("error")) {
                view.loadUrl("https://oauth.vk.com/authorize?client_id=6287930&scope=friends,notify,photos,photos,audio,video,docs,notes,pages,groups,offline&redirect_uri=https://oauth.vk.com/blank.html&display=mobile&v=5.5&response_type=token&revoke=1");
                return true;
            } else {
                view.loadUrl("https://oauth.vk.com/authorize?client_id=6287930&scope=friends,notify,photos,photos,audio,video,docs,notes,pages,groups,offline&redirect_uri=https://oauth.vk.com/blank.html&display=mobile&v=5.5&response_type=token&revoke=1");
                return true;
            }
        }
        return false;
    }


    @Override
    public void onPageFinished(final WebView view, String url)
    {
        if (url.contains("oauth.vk.com/blank.html#")) {
            if (url.contains("error")) {
                // Error
                //view.loadUrl("https://oauth.vk.com/authorize?client_id=6287930&scope=friends,notify,photos,photos,audio,video,docs,notes,pages,groups,offline&redirect_uri=https://oauth.vk.com/blank.html&display=mobile&v=5.5&response_type=token&revoke=1");
            }
            else
            {

                String ahrore = url.substring(url.indexOf("#")+1);
                String token = ahrore.split("&")[0].split("=")[1];

                ((IChangeFragment)view.getContext()).onReplaceFragment(FriendFragment.Instance(token, Constant.LIST), Constant.FRIEND_LIST_TAG);


            }
        }
    }


}
