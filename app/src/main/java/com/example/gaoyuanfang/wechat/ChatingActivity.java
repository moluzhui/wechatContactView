package com.example.gaoyuanfang.wechat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class ChatingActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EditText inputText;
    private List<Msg> msgList = new ArrayList<Msg>();
    private Button send;
    private MsgAdapter msgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chating);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        setTitle(name);
        initMsgs(); // 鍒濆鍖栨秷鎭暟鎹?
//        initView();

        inputText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.btn_send);
        recyclerView = (RecyclerView) findViewById(R.id.msg_recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        msgAdapter = new MsgAdapter(msgList);
        recyclerView.setAdapter(msgAdapter);
        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if (!"".equals(content)) {
                    Msg msg = new Msg(Msg.TYPE_SENT, content);
                    msgList.add(msg);
                    // 褰撴湁鏂版秷鎭椂锛屽埛鏂癓istView涓殑鏄剧ず
                    msgAdapter.notifyItemInserted(msgList.size() - 1);
                    // 灏哃istView瀹氫綅鍒版渶鍚庝竴琛?
                    recyclerView.scrollToPosition(msgList.size() - 1);
                    // 娓呯┖杈撳叆妗嗕腑鐨勫唴瀹?
                    inputText.setText("");
                }
            }
        });
    }

    private void initMsgs() {
        Msg msg1 = new Msg(Msg.TYPE_RECEIVED, "Hello guy.");
        msgList.add(msg1);
        Msg msg2 = new Msg(Msg.TYPE_SENT, "Hello. Who is that?");
        msgList.add(msg2);
        Msg msg3 = new Msg(Msg.TYPE_RECEIVED, "This is Tom. Nice talking to you. ");
        msgList.add(msg3);


    }
}

