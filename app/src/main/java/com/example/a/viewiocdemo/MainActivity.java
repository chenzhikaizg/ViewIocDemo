package com.example.a.viewiocdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.ioslibrary.ioc.OnClick;
import com.example.a.ioslibrary.ioc.ViewById;
import com.example.a.ioslibrary.ioc.ViewIocUtils;

public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.tv_text)
    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewIocUtils.inject(this);
    }

    @OnClick(R.id.tv_text)
    public void onClick(TextView textView ){
        Toast.makeText(this,"dinalewo",Toast.LENGTH_SHORT).show();

    }

}
