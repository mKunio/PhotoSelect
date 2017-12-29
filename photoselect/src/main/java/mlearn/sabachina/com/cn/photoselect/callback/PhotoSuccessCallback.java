package mlearn.sabachina.com.cn.photoselect.callback;

import java.util.List;
import java.util.Map;

/**
 * Created by zhc on 2017/11/1 0001.
 */

public interface PhotoSuccessCallback<T> {
    // Map<String, List<Photo>>
    void onResultCallback(Map<String, List<T>> dirPhoto);
}