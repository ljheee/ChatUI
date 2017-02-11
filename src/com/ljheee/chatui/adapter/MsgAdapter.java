package com.ljheee.chatui.adapter;

import java.util.List;

import com.ljheee.chatui.R;
import com.ljheee.chatui.bean.Msg;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *消息适配器 
 */
public class MsgAdapter extends ArrayAdapter<Msg> {
	
	private int resourceId;
	LayoutInflater inflater;
	int width = 180;

	/**
	 * 构造方法
	 * @param context	上下文
	 * @param textViewResourceId	视图XML文件
	 * @param objects	数据List
	 */
	public MsgAdapter(Context context,int textViewResourceId,
			List<Msg> objects) {
		super(context,  textViewResourceId, objects);
		resourceId = textViewResourceId;
		inflater = LayoutInflater.from(context);
		WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		width = (int) (wm.getDefaultDisplay().getWidth()*0.7);
	}
	
	/**
	 * 每个子项被滚动到屏幕内的时候，会被调用
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Msg msg = getItem(position);
		
		View view = null;
		ViewHolder viewHolder = null;
		if(convertView == null){//不存在可复用view
			view = inflater.inflate(resourceId, null);
			viewHolder = new ViewHolder();
			viewHolder.leftLayout = (LinearLayout)view.findViewById(R.id.left_layout);
			viewHolder.rightLayout = (LinearLayout)view.findViewById(R.id.right_layout);
			viewHolder.leftMsg = (TextView)view.findViewById(R.id.left_msg);
			viewHolder.rightMsg = (TextView)view.findViewById(R.id.right_msg);
			
			viewHolder.leftMsg.setMaxWidth(width);
			viewHolder.rightMsg.setMaxWidth(width);
			view.setTag(viewHolder);
		}else{
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		
		
		if(msg.getType()==Msg.TYPE_RECEIVED){
			viewHolder.leftLayout.setVisibility(View.VISIBLE);
			viewHolder.rightLayout.setVisibility(View.GONE);
			viewHolder.leftMsg.setText(msg.getContent());
		}else if(msg.getType()==Msg.TYPE_SENT){
			viewHolder.leftLayout.setVisibility(View.GONE);
			viewHolder.rightLayout.setVisibility(View.VISIBLE);
			viewHolder.rightMsg.setText(msg.getContent());
		}
		return view;
	}
	
	/**
	 * ViewHolder
	 *优化模式 
	 */
	class ViewHolder{
		
		LinearLayout leftLayout;
		LinearLayout rightLayout;
		
		TextView leftMsg;
		TextView rightMsg;
	}
	

}
