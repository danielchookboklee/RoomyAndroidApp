package com.example.roomy;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NewTaskActivity extends AppCompatActivity {

    ArrayList<Task> taskArrayList;
    EditText editTextTaskName, editTextDetails, editTextPointsWorth, editTextTimeDueBy;
    ImageButton button01, button02, button03, button04, button05, button06, button07, button08, button09, button00;
    NumberPicker pointsWorthNumberPicker;
    int selectedImageId = R.drawable.chore_00;
    ArrayList<ImageButton> buttonList = new ArrayList<ImageButton>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtask);

        editTextTaskName = (EditText)findViewById(R.id.newtask_edittext_name);
        editTextDetails = (EditText)findViewById(R.id.newtask_edittext_details);
        //editTextPointsWorth = (EditText)findViewById(R.id.newtask_edittext_pointsworth);
        editTextTimeDueBy = (EditText)findViewById(R.id.newtask_edittext_timedueby);

        //initialize buttons on gui
        button01 = (ImageButton)findViewById(R.id.buttonImageSelect01);
        button02 = (ImageButton)findViewById(R.id.buttonImageSelect02);
        button03 = (ImageButton)findViewById(R.id.buttonImageSelect03);
        button04 = (ImageButton)findViewById(R.id.buttonImageSelect04);
        button05 = (ImageButton)findViewById(R.id.buttonImageSelect05);
        button06 = (ImageButton)findViewById(R.id.buttonImageSelect06);
        button07 = (ImageButton)findViewById(R.id.buttonImageSelect07);
        button08 = (ImageButton)findViewById(R.id.buttonImageSelect08);
        button09 = (ImageButton)findViewById(R.id.buttonImageSelect09);
        button00 = (ImageButton)findViewById(R.id.buttonImageSelect00);
        buttonList.add(button01);
        buttonList.add(button02);
        buttonList.add(button03);
        buttonList.add(button04);
        buttonList.add(button05);
        buttonList.add(button06);
        buttonList.add(button07);
        buttonList.add(button08);
        buttonList.add(button09);
        buttonList.add(button00);

        pointsWorthNumberPicker = (NumberPicker)findViewById(R.id.numberpicker_pointsworth);
        int minValue = 0;
        int maxValue = 20;
        int step = 100;
        String[] numberValues = new String[maxValue - minValue + 1];
        for (int i = 0; i <= maxValue - minValue; i++) {
            numberValues[i] = String.valueOf((minValue + i) * step);
        }
        pointsWorthNumberPicker.setMinValue(minValue);
        pointsWorthNumberPicker.setMaxValue(maxValue);
        pointsWorthNumberPicker.setWrapSelectorWheel(false);
        pointsWorthNumberPicker.setDisplayedValues(numberValues);
        pointsWorthNumberPicker.setValue(1);

        Intent intent = this.getIntent();
        taskArrayList = intent.getParcelableArrayListExtra("tasks");
    }

    public void selectImageButton(View v) {
        ImageButton b = (ImageButton)v;
        b.getBackground().setTint(getResources().getColor(R.color.selected_green));
        for (int i=0; i<buttonList.size(); i++) {
            if (b != buttonList.get(i)) buttonList.get(i).getBackground().setTintList(ColorStateList.valueOf(getResources().getColor(R.color.underline)));
        }
        //link button to selectedImageId
        if (b == button01) selectedImageId = R.drawable.chore_01;
        else if (b == button02) selectedImageId = R.drawable.chore_02;
        else if (b == button03) selectedImageId = R.drawable.chore_03;
        else if (b == button04) selectedImageId = R.drawable.chore_04;
        else if (b == button05) selectedImageId = R.drawable.chore_05;
        else if (b == button06) selectedImageId = R.drawable.chore_06;
        else if (b == button07) selectedImageId = R.drawable.chore_07;
        else if (b == button08) selectedImageId = R.drawable.chore_08;
        else if (b == button09) selectedImageId = R.drawable.chore_09;
        else if (b == button00) selectedImageId = R.drawable.chore_00;
    }

    public void addTaskAndReturnToMain(View v) {
        Task task = new Task(editTextTaskName.getText().toString(),
        //        editTextDetails.getText().toString(), editTextPointsWorth.getText().toString(),
                editTextDetails.getText().toString(), String.valueOf(pointsWorthNumberPicker.getValue() * 100) + " points",
                editTextTimeDueBy.getText().toString(), selectedImageId);
        taskArrayList.add(task);

        Log.d("list", "Added new task: " + task.getTaskName());

        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("tasks", taskArrayList);
        startActivity(i);
    }
}
