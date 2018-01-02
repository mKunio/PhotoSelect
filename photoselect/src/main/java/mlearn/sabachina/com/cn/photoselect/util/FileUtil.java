package mlearn.sabachina.com.cn.photoselect.util;

import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zhc on 2017/12/25 0025.
 */

public class FileUtil {
    private static final String ROOT = "photoselect";
    private static final String IMAGE = "images";

    public static Uri getDefaultUri() {
        String time = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File file = new File(getRootFile(), IMAGE);
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
        }
        File targetFile = new File(file, time + ".jpg");
        return Uri.fromFile(targetFile);
    }

    private static File getRootFile() {
        return new File(Environment.getExternalStorageDirectory(), ROOT);
    }
}
