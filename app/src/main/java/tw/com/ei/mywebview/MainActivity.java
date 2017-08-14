package tw.com.ei.mywebview;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.net.URI;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private LocationManager lmgr;
    private MyListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    123);
        }else{
            init();
        }
        webView=(WebView)findViewById(R.id.webview);
        initWebView();

    }
    private class MyListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        init();
    }

    private void init(){
        webView = (WebView)findViewById(R.id.webview);
        initWebView();

        lmgr = (LocationManager) getSystemService(LOCATION_SERVICE);
        listener = new MyListener();
        lmgr.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,listener);



    }
    public void initWebView(){
        //webview.loadUrl("https://www.mlb.com/");
        //載入本機的HTML檔案 前面兩個//=網頁的//，第三根/ 代表根目錄..
        //webview.loadUrl("file:///android_asset/page2.html");
        webView.loadUrl("file:///android_asset/mymap.html");
        WebSettings settings=webView.getSettings();
        settings.setJavaScriptEnabled(true);//開啟使用JAVASCRIPT
    }

    public void Button1(View v){
        /*
        Uri uri= Uri.parse("https://www.mlb.com/");
        Intent it=new Intent(Intent.ACTION_VIEW,uri);
        startActivity(it);
        */
        webView.loadUrl("javascript:test2('Simon')");//呼叫javascript的方法
    }
    public void Button2(View v){

    }
}
