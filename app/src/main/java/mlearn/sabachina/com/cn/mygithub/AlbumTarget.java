package mlearn.sabachina.com.cn.mygithub;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * Created by zhc on 2017/12/25 0025.
 */

interface AlbumTarget {
    AlbumTarget albumOperation(AlbumOperation albumOperation);

    AlbumTarget requestCode(int requestCode);

    void start(Activity activity);

    void start(Fragment fragment);
}
