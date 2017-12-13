package company.nas.fotofriend.mvp.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by admin on 08.12.2017.
 */

public abstract class Basefragment extends Fragment {


    protected abstract void onCreateView(View view);
    protected abstract int getResourceID();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getResourceID(), container, false);
        onCreateView(view);
        return view;
    }


}
