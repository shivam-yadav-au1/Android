package com.example.shivam.listview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.shivam.listview.dataobject.PersonDetails;

import org.w3c.dom.Text;

import java.util.List;

public class PersonAdapter extends ArrayAdapter<PersonDetails> {

    private Context context;
    private List<PersonDetails> personList;

    public PersonAdapter(@NonNull Context context, List<PersonDetails> personList ) {
        super(context, 0,personList);
        this.context = context;
        personList =personList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null){
            listItem = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        }

        PersonDetails personDetails = personList.get(position);
        TextView nameTextView = (TextView)listItem.findViewById(R.id.name);
        nameTextView.setText(personDetails.getName());

        TextView locationTextView = (TextView)listItem.findViewById(R.id.location);
        locationTextView.setText(personDetails.getLocation());

        return listItem;
    }
}
