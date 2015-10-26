package com.fxsw.fxsw;

import android.app.ExpandableListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpanActivity extends ExpandableListActivity {
    private static final String KEY="key";
//    private int[] images = { R.drawable.one, R.drawable.two, R.drawable.threee };
    private String[] mChildStrings = { "Child One", "Child Two", "Child Three", "Child Four", "Child Five", "Child Six" };

    private String[] mGroupStrings = { "Group One", "Group Two", "Group Three" };
    private List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();
    private List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expan);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        for (String group : mGroupStrings) {
            Map<String, String> groupMap1 = new HashMap<String, String>();
            groupData.add(groupMap1);
            groupMap1.put(KEY, group);

            List<Map<String, String>> childList = new ArrayList<Map<String, String>>();
            for (String string : mChildStrings) {
                Map<String, String> childMap = new HashMap<String, String>();
                childList.add(childMap);
                childMap.put(KEY, string);
            }
            childData.add(childList);
        }
        SimpleExpandableListAdapter expand = new SimpleExpandableListAdapter(
                this, groupData, android.R.layout.simple_expandable_list_item_1, new String[] { KEY },
                new int[] { android.R.id.text1 }, childData, android.R.layout.simple_expandable_list_item_2,
                new String[] { KEY }, new int[] { android.R.id.text1 });
        PullToRefreshExpandableListView listView = (PullToRefreshExpandableListView) findViewById(R.id.expandableLV);
        //listView.setAdapter((ListAdapter) expand);
        setListAdapter(expand);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ExpandableListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ExpandableListView> refreshView) {
                new AsyncTask<Void,Void,Void>(){
                    @Override
                    protected Void doInBackground(Void... params) {
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                    }
                }.execute();
            }
        });
    }

}
