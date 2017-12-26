package mlearn.sabachina.com.cn.mygithub.util;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import java.util.List;
import java.util.Map;

import mlearn.sabachina.com.cn.mygithub.bean.Photo;
import mlearn.sabachina.com.cn.mygithub.callback.PhotoLoaderCallbacks;
import mlearn.sabachina.com.cn.mygithub.callback.PhotoSuccessCallback;

/**
 * Created by zhc on 2017/11/1 0001.
 */

public class PhotoStore {
    public static void getAllPhoto(FragmentActivity activity, Context context, PhotoSuccessCallback<Map<String,Photo>> resultCallback) {
        if (activity.getSupportLoaderManager().getLoader(1) != null) {
            activity.getSupportLoaderManager().restartLoader(1, null, new PhotoLoaderCallbacks(context, resultCallback));
        } else {
            activity.getSupportLoaderManager().initLoader(1, null, new PhotoLoaderCallbacks(context, resultCallback));
        }
    }
}
