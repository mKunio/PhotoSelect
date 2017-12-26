package mlearn.sabachina.com.cn.mygithub.request;

import mlearn.sabachina.com.cn.mygithub.request.AlbumRequest;
import mlearn.sabachina.com.cn.mygithub.request.AlbumTarget;
import mlearn.sabachina.com.cn.mygithub.request.CameraRequest;
import mlearn.sabachina.com.cn.mygithub.request.CameraTarget;

/**
 * Created by zhc on 2017/12/25 0025.
 */

public class PhotoPicker {
    public static final int REQUEST_CODE_CAMERA = 77;
    public static final int REQUEST_CODE_ALBUM = 78;
    public static CameraTarget getCamera() {
        return new CameraRequest();
    }

    public static AlbumTarget getAlbum() {
        return new AlbumRequest();
    }
}
