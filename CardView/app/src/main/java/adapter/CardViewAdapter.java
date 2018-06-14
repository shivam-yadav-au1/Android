package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shivam.cardview.R;

import java.util.ArrayList;

import model.Person;

public class CardViewAdapter extends Adapter<CardViewAdapter.MyViewHolder> {

    private ArrayList<Person> personList ;
    private LayoutInflater inflater;
    private Context context;
    private View view;
    private MyViewHolder holder;

    public CardViewAdapter(ArrayList<Person> dataList,Context context){

        this.personList = dataList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.cardview,parent,false);
        holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Person person = personList.get(position);
        holder.user_name.setText(person.getName());
        holder.content.setText(person.getContent());
        holder.time.setText(person.getTime());

    }



    @Override
    public int getItemCount() {
        return personList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView user_name,content,time;
        public MyViewHolder(View itemView) {
            super(itemView);



            user_name=(TextView)itemView.findViewById(R.id.user_name);
            content=(TextView)itemView.findViewById(R.id.content);
            time=(TextView)itemView.findViewById(R.id.time);
        }
    }
}
