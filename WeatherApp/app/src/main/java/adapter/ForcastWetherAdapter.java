package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shivam.weatherapp.R;

import java.util.ArrayList;

import dataobject.ForcastWether;

import static android.content.ContentValues.TAG;

public class ForcastWetherAdapter extends RecyclerView.Adapter<ForcastWetherAdapter.MyViewHolder>{


    private ArrayList<ForcastWether> forcastWetherList;
    private LayoutInflater inflater;
    private Context context;
    private View view,line;
    private MyViewHolder holder;
    TextView day,forcastTemp,phrase;
    ImageView cloudIcon;


    public ForcastWetherAdapter(ArrayList<ForcastWether> forcastWetherList,Context context){
        this.forcastWetherList = forcastWetherList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        view = inflater.inflate(R.layout.forcast_item,parent,false);
        holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ForcastWetherAdapter.MyViewHolder holder, int position) {

        ForcastWether forcastWether = forcastWetherList.get(position);
        holder.day.setText(forcastWether.getDay());
        holder.forcastTemp.setText(forcastWether.getTemperature());
        holder.phrase.setText(forcastWether.getPhrase());



    }

    @Override
    public int getItemCount() {
        Log.e(TAG, "getItemCount: Size of List "+forcastWetherList.size() );
        return forcastWetherList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView day,forcastTemp,phrase;
        ImageView cloudIcon;
        private View line;

        public MyViewHolder(View itemView) {
            super(itemView);

            day = (TextView)itemView.findViewById(R.id.day);
            forcastTemp = (TextView)itemView.findViewById(R.id.forcastTemp);
            phrase = (TextView)itemView.findViewById(R.id.phrase);
            cloudIcon = (ImageView)itemView.findViewById(R.id.cloudIcon);
            line = (View)itemView.findViewById(R.id.line);
        }
    }
}
