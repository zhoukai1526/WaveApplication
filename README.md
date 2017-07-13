# WaveApplication
水波纹效果控件，
可以在水波上方添加图片，图片会随水波上下浮动.<br>
<h3><b>attrs属性：</b><h3>

<pre><code>
 &lt;declare-styleable name="waveStyleable"&gt;
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
        &lt;attr name="distanceY" format="float"&gt; &lt;/attr&gt;

&lt;/declare-styleable&gt;

</code></pre><br>

<h3><b>xml文件引入</b></h3>
<pre><code>
   &lt;com.iwintrue.waveapplication.WaveView
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
        /&gt;

</code></pre><br>

<h3><b>效果图如下：</b></h3>
<br><br>
<img src="https://github.com/zhoukai1526/WaveApplication/blob/master/app/src/main/raw/waveGif.gif"></img>
<br>
<h3><b>注意：</b></h3>
<ul>
<li>水波纹上方的头像必须设置</li>
<li>如果水波纹view的宽高有设置为wrapContent,则宽为水波纹的宽度，高度为头像高度+水波纹高度+水波纹距底部的高度</li>
</ul>
