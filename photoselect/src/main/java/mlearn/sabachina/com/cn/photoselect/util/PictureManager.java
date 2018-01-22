package mlearn.sabachina.com.cn.photoselect.util;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import mlearn.sabachina.com.cn.photoselect.callback.PhotoLoaderCallbacks;
import mlearn.sabachina.com.cn.photoselect.callback.PhotoSuccessCallback;

/**
 * Created by zhc on 2017/11/1 0001.
 */

public class PictureManager {
    public void getAllPhoto(FragmentActivity activity, Context context, PhotoSuccessCallback resultCallback) {
        if (activity.getSupportLoaderManager().getLoader(1) != null) {
            activity.getSupportLoaderManager().restartLoader(1, null, new PhotoLoaderCallbacks(context, resultCallback));
        } else {
            activity.getSupportLoaderManager().initLoader(1, null, new PhotoLoaderCallbacks(context, resultCallback));
        }
    }
}
