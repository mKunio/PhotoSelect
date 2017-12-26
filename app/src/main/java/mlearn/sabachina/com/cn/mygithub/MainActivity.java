package mlearn.sabachina.com.cn.mygithub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                PhotoPicker.getCamera()
                        .requestCode(3)
                        .uri(FileUtil.getDefaultUri())
                        .start(this);
                break;
            case R.id.album:
                AlbumOperation operation = new AlbumOperation.Builder()
                        .needShowCameraIcon(false)
                        .marginSelectedSign(20)
                        .maxNum(3)
                        .column(3)
                        .style(Style.PICTURE)
                        .location(IconLocation.TOP_RIGHT)
                        .selectResId(R.drawable.checkbox)
                        .unSelectResId(R.drawable.checkbox_un)
                        .build();
                PhotoPicker.getAlbum()
                        .requestCode(4)
                        .albumOperation(operation)
                        .start(this);
                break;
        }
    }
}
