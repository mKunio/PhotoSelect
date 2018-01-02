package mlearn.sabachina.com.cn.photoselect.request;

import android.app.Activity;
import android.support.v4.app.Fragment;

import mlearn.sabachina.com.cn.photoselect.imageloader.BaseImageLoader;

/**
 * Created by zhc on 2017/12/25 0025.
 */

public interface AlbumTarget {
    String ALBUM_SELECT_PHOTO = "album_select_photo";
    AlbumTarget albumOperation(AlbumOperation albumOperation);

    AlbumTarget requestCode(int requestCode);

    void start(Activity activity);

    void start(Fragment fragment);

    AlbumTarget imageLoader(BaseImageLoader loader);
}
