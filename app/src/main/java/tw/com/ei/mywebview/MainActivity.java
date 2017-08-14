package tw.com.ei.mywebview;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import java.net.URI;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webview=(WebView)findViewById(R.id.webview);
        initWebView();
    }
    public void initWebView(){
        //webview.loadUrl("https://www.mlb.com/");
        //載入本機的HTML檔案 前面兩個//=網頁的//，第三根/ 代表根目錄..
        webview.loadUrl("file:///android_asset/simon.html");
    }
    public void Button1(View v){
        Uri uri= Uri.parse("https://www.mlb.com/");
        Intent it=new Intent(Intent.ACTION_VIEW,uri);
        startActivity(it);

    }
    public void Button2(View v){

    }
}
