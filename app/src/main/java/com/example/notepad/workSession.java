package com.example.notepad;

import java.util.Date;

public class workSession {
    // workSession ID/number, date-time, description
    int sessionNumber;
    Date currentTime; //Calendar.getInstance().getTime();
    String sessionDescription;

    public workSession(int sessionNumber, Date currentTime, String sessionDescription) {
        this.sessionNumber = sessionNumber;
        this.currentTime = currentTime;
        this.sessionDescription = sessionDescription;
    }

    public int getSessionNumber() {
        return sessionNumber;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public String getSessionDescription() {
        return sessionDescription;
    }
}
