# WaveApplication
水波纹效果控件，
可以在水波上方添加图片，图片会随水波上下浮动.<br>
<b>attrs属性：</b>

<pre><code>
 <declare-styleable name="waveStyleable">
        &lt;!-- 水波纹的长度--&gt;
        &lt;attr name="waveLength" format="float"&gt; &lt;/attr&gt;
         &lt;!-- 水波纹的高度-->
        &lt;attr name="waveHeight" format="float"&gt; &lt;/attr&gt;
        &lt;!-- 水波纹的速度-->
        &lt;attr name="waveSpeed" format="float"&gt; &lt;/attr&gt;
        &lt;!--水波纹上方的头像 -->
        &lt;attr name="waveTopIcon" format="reference"&gt; &lt;/attr&gt;
        &lt;!--水波的颜色 -->
        &lt;attr name="waveColor" format="color"&gt; &lt;/attr&gt;
        &lt;!--水波距离底部的距离 -->
        &lt;attr name="distanceY" format="float"&gt; &lt;/attr>

    </declare-styleable>

</code></pre><br>

<b>xml文件引入</b>
<pre><code>
  <com.iwintrue.waveapplication.WaveView
        xmlns:app="http://schemas.android.com/apk/res-auto"
        app:waveLength = "600"
        app:waveHeight = "50"
        app:waveSpeed = "10"
        app:waveColor = "#0ff"
        app:distanceY = "100"
        app:waveTopIcon = "@mipmap/icon"
        android:layout_centerInParent="true"
        android:id="@+id/waterView"
        android:layout_width="match_parent"
        android:layout_height="200dp"
        />

</code></pre><br>

效果图如下：<br>
![zhoukai1526](https://github.com/zhoukai1526/WaveApplication/blob/master/app/src/main/raw/waveGif.gif)  
