# PhotoSelect
您可以选择在activity或fragment中选择拍照、从相册中选择照片功能
# **效果图**
![photo_select1](https://raw.githubusercontent.com/wiki/mKunio/PhotoSelect/photo_select1.png)
![photo_select2](https://raw.githubusercontent.com/wiki/mKunio/PhotoSelect/photo_select2.png)
![photo_select3](https://raw.githubusercontent.com/wiki/mKunio/PhotoSelect/photo_select3.png)
# **参数释义**
# AlbumOperation
| 变量名 | 举例值 | 释义 | 
| - | :-: | -: | 
| maxNum（int） | 5| 相册最大选择数量 | 
| selectResId(int) | R.drawable.select | 图片选中标识图片资源文件id，  当CheckMarkStyle为PICTURE时生效| 
| unSelectResId(int) | R.drawable.unSelect | 图片未选中标识图片资源文件id， 当CheckMarkStyle为PICTURE时生效|
| marginSelectedSign（int） |20 | 图片选中标识距离周边距离 以像素为单位 | 
| column(int) | 3 | 相册页面列数| 
| albumTitleBarColor(int) | R.color.red | 相册页面顶部布局北京颜色和状态栏颜色id|
| style（CheckMarkStyle） | CheckMarkStyle.PICTURE| 相册指示器样式 | 
| location(IconLocation) | IconLocation.TOP_RIGHT | 相册指示器相对位置| 
| digitOutSideColor(int) | R.color.red | 外圈圆颜色id， 当CheckMarkStyle为DIGIT时生效|
| digitInSideColor（int） |R.color.red| 内圈圆颜色id， 当CheckMarkStyle为DIGIT时生效 | 
| digitTextColor(int) |R.color.red| 内圈指示文字颜色id， 当CheckMarkStyle为DIGIT时生效| 

# **使用**
## Step 1. 
* Add the JitPack repository to your build file ：<br>
  ```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  ```
 ## Step 2. 
*  Add the dependency ：<br>
  ```
	dependencies {
	        compile 'com.github.mKunio:PhotoSelect:v1.3'
	}
  ```
  # **示例代码**
  ## 相机
  ```
	 PhotoPicker.getCamera()
            .requestCode(3)
            .uri(uri)     // uri = FileUtil.getDefaultUri();
            .start(this);
  ``` 
  ## 相册
	 AlbumOperation operation = new AlbumOperation.Builder()
            // 图片选中指示器离四周的距离,单位像素
            .marginSelectedSign(12)
            // 最大选择数
            .maxNum(4)
            // 展示的列数
            .column(3)
            // 指示器的风格，可选图片标识和数字标识
            .style(CheckMarkStyle.DIGIT)
            // 图片选中指示相对位置，左上，左下，右上，右下
            .location(IconLocation.TOP_RIGHT)
            // 图片标识中选中之后的资源文件图片
            .selectResId(R.drawable.checkbox)
            // 图片标识中未选中的资源文件图片
            .unSelectResId(R.drawable.checkbox_un)
            // 数字标识中内圈圆颜色
            .digitInSideColor(R.color.colorAlbum)
            // 相册页面顶布局背景颜色(带状态栏)
            .albumTitleBarColor(R.color.colorAlbum)
            .build();
    PhotoPicker.getAlbum()
          .requestCode(4)
          //相册页面的属性设置
          .albumOperation(operation)
          //设置一下自己的加载图片方式
          .imageLoader(new ImageLoader()) //ImageLoader extends BaseImageLoader  并且需要自己实现Parcelable代码
          .start(this)                    
  ``` 
  
	 @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 相机返回resultCode 为0
        if (resultCode == RESULT_OK) {
            if (requestCode == 3) {
                // 相机
                // 直接将打开相机时设置的uri拿来使用即可
                Log.d("Photo", "onActivityResult: " + uri.getPath());
            }
            if (requestCode == 4) {
                ArrayList<Photo> photos = data.getParcelableArrayListExtra(AlbumTarget.ALBUM_SELECT_PHOTO);
                for (Photo photo : photos) {
                    Log.d("Photo", "onActivityResult: " + photo.getFilePath());
                }
            }
        }
    }
 ``` 
  ## 所需权限 
   ```
  <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
  <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
  <uses-permission android:name="android.permission.CAMERA"/>
   ```
   ## PS
   为尽量避免代码重复，如果您的项目需要适配Android6.0或者7.0，请自行判断，选择相册图片时设置的imageLoader请继承自BaseLmageLoader
   且自行实现Parcelable代码
   ## License
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
