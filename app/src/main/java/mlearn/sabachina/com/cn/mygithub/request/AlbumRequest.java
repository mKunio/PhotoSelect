package mlearn.sabachina.com.cn.mygithub.request;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import mlearn.sabachina.com.cn.mygithub.activity.AlbumActivity;

/**
 * Created by zhc on 2017/12/25 0025.
 */

public class AlbumRequest implements AlbumTarget {
    private int requestCode = -1;
    private AlbumOperation albumOperation;

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

    private Intent getDefaultIntent(Context context) {
        Intent intent = new Intent(context, AlbumActivity.class);
        intent.putExtra("albumOperation",albumOperation);
        return intent;
    }
}
