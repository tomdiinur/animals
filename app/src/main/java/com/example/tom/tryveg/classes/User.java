package com.example.tom.tryveg.classes;

import com.example.tom.tryveg.Globals;
import com.facebook.AccessToken;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tom on 29-Jul-16.
 */
public class User{
    public AccessToken Token;
    public Date StartVeganTime;
    public String Name;
    public int Level;

    private static SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");


    public User(Date startVeganTime, String name, int level) {
        StartVeganTime = startVeganTime;
        Name = name;
        Level = level;
    }

    public User(String startVeganTime, String name, int level) {

        this.Name = name;
        this.Level = level;

        try {
            Date dateObj = curFormater.parse(startVeganTime);
            this.StartVeganTime = dateObj;

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public VegTimeFormat getVeganTimeFormat() {
        long diff = new Date().getTime() - StartVeganTime.getTime();
        Date dateDiff = new Date(diff);

        long daysDiff = java.util.concurrent.TimeUnit.DAYS.convert(diff, java.util.concurrent.TimeUnit.MILLISECONDS);

        if (daysDiff > 365 * 2) {
            return new VegTimeFormat(TimeUnit.Year, (int) (daysDiff / 365));
        }
        else if (daysDiff > 120) {
            return new VegTimeFormat(TimeUnit.Month, (int) (daysDiff / 30));
        }
        else if (daysDiff > 60) {
            return new VegTimeFormat(TimeUnit.Week, (int) (daysDiff / 7));
        }
        else if (daysDiff > 2) {
            return new VegTimeFormat(TimeUnit.Day, (int) daysDiff);
        }

        long minutesDiff = java.util.concurrent.TimeUnit.MINUTES.convert(diff, java.util.concurrent.TimeUnit.MILLISECONDS);

        if (minutesDiff > 60) {
            return new VegTimeFormat(TimeUnit.Hour, (int) (minutesDiff / 60));
        }
        else if (minutesDiff > 5){
            return new VegTimeFormat(TimeUnit.Minuts, (int) (minutesDiff));
        }

        long secDiff = java.util.concurrent.TimeUnit.SECONDS.convert(diff, java.util.concurrent.TimeUnit.MILLISECONDS);

        return new VegTimeFormat(TimeUnit.Seconds, (int) (secDiff));
    }

    public int getDaysVeg() {
        return (int) java.util.concurrent.TimeUnit.DAYS.convert(new Date().getTime() - StartVeganTime.getTime(),
                                                                java.util.concurrent.TimeUnit.MILLISECONDS);
    }

    public String getTimeFormatString() {
        VegTimeFormat format = getVeganTimeFormat();

        if (format.time == 1) {
            if (format.timeUnit.equals(TimeUnit.Seconds)) {
                return "שניה";
            }
            else if (format.timeUnit.equals(TimeUnit.Minuts)) {
                return "דקה";
            }
            else if (format.timeUnit.equals(TimeUnit.Hour)) {
                return "שעה";
            }
            else if (format.timeUnit.equals(TimeUnit.Day)) {
                return "יום";
            }
            else if (format.timeUnit.equals(TimeUnit.Week)) {
                return "שבוע";
            }
            else if (format.timeUnit.equals(TimeUnit.Month)) {
                return "חודש";
            }
            else if (format.timeUnit.equals(TimeUnit.Year)) {
                return "שנה";
            }
        }
        else if (format.time > 1) {
            if (format.timeUnit.equals(TimeUnit.Seconds)) {
                return String.valueOf(format.time) + " שניות";
            }
            else if (format.timeUnit.equals(TimeUnit.Minuts)) {
                return String.valueOf(format.time) + " דקות";
            }
            else if (format.timeUnit.equals(TimeUnit.Hour)) {
                return String.valueOf(format.time) + " שעות";
            }
            else if (format.timeUnit.equals(TimeUnit.Day)) {
                return String.valueOf(format.time) + " ימים";
            }
            else if (format.timeUnit.equals(TimeUnit.Week)) {
                return String.valueOf(format.time) + " שבועות";
            }
            else if (format.timeUnit.equals(TimeUnit.Month)) {
                return String.valueOf(format.time) + " חודשים";
            }
            else if (format.timeUnit.equals(TimeUnit.Year)) {
                return String.valueOf(format.time) + " שנים";
            }
        }

        return "";
    }

    public Pet getPet() {
        int days = getDaysVeg();
        for (int i = 0; i < Globals.pets.size(); i++) {
            if (Globals.pets.get(i).getDays() > days) {
                // Not even ant
                if (i == 0) {
                    return Globals.pets.get(0);
                }
                return Globals.pets.get(i - 1);
            }
        }
        return Globals.pets.get(Globals.pets.size() - 1);
    }

    public int getPetDrawable() {
        return getPet().getDrawable();
    }

    public String getStartVeganString() {
        return curFormater.format(this.StartVeganTime);
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof User))
            return false;
        if (obj == this)
            return true;

        User user = (User)obj;
        if ((user.Name.equals(this.Name)) && (user.getDaysVeg() == this.getDaysVeg())) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
      return 17*31*getDaysVeg();
    }
}
