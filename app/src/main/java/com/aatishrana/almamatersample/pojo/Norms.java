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

    public Norms(int id, int standard, Subject subject, int noOfLectures)
    {
        this.id = id;
        this.standard = standard;
        this.subject = subject;
        this.noOfLectures = noOfLectures;
    }

    public int getId()
    {
        return id;
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

    @Override
    public String toString()
    {
        return "Norms{" +
                "standard=" + standard +
                ", subject=" + subject +
                ", noOfLectures=" + noOfLectures +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Norms norms = (Norms) o;

        if (id != norms.id) return false;
        if (standard != norms.standard) return false;
        if (noOfLectures != norms.noOfLectures) return false;
        return subject != null ? subject.equals(norms.subject) : norms.subject == null;

    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + standard;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + noOfLectures;
        return result;
    }
}
