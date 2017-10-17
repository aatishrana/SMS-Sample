package com.aatishrana.almamatersample;

import com.aatishrana.almamatersample.data.MasterDataRepository;
import com.aatishrana.almamatersample.data.MasterDataRepositoryTest;
import com.aatishrana.almamatersample.pojo.ConfigVariables;
import com.aatishrana.almamatersample.pojo.subject.Subject;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Aatish on 10/10/2017.
 */
public class TimeTableGeneratorTest
{
    private MasterDataRepository repository;
    private Map<Subject, Integer> load;

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

        load = new HashMap<>();
        load.put(math, 74);
        load.put(science, 72);
        load.put(socialScience, 72);
        load.put(english, 74);
        load.put(hindi, 60);

        ConfigVariables configVariables = new ConfigVariables();
        configVariables.setDelegateToNonSubjectTeacher(false);

        TimeTableGenerator generator = new TimeTableGenerator(repository);
        generator.loadAssigningCheck(load, configVariables);
    }

}