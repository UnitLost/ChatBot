package com.example.chatbot;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import android.os.Bundle;
//import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
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
        list.add(new Person("张三","18岁  电话:158888666"));
        list.add(new Person("李四","21岁  电话:158888777"));
        list.add(new Person("王二","23岁  电话:158888877"));
        list.add(new Person("孙武","25岁  电话:158888997"));

        //2.设置Adapter
        ListView lv_list = (ListView)findViewById(R.id.lv_list);//TODO：对照这里尝试一下教程里的布局
        lv_list.setAdapter(new MyAdapter());

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