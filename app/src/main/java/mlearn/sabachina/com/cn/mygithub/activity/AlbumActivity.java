package mlearn.sabachina.com.cn.mygithub.activity;

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

import java.util.List;
import java.util.Map;

import mlearn.sabachina.com.cn.mygithub.R;
import mlearn.sabachina.com.cn.mygithub.adapter.AlbumPhotoAdapter;
import mlearn.sabachina.com.cn.mygithub.adapter.FolderListAdapter;
import mlearn.sabachina.com.cn.mygithub.bean.Photo;
import mlearn.sabachina.com.cn.mygithub.callback.PhotoSuccessCallback;
import mlearn.sabachina.com.cn.mygithub.request.AlbumOperation;
import mlearn.sabachina.com.cn.mygithub.util.AnimUtils;
import mlearn.sabachina.com.cn.mygithub.util.PhotoStore;

/**
 * Created by zhc on 2017/10/31 0031.
 */

public class AlbumActivity extends AppCompatActivity implements View.OnClickListener,
        PhotoSuccessCallback<Photo>, AdapterView.OnItemClickListener {

    private TextView photoFolder;
    private GridView photoGridView;
    private AlbumOperation albumOperation;
    private View gridBg;
    private ListView photoFolderList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorAlbum));
        }
        findViews();
        getDataFromIntent();
        getPicture();
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        albumOperation = intent.getParcelableExtra("albumOperation");
    }

    private void findViews() {
        View back = findViewById(R.id.back);
        photoFolder = (TextView) findViewById(R.id.photo_folder);
        View chooseFolder = findViewById(R.id.choose_folder);
        View confirm = findViewById(R.id.confirm);
        photoGridView = (GridView) findViewById(R.id.photo_grid_view);
        gridBg = findViewById(R.id.grid_bg);
        photoFolderList = (ListView) findViewById(R.id.photo_folder_list);
        back.setOnClickListener(this);
        chooseFolder.setOnClickListener(this);
        confirm.setOnClickListener(this);
        gridBg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.choose_folder:
                if (photoFolderList.getVisibility() == View.GONE) {
                    AnimUtils.slideDownToShow(photoFolderList, gridBg);
                } else {
                    AnimUtils.slideUpToClose(photoFolderList, gridBg);
                }
                break;
            case R.id.confirm:
                finish();
                break;
            case R.id.grid_bg:
                AnimUtils.slideUpToClose(photoFolderList, gridBg);
                break;
        }
    }

    private long time;

    private void getPicture() {
        time = System.currentTimeMillis();
        PhotoStore.getAllPhoto(this, getApplicationContext(), this);
    }

    /**
     * Query completed ,finished in 50ms,470 photos
     */
    @Override
    public void onResultCallback(Map<String, List<Photo>> dirPhotos) {
        System.out.println(System.currentTimeMillis() - time + "毫秒");
        FolderListAdapter adapter = new FolderListAdapter(dirPhotos, this);
        photoFolderList.setAdapter(adapter);
        photoFolderList.setOnItemClickListener(this);
        List<Photo> allPhotos = dirPhotos.get("全部图片");
        photoGridView.setAdapter(new AlbumPhotoAdapter(allPhotos, this));
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
