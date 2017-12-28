package mlearn.sabachina.com.cn.mygithub.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import mlearn.sabachina.com.cn.mygithub.R;
import mlearn.sabachina.com.cn.mygithub.activity.AlbumActivity;
import mlearn.sabachina.com.cn.mygithub.bean.Photo;
import mlearn.sabachina.com.cn.mygithub.widget.SquareImageView;

/**
 * Created by zhc on 2017/11/1 0001.
 */

public class AlbumPhotoAdapter extends BaseAdapter {
    private List<Photo> photoList;
    private AlbumActivity activity;
    private int maxNum = 3;
    private ArrayList<Photo> selectPhoto = new ArrayList<>();

    public AlbumPhotoAdapter(List<Photo> files, AlbumActivity activity) {
        photoList = files;
        this.activity = activity;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }

    private boolean needToggle(Photo photo) {
        if (selectPhoto.size() < maxNum) {
            return true;
        } else if (selectPhoto.size() == maxNum) {
            if (selectPhoto.contains(photo)) {
                return true;
            }
        }
        return false;
    }

    public void itemToggle(Photo photo, View view) {
        if (needToggle(photo)) {
            photo.setSelected(!photo.isSelected());
            notifyItemView(photo, view);
            refreshSelectPhoto(photo);
        } else {
            Toast.makeText(activity, "您最多可选择" + maxNum + "张图片", Toast.LENGTH_SHORT).show();
        }
    }

    private void notifyItemView(Photo photo, View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.transparent_bg.setVisibility(photo.isSelected() ? View.VISIBLE : View.GONE);
        int resourceId = photo.isSelected() ? R.drawable.checkbox : R.drawable.checkbox_un;
        Glide.with(activity).load(resourceId).asBitmap()
                .placeholder(R.drawable.checkbox_un).error(R.drawable.checkbox_un).into(viewHolder.checkbox);
    }

    private void refreshSelectPhoto(Photo photo) {
        if (photo.isSelected()) {
            selectPhoto.add(photo);
        } else {
            selectPhoto.remove(photo);
        }
    }

    public ArrayList<Photo> getSelectPhoto() {
        return selectPhoto;
    }

    @Override
    public int getCount() {
        return photoList.size();
    }

    @Override
    public Photo getItem(int position) {
        return photoList.get(position);
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
            convertView = LayoutInflater.from(activity).inflate(R.layout.item_photo_select, parent, false);
            viewHolder.photo = convertView.findViewById(R.id.photo);
            viewHolder.transparent_bg = convertView.findViewById(R.id.transparent_bg);
            viewHolder.checkbox = convertView.findViewById(R.id.checkbox);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Photo photo = getItem(position);
        Glide.with(activity).load(photo.getFilePath()).asBitmap()
                .placeholder(R.drawable.image_holder).error(R.drawable.image_holder).into(viewHolder.photo);
        int resourceId = photo.isSelected() ? R.drawable.checkbox : R.drawable.checkbox_un;
        Glide.with(activity).load(resourceId).asBitmap()
                .placeholder(R.drawable.checkbox_un).error(R.drawable.checkbox_un).into(viewHolder.checkbox);
        viewHolder.transparent_bg.setVisibility(photo.isSelected() ? View.VISIBLE : View.GONE);
        return convertView;
    }


    private static class ViewHolder {
        private SquareImageView photo;
        private SquareImageView transparent_bg;
        private ImageView checkbox;
    }
}
