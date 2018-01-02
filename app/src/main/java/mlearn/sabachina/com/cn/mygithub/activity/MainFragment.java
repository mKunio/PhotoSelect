package mlearn.sabachina.com.cn.mygithub.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mlearn.sabachina.com.cn.mygithub.R;
import mlearn.sabachina.com.cn.photoselect.request.AlbumOperation;
import mlearn.sabachina.com.cn.photoselect.request.CheckMarkStyle;
import mlearn.sabachina.com.cn.photoselect.request.IconLocation;
import mlearn.sabachina.com.cn.photoselect.request.PhotoPicker;
import mlearn.sabachina.com.cn.photoselect.util.FileUtil;

/**
 * Created by zhc on 2018/1/2 0002.
 */

public class MainFragment extends Fragment implements View.OnClickListener {
    private Uri uri;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main,container,false);
        View takePhoto = view.findViewById(R.id.take_photo);
        View album = view.findViewById(R.id.album);
        takePhoto.setOnClickListener(this);
        album.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.take_photo:
                uri = FileUtil.getDefaultUri();
                PhotoPicker.getCamera()
                        .requestCode(3)
                        .uri(uri)
                        .start(this);
                break;
            case R.id.album:
                AlbumOperation operation = new AlbumOperation.Builder()
                        //图片选中指示器离四周的距离,单位像素
                        .marginSelectedSign(12)
                        //最大选择数
                        .maxNum(4)
                        //展示的列数
                        .column(3)
                        //指示器的风格，可选图片标识和数字标识
                        .style(CheckMarkStyle.DIGIT)
                        //图片选中指示相对位置，左上，左下，右上，右下
                        .location(IconLocation.TOP_RIGHT)
                        //图片标识中选中之后的资源文件图片
                        .selectResId(R.drawable.checkbox)
                        //图片标识中未选中的资源文件图片
                        .unSelectResId(R.drawable.checkbox_un)
                        //数字标识中内圈圆颜色
                        .digitInSideColor(R.color.colorPrimary)
                        //相册页面顶布局背景颜色(带状态栏)
                        .albumTitleBarColor(R.color.colorAlbum)
                        .build();
                PhotoPicker.getAlbum()
                        .requestCode(4)
                        //相册页面的属性设置
                        .albumOperation(operation)
                        //设置一下自己的加载图片方式
                        .imageLoader(new ImageLoader())
                        .start(this);
                break;
        }
    }
}
