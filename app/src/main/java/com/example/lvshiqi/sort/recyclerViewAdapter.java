package com.example.lvshiqi.sort;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lvshiqi on 16-11-29.
 * RecyclerView adapter and Viewholder
 */

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.recyclerViewHolder>{
    private List<String> mheadings;
    private Context mContext;
    private LayoutInflater inflater;
    private MyItemClickListener mItemClickListener;

    public recyclerViewAdapter(List<String> headings, Context context){
        mContext = context;
        mheadings = headings;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public recyclerViewAdapter.recyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.heading_item, parent, false);
        recyclerViewHolder holder = new recyclerViewHolder(view, mItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(final recyclerViewAdapter.recyclerViewHolder holder, int position) {
        holder.textView.setText(mheadings.get(position));
    }

    @Override
    public int getItemCount() {
        return mheadings.size();
    }

    public void setOnItemClickListener(MyItemClickListener listener){
        mItemClickListener = listener;
    }

    /**
     * View holder
     */
    class recyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textView;
        private MyItemClickListener mOnItemClickListner;

        public recyclerViewHolder(View itemView, MyItemClickListener listener) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.heading);
            mOnItemClickListner = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mOnItemClickListner!=null){
                mOnItemClickListner.onItemClick(view, getPosition());
            }
        }
    }
}