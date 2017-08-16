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
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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
        //lmgr.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,listener);
    }

    @Override
    public void finish() {
        lmgr.removeUpdates(listener);
        super.finish();
    }

    private class MyListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            webView.loadUrl("javascript:moveTo(" + lat + "," + lng + ")");
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

    private void initWebView(){
        WebViewClient client = new WebViewClient();
        webView.setWebViewClient(client);

        WebSettings setting = webView.getSettings();
        setting.setJavaScriptEnabled(true);

        // 1.
        //webView.loadUrl("http://www.tcca.org.tw");
        // 2.
        //webView.loadUrl("file:///android_asset/mymap.html");

        //讓webView認識MyJS的物件
        webView.addJavascriptInterface(new MyJS(),"");
        webView.loadUrl("file:///android_asset/simon.html");
    }

    public void Button1(View view){
        webView.loadUrl("javascript:test2('simon')");
    }
    //自訂類別 官方說法指示要public
    public class MyJS{
        //方法須加上@JavascriptInterface
        @JavascriptInterface
        public String m1(){
            Log.i("simon","aa");
            return "";
        }

    }
    public void Button2(View view){



    }
}
