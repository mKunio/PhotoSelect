package mlearn.sabachina.com.cn.mygithub.request;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

import mlearn.sabachina.com.cn.mygithub.R;

/**
 * Created by zhc on 2017/12/25 0025.
 * <p>
 * This class configures the data of some extra albums,
 * such as the number of display columns, the display style
 */

public class AlbumOperation implements Parcelable {
    /**
     * Whether you need to show the camera icon
     */
    private final boolean needShowCameraIcon;
    /**
     * The max number of pictures to choose
     */
    private final int maxNum;
    /**
     * The ID of the selected logo resource file
     * When the Style is set to PICTURE, it takes effect
     *
     * @see Style
     */
    private final int selectResId;
    private final int unSelectResId;
    /**
     * Select the distance between the four sides of the identification distance
     */
    private final int marginSelectedSign;
    /**
     * The number of columns displayed in the album
     */
    private final int column;
    /**
     * Set the style of the selected logo is displayed as pictures or numbers
     */
    private final Style style;
    /**
     * The location of the selected logo
     */
    private final IconLocation location;
    /**
     * The color of selected logo
     * When the Style is set to DIGIT, it takes effect
     *
     * @see Style
     */
    private final int digitLogoColor;

    public AlbumOperation() {
        this(new Builder());
    }

    public AlbumOperation(Builder builder) {
        needShowCameraIcon = builder.needShowCameraIcon;
        maxNum = builder.maxNum;
        selectResId = builder.selectResId;
        unSelectResId = builder.unSelectResId;
        marginSelectedSign = builder.marginSelectedSign;
        style = builder.style;
        location = builder.location;
        column = builder.column;
        digitLogoColor = builder.digitLogoColor;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public static final class Builder {
        private boolean needShowCameraIcon;
        private int maxNum;
        private int selectResId;
        private int unSelectResId;
        private int marginSelectedSign;
        private Style style;
        private IconLocation location;
        private int column;
        private int digitLogoColor;

        public Builder() {
            needShowCameraIcon = false;
            maxNum = 1;
            selectResId = R.drawable.checkbox;
            unSelectResId = R.drawable.checkbox_un;
            marginSelectedSign = 30;
            style = Style.PICTURE;
            column = 3;
            location = IconLocation.TOP_RIGHT;
            digitLogoColor = Color.CYAN;
        }

        public Builder(AlbumOperation operation) {
            needShowCameraIcon = operation.needShowCameraIcon;
            maxNum = operation.maxNum;
            selectResId = operation.selectResId;
            unSelectResId = operation.unSelectResId;
            marginSelectedSign = operation.marginSelectedSign;
            style = operation.style == null ? Style.PICTURE : style;
            column = operation.column;
            location = operation.location;
            digitLogoColor = operation.digitLogoColor;
        }

        public Builder needShowCameraIcon(boolean needShowCameraIcon) {
            this.needShowCameraIcon = needShowCameraIcon;
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

        public Builder style(Style style) {
            this.style = style;
            return this;
        }

        public Builder column(int column) {
            this.column = column;
            return this;
        }

        public Builder location(IconLocation location) {
            this.location = location;
            return this;
        }

        public Builder digitLogoColor(int digitLogoColor) {
            this.digitLogoColor = digitLogoColor;
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
        dest.writeByte(this.needShowCameraIcon ? (byte) 1 : (byte) 0);
        dest.writeInt(this.maxNum);
        dest.writeInt(this.selectResId);
        dest.writeInt(this.unSelectResId);
        dest.writeInt(this.marginSelectedSign);
        dest.writeInt(this.column);
        dest.writeInt(this.style == null ? -1 : this.style.ordinal());
        dest.writeInt(this.location == null ? -1 : this.location.ordinal());
        dest.writeInt(this.digitLogoColor);
    }

    protected AlbumOperation(Parcel in) {
        this.needShowCameraIcon = in.readByte() != 0;
        this.maxNum = in.readInt();
        this.selectResId = in.readInt();
        this.unSelectResId = in.readInt();
        this.marginSelectedSign = in.readInt();
        this.column = in.readInt();
        int tmpStyle = in.readInt();
        this.style = tmpStyle == -1 ? null : Style.values()[tmpStyle];
        int tmpLocation = in.readInt();
        this.location = tmpLocation == -1 ? null : IconLocation.values()[tmpLocation];
        this.digitLogoColor = in.readInt();
    }

    public static final Creator<AlbumOperation> CREATOR = new Creator<AlbumOperation>() {
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
