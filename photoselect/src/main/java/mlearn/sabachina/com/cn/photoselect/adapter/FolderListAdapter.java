package mlearn.sabachina.com.cn.photoselect.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mlearn.sabachina.com.cn.photoselect.R;
import mlearn.sabachina.com.cn.photoselect.bean.Photo;
import mlearn.sabachina.com.cn.photoselect.widget.SquareImageView;

/**
 * Created by zhc on 2017/12/28 0028.
 */

public class FolderListAdapter extends BaseAdapter {
    private Map<String, List<Photo>> dirPhotos;
    private Context context;
    private List<String> folders;
    private int currentPosition;

    public FolderListAdapter(Map<String, List<Photo>> dirPhotos, Context context) {
        Set<String> folderName = dirPhotos.keySet();
        this.folders = new ArrayList<>();
        for (String f : folderName) {
            this.folders.add(f);
        }
        this.dirPhotos = dirPhotos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return folders.size();
    }

    @Override
    public String getItem(int position) {
        return folders.get(position);
    }

    public List<Photo> getPhotoList(int position) {
        return dirPhotos.get(folders.get(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_album_photo, parent, false);
            viewHolder.folderName = convertView.findViewById(R.id.folder_name);
            viewHolder.imageView = convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        StringBuffer buffer = new StringBuffer();
        String folderName = folders.get(position);
        buffer.append(folderName);
        buffer.append("(");
        List<Photo> photos = dirPhotos.get(folderName);
        buffer.append(photos.size());
        buffer.append(")");
        viewHolder.folderName.setText(buffer);
        Glide.with(context).load(photos.get(0).getFilePath()).asBitmap().error(R.drawable.image_holder).into(viewHolder.imageView);
        return convertView;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    private static class ViewHolder {
        private TextView folderName;
        private SquareImageView imageView;
    }
}
