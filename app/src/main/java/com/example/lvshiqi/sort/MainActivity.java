package com.example.lvshiqi.sort;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvshiqi on 16-11-28.
 * Create recyclerView and choose sort choosen
 */
public class MainActivity extends AppCompatActivity{

    private SortFactory mSortFacory;
    private RecyclerView mRecyclerView;
    private List<String> mHeadings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.choose);

        // Initialize
        initHeadingData();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setAutoMeasureEnabled(true);

        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter recyclerViewAdapter = new recyclerViewAdapter(mHeadings, this);
        recyclerViewAdapter.setOnItemClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, resultAcitvity.class);
                intent.putExtra("Function", position);
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(recyclerViewAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initHeadingData(){
        mHeadings = new ArrayList<>();
        mHeadings.add("Bubble sort");
        mHeadings.add("Select sort");
        mHeadings.add("Quick sort");
        mHeadings.add("Merge sort");
    }


}
