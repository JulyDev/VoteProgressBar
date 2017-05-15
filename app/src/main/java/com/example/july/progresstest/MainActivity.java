package com.example.july.progresstest;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<VoteItem> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        initData();
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(new MyAdapter());
    }

    private void initData() {
        VoteItem item = new VoteItem();
        item.title = "xuan wo xuan wo xuan wo";
        item.result = 60;
        list.add(item);
        VoteItem item2 = new VoteItem();
        item2.title = "here here here";
        item2.result = 40;
        list.add(item2);
    }

    private class MyAdapter extends BaseAdapter {

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
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(getApplication()).inflate(R.layout.layout_listview_item, null);
            VoteItem item = (VoteItem) getItem(position);
            TextView titleView = (TextView) convertView.findViewById(R.id.text);
            titleView.setText(item.title);
            ProgressBar pb = (ProgressBar) convertView.findViewById(R.id.progress);
            pb.setProgress(item.result);
            startAnimator(pb, 0, item.result);
            if (position == 0) {
                // 改变进度条的颜色
                pb.setProgressDrawable(MainActivity.this.getResources().getDrawable(R.drawable.progressbar));
            }
            return convertView;
        }
    }

    /**
     * 属性动画
     * @param pb 执行属性动画的View
     * @param min
     * @param max
     */
    private void startAnimator(ProgressBar pb, int min, int max) {

        //progress是ProgressBar的属性，即在ProgressBar的progress属性上加上动画效果
        ObjectAnimator animator = ObjectAnimator.ofInt(pb, "progress", min, max);
        animator.setDuration(1000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
