package com.aatishrana.almamatersample;

import com.aatishrana.almamatersample.pojo.ConfigVariables;
import com.aatishrana.almamatersample.pojo.Norms;
import com.aatishrana.almamatersample.pojo.StudentGroup;
import com.aatishrana.almamatersample.pojo.subject.Subject;
import com.aatishrana.almamatersample.pojo.Teacher;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Aatish on 10/10/2017.
 */

public class TimeTableGenerator
{

    public void makeYearlyTimeTable(Set<Teacher> teachers, Set<StudentGroup> allSections, ConfigVariables configVariables)
    {
        //check if all teachers of all subjects are available
        checkAllTeachersAvailable(teachers, extractSubjectsFromAllGivenSections(allSections));

        //calculate total no. of load of a subject for all student groups from adding norms

        //depending on no. of teachers available for a particular subject and amount of load of
        //that subject
        // 1)check if their is a shortage of teacher
        // 2)start assigning subject to student group keeping teachers available in mind
    }

    /**
     * Currently it is assumed that the teachers who teach maths
     * can teach maths for all provided student group
     */
    public void checkAllTeachersAvailable(Set<Teacher> teachers, Set<Subject> subjects)
    {
        for (Subject subject : subjects)
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

    public Set<Subject> extractSubjectsFromAllGivenSections(Set<StudentGroup> allSections)
    {
        Set<Subject> allSubject = new HashSet<>();
        for (StudentGroup section : allSections)
            //todo assuming subject are taught be same qualified teacher
            for (Norms norms : section.getNormsToFollow())
                if (!allSections.contains(norms.getSubject()))
                    allSubject.add(norms.getSubject());
        return allSubject;
    }

    private void log(String msg)
    {
        System.out.println(msg);
    }

//    public void methodOne(Set<Teacher> teachers,
//                          Map<Subject, Integer> subjectsWithMinLectures,
//                          Set<StudentGroup> classes,
//                          int noOfLecturesInADay,
//                          int noOfDays)
//    {
//        int totalLectures = noOfLecturesInADay * noOfDays;
//
//        // if teachers of all subjects are available then move forward
//        checkAllTeachersAvailable(teachers, subjectsWithMinLectures);
//    }
//    public void checkAllTeachersAvailable(Set<Teacher> teachers, Map<Subject, Integer> subjectsWithMinLectures)
//    {
//        for (Subject subject : subjectsWithMinLectures.keySet())
//        {
//            boolean found = false;
//            for (Teacher teacher : teachers)
//            {
//                if (teacher.getSubjects().contains(subject))
//                {
//                    found = true;
//                    log(subject.getName() + " Teacher Found");
//                    break;
//                }
//            }
//            if (!found)
//                throw new RuntimeException("Teachers of " + subject.getName() + " not available");
//        }
//    }
}
