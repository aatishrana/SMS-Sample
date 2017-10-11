package com.aatishrana.almamatersample;

import com.aatishrana.almamatersample.pojo.subject.Biology;
import com.aatishrana.almamatersample.pojo.subject.History;
import com.aatishrana.almamatersample.pojo.subject.Math;
import com.aatishrana.almamatersample.pojo.StudentGroup;
import com.aatishrana.almamatersample.pojo.subject.Subject;
import com.aatishrana.almamatersample.pojo.Teacher;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Aatish on 10/10/2017.
 */
public class TimeTableGeneratorTest
{
    Set<Teacher> teachers;
    Set<StudentGroup> classes;
    Map<Subject, Integer> subjectsWithMinLectures;

    @Before
    public void setUp() throws Exception
    {
        classes = new HashSet<>();
        classes.add(new StudentGroup(1, "10th A", 30));
        classes.add(new StudentGroup(2, "10th B", 30));
        classes.add(new StudentGroup(3, "10th C", 30));

        Set<Subject> onlyMathTeacher = new HashSet<>();
        onlyMathTeacher.add(new Math(101));

        Set<Subject> onlyHistoryTeacher = new HashSet<>();
        onlyHistoryTeacher.add(new History(102));

        Set<Subject> onlyBiologyTeacher = new HashSet<>();
        onlyBiologyTeacher.add(new Biology(103));

        Set<Subject> mathAndBioTeacher = new HashSet<>();
        mathAndBioTeacher.add(new Biology(103));
        mathAndBioTeacher.add(new Math(101));

        teachers = new HashSet<>();
        teachers.add(new Teacher(1, "Bhupinder Maam", onlyMathTeacher));
        teachers.add(new Teacher(2, "Manjeet Sir", onlyBiologyTeacher));
        teachers.add(new Teacher(3, "Pradeep Sir", onlyHistoryTeacher));

        subjectsWithMinLectures = new HashMap<>();
        subjectsWithMinLectures.put(new Math(101), 3);
        subjectsWithMinLectures.put(new History(102), 3);
        subjectsWithMinLectures.put(new Biology(103), 3);
    }

    @Test
    public void methodOne() throws Exception
    {
        TimeTableGenerator generator = new TimeTableGenerator();
        generator.checkAllTeachersAvailable(teachers, subjectsWithMinLectures);
    }

}