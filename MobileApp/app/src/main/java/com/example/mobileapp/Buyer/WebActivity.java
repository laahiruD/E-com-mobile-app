package com.example.mobileapp.Buyer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.EditText;

import com.example.mobileapp.R;

public class WebActivity extends AppCompatActivity {




    private String a,url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        a=getIntent().getStringExtra("word");
         url = "https://www.ebay.com/sch/i.html?_from=R40&_trksid=m570.l1313&_nkw=" + a;



        WebView web = (WebView)findViewById(R.id.webView1);
        web.loadUrl(url);


    }
}
