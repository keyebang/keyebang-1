package com.shg.keyebang.view.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {
    private static final String TAG = "KYB";

    abstract protected void init();

    public void toastAndLog(String message){
        Log.d(TAG, message);
        if(getActivity() == null) return;
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

    }

    protected void startActivityDirectly(Class<?> cls){
        Intent intent = new Intent(getActivity(), cls);
        startActivity(intent);
    }

    public abstract void showErrorMessage(String errMsg);
}
