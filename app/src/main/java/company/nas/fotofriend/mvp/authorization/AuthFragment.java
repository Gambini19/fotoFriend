package company.nas.fotofriend.mvp.authorization;


import android.app.Fragment;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import company.nas.fotofriend.R;
import company.nas.fotofriend.VkWebViewClient;
import company.nas.fotofriend.mvp.base.Basefragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class AuthFragment extends Basefragment {
    WebView wvAuth;

    @Override
    protected void onCreateView(View view) {
        wvAuth = view.findViewById(R.id.wv_auth);
        initWV();
    }

    @Override
    protected int getResourceID() {
        return R.layout.fragment_auth;
    }



    private void initWV() {
        WebSettings webSettings = wvAuth.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wvAuth.setHorizontalScrollBarEnabled(false);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wvAuth.setWebViewClient(new VkWebViewClient());
        wvAuth.loadUrl("https://oauth.vk.com/authorize?client_id=6287930&scope=friends,notify,photos,photos,audio,video,docs,notes,pages,groups,offline&redirect_uri=https://oauth.vk.com/blank.html&display=mobile&v=5.5&response_type=token&revoke=1");

    }

}
