package mlearn.sabachina.com.cn.mygithub.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import mlearn.sabachina.com.cn.mygithub.R;
import mlearn.sabachina.com.cn.photoselect.bean.Photo;
import mlearn.sabachina.com.cn.photoselect.request.AlbumOperation;
import mlearn.sabachina.com.cn.photoselect.request.AlbumTarget;
import mlearn.sabachina.com.cn.photoselect.request.CheckMarkStyle;
import mlearn.sabachina.com.cn.photoselect.request.IconLocation;
import mlearn.sabachina.com.cn.photoselect.request.PhotoPicker;
import mlearn.sabachina.com.cn.photoselect.util.FileUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.add(R.id.content,new MainFragment());
//        transaction.commit();
        View takePhoto = findViewById(R.id.take_photo);
        View album = findViewById(R.id.album);
        takePhoto.setOnClickListener(this);
        album.setOnClickListener(this);
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
                        .digitInSideColor(R.color.colorAlbum)
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //相机返回resultCode 为0
        if (resultCode == RESULT_OK) {
            if (requestCode == 3) {
                //相机
                Log.d("Photo", "onActivityResult: " + uri.getPath());
            }
            if (requestCode == 4) {
                ArrayList<Photo> photos = data.getParcelableArrayListExtra(AlbumTarget.ALBUM_SELECT_PHOTO);
                for (Photo photo : photos) {
                    Log.d("Photo", "onActivityResult: " + photo.getFilePath());
                }
            }
        }
    }
}
