package com.aatishrana.almamatersample;

import com.aatishrana.almamatersample.data.DataSampleOne;
import com.aatishrana.almamatersample.data.DataSampleTwo;
import com.aatishrana.almamatersample.data.MasterDataRepository;
import com.aatishrana.almamatersample.data.MasterDataRepositoryTest;
import com.aatishrana.almamatersample.pojo.ConfigVariables;
import com.aatishrana.almamatersample.pojo.Standard;
import com.aatishrana.almamatersample.pojo.SubjectTeacher;
import com.aatishrana.almamatersample.pojo.Teacher;
import com.aatishrana.almamatersample.pojo.subject.Hindi;
import com.aatishrana.almamatersample.pojo.subject.Math;
import com.aatishrana.almamatersample.pojo.subject.Science;
import com.aatishrana.almamatersample.pojo.subject.Subject;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Aatish on 10/10/2017.
 */
public class TimeTableGeneratorTest
{
    private MasterDataRepository repository;

    @Before
    public void setUp() throws Exception
    {
        repository = new MasterDataRepositoryTest(new DataSampleTwo());
    }


    @Test
    public void checkAllTeachersAvailable() throws Exception
    {
        TimeTableGenerator generator = new TimeTableGenerator(repository);
        generator.checkAllTeachersAvailable(repository.getAllTeachers(), repository.getAllSubjects());
    }

    @Test
    public void calculateTotalLoad() throws Exception
    {
        TimeTableGenerator generator = new TimeTableGenerator(repository);
        Map<Subject, Integer> obj = generator.calculateTotalLoad(repository.getAllSubjects());
        Subject science = repository.getSubjectWithId(102);
        Subject hindi = repository.getSubjectWithId(105);

        System.out.println(obj.toString());
        assertEquals(Integer.valueOf(72), obj.get(science));
        assertEquals(Integer.valueOf(60), obj.get(hindi));
    }

    @Test
    public void loadAssigningCheck()
    {
        Subject math = repository.getSubjectWithId(101);
        Subject science = repository.getSubjectWithId(102);
        Subject socialScience = repository.getSubjectWithId(103);
        Subject english = repository.getSubjectWithId(104);
        Subject hindi = repository.getSubjectWithId(105);

        Map<Subject, Integer> load = new HashMap<>();
        load.put(math, 74);
        load.put(science, 72);
        load.put(socialScience, 72);
        load.put(english, 72);
        load.put(hindi, 60);

        ConfigVariables configVariables = new ConfigVariables();
        configVariables.setDelegateToNonSubjectTeacher(false);

        TimeTableGenerator generator = new TimeTableGenerator(repository);
        generator.loadAssigningCheck(load, configVariables);
    }

    @Test
    public void createTimeTable()
    {
        TimeTableGenerator generator = new TimeTableGenerator(repository);
        List<Standard> allStandards = new ArrayList<>();
        allStandards.addAll(repository.getAllClasses());
        generator.createTimeTable(repository.getAllTeachers(), allStandards, new ConfigVariables());
    }


    @Test
    public void isSubjectNormsFullFilled()
    {
        Subject math = new Math(201);
        Subject science = new Science(202);

        Stack<Integer> stack1 = new Stack<>();
        stack1.push(1);
        stack1.push(1);

        Map<Subject, Stack<Integer>> data1 = new HashMap<>();
        data1.put(math, stack1);

        TimeTableGenerator generator = new TimeTableGenerator(repository);
        assertEquals(false, generator.isSubjectNormsFullFilled(math, data1));
        stack1.pop();
        assertEquals(false, generator.isSubjectNormsFullFilled(science, data1));
        stack1.pop();
        assertEquals(true, generator.isSubjectNormsFullFilled(math, data1));
    }

    @Test
    public void getUnTaughtSubjectsOfTheDay()
    {
        TimeTableGenerator generator = new TimeTableGenerator(repository);
        int day = 4;// monday, tue, wed, thur
        int sectionIndex = 4;//6thA 6thB 7thA 7thB
        int lecturesInADay = 4;

        Subject english = repository.getSubjectWithId(104);
        Subject math = repository.getSubjectWithId(101);
        Subject hindi = repository.getSubjectWithId(105);
        Subject science = repository.getSubjectWithId(102);

        SubjectTeacher[][][] sampleData = new SubjectTeacher[day][lecturesInADay][sectionIndex];

        //6thA
        //mon : math,science,hindi,english
        sampleData[0][0][0] = new SubjectTeacher(math, repository.getTeacherWithId(203));
        sampleData[0][1][0] = new SubjectTeacher(science, repository.getTeacherWithId(201));
        sampleData[0][2][0] = new SubjectTeacher(hindi, repository.getTeacherWithId(212));
        sampleData[0][3][0] = new SubjectTeacher(english, repository.getTeacherWithId(206));

        //tue : science,hindi,math,english
        sampleData[1][0][0] = new SubjectTeacher(science, repository.getTeacherWithId(201));
        sampleData[1][1][0] = new SubjectTeacher(hindi, repository.getTeacherWithId(212));
        sampleData[1][2][0] = new SubjectTeacher(math, repository.getTeacherWithId(203));
        sampleData[1][3][0] = new SubjectTeacher(english, repository.getTeacherWithId(206));

        //wed : math,english
        sampleData[2][0][0] = new SubjectTeacher(math, repository.getTeacherWithId(203));
        sampleData[2][1][0] = new SubjectTeacher(english, repository.getTeacherWithId(206));

        //thu : hindi,math,english,science
        sampleData[3][0][0] = new SubjectTeacher(hindi, repository.getTeacherWithId(212));
        sampleData[3][1][0] = new SubjectTeacher(math, repository.getTeacherWithId(203));
        sampleData[3][2][0] = new SubjectTeacher(english, repository.getTeacherWithId(206));
        sampleData[3][3][0] = new SubjectTeacher(science, repository.getTeacherWithId(201));

        Set<Subject> obj = generator.getUnTaughtSubjectsOfTheDay(sampleData, 2, 0, lecturesInADay);
        assertEquals(3, obj.size());


//        sampleData[day][0][sectionIndex] = new SubjectTeacher(repository.getSubjectWithId(101), repository.getTeacherWithId(201));
//        sampleData[day][1][sectionIndex] = new SubjectTeacher(repository.getSubjectWithId(102), repository.getTeacherWithId(202));
//
//        Set<Subject> obj = generator.getUnTaughtSubjectsOfTheDay(sampleData, day, sectionIndex, lecturesInADay);
//        assertEquals(3, obj.size());
//
//        sampleData[day][2][sectionIndex] = new SubjectTeacher(repository.getSubjectWithId(102), repository.getTeacherWithId(202));
//
//        Set<Subject> obj2 = generator.getUnTaughtSubjectsOfTheDay(sampleData, day, sectionIndex, lecturesInADay);
//        assertEquals(3, obj2.size());
//
//        sampleData[day][2][sectionIndex] = new SubjectTeacher(repository.getSubjectWithId(103), repository.getTeacherWithId(203));
//        sampleData[day][3][sectionIndex] = new SubjectTeacher(repository.getSubjectWithId(104), repository.getTeacherWithId(204));
//        sampleData[day][4][sectionIndex] = new SubjectTeacher(repository.getSubjectWithId(105), repository.getTeacherWithId(205));
//        sampleData[day][5][sectionIndex] = new SubjectTeacher(repository.getSubjectWithId(106), repository.getTeacherWithId(206));
//
//        Set<Subject> obj3 = generator.getUnTaughtSubjectsOfTheDay(sampleData, day, sectionIndex, lecturesInADay);
//        assertEquals(0, obj3.size());
    }
}