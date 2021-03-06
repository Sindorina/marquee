package com.smartpoint.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smartpoint.entity.Contact;
import com.smartpoint.marquee.R;

import java.util.List;

/**
 * Created by gjz on 9/4/16.
 */
public class FindAdapter extends RecyclerView.Adapter<FindAdapter.FindViewHolder> {

    private List<String> contacts;
    private int layoutId;

    public FindAdapter(List<String> contacts, int layoutId) {
        this.contacts = contacts;
        this.layoutId = layoutId;
    }

    public List<String> getContacts() {
        return contacts;
    }

    @Override
    public FindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(layoutId, null);
        return new FindViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FindViewHolder holder, final int position) {
        String result = contacts.get(position);
        holder.textView.setText(result==null?"":result);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick!=null){
                    onItemClick.clickItem(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class FindViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public FindViewHolder(View itemView) {
            super(itemView);
            textView =  itemView.findViewById(R.id.textView);

        }
    }
    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick {
        void clickItem(int position);
    }
}