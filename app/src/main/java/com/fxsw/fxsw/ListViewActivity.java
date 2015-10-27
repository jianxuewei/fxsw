package com.fxsw.fxsw;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.fxsw.models.Info;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {
private List<String> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        final PullToRefreshListView listView= (PullToRefreshListView) findViewById(R.id.pull_to_refresh_listview);

//        ListAdapter adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);

        InfoAdapter adapter=new InfoAdapter(this,R.layout.fruit_item,initFruitList());
        listView.setAdapter(adapter);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {

                        Info info = new Info("apple2", R.drawable.apple512);
                        infoList.add(info);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        listView.onRefreshComplete();
                        super.onPostExecute(aVoid);
                    }
                }.execute();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(id==-1){
                    return;
                }
                int realPosition= (int) id;
                Info info = infoList.get(realPosition);
                info.setDescription("This is "+ info.getName()+",it is good for your health.\n Please eat it everyday!\n Enjoy it !");
                Intent intent=new Intent(ListViewActivity.this,FruitDetailActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("info", info);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        PullToRefreshExpandableListView expandableListView = (PullToRefreshExpandableListView) findViewById(R.id.expandableLV);

    }

    private void showPopupWindow(View view) {
        View contentView=LayoutInflater.from(ListViewActivity.this).inflate(R.layout.pop_window,null);
        Button btnShowPurchase= (Button) contentView.findViewById(R.id.button_purchase);
        TextView textView= (TextView) contentView.findViewById(R.id.tv);

        textView.setText(((TextView)view.findViewById(R.id.textview)).getText());
        btnShowPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"replace with your own action",Snackbar.LENGTH_SHORT).setAction("Action",null).show();

            }
        });
        final PopupWindow popupWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return false;
//            }

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.i("mengdd", "onTouch : ");

                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.alert_dark_frame));

        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);

    }

    private String[] names={"apple","apricot","banana","cherry","kiwi","lemon","orange",
            "pear","peach","tomato","strawberry"};
    private int[] imgIds={R.drawable.apple512,R.drawable.apricot512,R.drawable.banana512,R.drawable.cherry512,
            R.drawable.kiwi512,R.drawable.lemon512,R.drawable.orange512,R.drawable.pear512,R.drawable.peach512,
            R.drawable.tomato512,R.drawable.strawberry512};
    private List<Info> infoList =new ArrayList<>();
    private List<Info> initFruitList(){

        for (int i = 0; i < names.length; i++) {
            Info info =new Info(names[i],imgIds[i]);
            infoList.add(info);
        }
        return infoList;
    }
    class InfoAdapter extends ArrayAdapter<Info>{
        int resourceId;
        List<Info> list=new ArrayList<>();

        public InfoAdapter(Context context, int resource, List<Info> objects) {
            super(context, resource, objects);
            this.resourceId=resource;
            this.list=objects;

        }

        @Override
        public Info getItem(int position) {
            return list.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Info info =getItem(position);
            View v= LayoutInflater.from(getContext()).inflate(resourceId,null);
            ImageView imageView=(ImageView)v.findViewById(R.id.imageview);
            TextView textView=(TextView)v.findViewById(R.id.textview);
            imageView.setImageResource(info.getImgId());
            textView.setText(info.getName());
            return  v;

        }
    }

}
