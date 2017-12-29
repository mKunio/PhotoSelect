package mlearn.sabachina.com.cn.photoselect.request;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

import mlearn.sabachina.com.cn.photoselect.R;

/**
 * Created by zhc on 2017/12/25 0025.
 * <p>
 * This class configures the data of some extra albums,
 * such as the number of display columns, the display style
 */

public class AlbumOperation implements Parcelable {
    /**
     * The max number of pictures to choose
     */
    private final int maxNum;
    /**
     * The ID of the selected logo resource file
     * When the CheckMarkStyle is set to PICTURE, it takes effect
     *
     * @see CheckMarkStyle
     */
    private final int selectResId;
    private final int unSelectResId;

    /**
     * Select the distance between the four sides of the identification distance
     * it's pixel
     */
    private final int marginSelectedSign;
    /**
     * The number of columns displayed in the album
     */
    private final int column;
    /**
     * The color of statusBar in the album
     */
    private final int albumTitleBarColor;
    /**
     * Set the style of the selected logo is displayed as pictures or numbers
     */
    private final CheckMarkStyle style;
    /**
     * The location of the selected logo
     */
    private final IconLocation location;
    /**
     * The color of selected logo
     * When the CheckMarkStyle is set to DIGIT, it takes effect
     *
     * @see CheckMarkStyle
     */
    private final int digitOutSideColor;
    private final int digitInSideColor;
    private final int digitTextColor;

    public AlbumOperation() {
        this(new Builder());
    }

    public AlbumOperation(Builder builder) {
        maxNum = builder.maxNum;
        selectResId = builder.selectResId;
        unSelectResId = builder.unSelectResId;
        marginSelectedSign = builder.marginSelectedSign;
        style = builder.style;
        location = builder.location;
        column = builder.column;
        digitOutSideColor = builder.digitOutSideColor;
        albumTitleBarColor = builder.albumTitleBarColor;
        digitInSideColor = builder.digitInSideColor;
        digitTextColor = builder.digitTextColor;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public int getSelectResId() {
        return selectResId;
    }

    public int getUnSelectResId() {
        return unSelectResId;
    }

    public int getMarginSelectedSign() {
        return marginSelectedSign;
    }

    public int getColumn() {
        return column;
    }

    public CheckMarkStyle getStyle() {
        return style;
    }

    public IconLocation getLocation() {
        return location;
    }

    public int getDigitOutSideColor() {
        return digitOutSideColor;
    }

    public int getAlbumTitleBarColor() {
        return albumTitleBarColor;
    }

    public int getDigitInSideColor() {
        return digitInSideColor;
    }

    public int getDigitTextColor() {
        return digitTextColor;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public static final class Builder {
        private int maxNum;
        private int selectResId;
        private int unSelectResId;
        private int marginSelectedSign;
        private CheckMarkStyle style;
        private IconLocation location;
        private int column;
        private int digitOutSideColor;
        private int albumTitleBarColor;
        private int digitInSideColor;
        private int digitTextColor;

        public Builder() {
            maxNum = 1;
            selectResId = R.drawable.checkbox;
            unSelectResId = R.drawable.checkbox_un;
            marginSelectedSign = 6;
            style = CheckMarkStyle.PICTURE;
            column = 3;
            location = IconLocation.TOP_RIGHT;
            digitOutSideColor = R.color.album_digit_default_outside_color;
            albumTitleBarColor = R.color.colorAlbum;
            digitInSideColor = R.color.colorAlbum;
            digitTextColor = R.color.white;
        }

        public Builder(AlbumOperation operation) {
            maxNum = operation.maxNum;
            selectResId = operation.selectResId;
            unSelectResId = operation.unSelectResId;
            marginSelectedSign = operation.marginSelectedSign;
            style = operation.style == null ? CheckMarkStyle.PICTURE : style;
            column = operation.column;
            location = operation.location;
            digitOutSideColor = operation.digitOutSideColor;
            albumTitleBarColor = operation.albumTitleBarColor;
            digitInSideColor = operation.digitInSideColor;
            digitTextColor = operation.digitTextColor;
        }

        public Builder maxNum(int maxNum) {
            this.maxNum = maxNum;
            return this;
        }

        public Builder selectResId(@DrawableRes int selectResId) {
            this.selectResId = selectResId;
            return this;
        }

        public Builder unSelectResId(@DrawableRes int unSelectResId) {
            this.unSelectResId = unSelectResId;
            return this;
        }

        public Builder marginSelectedSign(int marginSelectedSign) {
            this.marginSelectedSign = marginSelectedSign;
            return this;
        }

        public Builder style(CheckMarkStyle style) {
            this.style = style;
            return this;
        }

        public Builder column(int column) {
            if (column < 2) {
                column = 2;
            }
            this.column = column;
            return this;
        }

        public Builder location(IconLocation location) {
            this.location = location;
            return this;
        }

        public Builder digitOutSideColor(@ColorRes int digitOutSideColor) {
            this.digitOutSideColor = digitOutSideColor;
            return this;
        }

        public Builder digitInSideColor(@ColorRes int digitInSideColor) {
            this.digitInSideColor = digitInSideColor;
            return this;
        }

        public Builder digitTextColor(@ColorRes int digitTextColor) {
            this.digitTextColor = digitTextColor;
            return this;
        }

        public Builder albumTitleBarColor(@ColorRes int albumTitleBarColor) {
            this.albumTitleBarColor = albumTitleBarColor;
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
        dest.writeInt(this.maxNum);
        dest.writeInt(this.selectResId);
        dest.writeInt(this.unSelectResId);
        dest.writeInt(this.marginSelectedSign);
        dest.writeInt(this.column);
        dest.writeInt(this.albumTitleBarColor);
        dest.writeInt(this.style == null ? -1 : this.style.ordinal());
        dest.writeInt(this.location == null ? -1 : this.location.ordinal());
        dest.writeInt(this.digitOutSideColor);
        dest.writeInt(this.digitInSideColor);
        dest.writeInt(this.digitTextColor);
    }

    protected AlbumOperation(Parcel in) {
        this.maxNum = in.readInt();
        this.selectResId = in.readInt();
        this.unSelectResId = in.readInt();
        this.marginSelectedSign = in.readInt();
        this.column = in.readInt();
        this.albumTitleBarColor = in.readInt();
        int tmpStyle = in.readInt();
        this.style = tmpStyle == -1 ? null : CheckMarkStyle.values()[tmpStyle];
        int tmpLocation = in.readInt();
        this.location = tmpLocation == -1 ? null : IconLocation.values()[tmpLocation];
        this.digitOutSideColor = in.readInt();
        this.digitInSideColor = in.readInt();
        this.digitTextColor = in.readInt();
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
