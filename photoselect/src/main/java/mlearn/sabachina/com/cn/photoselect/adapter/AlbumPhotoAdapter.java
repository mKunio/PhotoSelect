package mlearn.sabachina.com.cn.photoselect.adapter;

import android.content.res.Resources;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mlearn.sabachina.com.cn.photoselect.R;
import mlearn.sabachina.com.cn.photoselect.activity.AlbumActivity;
import mlearn.sabachina.com.cn.photoselect.bean.Photo;
import mlearn.sabachina.com.cn.photoselect.request.AlbumOperation;
import mlearn.sabachina.com.cn.photoselect.request.CheckMarkStyle;
import mlearn.sabachina.com.cn.photoselect.widget.SectorIndicatorView;
import mlearn.sabachina.com.cn.photoselect.widget.SquareImageView;

/**
 * Created by zhc on 2017/11/1 0001.
 */

public class AlbumPhotoAdapter extends BaseAdapter {
    private List<Photo> photoList;
    private AlbumActivity activity;
    private ArrayList<Photo> selectPhoto;
    private AlbumOperation albumOperation;

    public AlbumPhotoAdapter(AlbumOperation albumOperation, List<Photo> photos, AlbumActivity activity) {
        selectPhoto = new ArrayList<>();
        photoList = photos;
        this.activity = activity;
        this.albumOperation = albumOperation;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }

    private boolean needToggle(Photo photo) {
        if (selectPhoto.size() < albumOperation.getMaxNum()) {
            return true;
        } else if (selectPhoto.size() == albumOperation.getMaxNum()) {
            if (selectPhoto.contains(photo)) {
                return true;
            }
        }
        return false;
    }

    public void itemToggle(Photo photo, View view) {
        if (needToggle(photo)) {
            photo.setSelected(!photo.isSelected());
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            viewHolder.transparent_bg.setVisibility(photo.isSelected() ? View.VISIBLE : View.GONE);
            refreshSelectPhoto(photo);
            if (albumOperation.getStyle() == CheckMarkStyle.PICTURE || photo.isSelected()) {
                notifySingleItemView(photo, viewHolder);
            } else {
                notifyAllItemView(photo);
            }
        } else {
            Toast.makeText(activity, "您最多可选择" + albumOperation.getMaxNum() + "张图片", Toast.LENGTH_SHORT).show();
        }
    }

    private void notifyAllItemView(Photo changePhoto) {
        for (Photo photo : selectPhoto) {
            if (photo.getNumber() > changePhoto.getNumber()) {
                photo.setNumber(photo.getNumber() - 1);
            }
        }
        changePhoto.setNumber(-1);
        notifyDataSetChanged();
    }

    private void notifySingleItemView(Photo photo, ViewHolder holder) {
        if (albumOperation.getStyle() == CheckMarkStyle.PICTURE) {
            int resourceId = photo.isSelected() ? albumOperation.getSelectResId() : albumOperation.getUnSelectResId();
//            Glide.with(activity).load(resourceId).asBitmap()
//                    .placeholder(resourceId).error(resourceId).into(holder.checkbox);
        } else {
            photo.setNumber(selectPhoto.size());
            holder.indicatorView.setText(photo.getNumber());
        }
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
        CheckMarkStyle checkMarkStyle = albumOperation.getStyle();
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(activity).inflate(R.layout.item_photo_select, parent, false);
            viewHolder.photo = convertView.findViewById(R.id.photo);
            viewHolder.transparent_bg = convertView.findViewById(R.id.transparent_bg);
            viewHolder.checkbox = convertView.findViewById(R.id.checkbox);
            viewHolder.indicatorView = convertView.findViewById(R.id.indicator);
            FrameLayout.LayoutParams layoutParams;
            if (checkMarkStyle == CheckMarkStyle.PICTURE) {
                viewHolder.indicatorView.setVisibility(View.GONE);
                layoutParams = (FrameLayout.LayoutParams) viewHolder.checkbox.getLayoutParams();
            } else {
                viewHolder.checkbox.setVisibility(View.GONE);
                setIndicatorAttributes(viewHolder.indicatorView);
                layoutParams = (FrameLayout.LayoutParams) viewHolder.indicatorView.getLayoutParams();
            }
            setLocationForCheckboxInParent(layoutParams);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Photo photo = getItem(position);
//        Glide.with(activity).load(photo.getFilePath()).asBitmap()
//                .placeholder(R.drawable.image_holder).error(R.drawable.image_holder).into(viewHolder.photo);
        if (checkMarkStyle == CheckMarkStyle.PICTURE) {
//            int resourceId = photo.isSelected() ? albumOperation.getSelectResId() : albumOperation.getUnSelectResId();
//            Glide.with(activity).load(resourceId).asBitmap()
//                    .placeholder(R.drawable.checkbox_un).error(R.drawable.checkbox_un).into(viewHolder.checkbox);
        } else {
            viewHolder.indicatorView.setText(photo.getNumber());
        }
        viewHolder.transparent_bg.setVisibility(photo.isSelected() ? View.VISIBLE : View.GONE);
        return convertView;
    }

    private void setIndicatorAttributes(SectorIndicatorView indicatorView) {
        Resources resources = activity.getResources();
        indicatorView.setStrokeColor(resources.getColor(albumOperation.getDigitOutSideColor()));
        indicatorView.setInsideColor(resources.getColor(albumOperation.getDigitInSideColor()));
        indicatorView.setInsideTextColor(resources.getColor(albumOperation.getDigitTextColor()));
        // 初始化
        indicatorView.setText(-1);
        indicatorView.postInvalidate();
    }

    private void setLocationForCheckboxInParent(FrameLayout.LayoutParams params) {
        switch (albumOperation.getLocation()) {
            case BOTTOM_LEFT:
                params.gravity = Gravity.START | Gravity.BOTTOM;
                break;
            case BOTTOM_RIGHT:
                params.gravity = Gravity.END | Gravity.BOTTOM;
                break;
            case TOP_LEFT:
                params.gravity = Gravity.START | Gravity.TOP;
                break;
            case TOP_RIGHT:
                params.gravity = Gravity.END | Gravity.TOP;
                break;
            case CENTER:
                params.gravity = Gravity.CENTER;
                break;
        }
        int margin = albumOperation.getMarginSelectedSign();
        params.setMargins(margin, margin, margin, margin);
    }


    private static class ViewHolder {
        private SquareImageView photo;
        private SquareImageView transparent_bg;
        private ImageView checkbox;
        private SectorIndicatorView indicatorView;
    }
}
