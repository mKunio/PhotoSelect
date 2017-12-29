package mlearn.sabachina.com.cn.photoselect.request;

import android.app.Activity;
import android.net.Uri;
import android.support.v4.app.Fragment;

/**
 * Created by zhc on 2017/12/25 0025.
 */

public interface CameraTarget {
    CameraTarget uri(Uri uri);

    CameraTarget requestCode(int requestCode);

    void start(Activity activity);

    void start(Fragment fragment);
}
