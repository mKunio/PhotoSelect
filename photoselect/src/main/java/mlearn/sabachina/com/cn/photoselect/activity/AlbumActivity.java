package mlearn.sabachina.com.cn.photoselect.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mlearn.sabachina.com.cn.photoselect.R;
import mlearn.sabachina.com.cn.photoselect.adapter.AlbumPhotoAdapter;
import mlearn.sabachina.com.cn.photoselect.adapter.FolderListAdapter;
import mlearn.sabachina.com.cn.photoselect.bean.Photo;
import mlearn.sabachina.com.cn.photoselect.callback.PhotoSuccessCallback;
import mlearn.sabachina.com.cn.photoselect.imageloader.BaseImageLoader;
import mlearn.sabachina.com.cn.photoselect.request.AlbumOperation;
import mlearn.sabachina.com.cn.photoselect.request.AlbumTarget;
import mlearn.sabachina.com.cn.photoselect.util.AnimUtils;
import mlearn.sabachina.com.cn.photoselect.util.PictureManager;

/**
 * Created by zhc on 2017/10/31 0031.
 */

public class AlbumActivity extends AppCompatActivity implements View.OnClickListener,
        PhotoSuccessCallback, AdapterView.OnItemClickListener {

    private TextView photoFolder;
    private GridView photoGridView;
    private AlbumOperation albumOperation;
    private View gridBg;
    private ListView photoFolderList;
    private BaseImageLoader loader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        getDataFromIntent();
        findViews();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(albumOperation.getAlbumTitleBarColor()));
        }
        getPicture();
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        albumOperation = intent.getParcelableExtra("albumOperation");
        loader = intent.getParcelableExtra("loader");
    }

    private void findViews() {
        View back = findViewById(R.id.back);
        photoFolder = (TextView) findViewById(R.id.photo_folder);
        View confirm = findViewById(R.id.confirm);
        photoGridView = (GridView) findViewById(R.id.photo_grid_view);
        gridBg = findViewById(R.id.grid_bg);
        photoFolderList = (ListView) findViewById(R.id.photo_folder_list);
        View titleBar = findViewById(R.id.title_bar);
        titleBar.setBackgroundColor(getResources().getColor(albumOperation.getAlbumTitleBarColor()));
        back.setOnClickListener(this);
        photoFolder.setOnClickListener(this);
        confirm.setOnClickListener(this);
        gridBg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.back) {
            finish();
        } else if (i == R.id.photo_folder) {
            if (photoFolderList.getVisibility() == View.GONE) {
                AnimUtils.slideDownToShow(photoFolderList, gridBg);
            } else {
                AnimUtils.slideUpToClose(photoFolderList, gridBg);
            }

        } else if (i == R.id.confirm) {
            AlbumPhotoAdapter adapter = (AlbumPhotoAdapter) photoGridView.getAdapter();
            ArrayList<Photo> selectPhoto = adapter.getSelectPhoto();
            if (selectPhoto.size() != 0) {
                Intent intent = new Intent();
                intent.putParcelableArrayListExtra(AlbumTarget.ALBUM_SELECT_PHOTO, selectPhoto);
                setResult(RESULT_OK, intent);
                finish();
            }
        } else if (i == R.id.grid_bg) {
            AnimUtils.slideUpToClose(photoFolderList, gridBg);
        }
    }

    private void getPicture() {
        PictureManager manager = new PictureManager();
        manager.getAllPhoto(this, getApplicationContext(), this);
    }

    /**
     * Query completed ,finished in 50ms,470 photos
     */
    @Override
    public void onResultCallback(Map<String, List<Photo>> dirPhotos) {
        FolderListAdapter adapter = new FolderListAdapter(dirPhotos, this);
        adapter.setLoader(loader);
        photoFolderList.setAdapter(adapter);
        photoFolderList.setOnItemClickListener(this);
        List<Photo> allPhotos = dirPhotos.get("全部图片");
        photoGridView.setNumColumns(albumOperation.getColumn());
        AlbumPhotoAdapter albumPhotoAdapter = new AlbumPhotoAdapter(albumOperation, allPhotos, this);
        albumPhotoAdapter.setLoader(loader);
        photoGridView.setAdapter(albumPhotoAdapter);
        photoGridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent instanceof ListView) {
            FolderListAdapter adapter = (FolderListAdapter) photoFolderList.getAdapter();
            AnimUtils.slideUpToClose(photoFolderList, gridBg);
            if (adapter.getCurrentPosition() != position) {
                //点击条目与上次点击条目不一致
                adapter.setCurrentPosition(position);
                photoFolder.setText(adapter.getItem(position));
                List<Photo> photos = adapter.getPhotoList(position);
                AlbumPhotoAdapter photoAdapter = (AlbumPhotoAdapter) photoGridView.getAdapter();
                photoAdapter.setPhotoList(photos);
                photoAdapter.notifyDataSetChanged();
            }
        }
        if (parent instanceof GridView) {
            AlbumPhotoAdapter adapter = (AlbumPhotoAdapter) photoGridView.getAdapter();
            Photo photo = adapter.getItem(position);
            adapter.itemToggle(photo, view);
        }
    }
}
