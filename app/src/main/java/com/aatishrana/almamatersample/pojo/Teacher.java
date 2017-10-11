package com.aatishrana.almamatersample.pojo;

import com.aatishrana.almamatersample.pojo.subject.Subject;

import java.util.Set;

/**
 * Created by Aatish on 10/10/2017.
 */

public class Teacher
{
    private final int id;
    private final String name;
    private final Set<Subject> subjects;

    public Teacher(int id, String name, Set<Subject> subjects)
    {
        this.id = id;
        this.name = name;
        this.subjects = subjects;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public Set<Subject> getSubjects()
    {
        return subjects;
    }

    @Override
    public String toString()
    {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subjects=" + subjects +
                '}';
    }
}
