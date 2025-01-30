package com.example.roomy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Task> {


    public ListAdapter(Context context, ArrayList<Task> taskArrayList){

        super(context,R.layout.list_item, taskArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Task task = getItem(position);

        if (convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);

        }

        //Initialize components on GUI
        ImageView imageView = convertView.findViewById(R.id.task_pic);
        TextView userName = convertView.findViewById(R.id.taskName);
        TextView details = convertView.findViewById(R.id.detailsMessage);
        TextView pointsWorthMessage = convertView.findViewById(R.id.pointsMessage);
        TextView timeDue = convertView.findViewById(R.id.msgtime);

        imageView.setImageResource(Integer.valueOf(task.imageId));
        userName.setText(task.taskName);
        details.setText(task.details);
        pointsWorthMessage.setText(task.pointsWorthMessage);
        timeDue.setText(task.timeDue);

        return convertView;
    }
}
