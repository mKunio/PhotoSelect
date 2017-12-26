package mlearn.sabachina.com.cn.mygithub.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import mlearn.sabachina.com.cn.mygithub.R;
import mlearn.sabachina.com.cn.mygithub.request.AlbumOperation;

/**
 * Created by zhc on 2017/10/31 0031.
 */

public class AlbumActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView photoFolder;
    private View photoGridView;
    private AlbumOperation albumOperation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        findViews();
        getDataFromIntent();
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
        photoGridView = findViewById(R.id.photo_grid_view);
        back.setOnClickListener(this);
        chooseFolder.setOnClickListener(this);
        confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.choose_folder:
                finish();
                break;
            case R.id.confirm:
                finish();
                break;
        }
    }
}
