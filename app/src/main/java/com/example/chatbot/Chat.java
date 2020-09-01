package com.example.chatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

public class Chat extends AppCompatActivity {

    private ListView list_one;
    private MyAdapter mAdapter = null;
    private List<Data> mData = null;
    private Context mContext = null;
    private Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mContext = Chat.this;
        bindViews();
        mData = new LinkedList<Data>();
        mAdapter = new MyAdapter((LinkedList<Data>) mData,mContext);
        list_one.setAdapter(mAdapter);
    }

    private void bindViews(){
        list_one = (ListView) findViewById(R.id.list_one);
    }

    /*public void clickUpdate(View v){
        mAdapter.add(new Data(R.mipmap.ic_launcher,"新消息"));
    }*/
    public void clickUpdateLeft(View v){
        mAdapter.add(new Data(R.mipmap.ic_launcher,"新消息",0),0);
    }

    public void clickUpdateRight(View v){
        mAdapter.add(new Data(R.mipmap.ic_launcher,"新消息",1),1);
    }

}