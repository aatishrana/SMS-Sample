package com.aatishrana.almamatersample;

import com.aatishrana.almamatersample.pojo.Norms;
import com.aatishrana.almamatersample.pojo.TimeTable;
import com.aatishrana.almamatersample.pojo.subject.English;
import com.aatishrana.almamatersample.pojo.subject.Hindi;
import com.aatishrana.almamatersample.pojo.subject.Science;
import com.aatishrana.almamatersample.pojo.subject.SocialScience;
import com.aatishrana.almamatersample.pojo.subject.Math;
import com.aatishrana.almamatersample.pojo.StudentGroup;
import com.aatishrana.almamatersample.pojo.subject.Subject;
import com.aatishrana.almamatersample.pojo.Teacher;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
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
    private Set<Teacher> teachers;
    private Set<StudentGroup> classes;
    private Subject math, science, socialScience, english, hindi;

    //    private Map<Subject, Integer> subjectsWithMinLectures;

    @Before
    public void setUp() throws Exception
    {
        // all subjects
        math = new Math(101);
        science = new Science(102);
        socialScience = new SocialScience(103);
        english = new English(104);
        hindi = new Hindi(105);

        Set<Subject> engAndSocialScience = new HashSet<>();
        engAndSocialScience.add(english);
        engAndSocialScience.add(socialScience);


        // all classes with sections
        StudentGroup class_6thA = new StudentGroup(1, "6th A", 30, 6, getNormsForStandard(6));
        StudentGroup class_6thB = new StudentGroup(2, "6th B", 30, 6, getNormsForStandard(6));

        StudentGroup class_7thA = new StudentGroup(4, "7th A", 30, 7, getNormsForStandard(7));
        StudentGroup class_7thB = new StudentGroup(5, "7th B", 30, 7, getNormsForStandard(7));

        StudentGroup class_8thA = new StudentGroup(7, "8th A", 30, 8, getNormsForStandard(8));
        StudentGroup class_8thB = new StudentGroup(8, "8th B", 30, 8, getNormsForStandard(8));

        StudentGroup class_9thA = new StudentGroup(10, "9th A", 40, 9, getNormsForStandard(9));
        StudentGroup class_9thB = new StudentGroup(11, "9th B", 35, 9, getNormsForStandard(9));
        StudentGroup class_9thC = new StudentGroup(12, "9th C", 19, 9, getNormsForStandard(9));

        StudentGroup class_10thA = new StudentGroup(13, "10th A", 45, 10, getNormsForStandard(10));


        classes = new HashSet<>();
        classes.add(class_6thA);
        classes.add(class_6thB);
        classes.add(class_7thA);
        classes.add(class_7thB);
        classes.add(class_8thA);
        classes.add(class_8thB);
        classes.add(class_9thA);
        classes.add(class_9thB);
        classes.add(class_9thC);
        classes.add(class_10thA);


        teachers = new HashSet<>();
        // all teachers of science
        Teacher t_UshaRawat = new Teacher(1, "Usha Rawat", science);
        Teacher t_Suman = new Teacher(1, "Suman", science);
        teachers.add(t_UshaRawat);
        teachers.add(t_Suman);

        // all teachers of maths
        Teacher t_SeemaMahajan = new Teacher(2, "Seema Mahajan", math);
        Teacher t_RajinderSingh = new Teacher(2, "Rajinder Singh", math);
        Teacher t_RajeshKaushik = new Teacher(2, "Rajesh Kaushik", math);
        teachers.add(t_SeemaMahajan);
        teachers.add(t_RajinderSingh);
        teachers.add(t_RajeshKaushik);

        // all teachers of ss and english
        Teacher t_AstinderKaur = new Teacher(2, "Astinder Kaur", engAndSocialScience);
        Teacher t_PreetyGogia = new Teacher(2, "Preety Gogia", engAndSocialScience);
        Teacher t_JasmeetKaur = new Teacher(2, "Jasmeet Kaur", engAndSocialScience);
        Teacher t_PoonamRani = new Teacher(2, "Poonam Rani", engAndSocialScience);
        Teacher t_Ishita = new Teacher(2, "Ishita", engAndSocialScience);
        teachers.add(t_AstinderKaur);
        teachers.add(t_PreetyGogia);
        teachers.add(t_JasmeetKaur);
        teachers.add(t_PoonamRani);
        teachers.add(t_Ishita);

        // all teachers of hindi
        Teacher t_PoonamSoni = new Teacher(2, "Poonam Soni", hindi);
        Teacher t_Bindu = new Teacher(2, "Bindu", hindi);
        teachers.add(t_PoonamSoni);
        teachers.add(t_Bindu);


//        subjectsWithMinLectures = new HashMap<>();
//        subjectsWithMinLectures.put(math, 3);
//        subjectsWithMinLectures.put(socialScience, 3);
//        subjectsWithMinLectures.put(science, 3);
    }


    @Test
    public void testExtractSubjects() throws Exception
    {
        TimeTableGenerator generator = new TimeTableGenerator();
        Set<Subject> allSubjects = generator.extractSubjectsFromAllGivenSections(classes);
        assertEquals(5, allSubjects.size());
    }

    @Test
    public void testCheckAllTeacherAvailable() throws Exception
    {
        TimeTableGenerator generator = new TimeTableGenerator();
        generator.checkAllTeachersAvailable(teachers, generator.extractSubjectsFromAllGivenSections(classes));
    }


    private List<Norms> getNormsForStandard(int standard)
    {
        if (standard > 10 || standard < 6)
            return null;

        // all norms for different subjects, sample db

        //for science
        Norms norm_science6th = new Norms(1, 6, science, 6, 6);
        Norms norm_science7th = new Norms(2, 7, science, 6, 6);
        Norms norm_science8th = new Norms(3, 8, science, 6, 6);
        Norms norm_science9th = new Norms(4, 9, science, 9, 6);
        Norms norm_science10th = new Norms(5, 10, science, 9, 6);

        //for social science
        Norms norm_socialScience6th = new Norms(6, 6, socialScience, 6, 6);
        Norms norm_socialScience7th = new Norms(7, 7, socialScience, 6, 6);
        Norms norm_socialScience8th = new Norms(8, 8, socialScience, 6, 6);
        Norms norm_socialScience9th = new Norms(9, 9, socialScience, 9, 6);
        Norms norm_socialScience10th = new Norms(10, 10, socialScience, 9, 6);

        //for maths
        Norms norm_math6th = new Norms(11, 6, math, 7, 6);
        Norms norm_math7th = new Norms(12, 7, math, 7, 6);
        Norms norm_math8th = new Norms(13, 8, math, 7, 6);
        Norms norm_math9th = new Norms(14, 9, math, 8, 6);
        Norms norm_math10th = new Norms(15, 10, math, 8, 6);

        //for english
        Norms norm_english6th = new Norms(16, 6, english, 7, 6);
        Norms norm_english7th = new Norms(17, 7, english, 7, 6);
        Norms norm_english8th = new Norms(18, 8, english, 7, 6);
        Norms norm_english9th = new Norms(19, 9, english, 8, 6);
        Norms norm_english10th = new Norms(20, 10, english, 8, 6);

        //for hindi
        Norms norm_hindi6th = new Norms(21, 6, hindi, 6, 6);
        Norms norm_hindi7th = new Norms(22, 7, hindi, 6, 6);
        Norms norm_hindi8th = new Norms(23, 8, hindi, 6, 6);
        Norms norm_hindi9th = new Norms(24, 9, hindi, 6, 6);
        Norms norm_hindi10th = new Norms(25, 10, hindi, 6, 6);


        List<Norms> normsList = new ArrayList<>();
        switch (standard)
        {
            case 6:
                normsList.add(norm_science6th);
                normsList.add(norm_socialScience6th);
                normsList.add(norm_math6th);
                normsList.add(norm_english6th);
                normsList.add(norm_hindi6th);
                break;
            case 7:
                normsList.add(norm_science7th);
                normsList.add(norm_socialScience7th);
                normsList.add(norm_math7th);
                normsList.add(norm_english7th);
                normsList.add(norm_hindi7th);
                break;
            case 8:
                normsList.add(norm_science8th);
                normsList.add(norm_socialScience8th);
                normsList.add(norm_math8th);
                normsList.add(norm_english8th);
                normsList.add(norm_hindi8th);
                break;
            case 9:
                normsList.add(norm_science9th);
                normsList.add(norm_socialScience9th);
                normsList.add(norm_math9th);
                normsList.add(norm_english9th);
                normsList.add(norm_hindi9th);
                break;
            case 10:
                normsList.add(norm_science10th);
                normsList.add(norm_socialScience10th);
                normsList.add(norm_math10th);
                normsList.add(norm_english10th);
                normsList.add(norm_hindi10th);
                break;
        }
        return normsList;
    }
}