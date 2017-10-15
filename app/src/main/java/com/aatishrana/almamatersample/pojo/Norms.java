package com.aatishrana.almamatersample.pojo;

import com.aatishrana.almamatersample.pojo.subject.Subject;

/**
 * Created by Aatish on 10/15/2017.
 * <p>
 * Norms describe the number of lectures of a particular subject
 * which must be present in the time table for specified number
 * of days.
 * Eg:- Maths 8 lectures per week
 */
public class Norms
{
    private int id;
    private int standard;
    private Subject subject;
    private int noOfLectures;
    private int perDays;

    public Norms(int id, int standard, Subject subject, int noOfLectures, int perDays)
    {
        this.id = id;
        this.standard = standard;
        this.subject = subject;
        this.noOfLectures = noOfLectures;
        this.perDays = perDays;
    }

    public int getStandard()
    {
        return standard;
    }

    public Subject getSubject()
    {
        return subject;
    }

    public int getNoOfLectures()
    {
        return noOfLectures;
    }

    public int getPerDays()
    {
        return perDays;
    }
}
