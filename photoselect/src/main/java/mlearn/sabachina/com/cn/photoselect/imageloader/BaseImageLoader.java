package mlearn.sabachina.com.cn.photoselect.imageloader;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

/**
 * Created by zhc on 2018/1/2 0002.
 */

public abstract class BaseImageLoader implements Parcelable {
    public abstract void load(String filePath, ImageView imageView, Context context);

    public BaseImageLoader() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    protected BaseImageLoader(Parcel in) {
    }

}
