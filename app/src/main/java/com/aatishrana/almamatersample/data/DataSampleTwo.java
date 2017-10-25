package com.aatishrana.almamatersample.data;

import com.aatishrana.almamatersample.pojo.Norms;
import com.aatishrana.almamatersample.pojo.Standard;
import com.aatishrana.almamatersample.pojo.Teacher;
import com.aatishrana.almamatersample.pojo.subject.English;
import com.aatishrana.almamatersample.pojo.subject.Hindi;
import com.aatishrana.almamatersample.pojo.subject.Math;
import com.aatishrana.almamatersample.pojo.subject.Science;
import com.aatishrana.almamatersample.pojo.subject.SocialScience;
import com.aatishrana.almamatersample.pojo.subject.Subject;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Aatish Rana on 25-Oct-17.
 */

public class DataSampleTwo implements DataSample
{
    private Set<Subject> allSubjects;
    private Set<Teacher> allTeachers;
    private Set<Standard> allClasses;
    private Set<Norms> allNorms;

    public DataSampleTwo()
    {
        Subject math = new Math(101);
        Subject science = new Science(102);
        Subject socialScience = new SocialScience(103);

        allSubjects = new HashSet<>();
        allSubjects.add(math);
        allSubjects.add(science);
        allSubjects.add(socialScience);


        allTeachers = new HashSet<>();

        Teacher t_UshaRawat = new Teacher(201, "Teacher A", science);
        allTeachers.add(t_UshaRawat);

        Teacher t_SeemaMahajan = new Teacher(203, "Teacher B", math);
        allTeachers.add(t_SeemaMahajan);

        Teacher t_JasmeetKaur = new Teacher(208, "Teacher C", socialScience);
        allTeachers.add(t_JasmeetKaur);

        Standard _6thA = new Standard(301, "6thA", 6, 'A', 30);
        Standard _6thB = new Standard(302, "6thB", 6, 'B', 30);

        Standard _7thA = new Standard(303, "7thA", 7, 'A', 30);
        Standard _7thB = new Standard(304, "7thB", 7, 'B', 30);

        allClasses = new HashSet<>();
        allClasses.add(_6thA);
        allClasses.add(_6thB);
        allClasses.add(_7thA);
        allClasses.add(_7thB);


        // all norms for different subjects, sample db
        allNorms = new HashSet<>();


        //for science
        Norms norm_science6th = new Norms(401, 6, science, 13);
        Norms norm_science7th = new Norms(402, 7, science, 7);
        allNorms.add(norm_science6th);
        allNorms.add(norm_science7th);


        //for social science
        Norms norm_socialScience6th = new Norms(406, 6, socialScience, 14);
        Norms norm_socialScience7th = new Norms(407, 7, socialScience, 10);
        allNorms.add(norm_socialScience6th);
        allNorms.add(norm_socialScience7th);


        //for maths
        Norms norm_math6th = new Norms(411, 6, math, 9);
        Norms norm_math7th = new Norms(412, 7, math, 19);
        allNorms.add(norm_math6th);
        allNorms.add(norm_math7th);
    }

    @Override
    public Set<Subject> getAllSubjects()
    {
        return this.allSubjects;
    }

    @Override
    public Set<Teacher> getAllTeachers()
    {
        return this.allTeachers;
    }

    @Override
    public Set<Standard> getAllClasses()
    {
        return this.allClasses;
    }

    @Override
    public Set<Norms> getAllNorms()
    {
        return this.allNorms;
    }
}
