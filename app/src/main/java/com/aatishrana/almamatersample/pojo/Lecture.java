package com.aatishrana.almamatersample.pojo;

import com.aatishrana.almamatersample.pojo.subject.Subject;

/**
 * Created by Aatish on 10/10/2017.
 */

public class Lecture
{
    private final int id;
    private final Teacher teacher;
    private final Subject subject;

    public Lecture(int id, Teacher teacher, Subject subject)
    {
        this.id = id;
        this.teacher = teacher;
        this.subject = subject;
    }

    public int getId()
    {
        return id;
    }

    public Teacher getTeacher()
    {
        return teacher;
    }

    public Subject getSubject()
    {
        return subject;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lecture lecture = (Lecture) o;

        return id == lecture.id;

    }

    @Override
    public int hashCode()
    {
        return id;
    }

    @Override
    public String toString()
    {
        return "Lecture{" +
                "id=" + id +
                ", teacher=" + teacher +
                ", subject=" + subject +
                '}';
    }
}
