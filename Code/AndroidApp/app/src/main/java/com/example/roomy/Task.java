package com.example.roomy;

import android.os.Parcel;
import android.os.Parcelable;

public class Task implements Parcelable {

    String taskName, details, pointsWorthMessage, timeDue, phoneNo, country;
    int imageId;

    public Task(String taskName, String details, String pointsWorthMessage, String timeDue, int imageId) {
        this.taskName = taskName;
        this.details = details;
        this.pointsWorthMessage = pointsWorthMessage;
        this.timeDue = timeDue;
        this.imageId = imageId;
    }

    protected Task(Parcel in) {
        taskName = in.readString();
        details = in.readString();
        pointsWorthMessage = in.readString();
        timeDue = in.readString();
        phoneNo = in.readString();
        country = in.readString();
        imageId = in.readInt();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    public String getTaskName() {
        return taskName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(taskName);
        parcel.writeString(details);
        parcel.writeString(pointsWorthMessage);
        parcel.writeString(timeDue);
        parcel.writeString(phoneNo);
        parcel.writeString(country);
        parcel.writeInt(imageId);
    }
}
