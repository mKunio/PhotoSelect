package mlearn.sabachina.com.cn.photoselect.callback;

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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import mlearn.sabachina.com.cn.photoselect.bean.Photo;

public class PhotoLoaderCallbacks implements LoaderManager.LoaderCallbacks<Cursor> {
    private Context context;
    private PhotoSuccessCallback resultCallback;
    private final String[] IMAGE_PROJECTION = {
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.TITLE
    };

    public PhotoLoaderCallbacks(Context context, PhotoSuccessCallback resultCallback) {
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
        Map<String, List<Photo>> dirPhoto = new LinkedHashMap<>();
        dirPhoto.put("全部图片", new ArrayList<Photo>());
        while (data.moveToNext()) {
//            int imageId = data.getInt(data.getColumnIndexOrThrow(MediaStore.Images.Media._ID));
            //图片所属文件夹名称
            String folderName = data.getString(data.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME));

            String filePath = data.getString(data.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA));
            String fileName = data.getString(data.getColumnIndexOrThrow( MediaStore.Images.Media.TITLE));
            //过滤GIF图
            if (!filePath.toLowerCase().endsWith("gif")) {
                Photo photo = new Photo(fileName, filePath);
                List<Photo> allPhoto = dirPhoto.get("全部图片");
                if (!allPhoto.contains(photo)) {
                    allPhoto.add(photo);
                }
                if (dirPhoto.containsKey(folderName)) {
                    List<Photo> photos = dirPhoto.get(folderName);
                    if (!photos.contains(photo)) {
                        photos.add(photo);
                    }
                } else {
                    List<Photo> newPhotoList = new ArrayList<>();
                    newPhotoList.add(photo);
                    dirPhoto.put(folderName, newPhotoList);
                }
            }
        }

        if (resultCallback != null) {
            resultCallback.onResultCallback(dirPhoto);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
