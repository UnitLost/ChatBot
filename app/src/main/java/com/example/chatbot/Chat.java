package com.example.chatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Chat extends AppCompatActivity {

    private ListView list_one;
    private MyAdapter mAdapter = null;
    private List<Data> mData = null;
    private Context mContext = null;
    private Button btn_add;

    //定义相关变量,完成初始化
    private TextView txtshow;
    private EditText editsend;
    private Button btnsend;
    private static final String HOST = "10.0.2.2";
    private static final int PORT = 9898;
    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private String content = "";
    private StringBuilder sb = null;

    //定义一个handler对象,用来刷新界面
    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            //if (msg.what == 0x123) {
                //System.out.println(content+"sss");
                //sb.append(content);
                //txtshow.setText(sb.toString());
                //System.out.println(sb.toString()+"kkk");
                //System.out.println(msg.obj.toString()+"sss");
                //System.out.println(msg.obj);
                clickUpdateLeft(findViewById(R.id.list_one),msg.obj.toString());//刷新界面，
            //}
        }


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mContext = Chat.this;
        bindViews();
        mData = new LinkedList<Data>();
        mAdapter = new MyAdapter((LinkedList<Data>) mData,mContext);
        list_one.setAdapter(mAdapter);

        sb = new StringBuilder();
        //txtshow = (TextView) findViewById(R.id.txtshow);
        editsend = (EditText) findViewById(R.id.editsend);
        btnsend = (Button) findViewById(R.id.btnsend);

        //当程序一开始运行的时候就实例化Socket对象,与服务端进行连接,获取输入输出流
        //因为4.0以后不能再主线程中进行网络操作,所以需要另外开辟一个线程
        new Thread() {

            public void run() {
                try {
                    socket = new Socket(HOST, PORT);
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                            socket.getOutputStream())), true);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                new Thread(){
                    public void run(){
                        try {
                            while (true) {
                                if (socket.isConnected()) {
                                    if (!socket.isInputShutdown()) {
                                        if ((content = in.readLine()) != null) {
                                            //content += "\n";
                                            //handler.sendEmptyMessage(0x123);
                                            System.out.println(content);
                                            //System.out.println(content);
                                            //clickUpdateLeft(findViewById(R.id.list_one),content);
                                            //content += "\n";
                                            //handler.sendEmptyMessage(0x123);
                                            content.substring(0,content.length());


                                            //从全局池中返回一个message实例，避免多次创建message（如new Message）
                                            Message msg =Message.obtain();
                                            msg.obj = content;
                                            //msg.
                                            System.out.println(msg.obj);
                                            handler.sendMessage(msg);
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

                btnsend.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //String msg = editsend.getText().toString();
                        sendMsg();
                    }
                });
                //new Thread((Runnable) Chat.this).start();






            }
        }.start();


        /*new Thread() {

            public void run() {
                try {
                    socket = new Socket(HOST, PORT);
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                            socket.getOutputStream())), true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();*/

        //为发送按钮设置点击事件
        /*btnsend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String msg = editsend.getText().toString();
                if (socket.isConnected()) {
                    if (!socket.isOutputShutdown()) {
                        out.println(msg);
                        clickUpdateRight(v,msg);
                    }
                }
            }
        });
        new Thread((Runnable) Chat.this).start();*/



    }

    //重写run方法,在该方法中输入流的读取
    //@Override
    public void run() {
        try {
            while (true) {
                if (socket.isConnected()) {
                    if (!socket.isInputShutdown()) {
                        if ((content = in.readLine()) != null) {
                            content += "\n";
                            //handler.sendEmptyMessage(0x123);
                            clickUpdateLeft(this.list_one,content);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(){
        String msg;
        //msg = scan.nextLine();
        EditText editText = (EditText) findViewById(R.id.editsend);
        msg = editText.getText().toString();
        if (socket.isConnected()) {
            if (!socket.isOutputShutdown()) {
                clickUpdateRight(findViewById(R.id.list_one),msg);
                out.println(msg);
            }
        }
    }


    private void bindViews(){
        list_one = (ListView) findViewById(R.id.list_one);
    }

    /*public void clickUpdate(View v){
        mAdapter.add(new Data(R.mipmap.ic_launcher,"新消息"));
    }*/
    public void clickUpdateLeft(View v,String receive){
        mAdapter.add(new Data(R.mipmap.ic_launcher,receive,0),0);
    }

    public void clickUpdateRight(View v,String send){
        mAdapter.add(new Data(R.mipmap.ic_launcher,send,1),1);
    }

}