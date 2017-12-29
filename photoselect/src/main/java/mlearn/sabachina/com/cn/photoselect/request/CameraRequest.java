package mlearn.sabachina.com.cn.photoselect.request;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * Created by zhc on 2017/12/25 0025.
 */

public class CameraRequest implements CameraTarget {
    private Uri uri;
    private int requestCode = -1;

    @Override
    public CameraRequest uri(@NonNull Uri uri) {
        this.uri = uri;
        return this;
    }

    @Override
    public CameraTarget requestCode(int requestCode) {
        this.requestCode = requestCode;
        return this;
    }

    @Override
    public void start(Activity activity) {
        Intent intent = getCameraIntent(activity);
        if (intent != null) {
            if (uri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                activity.startActivityForResult(intent, requestCode == -1 ? PhotoPicker.REQUEST_CODE_CAMERA : requestCode);
            } else {
                Toast.makeText(activity, "Please try to assign a value to URI", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void start(Fragment fragment) {
        Intent intent = getCameraIntent(fragment.getActivity());
        if (intent != null) {
            if (uri != null)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            fragment.startActivityForResult(intent, requestCode == -1 ? PhotoPicker.REQUEST_CODE_CAMERA : requestCode);
        } else {
            Toast.makeText(fragment.getContext(), "Please try to assign a value to URI", Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    private Intent getCameraIntent(Context context) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            return intent;
        } else {
            Toast.makeText(context, "Your phone doesn't have a camera and can't take a picture", Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}
