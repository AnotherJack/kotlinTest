package io.github.anotherjack.kotlintest;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;

import io.reactivex.Observable;

/**
 * Created by jack on 2017/12/27.
 */

public class AvoidOnResult {
    private static final String TAG = "AvoidOnResult";
    private AvoidOnResultFragment mAvoidOnResultFragment;

    public AvoidOnResult(Activity activity) {
        mAvoidOnResultFragment = getAvoidOnResultFragment(activity);
    }


    private AvoidOnResultFragment getAvoidOnResultFragment(Activity activity) {
        AvoidOnResultFragment avoidOnResultFragment = findAvoidOnResultFragment(activity);
        boolean isNewInstance = avoidOnResultFragment == null;
        if (isNewInstance) {
            avoidOnResultFragment = new AvoidOnResultFragment();
            FragmentManager fragmentManager = activity.getFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .add(avoidOnResultFragment, TAG)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        return avoidOnResultFragment;
    }

    private AvoidOnResultFragment findAvoidOnResultFragment(Activity activity){
        return (AvoidOnResultFragment) activity.getFragmentManager().findFragmentByTag(TAG);
    }

    public Observable<ActivityResultInfo> startForResult(Intent intent, int requestCode){
        return mAvoidOnResultFragment.startForResult(intent, requestCode);
    }

    public void startForResult(Intent intent,int requestCode,Callback callback){
        mAvoidOnResultFragment.startForResult(intent, requestCode, callback);
    }

    public interface Callback{
        void onActivityResult(int requestCode, int resultCode, Intent data);
    }
}
