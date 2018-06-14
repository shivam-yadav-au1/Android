package com.example.shivam.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shivam.recyclerview.dataobject.CustomPojo;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<CustomPojo> customPojos = new ArrayList<CustomPojo>();
    private  LayoutInflater inflater;
    private ArrayList<CustomPojo> customPojosList;
    View view;
    MyViewHolder holder;
    private Context context;
    TextView tv_name,tv_content;
    public CustomAdapter(Context context){
            this.context = context;


    }

    public CustomAdapter(ArrayList<CustomPojo> dataList,Context context){
        this.customPojosList = dataList;
        this.context=context;
        inflater = LayoutInflater.from(context);
        Log.e(TAG, "CustomAdapter: "+customPojosList.size() );
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e(TAG, "onCreateViewHolder: call" );

        view = inflater.inflate(R.layout.custom_row,parent,false);

        holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {
        Log.e(TAG, "onBindViewHolder: call" );

        CustomPojo customPojo = customPojosList.get(position);
        holder.user_name.setText(customPojo.getName());
        holder.time.setText(customPojo.getTime());
        holder.content.setText(customPojo.getContent());
    }


    @Override
    public int getItemCount() {

        return customPojosList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView user_name,content,time;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            user_name=(TextView)itemView.findViewById(R.id.user_name);
            content=(TextView)itemView.findViewById(R.id.content);
            time=(TextView)itemView.findViewById(R.id.time);
        }
        @Override
        public void onClick(View v) {

        }
    }
}
