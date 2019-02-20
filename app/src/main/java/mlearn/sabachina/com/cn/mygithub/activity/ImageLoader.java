package mlearn.sabachina.com.cn.mygithub.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;

import mlearn.sabachina.com.cn.mygithub.R;
import mlearn.sabachina.com.cn.photoselect.imageloader.BaseImageLoader;

import static com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade;


/**
 * Created by zhc on 2018/1/2 0002.
 */

public class ImageLoader extends BaseImageLoader {

    @Override
    public void load(String filePath, ImageView imageView, Context context) {
        Glide.with(context).asBitmap().load(filePath).transition(withCrossFade()).placeholder(R.drawable.image_holder).into(imageView);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    public ImageLoader() {
    }

    protected ImageLoader(Parcel in) {
        super(in);
    }

    public static final Creator<ImageLoader> CREATOR = new Creator<ImageLoader>() {
        @Override
        public ImageLoader createFromParcel(Parcel source) {
            return new ImageLoader(source);
        }

        @Override
        public ImageLoader[] newArray(int size) {
            return new ImageLoader[size];
        }
    };
}
