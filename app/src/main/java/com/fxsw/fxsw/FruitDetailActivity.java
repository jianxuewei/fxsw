package com.fxsw.fxsw;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fxsw.models.Fruit;

public class FruitDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();

    }

    private void initView() {
        Intent intent=getIntent();
        Fruit fruit=(Fruit)intent.getSerializableExtra("fruit");
        TextView tvName= (TextView) findViewById(R.id.tvName);
        ImageView imageView= (ImageView) findViewById(R.id.imageView_fruit);
        TextView tvDesprition= (TextView) findViewById(R.id.tv_description);

        tvName.setText(fruit.getName());
        imageView.setImageResource(fruit.getImgId());
        tvDesprition.setText(fruit.getDescription());
    }

}
