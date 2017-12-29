package mlearn.sabachina.com.cn.photoselect.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhc on 2017/11/1 0001.
 */

public class Photo implements Parcelable {
    private String fileName;
    private String filePath;
    private boolean selected;
    // 当CheckMarkStyle为CheckMarkStyle.DIGIT的时候，记录是第几个选择
    private int number = -1;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Photo)) return false;

        Photo photo = (Photo) o;

        if (selected != photo.selected) return false;
        if (number != photo.number) return false;
        if (!fileName.equals(photo.fileName)) return false;
        return filePath.equals(photo.filePath);

    }

    @Override
    public int hashCode() {
        int result = fileName.hashCode();
        result = 31 * result + filePath.hashCode();
        result = 31 * result + (selected ? 1 : 0);
        result = 31 * result + number;
        return result;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getFileName() {

        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public boolean isSelected() {
        return selected;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Photo(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public Photo(String fileName, String filePath, boolean selected) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.selected = selected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fileName);
        dest.writeString(this.filePath);
        dest.writeByte(this.selected ? (byte) 1 : (byte) 0);
    }

    protected Photo(Parcel in) {
        this.fileName = in.readString();
        this.filePath = in.readString();
        this.selected = in.readByte() != 0;
    }

    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel source) {
            return new Photo(source);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };
}
