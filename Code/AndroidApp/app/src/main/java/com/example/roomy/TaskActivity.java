package com.example.roomy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.roomy.databinding.ActivityMainBinding;
import com.example.roomy.databinding.ActivityTaskinfoBinding;

import java.util.ArrayList;

public class TaskActivity extends AppCompatActivity {

    ActivityTaskinfoBinding binding;
    String name, details, pointsWorth, timeDue;
    int imageid, userPoints;
    ArrayList<Task> taskArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTaskinfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();

        if (intent != null){
            //retrieve parameters from intent
            name = intent.getStringExtra("name");
            details = intent.getStringExtra("details");
            pointsWorth = intent.getStringExtra("pointsWorth");
            timeDue = intent.getStringExtra("timeDue");
            imageid = intent.getIntExtra("imageid", R.drawable.chore_00);
            userPoints = intent.getIntExtra("userPoints", 0);
            taskArrayList = intent.getParcelableArrayListExtra("tasks");

            binding.nameProfile.setText(name);
            binding.detailsProfile.setText(details);
            if (binding.detailsProfile.getText().equals("")) binding.detailsProfile.setText("No details");
            binding.phoneProfile.setText(pointsWorth);
            binding.countryProfile.setText(timeDue);
            binding.profileImage.setImageResource(imageid);
        }

    }

    public void markTaskAsFinished(View v) {
        try {
            int pointsWorthRaw = Integer.parseInt(pointsWorth.split(" ")[0]);
            userPoints = userPoints + pointsWorthRaw;
        } catch (Exception e) {
            Log.e("Points", "Cannot convert points string to int, will not add to user total");
        }

        for (int i=0; i<taskArrayList.size(); i++) {
            if (name.equals(taskArrayList.get(i).taskName)) taskArrayList.remove(i);
        }

        //Add intents to i to feed into startActivity function
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("name", name);
        i.putExtra("details", details);
        i.putExtra("pointsWorth", pointsWorth);
        i.putExtra("timeDue", timeDue);
        i.putExtra("imageid", imageid);
        i.putExtra("userPoints", userPoints);
        i.putExtra("tasks", taskArrayList);
        startActivity(i);
    }
}
