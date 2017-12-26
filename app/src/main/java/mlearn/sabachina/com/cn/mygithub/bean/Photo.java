package mlearn.sabachina.com.cn.mygithub.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhc on 2017/11/1 0001.
 */

public class Photo implements Parcelable {
    /**
     * 判断是否是视频
     */
    private boolean isVideo;
    /**
     * 本身用于本地图片在系统中的id,因为项目用不到此属性，故改为网络图片或者视频
     * 时对应的pKid
     */
    private long id;
    /**
     * 文件名称
     */
    private String name;
    /**
     * 当文件为本地资源时,对应本地路径
     * 当文件为网络资源时，对应网络url
     */
    private String path;
    /**
     * 是否选中,用于相册选择标识
     */
    private boolean isSelect;
    /**
     * 标识是否是网络资源
     */
    private boolean isNetResource;

    public boolean isNetResource() {
        return isNetResource;
    }

    public void setNetResource(boolean netResource) {
        isNetResource = netResource;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public void setVideo(boolean video) {
        isVideo = video;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Photo)) return false;

        Photo photo = (Photo) o;

        if (isVideo != photo.isVideo) return false;
        if (id != photo.id) return false;
        if (isSelect != photo.isSelect) return false;
        if (isNetResource != photo.isNetResource) return false;
        if (name != null ? !name.equals(photo.name) : photo.name != null) return false;
        return path != null ? path.equals(photo.path) : photo.path == null;
    }

    @Override
    public int hashCode() {
        int result = (isVideo ? 1 : 0);
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (isSelect ? 1 : 0);
        result = 31 * result + (isNetResource ? 1 : 0);
        return result;
    }

    public Photo(boolean isVideo, long id, String name, String path) {
        this.isVideo = isVideo;
        this.id = id;
        this.name = name;
        this.path = path;
    }

    public Photo(long id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.isVideo ? (byte) 1 : (byte) 0);
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.path);
        dest.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isNetResource ? (byte) 1 : (byte) 0);
    }

    protected Photo(Parcel in) {
        this.isVideo = in.readByte() != 0;
        this.id = in.readLong();
        this.name = in.readString();
        this.path = in.readString();
        this.isSelect = in.readByte() != 0;
        this.isNetResource = in.readByte() != 0;
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
