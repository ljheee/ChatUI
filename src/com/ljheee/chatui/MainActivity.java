package com.ljheee.chatui;

import java.util.ArrayList;
import java.util.List;

import com.ljheee.chatui.adapter.MsgAdapter;
import com.ljheee.chatui.bean.Msg;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	
	
	private ListView msgListView;
	private MsgAdapter adapter;
	private List<Msg>  msgList = new ArrayList<Msg>();
	
	private EditText inputText;
	private Button send;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        initMsg();
        msgListView = (ListView) findViewById(R.id.msg_list_view);
        inputText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);
        
        adapter = new MsgAdapter(this, R.layout.msg_item, msgList);
        msgListView.setAdapter(adapter);
        
        send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String str = inputText.getText().toString();
				if(!"".equals(str)){
					Msg msg = new Msg(str, Msg.TYPE_SENT);
					msgList.add(msg);
					adapter.notifyDataSetChanged();
					msgListView.setSelection(msgList.size());
					inputText.setText("");
				}
			}
		});
    }

	private void initMsg() {
		msgList.add(new Msg("aaa333333333333",Msg.TYPE_RECEIVED));
		msgList.add(new Msg("bbb33355555555",Msg.TYPE_SENT));
		msgList.add(new Msg("ccc",Msg.TYPE_RECEIVED));
		msgList.add(new Msg("ddd",Msg.TYPE_SENT));
	}
    
	/**
     * 创建选项菜单
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    /**
     * 选项菜单-选项监听
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
    	switch (item.getItemId()) {
		case R.id.action_settings:
			Toast.makeText(MainActivity.this , "action_settings", Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_exit:
			finish();
			break;

		default:
			break;
		}
    	return super.onOptionsItemSelected(item);
    }
 
    
    
}
