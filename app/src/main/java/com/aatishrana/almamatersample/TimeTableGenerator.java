package com.aatishrana.almamatersample;

import com.aatishrana.almamatersample.data.MasterDataRepository;
import com.aatishrana.almamatersample.pojo.ConfigVariables;
import com.aatishrana.almamatersample.pojo.Norms;
import com.aatishrana.almamatersample.pojo.Standard;
import com.aatishrana.almamatersample.pojo.SubjectTeacher;
import com.aatishrana.almamatersample.pojo.subject.Subject;
import com.aatishrana.almamatersample.pojo.Teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

/**
 * Created by Aatish on 10/10/2017.
 */

public class TimeTableGenerator
{
    private MasterDataRepository repository;

    public TimeTableGenerator(MasterDataRepository repository)
    {
        this.repository = repository;
    }

    public void makeYearlyTimeTable(Set<Teacher> teachers, Set<Standard> allSections,
                                    Set<Subject> allSubjects, ConfigVariables configVariables)
    {
        //check if all teachers of all subjects are available
        checkAllTeachersAvailable(teachers, allSubjects);

        //calculate total no. of load of a subject for all student groups from adding norms
        Map<Subject, Integer> totalLoad = calculateTotalLoad(allSubjects);

        //check if there are enough teachers to handle the load
        loadAssigningCheck(totalLoad, configVariables);

        //start creating time table
        List<Standard> allStandards = new ArrayList<>();
        allStandards.addAll(allSections);
        SubjectTeacher[][][] timetable = createTimeTable(teachers, allStandards, configVariables);
    }


    /**
     * Currently it is assumed that the teachers who teach maths
     * can teach maths for all provided student group
     */
    void checkAllTeachersAvailable(Set<Teacher> teachers, Set<Subject> subjects)
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

    Map<Subject, Integer> calculateTotalLoad(Set<Subject> allSubjects)
    {
        Map<Subject, Integer> totalLoad = new HashMap<>();
        for (Subject subject : allSubjects)
            totalLoad.put(subject, 0);


        for (int _class = 6; _class <= 10; _class++)
        {
            Set<Norms> norms = repository.getAllNormsOfStandard(_class);
            Set<Standard> sections = repository.getAllSectionOfStandard(_class);
            for (int i = 0; i < sections.size(); i++)
                for (Norms normsFor_class : norms)
                {
                    if (totalLoad.containsKey(normsFor_class.getSubject()))
                    {
                        int lectures = normsFor_class.getNoOfLectures();
                        int count = totalLoad.get(normsFor_class.getSubject());
                        totalLoad.put(normsFor_class.getSubject(), (count + lectures));
                    }
                }
        }
        return totalLoad;
    }

    void loadAssigningCheck(Map<Subject, Integer> totalLoad, ConfigVariables configVariables)
    {
        for (Map.Entry<Subject, Integer> load : totalLoad.entrySet())
        {
            int totalNoOfLectures = configVariables.getNoOfWorkWeek() * configVariables.getNoOfLecturesInADay();
            Set<Teacher> teachers = repository.getAllTeachersTeachingSubject(load.getKey().getId());
            int totalMaxLoadOfTeachersForOneSubject = totalNoOfLectures * teachers.size();

            System.out.print(load.getKey().getName() + " Load(" + load.getValue() + ") / Max(");
            System.out.println(totalMaxLoadOfTeachersForOneSubject + ")");

            if (load.getValue() > totalMaxLoadOfTeachersForOneSubject)
                if (!configVariables.isDelegateToNonSubjectTeacher())
                    throw new RuntimeException(load.getKey() + " load is greater then available teachers. Load = "
                            + load.getValue() + ", No. of teachers available = "
                            + teachers.size() + ", Max no. of lectures in a week for 1 teacher = "
                            + totalNoOfLectures);
                else
                {
                    // todo if delegate is true find other teacher to give this subject to
                }

        }
    }


    SubjectTeacher[][][] createTimeTable(Set<Teacher> teachers, List<Standard> allSections, ConfigVariables config)
    {
        //create variables
        SubjectTeacher[][][] data = new SubjectTeacher[config.getNoOfWorkWeek()][config.getNoOfLecturesInADay()][allSections.size()];
        //key is standard id(unique to every class) value is a map with keys(subjects) and its values as stack
        //of lectures left to be full filled
        Map<Integer, Map<Subject, Stack<Integer>>> normsSets = new HashMap<>();

        //key is standard id value is a map with keys(subjects) and its values(teacher) to signify which
        //teacher is assigned to a class for a particular subject
        Map<Integer, Map<Subject, Teacher>> teachersSets = new HashMap<>();
        for (Standard standard : allSections)
        {
            normsSets.put(standard.getId(), repository.getAllNormsOfStandardInStack(standard.getStandard()));

            //todo does all subjects should be included?
            Map<Subject, Teacher> mst = new HashMap<>();
            for (Subject subject : repository.getAllSubjects())
                mst.put(subject, null);
            teachersSets.put(standard.getId(), mst);
        }


        for (int day = 0; day < config.getNoOfWorkWeek(); day++)
        {
//            log("Day:" + day);
            for (int lecture = 0; lecture < config.getNoOfLecturesInADay(); lecture++)
            {
//                log("\tlecture:" + lecture);
                List<Teacher> freeTeachers = new ArrayList<>();
                freeTeachers.addAll(teachers);

                for (int sectionIndex = 0; sectionIndex < allSections.size(); sectionIndex++)
                {
                    Standard standard = allSections.get(sectionIndex);
//                    log("\t\tsectionIndex:" + sectionIndex);
                    Set<Subject> unTaughtSubjects = getUnTaughtSubjectsOfTheDay(data, day, sectionIndex, config.getNoOfLecturesInADay());
//                    log("\t\tunTaughtSubjects:" + unTaughtSubjects);
                    for (Subject pickedSubject : unTaughtSubjects)
                    {
//                        log("\t\t\tpickedSubject:" + pickedSubject.getName());

                        //check if a teacher is assigned for selected subject
                        Teacher assignedTeacher = teachersSets.get(standard.getId()).get(pickedSubject);
                        if (assignedTeacher == null)
                        {
                            //pick a new teacher from free teachers
//                            log("\t\t\tfreeTeachers:" + freeTeachers.size());
                            Teacher selectedTeacher = pickATeacher(freeTeachers, pickedSubject);
                            if (selectedTeacher != null)
                            {
//                                log("\t\t\tselectedTeacher:" + selectedTeacher);
                                //if selectedTeacher found then add subject teacher to data array and pop that subject's load from stack
                                data[day][lecture][sectionIndex] = new SubjectTeacher(pickedSubject, selectedTeacher);
                                freeTeachers.remove(selectedTeacher);
                                teachersSets.get(standard.getId()).put(pickedSubject, selectedTeacher);
                                normsSets.get(standard.getId()).get(pickedSubject).pop();
                                break;
                            }
//                            else
//                                log("No new teacher found to assign");
                        } else
                        {
                            if (freeTeachers.contains(assignedTeacher))
                            {
//                                log("\t\t\tselectedTeacher:" + assignedTeacher);
                                //if free then add subject teacher to data array and pop that subject's load from stack
                                data[day][lecture][sectionIndex] = new SubjectTeacher(pickedSubject, assignedTeacher);
                                freeTeachers.remove(assignedTeacher);
                                normsSets.get(standard.getId()).get(pickedSubject).pop();
                                break;
                            }
//                            else
//                                log("Assigned teacher is not free");
                        }

                    }
//                    log("\t\t\t\t\t\t" + data[day][lecture][sectionIndex]);
                }
            }
        }
        log(" ");
        log(" ");
        log(" ");
        log(" ");
        log(" ");
        log(" ");
        //printing proper time table
        for (int sectionIndex = 0; sectionIndex < allSections.size(); sectionIndex++)
        {
            Standard standard = allSections.get(sectionIndex);
            log("Class:" + standard.getName());

            for (int day = 0; day < config.getNoOfWorkWeek(); day++)
            {
                for (int lecture = 0; lecture < config.getNoOfLecturesInADay(); lecture++)
                {
                    SubjectTeacher item = data[day][lecture][sectionIndex];
                    if (item != null)
                        System.out.printf("\t%30s", item.getSubject().getName() + "/" + item.getTeacher().getName());
                    else
                        System.out.printf("\t%30s", " - ");
                }
                System.out.println(" ");
            }

            log(" ");
            log(" ");
        }


        return data;
    }


    private Teacher pickATeacher(List<Teacher> freeTeachers, Subject pickedSubject)
    {
        if (freeTeachers == null || freeTeachers.isEmpty())
            return null;

        Set<Teacher> pickAbleTeachers = new HashSet<>();
        for (Teacher teacher : freeTeachers)
            if (teacher.getSubjects().contains(pickedSubject))
                pickAbleTeachers.add(teacher);

        if (pickAbleTeachers.isEmpty())
        {
//            log("Not enough free teachers!!");
            return null;
        }


        //todo write proper algorithm
        int index = new Random().nextInt(pickAbleTeachers.size());
        Iterator<Teacher> iter = pickAbleTeachers.iterator();
        for (int i = 0; i < index; i++)
            iter.next();
        return iter.next();
    }

    /**
     * This method is the core of entire engine.
     * changing how a subject is picked can change the entire output
     * of system
     *
     * @param unTaughtSubjects set of subjects from which we can pick a subject
     * @return picked subject
     */
    Subject pickASubject(Set<Subject> unTaughtSubjects)
    {
        if (unTaughtSubjects == null || unTaughtSubjects.isEmpty())
            return null;

        //todo write proper algorithm, currently a subject is picked randomly
        //algorithm should be in such a way that its random and non repetitive
        int index = new Random().nextInt(unTaughtSubjects.size());
        Iterator<Subject> iter = unTaughtSubjects.iterator();
        for (int i = 0; i < index; i++)
            iter.next();
        return iter.next();
    }


    /**
     * This method takes 3d array of subjectTeacher as input and finds all the subjects
     * that have been taught in a given day for a given section.
     * <h1>Note:This method will not keep track of no. of time a same subject is found.</h1>
     *
     * @param data               3d array of subject teacher
     * @param day                at which day are we looking for un taught subjects
     * @param sectionIndex       of which section's time table to look for
     * @param noOfLecturesInADay as explained
     * @return set of subjects which are un taught at that moment
     */
    Set<Subject> getUnTaughtSubjectsOfTheDay(SubjectTeacher[][][] data, int day, int sectionIndex, int noOfLecturesInADay)
    {
        //todo should all subject be fetched?
        Set<Subject> allSubject = repository.getAllSubjects();
        for (int i = 0; i < noOfLecturesInADay; i++)
        {
            SubjectTeacher lecture = data[day][i][sectionIndex];
            if (lecture != null)
                if (allSubject.contains(lecture.getSubject()))
                    allSubject.remove(lecture.getSubject());
        }
        return allSubject;
    }

    private void log(String msg)
    {
        System.out.println(msg);
    }

}
