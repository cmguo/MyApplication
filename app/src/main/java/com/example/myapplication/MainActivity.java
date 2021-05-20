package com.example.myapplication;

import android.os.Bundle;

import com.xhb.business.BookService;
import com.xhb.business.Services;
import com.xhb.prism.http.HttpServices;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.readystatesoftware.chuck.ChuckInterceptor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        List<Interceptor> interceptors = new ArrayList<Interceptor>();
        interceptors.add(new ChuckInterceptor(getApplicationContext()));
        BookService svc = Services.bookService(interceptors);
        svc.getPaperInfo("68560").subscribe(info -> {
            Log.i("MainActivity", "paperId:" + info.paperTypesetInfo.paperId);
            Log.i("MainActivity", "papers:" + info.paperTypesetInfo.datas.size());
        });
        svc.getEBookInfo("66345", "525174").subscribe(info -> {
            Log.i("MainActivity", "pages:" + info.electronicTeachingMaterialList.size());
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}