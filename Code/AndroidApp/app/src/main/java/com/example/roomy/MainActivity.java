package com.example.roomy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.roomy.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    int points;
    ArrayList<Task> taskArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();
        points = intent.getIntExtra("userPoints", 0);

        TextView userPointsDisplay = (TextView)findViewById(R.id.textView_points);
        userPointsDisplay.setText("Score: " + points);

        String chore = "Chore: ";
        chore += intent.getStringExtra("name");
        Log.d("list", chore);

        // Sample initial data
        int[] imageIdArray = {R.drawable.chore_03, R.drawable.chore_04, R.drawable.chore_06, R.drawable.chore_07, R.drawable.chore_02,
                R.drawable.chore_08, R.drawable.chore_01};
        String[] nameArray = {"Vacuum", "Sweep garage", "Mow the lawn", "Recycling", "Do laundry",
                "Water the plants", "Clean dishes"}; //Sample list of chores
        String[] detailsArray = {null, null, null, null, "2 baskets",
                "5 plants", null};
        String[] pointsWorthArray = {"400 points", "200 points", "400 points", "100 points", "200 points",
                "100 points", "200 points"}; //Ascribe points to each chore
        String[] timeDueArray = {"Sat 8:00 pm", "Fri 4:00 pm", "Sun 8:00 pm", "Wed 7:00 am", "Fri 1:30 pm",
                "Fri 7:00 am", "Fri 11:00pm"}; //List of due dates for each chore


        taskArrayList = new ArrayList<>();
        ArrayList<Task> sentTaskArrayList = intent.getParcelableArrayListExtra("tasks");
        if (sentTaskArrayList == null) {
            for (int i = 0; i < nameArray.length; i++) {
                Task task = new Task(nameArray[i], detailsArray[i], pointsWorthArray[i], timeDueArray[i], imageIdArray[i]);
                taskArrayList.add(task);
                //String chore = intent.getStringExtra("name");
                //Log.d("list", chore);
                Log.d("list", task.getTaskName());
            }
        } else {
            taskArrayList.addAll(sentTaskArrayList);
        }

        ListAdapter listAdapter = new ListAdapter(MainActivity.this, taskArrayList);

        binding.listview.setAdapter(listAdapter);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, TaskActivity.class);

                i.putExtra("name", taskArrayList.get(position).taskName);
                i.putExtra("details", taskArrayList.get(position).details);
                i.putExtra("pointsWorth", taskArrayList.get(position).pointsWorthMessage);
                i.putExtra("timeDue",taskArrayList.get(position).timeDue);
                i.putExtra("imageid",taskArrayList.get(position).imageId);
                i.putExtra("userPoints", points);
                i.putExtra("tasks", taskArrayList);
                startActivity(i);

            }
        });
    }

    public void toGoNewTaskActivity(View v) {
        Intent i = new Intent(MainActivity.this, NewTaskActivity.class);

        i.putExtra("tasks", taskArrayList);
        startActivity(i);
    }
}
