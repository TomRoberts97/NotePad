package com.example.notepad;

import android.os.Parcel;
import android.os.Parcelable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkSession implements Parcelable {
    // workSession ID/number, date-time, description
    int sessionNumber;
    //Date currentTime; //Calendar.getInstance().getTime();
    String currentDate;
    String sessionDescription;

    public WorkSession(int sessionNumber, String currentDate, String sessionDescription) {
        this.sessionNumber = sessionNumber;
        this.currentDate = currentDate;
        this.sessionDescription = sessionDescription;
    }

    public int getSessionNumber() {
        return sessionNumber;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public String getSessionDescription() {
        return sessionDescription;
    }



    //parcel part
    public WorkSession(Parcel in){
        String[] data= new String[3];

        in.readStringArray(data);
        this.sessionNumber= Integer.parseInt(data[0]);
        /*Date date = this.currentTime;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String str Date = dateFormat.format(date);
        strDate = data[1];*/
        this.currentDate= data[1]; //the way its meant to be , not sure on correct conversion to String
        this.sessionDescription= data[2];
    }
    @Override
    public int describeContents() {
// TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
// TODO Auto-generated method stub

        dest.writeStringArray(new String[]{String.valueOf(this.sessionNumber),this.currentDate,this.sessionDescription});
    }

    public static final Parcelable.Creator<WorkSession> CREATOR= new Parcelable.Creator<WorkSession>() {

        @Override
        public WorkSession createFromParcel(Parcel source) {
// TODO Auto-generated method stub
            return new WorkSession(source);  //using parcelable constructor
        }

        @Override
        public WorkSession[] newArray(int size) {
// TODO Auto-generated method stub
            return new WorkSession[size];
        }
    };


}
