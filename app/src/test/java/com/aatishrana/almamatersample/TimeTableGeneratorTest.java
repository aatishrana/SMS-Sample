package com.aatishrana.almamatersample;

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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        repository = new MasterDataRepositoryTest();
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
        Collections.sort(
                allStandards, new Comparator<Standard>()
                {
                    @Override
                    public int compare(Standard o1, Standard o2)
                    {
                        if (o1.getId() > o2.getId())
                            return -1;
                        else if (o1.getId() < o2.getId())
                            return 1;
                        return 0;
                    }
                });
        generator.createTimeTable(repository.getAllTeachers(), allStandards, new ConfigVariables());
    }

    @Test
    public void createTimeTable2()
    {
        TimeTableGenerator generator = new TimeTableGenerator(repository);

        Subject math = new Math(201);
        Subject science = new Science(202);
        Subject hindi = new Hindi(203);

        Set<Teacher> allTeachers = new HashSet<>();
        allTeachers.add(new Teacher(101, "Teacher 101", math));
        allTeachers.add(new Teacher(102, "Teacher 102", science));
        allTeachers.add(new Teacher(103, "Teacher 103", hindi));

        List<Standard> allStandards = new ArrayList<>();
        allStandards.add(new Standard(301, "10th A", 10, 'A', 35));
        allStandards.add(new Standard(302, "10th B", 10, 'B', 35));

        ConfigVariables config = new ConfigVariables();
        config.setNoOfWorkWeek(2);
        config.setNoOfLecturesInADay(3);

        generator.createTimeTable(allTeachers, allStandards, config);
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
        sampleData[0][0][0]=new SubjectTeacher(math,repository.getTeacherWithId(203));
        sampleData[0][1][0]=new SubjectTeacher(science,repository.getTeacherWithId(201));
        sampleData[0][2][0]=new SubjectTeacher(hindi,repository.getTeacherWithId(212));
        sampleData[0][3][0]=new SubjectTeacher(english,repository.getTeacherWithId(206));

        //tue : science,hindi,math,english
        sampleData[1][0][0]=new SubjectTeacher(science,repository.getTeacherWithId(201));
        sampleData[1][1][0]=new SubjectTeacher(hindi,repository.getTeacherWithId(212));
        sampleData[1][2][0]=new SubjectTeacher(math,repository.getTeacherWithId(203));
        sampleData[1][3][0]=new SubjectTeacher(english,repository.getTeacherWithId(206));

        //wed : math,english
        sampleData[2][0][0]=new SubjectTeacher(math,repository.getTeacherWithId(203));
        sampleData[2][1][0]=new SubjectTeacher(english,repository.getTeacherWithId(206));

        //thu : hindi,math,english,science
        sampleData[3][0][0]=new SubjectTeacher(hindi,repository.getTeacherWithId(212));
        sampleData[3][1][0]=new SubjectTeacher(math,repository.getTeacherWithId(203));
        sampleData[3][2][0]=new SubjectTeacher(english,repository.getTeacherWithId(206));
        sampleData[3][3][0]=new SubjectTeacher(science,repository.getTeacherWithId(201));

        Set<Subject> obj = generator.getUnTaughtSubjectsOfTheDay(sampleData, 2, 0, lecturesInADay);
        assertEquals(3,obj.size());


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