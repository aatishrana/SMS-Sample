package com.aatishrana.almamatersample.pojo;

/**
 * Created by Aatish on 10/10/2017.
 */

public class StudentGroup
{
    private final int id;
    private final String name;
    private final int noOfStudents;

    public StudentGroup(int id, String name, int noOfStudents)
    {
        this.id = id;
        this.name = name;
        this.noOfStudents = noOfStudents;
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

    @Override
    public String toString()
    {
        return "StudentGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", noOfStudents=" + noOfStudents +
                '}';
    }
}
