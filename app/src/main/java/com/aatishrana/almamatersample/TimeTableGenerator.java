package com.aatishrana.almamatersample;

import com.aatishrana.almamatersample.pojo.StudentGroup;
import com.aatishrana.almamatersample.pojo.subject.Subject;
import com.aatishrana.almamatersample.pojo.Teacher;

import java.util.Map;
import java.util.Set;

/**
 * Created by Aatish on 10/10/2017.
 */

public class TimeTableGenerator
{

    public void methodOne(Set<Teacher> teachers,
                          Map<Subject, Integer> subjectsWithMinLectures,
                          Set<StudentGroup> classes,
                          int noOfLecturesInADay,
                          int noOfDays)
    {
        int totalLectures = noOfLecturesInADay * noOfDays;

        // if teachers of all subjects are available then move forward
        checkAllTeachersAvailable(teachers, subjectsWithMinLectures);
    }

    public void checkAllTeachersAvailable(Set<Teacher> teachers, Map<Subject, Integer> subjectsWithMinLectures)
    {
        for (Subject subject : subjectsWithMinLectures.keySet())
        {
            boolean found = false;
            for (Teacher teacher : teachers)
            {
                if (teacher.getSubjects().contains(subject))
                {
                    found = true;
                    log(subject.getName() + " Teacher Found");
                    break;
                }
            }
            if (!found)
                throw new RuntimeException("Teachers of " + subject.getName() + " not available");
        }
    }

    private void log(String msg)
    {
        System.out.println(msg);
    }
}
