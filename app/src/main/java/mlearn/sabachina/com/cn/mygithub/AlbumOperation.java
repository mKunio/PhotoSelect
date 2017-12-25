package mlearn.sabachina.com.cn.mygithub;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhc on 2017/12/25 0025.
 */

public class AlbumOperation implements Parcelable {
    final boolean needShowVideo;
    final boolean needShowVideoIcon;
    final int maxNum;
    final int selectResId;
    final int unSelectResId;
    final int marginSelectedSign;

    public AlbumOperation() {
        this(new Builder());
    }

    public AlbumOperation(Builder builder) {
        needShowVideo = builder.needShowVideo;
        needShowVideoIcon = builder.needShowVideoIcon;
        maxNum = builder.maxNum;
        selectResId = builder.selectResId;
        unSelectResId = builder.unSelectResId;
        marginSelectedSign = builder.marginSelectedSign;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public static final class Builder {
        private boolean needShowVideo;
        private boolean needShowVideoIcon;
        private int maxNum;
        private int selectResId;
        private int unSelectResId;
        private int marginSelectedSign = 10;

        public Builder() {
            needShowVideo = false;
            needShowVideoIcon = true;
            maxNum = 1;
            selectResId = R.drawable.checkbox;
            unSelectResId = R.drawable.checkbox_un;
        }

        public Builder(AlbumOperation operation) {
            needShowVideo = operation.needShowVideo;
            needShowVideoIcon = operation.needShowVideoIcon;
            maxNum = operation.maxNum;
            selectResId = operation.selectResId;
            unSelectResId = operation.unSelectResId;
            marginSelectedSign = operation.marginSelectedSign;
        }

        public Builder needShowVideo(boolean needShowVideo) {
            this.needShowVideo = needShowVideo;
            return this;
        }

        public Builder needShowVideoIcon(boolean needShowVideoIcon) {
            this.needShowVideoIcon = needShowVideoIcon;
            return this;
        }

        public Builder maxNum(int maxNum) {
            this.maxNum = maxNum;
            return this;
        }

        public Builder selectResId(int selectResId) {
            this.selectResId = selectResId;
            return this;
        }

        public Builder unSelectResId(int unSelectResId) {
            this.unSelectResId = unSelectResId;
            return this;
        }

        public Builder marginSelectedSign(int marginSelectedSign) {
            this.marginSelectedSign = marginSelectedSign;
            return this;
        }

        public AlbumOperation build() {
            return new AlbumOperation(this);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.needShowVideo ? (byte) 1 : (byte) 0);
        dest.writeByte(this.needShowVideoIcon ? (byte) 1 : (byte) 0);
        dest.writeInt(this.maxNum);
        dest.writeInt(this.selectResId);
        dest.writeInt(this.unSelectResId);
        dest.writeInt(this.marginSelectedSign);
    }

    protected AlbumOperation(Parcel in) {
        this.needShowVideo = in.readByte() != 0;
        this.needShowVideoIcon = in.readByte() != 0;
        this.maxNum = in.readInt();
        this.selectResId = in.readInt();
        this.unSelectResId = in.readInt();
        this.marginSelectedSign = in.readInt();
    }

    public static final Parcelable.Creator<AlbumOperation> CREATOR = new Parcelable.Creator<AlbumOperation>() {
        @Override
        public AlbumOperation createFromParcel(Parcel source) {
            return new AlbumOperation(source);
        }

        @Override
        public AlbumOperation[] newArray(int size) {
            return new AlbumOperation[size];
        }
    };
}
