package mlearn.sabachina.com.cn.photoselect.request;

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
