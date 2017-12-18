package company.nas.fotofriend.mvp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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


    @Override
    public void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount() > 1) super.onBackPressed();
        else showMyDialog();
    }

    private void showMyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.exit);

        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
