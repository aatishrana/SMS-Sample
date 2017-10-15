package com.aatishrana.almamatersample.pojo;

import java.util.List;

/**
 * Created by Aatish on 10/10/2017.
 */
// section
public class StudentGroup
{
    private final int id;
    private final String name;
    private final int noOfStudents;
    private final int standard; // classes
    private final List<Norms> normsToFollow;

    public StudentGroup(int id, String name, int noOfStudents, int standard, List<Norms> normsToFollow)
    {
        this.id = id;
        this.name = name;
        this.noOfStudents = noOfStudents;
        this.standard = standard;
        this.normsToFollow = normsToFollow;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public int getNoOfStudents()
    {
        return noOfStudents;
    }

    public int getStandard()
    {
        return standard;
    }

    public List<Norms> getNormsToFollow()
    {
        return normsToFollow;
    }

    @Override
    public String toString()
    {
        return "StudentGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", noOfStudents=" + noOfStudents +
                ", standard=" + standard +
                ", normsToFollow=" + normsToFollow +
                '}';
    }
}
