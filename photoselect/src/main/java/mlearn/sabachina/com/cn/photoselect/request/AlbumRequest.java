package mlearn.sabachina.com.cn.photoselect.request;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import mlearn.sabachina.com.cn.photoselect.activity.AlbumActivity;
import mlearn.sabachina.com.cn.photoselect.imageloader.BaseImageLoader;

/**
 * Created by zhc on 2017/12/25 0025.
 */

public class AlbumRequest implements AlbumTarget {
    private int requestCode = -1;
    private AlbumOperation albumOperation;
    private BaseImageLoader loader;

    @Override
    public AlbumTarget albumOperation(AlbumOperation albumOperation) {
        this.albumOperation = albumOperation;
        return this;
    }

    @Override
    public AlbumTarget requestCode(int requestCode) {
        this.requestCode = requestCode;
        return this;
    }

    @Override
    public void start(Activity activity) {
        activity.startActivityForResult(getDefaultIntent(activity), requestCode == -1
                ? PhotoPicker.REQUEST_CODE_ALBUM : requestCode);
    }


    @Override
    public void start(Fragment fragment) {
        fragment.startActivityForResult(getDefaultIntent(fragment.getContext()), requestCode == -1
                ? PhotoPicker.REQUEST_CODE_ALBUM : requestCode);
    }

    @Override
    public AlbumTarget imageLoader(@NonNull BaseImageLoader loader) {
        this.loader = loader;
        return this;
    }

    private Intent getDefaultIntent(Context context) {
        Intent intent = new Intent(context, AlbumActivity.class);
        if (albumOperation == null) {
            albumOperation = new AlbumOperation.Builder().build();
        }
        intent.putExtra("albumOperation", albumOperation);
        intent.putExtra("loader", loader);
        return intent;
    }
}
