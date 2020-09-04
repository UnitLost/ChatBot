package com.example.chatbot;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
//import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

class Person {
    String name;
    String content;

    public Person(String name,String content){
        this.name = name;
        this.content = content;
    }
    public String getName() {
        return name;
    }
    public String getContent() {
        return content;
    }
}

public class HomePage extends AppCompatActivity {

    ArrayList<Person>    list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //1.添加要显示的数据
        list  = new ArrayList<Person>();
        list.add(new Person("聊天机器人","我是一个聊天机器人"));

        //2.设置Adapter
        final ListView lv_list = (ListView)findViewById(R.id.lv_list);//对照这里尝试一下教程里的布局
        lv_list.setAdapter(new MyAdapter());

        //点击监听
        /*lv_list.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startChat(v);
            }
        });*/
        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {
                startChat(lv_list);
            }
        });

    }

    /*启动聊天activity，当点击列表时调用*/
    public void startChat(View view){
        Intent intent = new Intent(this, Chat.class);
        //EditText editText = (EditText) findViewById(R.id.editText);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = null;

            //如果convertView为空则需要重新创建资源视图,如果不为空则表示可以用来复用.无需再次new一个view来使用.
            if(convertView==null){
                //通过R.layout.item 来创建一个item视图资源
                view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item, null);
            }else{

                view = convertView;
            }

            //设置要显示的内容
            TextView  tv_name =  (TextView)view.findViewById(R.id.tv_name);
            TextView  tv_content =  (TextView)view.findViewById(R.id.tv_content);

            tv_name.setText(list.get(position).getName());
            tv_content.setText(list.get(position).getContent());


            return view;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}