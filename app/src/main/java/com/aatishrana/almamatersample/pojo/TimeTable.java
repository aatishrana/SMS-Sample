package com.aatishrana.almamatersample.pojo;

import java.util.Set;

/**
 * Created by Aatish on 10/10/2017.
 */

public class TimeTable
{
    Set<Lecture> lectures;

    public TimeTable(Set<Lecture> lectures)
    {
        this.lectures = lectures;
    }

    public Set<Lecture> getLectures()
    {
        return lectures;
    }

    @Override
    public String toString()
    {
        return "TimeTable{" +
                "lectures=" + lectures +
                '}';
    }
}
