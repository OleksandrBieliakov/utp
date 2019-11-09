package ass6.generators;

import ass6.extents.*;
import ass6.groups.Department;
import ass6.groups.StudentsGroup;
import ass6.groups.Subject;
import ass6.people.*;

import java.time.LocalDate;
import java.util.*;

public class Generator {

    private static final int STUDENTS_NUMBER = 100;
    private static final int GROUPS_NUMBER = 12;
    private static final int GROUP_SIZE = STUDENTS_NUMBER / GROUPS_NUMBER;
    private int extraStudents = STUDENTS_NUMBER % GROUPS_NUMBER;

    private static final int DEPARTMENTS_NUMBER = 3;
    private static final int TEACHERS_NUMBER = 10;
    private static final int DEPARTMENT_SIZE = TEACHERS_NUMBER / DEPARTMENTS_NUMBER;
    private int extraTeachers = TEACHERS_NUMBER % DEPARTMENTS_NUMBER;

    private static final int SUBJECTS_NUMBER = 10;
    private static final int STUDENTS_PER_SUBJECT = STUDENTS_NUMBER / SUBJECTS_NUMBER;
    private int extraSubjectStudents = STUDENTS_NUMBER % SUBJECTS_NUMBER;

    private static final int MIN_STUDENT_AGE = 18;
    private static final int MAX_STUDENT_AGE = 30;
    private static final int MIN_TEACHER_AGE = 30;
    private static final int MAX_TEACHER_AGE = 60;

    private static final int TEACHER_CAN_WORK_FROM_YEARS = 25;

    private static final int STUDENT_NUMBERS_RANGE = 100_000;

    private static final int DAYS_IN_MONTH = 28;
    private static final int MONTHS_IN_YEAR = 12;


    private static final Random RANDOM = new Random();

    private People people = new People();
    private Students students = new Students();
    private Teachers teachers = new Teachers();

    private StudentsGroups studentsGroups = new StudentsGroups();
    private Departments departments = new Departments();
    private Subjects subjects = new Subjects();

    private Iterator<Student> studentsIterator = students.iterator();
    private Iterator<Teacher> teachersIterator = teachers.iterator();

    private Iterator<StudentsGroup> studentsGroupsIterator = studentsGroups.iterator();
    private Iterator<Department> departmentsIterator = departments.iterator();
    private Iterator<Subject> subjectsIterator = subjects.iterator();

    public static int randomYearInAgeRange(int from, int to) {
        int age = from + RANDOM.nextInt(to - from);
        return LocalDate.now().getYear() - age;
    }

    public static int generateStudentBirthYear() {
        return randomYearInAgeRange(MIN_STUDENT_AGE, MAX_STUDENT_AGE);
    }

    public static int generateTeacherBirthYear() {
        return randomYearInAgeRange(MIN_TEACHER_AGE, MAX_TEACHER_AGE);
    }

    public static int generateTeacherHireYear(int birthYear) {
        int age = LocalDate.now().getYear() - birthYear;
        return randomYearInAgeRange(0, age - TEACHER_CAN_WORK_FROM_YEARS);
    }

    public static LocalDate generateDate(int year) {
        return LocalDate.of(year, RANDOM.nextInt(MONTHS_IN_YEAR) + 1, RANDOM.nextInt(DAYS_IN_MONTH) + 1);
    }

    public static String generateStudentID() {
        int number = RANDOM.nextInt(STUDENT_NUMBERS_RANGE);
        return String.format("s%05d", number);
    }

    public Student generateStudent() {
        LocalDate birthDate = generateDate(generateStudentBirthYear());
        Student student = new Student(peselGenerator.generateMalePesel(birthDate), NameGenerator.generateMaleName(),
                SurnameGenerator.generateMaleSurname(), birthDate, Nationality.generateNationality(), generateStudentID());
        students.add(student);
        people.add(student);
        return student;
    }

    public Teacher generateTeacher() {
        int birthYear = generateTeacherBirthYear();
        int hireDate = generateTeacherHireYear(birthYear);
        LocalDate birthDate = generateDate(birthYear);
        Teacher teacher = new Teacher(peselGenerator.generateMalePesel(birthDate), NameGenerator.generateMaleName(),
                SurnameGenerator.generateMaleSurname(), birthDate,
                Nationality.generateNationality(), generateDate(hireDate), Degree.generateDegree());
        teachers.add(teacher);
        people.add(teacher);
        return teacher;
    }

    public StudentsGroup generateStudentsGroup() {
        StudentsGroup studentsGroup = new StudentsGroup(String.format("Group %02d", studentsGroups.size() + 1));
        studentsGroups.add(studentsGroup);
        return studentsGroup;
    }

    public Department generateDepartment() {
        Department department = new Department(String.format("Department %02d", departments.size() + 1));
        departments.add(department);
        return department;
    }

    public Subject generateSubject() {
        if (departments.size() == 0) generateDepartment();
        if (teachers.size() == 0) generateTeacher();
        if (!departmentsIterator.hasNext()) departmentsIterator = departments.iterator();
        if (!teachersIterator.hasNext()) teachersIterator = teachers.iterator();
        Subject subject = new Subject(String.format("Subject %02d", subjects.size() + 1), departmentsIterator.next(), teachersIterator.next());
        subjects.add(subject);
        return subject;
    }

    public void addStudentToGroup(StudentsGroup studentsGroup) {
        if (students.size() == 0) generateStudent();
        if (!studentsIterator.hasNext()) studentsIterator = students.iterator();
        studentsGroup.add(studentsIterator.next());
    }

    public void addStudentToSubject(Subject subject) {
        if (students.size() == 0) generateStudent();
        if (!studentsIterator.hasNext()) studentsIterator = students.iterator();
        subject.add(studentsIterator.next());
    }

    public void addTeacherToDepartment(Department department) {
        if (teachers.size() == 0) generateTeacher();
        if (!teachersIterator.hasNext()) teachersIterator = teachers.iterator();
        department.add(teachersIterator.next());
    }

    public void generateStudents(int studentsTotalNumber) {
        while (students.size() < studentsTotalNumber)
            generateStudent();
    }

    public void generateTeachers(int teachersTotalNumber) {
        while (teachers.size() < teachersTotalNumber)
            generateTeacher();
    }

    public void generateStudentsGroups(int studentsGroupsTotalNumber) {
        while (studentsGroups.size() < studentsGroupsTotalNumber)
            generateStudentsGroup();
    }

    public void generateDepartments(int departmentsTotalNumber) {
        while (departments.size() < departmentsTotalNumber)
            generateDepartment();
    }

    public void generateSubjects(int subjectsTotalNumber) {
        while (subjects.size() < subjectsTotalNumber)
            generateSubject();
    }

    public void generateAll() {
        generateStudents(STUDENTS_NUMBER);
        generateTeachers(TEACHERS_NUMBER);
        generateStudentsGroups(GROUPS_NUMBER);
        generateDepartments(DEPARTMENTS_NUMBER);
        generateSubjects(SUBJECTS_NUMBER);
    }

    public void fillStudentsGroup(StudentsGroup studentsGroup) {
        while (students.size() < GROUP_SIZE) generateStudent();
        while (studentsGroup.size() < GROUP_SIZE)
            addStudentToGroup(studentsGroup);
        if (extraStudents > 0) {
            addStudentToGroup(studentsGroup);
            extraStudents--;
        }
    }

    public void fillDepartment(Department department) {
        while (teachers.size() < DEPARTMENT_SIZE) generateTeacher();
        while (department.size() < DEPARTMENT_SIZE)
            addTeacherToDepartment(department);
        if (extraTeachers > 0) {
            addTeacherToDepartment(department);
            extraTeachers--;
        }
    }

    public void fillSubject(Subject subject) {
        while (students.size() < STUDENTS_PER_SUBJECT) generateStudent();
        while (subject.size() < STUDENTS_PER_SUBJECT)
            addStudentToSubject(subject);
        if (extraSubjectStudents > 0) {
            addStudentToSubject(subject);
            extraSubjectStudents--;
        }
    }

    public void fillStudentsGroups() {
        studentsGroupsIterator = studentsGroups.iterator();
        while (studentsGroupsIterator.hasNext())
            fillStudentsGroup(studentsGroupsIterator.next());
    }

    public void fillDepartments() {
        departmentsIterator = departments.iterator();
        while (departmentsIterator.hasNext())
            fillDepartment(departmentsIterator.next());
    }

    public void fillSubjects() {
        subjectsIterator = subjects.iterator();
        while (subjectsIterator.hasNext())
            fillSubject(subjectsIterator.next());
    }

    public void fillAllGroups() {
        fillStudentsGroups();
        fillDepartments();
        fillSubjects();
    }

    public void generateAndFillAll() {
        generateAll();
        fillAllGroups();
    }

    public StudentsGroups getStudentsGroups() {
        return studentsGroups;
    }

    public Departments getDepartments() {
        return departments;
    }

    public Subjects getSubjects() {
        return subjects;
    }

    public People getPeople() {
        return people;
    }

    public Students getStudents() {
        return students;
    }

    public Teachers getTeachers() {
        return teachers;
    }

}
