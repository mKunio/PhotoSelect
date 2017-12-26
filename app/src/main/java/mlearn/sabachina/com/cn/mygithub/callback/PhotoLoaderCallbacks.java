package mlearn.sabachina.com.cn.mygithub.callback;

/**
 * Created by zhc on 2017/11/1 0001.
 */

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mlearn.sabachina.com.cn.mygithub.bean.Photo;

import static android.provider.BaseColumns._ID;
import static android.provider.MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME;
import static android.provider.MediaStore.MediaColumns.DATA;
import static android.provider.MediaStore.MediaColumns.TITLE;


public class PhotoLoaderCallbacks implements LoaderManager.LoaderCallbacks<Cursor> {
    private Context context;
    private PhotoSuccessCallback<Map<String,Photo>> resultCallback;
    private final String[] IMAGE_PROJECTION = {
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.TITLE
    };

    public PhotoLoaderCallbacks(Context context, PhotoSuccessCallback<Map<String,Photo>> resultCallback) {
        this.context = context;
        this.resultCallback = resultCallback;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                IMAGE_PROJECTION, null, null, MediaStore.Images.Media._ID + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        if (data == null) return;
        List<Photo> photos = new ArrayList<>();
        List<Map<String,Photo>> folder = new ArrayList<>();

        while (data.moveToNext()) {

            int imageId = data.getInt(data.getColumnIndexOrThrow(_ID));
            //图片所属文件夹名称
            String name = data.getString(data.getColumnIndexOrThrow(BUCKET_DISPLAY_NAME));

            String path = data.getString(data.getColumnIndexOrThrow(DATA));
            String fileName = data.getString(data.getColumnIndexOrThrow(TITLE));

            Photo photo = new Photo(imageId, fileName, path);

            if (!photos.contains(photo)) {
                //只需要不是GIF图
                File file = new File(path);
                if (file.exists()) {
                    if (!path.toLowerCase().endsWith("gif")) {
                        photos.add(photo);
                    }
                }
            }
        }

        if (resultCallback != null) {
            resultCallback.onResultCallback(folder);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
