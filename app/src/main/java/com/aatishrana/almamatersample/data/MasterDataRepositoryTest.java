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
 * Created by Aatish on 10/17/2017.
 */
public class MasterDataRepositoryTest implements MasterDataRepository
{
    private Set<Subject> allSubjects;
    private Set<Teacher> allTeachers;
    private Set<Standard> allClasses;
    private Set<Norms> allNorms;

    public MasterDataRepositoryTest()
    {
        Subject math = new Math(101);
        Subject science = new Science(102);
        Subject socialScience = new SocialScience(103);
        Subject english = new English(104);
        Subject hindi = new Hindi(105);
        allSubjects = new HashSet<>();
        allSubjects.add(math);
        allSubjects.add(science);
        allSubjects.add(socialScience);
        allSubjects.add(english);
        allSubjects.add(hindi);


        Set<Subject> engAndSocialScience = new HashSet<>();
        engAndSocialScience.add(english);
        engAndSocialScience.add(socialScience);


        allTeachers = new HashSet<>();
        // all allTeachers of science
        Teacher t_UshaRawat = new Teacher(201, "Usha Rawat", science);
        Teacher t_Suman = new Teacher(202, "Suman", science);
        allTeachers.add(t_UshaRawat);
        allTeachers.add(t_Suman);

        // all allTeachers of maths
        Teacher t_SeemaMahajan = new Teacher(203, "Seema Mahajan", math);
        Teacher t_RajinderSingh = new Teacher(204, "Rajinder Singh", math);
        Teacher t_RajeshKaushik = new Teacher(205, "Rajesh Kaushik", math);
        allTeachers.add(t_SeemaMahajan);
        allTeachers.add(t_RajinderSingh);
        allTeachers.add(t_RajeshKaushik);

        // all allTeachers of ss and english
        Teacher t_AstinderKaur = new Teacher(206, "Astinder Kaur", english);
        Teacher t_PreetyGogia = new Teacher(207, "Preety Gogia", english);
        Teacher t_JasmeetKaur = new Teacher(208, "Jasmeet Kaur", socialScience);
        Teacher t_PoonamRani = new Teacher(209, "Poonam Rani", socialScience);
        Teacher t_Ishita = new Teacher(210, "Ishita", socialScience);
        allTeachers.add(t_AstinderKaur);
        allTeachers.add(t_PreetyGogia);
        allTeachers.add(t_JasmeetKaur);
        allTeachers.add(t_PoonamRani);
        allTeachers.add(t_Ishita);

        // all allTeachers of hindi
        Teacher t_PoonamSoni = new Teacher(211, "Poonam Soni", hindi);
        Teacher t_Bindu = new Teacher(212, "Bindu", hindi);
        allTeachers.add(t_PoonamSoni);
        allTeachers.add(t_Bindu);


        Standard _6thA = new Standard(301, "6thA", 6, 'A', 30);
        Standard _6thB = new Standard(302, "6thB", 6, 'B', 30);

        Standard _7thA = new Standard(303, "7thA", 7, 'A', 30);
        Standard _7thB = new Standard(304, "7thB", 7, 'B', 30);

        Standard _8thA = new Standard(305, "8thA", 8, 'A', 30);
        Standard _8thB = new Standard(306, "8thB", 8, 'B', 30);

        Standard _9thA = new Standard(307, "9thA", 9, 'A', 30);
        Standard _9thB = new Standard(308, "9thB", 9, 'B', 30);
        Standard _9thC = new Standard(309, "9thC", 9, 'C', 30);

        Standard _10thA = new Standard(310, "10thA", 10, 'A', 30);

        allClasses = new HashSet<>();
        allClasses.add(_6thA);
        allClasses.add(_6thB);
        allClasses.add(_7thA);
        allClasses.add(_7thB);
        allClasses.add(_8thA);
        allClasses.add(_8thB);
        allClasses.add(_9thA);
        allClasses.add(_9thB);
        allClasses.add(_9thC);
        allClasses.add(_10thA);


        // all norms for different subjects, sample db
        allNorms = new HashSet<>();

        //for science
        Norms norm_science6th = new Norms(401, 6, science, 6);
        Norms norm_science7th = new Norms(402, 7, science, 6);
        Norms norm_science8th = new Norms(403, 8, science, 6);
        Norms norm_science9th = new Norms(404, 9, science, 9);
        Norms norm_science10th = new Norms(405, 10, science, 9);
        allNorms.add(norm_science6th);
        allNorms.add(norm_science7th);
        allNorms.add(norm_science8th);
        allNorms.add(norm_science9th);
        allNorms.add(norm_science10th);

        //for social science
        Norms norm_socialScience6th = new Norms(406, 6, socialScience, 6);
        Norms norm_socialScience7th = new Norms(407, 7, socialScience, 6);
        Norms norm_socialScience8th = new Norms(408, 8, socialScience, 6);
        Norms norm_socialScience9th = new Norms(409, 9, socialScience, 9);
        Norms norm_socialScience10th = new Norms(410, 10, socialScience, 9);
        allNorms.add(norm_socialScience6th);
        allNorms.add(norm_socialScience7th);
        allNorms.add(norm_socialScience8th);
        allNorms.add(norm_socialScience9th);
        allNorms.add(norm_socialScience10th);

        //for maths
        Norms norm_math6th = new Norms(411, 6, math, 7);
        Norms norm_math7th = new Norms(412, 7, math, 7);
        Norms norm_math8th = new Norms(413, 8, math, 7);
        Norms norm_math9th = new Norms(414, 9, math, 8);
        Norms norm_math10th = new Norms(415, 10, math, 8);
        allNorms.add(norm_math6th);
        allNorms.add(norm_math7th);
        allNorms.add(norm_math8th);
        allNorms.add(norm_math9th);
        allNorms.add(norm_math10th);

        //for english
        Norms norm_english6th = new Norms(416, 6, english, 7);
        Norms norm_english7th = new Norms(417, 7, english, 7);
        Norms norm_english8th = new Norms(418, 8, english, 7);
        Norms norm_english9th = new Norms(419, 9, english, 8);
        Norms norm_english10th = new Norms(420, 10, english, 8);
        allNorms.add(norm_english6th);
        allNorms.add(norm_english7th);
        allNorms.add(norm_english8th);
        allNorms.add(norm_english9th);
        allNorms.add(norm_english10th);

        //for hindi
        Norms norm_hindi6th = new Norms(421, 6, hindi, 6);
        Norms norm_hindi7th = new Norms(422, 7, hindi, 6);
        Norms norm_hindi8th = new Norms(423, 8, hindi, 6);
        Norms norm_hindi9th = new Norms(424, 9, hindi, 6);
        Norms norm_hindi10th = new Norms(425, 10, hindi, 6);
        allNorms.add(norm_hindi6th);
        allNorms.add(norm_hindi7th);
        allNorms.add(norm_hindi8th);
        allNorms.add(norm_hindi9th);
        allNorms.add(norm_hindi10th);


    }

    @Override
    public Set<Subject> getAllSubjects()
    {
        return allSubjects;
    }

    @Override
    public Subject getSubjectWithId(int id)
    {
        for (Subject subject : allSubjects)
            if (subject.getId() == id)
                return subject;
        return null;
    }

    @Override
    public Set<Teacher> getAllTeachers()
    {
        return allTeachers;
    }

    @Override
    public Teacher getTeacherWithId(int id)
    {
        for (Teacher teacher : allTeachers)
            if (teacher.getId() == id)
                return teacher;
        return null;
    }

    @Override
    public Set<Teacher> getAllTeachersTeachingSubject(int subject_id)
    {
        Set<Teacher> teacherSet = new HashSet<>();
        for (Teacher teacher : allTeachers)
            for (Subject subject : teacher.getSubjects())
                if (subject.getId() == subject_id)
                    teacherSet.add(teacher);
        return teacherSet;
    }

    @Override
    public Set<Standard> getAllClasses()
    {
        return allClasses;
    }

    @Override
    public Standard getClassWithId(int id)
    {
        for (Standard standard : allClasses)
            if (standard.getId() == id)
                return standard;
        return null;
    }

    @Override
    public Set<Standard> getAllSectionOfStandard(int standard)
    {
        Set<Standard> classes = new HashSet<>();
        for (Standard standard1 : allClasses)
            if (standard1.getStandard() == standard)
                classes.add(standard1);
        return classes;
    }

    @Override
    public Set<Norms> getAllNorms()
    {
        return allNorms;
    }

    @Override
    public Norms getNormWithId(int id)
    {
        for (Norms norms : allNorms)
            if (norms.getId() == id)
                return norms;
        return null;
    }

    @Override
    public Set<Norms> getNormOfSubject(Subject subject)
    {
        Set<Norms> normsSet = new HashSet<>();
        for (Norms norms : allNorms)
            if (norms.getSubject().getId() == subject.getId())
                normsSet.add(norms);
        return normsSet;
    }

    @Override
    public Set<Norms> getAllNormsOfStandard(int standard)
    {
        Set<Norms> normsSet = new HashSet<>();
        for (Norms norms : allNorms)
            if (norms.getStandard() == standard)
                normsSet.add(norms);
        return normsSet;
    }
}
