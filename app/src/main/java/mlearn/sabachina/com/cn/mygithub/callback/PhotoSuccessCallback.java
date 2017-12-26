package mlearn.sabachina.com.cn.mygithub.callback;

import java.util.List;

/**
 * Created by zhc on 2017/11/1 0001.
 */

public interface PhotoSuccessCallback<T> {
    void onResultCallback(List<T> folder);
}