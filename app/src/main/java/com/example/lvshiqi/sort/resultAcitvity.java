package com.example.lvshiqi.sort;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;

/**
 * Created by lvshiqi on 16-11-28.
 * Display results
 */
public class resultAcitvity extends Activity {
    TextView mResults;
    private int[] numbers = {58, 3, 11, 1, 71, 2, 4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        mResults = (TextView) findViewById(R.id.results);

        // Get function number
        Bundle bundle = this.getIntent().getExtras();
        int function = bundle.getInt("Function");

        // Get sort result
        SortFactory sortFactory = new SortFactory();
        sortFactory.excuteFunction(function, numbers);

        mResults.setText(Arrays.toString(numbers));
    }
}
