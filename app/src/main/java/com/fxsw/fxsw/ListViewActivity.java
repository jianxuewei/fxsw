package com.fxsw.fxsw;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
        initList();
//        ListAdapter adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);

        FruitAdapter adapter=new FruitAdapter(this,R.layout.fruit_item,initFruitList());
        listView.setAdapter(adapter);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
//                        try {
//                            Thread.sleep(1000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        list.add("foo");
//                        list.add("bar");
                        Fruit fruit = new Fruit("apple2", R.drawable.apple512);
                        fruitList.add(fruit);
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
                Fruit fruit=fruitList.get(realPosition);
//                Fruit item=getItem(realPosition);
                Toast.makeText(ListViewActivity.this,"this is "+fruit.getName().toString(),Toast.LENGTH_SHORT).show();
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        PullToRefreshExpandableListView expandableListView = (PullToRefreshExpandableListView) findViewById(R.id.expandableLV);

    }

    private void initList() {
        list.add("hello");
        list .add("world");
    }
    public class Fruit{
        private String name;
        private int imgId;
        public String getName() {
            return name;
        }

        public int getImgId() {
            return imgId;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setImgId(int imgId) {
            this.imgId = imgId;
        }

        public Fruit(String name,int imgId){
            this.name=name;
            this.imgId=imgId;
        }
    }
    private String[] names={"apple","apricot","banana","cherry","kiwi","lemon","orange",
            "pear","peach","strawberry","tomato"};
    private int[] imgIds={R.drawable.apple512,R.drawable.apricot512,R.drawable.banana512,R.drawable.cherry512,
            R.drawable.kiwi512,R.drawable.lemon512,R.drawable.lemon512,R.drawable.orange512,R.drawable.pear512,R.drawable.peach512,
            R.drawable.strawberry512,R.drawable.tomato512};
    private List<Fruit> fruitList=new ArrayList<>();
    private List<Fruit> initFruitList(){

        for (int i = 0; i < names.length; i++) {
            Fruit fruit=new Fruit(names[i],imgIds[i]);
            fruitList.add(fruit);
        }
        return fruitList;
    }
    class  FruitAdapter extends ArrayAdapter{
        int resourceId;

        public FruitAdapter(Context context, int resource, List<Fruit> objects) {
            super(context, resource, objects);
            this.resourceId=resource;
        }

        @Override
        public Fruit getItem(int position) {
            return fruitList.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Fruit fruit=(Fruit)getItem(position);
            View v= LayoutInflater.from(getContext()).inflate(resourceId,null);
            ImageView imageView=(ImageView)v.findViewById(R.id.imageview);
            TextView textView=(TextView)v.findViewById(R.id.textview);
            imageView.setImageResource(fruit.getImgId());
            textView.setText(fruit.getName());
            return  v;

        }
    }

}
