package company.nas.fotofriend.mvp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.stetho.Stetho;

import company.nas.fotofriend.R;
import company.nas.fotofriend.mvp.authorization.AuthFragment;

public class MainActivity extends AppCompatActivity implements IChangeFragment {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);

        onReplaceFragment(new AuthFragment(), "auth");




    }

    @Override
    public void onReplaceFragment(Fragment fr, String tag) {
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
                .add(R.id.frame_for_fragment, fr, tag)
                .addToBackStack(tag)
                .commit();
    }
}
