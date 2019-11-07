package ass6;

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
    private static final int EXTRA_STUDENTS = STUDENTS_NUMBER % GROUPS_NUMBER;

    private static final int DEPARTMENTS_NUMBER = 3;
    private static final int TEACHER_NUMBER = 10;
    private static final int DEPARTMENT_SIZE = TEACHER_NUMBER / DEPARTMENTS_NUMBER;
    private static final int EXTRA_TEACHERS = TEACHER_NUMBER % DEPARTMENTS_NUMBER;

    private static final int SUBJECTS_NUMBER = 10;
    private static final int STUDENTS_PER_SUBJECT = STUDENTS_NUMBER / SUBJECTS_NUMBER;
    private static final int EXTRA_SUBJECT_STUDENTS = STUDENTS_NUMBER % SUBJECTS_NUMBER;

    private static final String[] names =
            {"ANTONI", "JAKUB", "JAN", "SZYMON", "ALEKSANDER", "FRANCISZEK", "FILIP", "WOJCIECH", "MIKOŁAJ", "KACPER",
                    "ADAM", "STANISŁAW", "MARCEL", "MICHAŁ", "MIŁOSZ", "PAWEŁ", "BARTŁOMIEJ", "WIKTOR", "TYMON", "IGOR"};
    private static final String[] surnames =
            {"Nowak", "Kowalski", "Wiśniewski", "Wójcik", "Kowalczyk", "Kamiński", "Lewandowski", "Zieliński", "Szymański",
                    "Woźniak", "Dąbrowski", "Kozłowski", "Jankowski", "Mazur", "Wojciechowski", "Kwiatkowski", "Krawczyk",
                    "Kaczmarek", "Piotrowski", "Grabowski"};

    private static final Random random = new Random();

    private Set<StudentsGroup> studentsGroups = new HashSet<>();
    private Set<Department> departments = new HashSet<>();
    private Set<Subject> subjects = new HashSet<>();

    private Set<Person> people = new HashSet<>();
    private Set<Student> students = new HashSet<>();
    private Set<Teacher> teachers = new HashSet<>();

    public Generator() {
        generateAll();
    }

    //TODO
    public static String generatePESEL() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 11; ++i)
            sb.append(random.nextInt(10));
        return sb.toString();
    }

    public static String generateName() {
        return names[random.nextInt(names.length)];
    }

    public static String generateSurname() {
        return surnames[random.nextInt(surnames.length)];
    }

    //TODO
    public static LocalDate generateBirthDate() {
        return LocalDate.of(1990 + random.nextInt(10), random.nextInt(12), random.nextInt(30));
    }

    public static Nationality generateNationality() {
        Nationality[] nationalities = Nationality.values();
        return nationalities[new Random().nextInt(nationalities.length)];
    }

    //TODO
    public static String generateStudentID() {
        return "";
    }

    public static Degree generateDegree() {
        Degree[] degrees = Degree.values();
        return degrees[new Random().nextInt(degrees.length)];
    }

    //TODO
    public static LocalDate generateHireDate() {
        return LocalDate.of(2014 + random.nextInt(5), random.nextInt(12), random.nextInt(30));
    }

    public static Student generateStudent() {
        return new Student(generatePESEL(), generateName(), generateSurname(), generateBirthDate(), generateNationality(), generateStudentID());
    }

    public static Teacher generateTeacher() {
        return new Teacher(generatePESEL(), generateName(), generateSurname(), generateBirthDate(),
                generateNationality(), generateHireDate(), generateDegree());
    }

    private void generateStudents() {
        Student student;
        while (students.size() < STUDENTS_NUMBER) {
            student = generateStudent();
            students.add(student);
            people.add(student);
        }
    }

    private void generateTeachers() {
        Teacher teacher;
        while (teachers.size() < TEACHER_NUMBER) {
            teacher = generateTeacher();
            teachers.add(teacher);
            people.add(teacher);
        }
    }

    private void fillStudentGroups() {
        Iterator<Student> iterator = students.iterator();
        int extraStudents = EXTRA_STUDENTS;
        int groupsCount = 1;
        while (studentsGroups.size() < GROUPS_NUMBER) {
            Set<Student> students = new HashSet<>();
            while (students.size() < GROUP_SIZE && iterator.hasNext()) {
                students.add(iterator.next());
            }
            if (extraStudents > 0 && iterator.hasNext()) {
                students.add(iterator.next());
                extraStudents--;
            }
            StudentsGroup group = new StudentsGroup("Group" + groupsCount++, students);
            studentsGroups.add(group);
        }
    }

    private void fillDepartments() {
        Iterator<Teacher> iterator = teachers.iterator();
        int extraTeachers = EXTRA_TEACHERS;
        int departmentsCount = 1;
        while (departments.size() < DEPARTMENTS_NUMBER) {
            Set<Teacher> teachers = new HashSet<>();
            while (teachers.size() < DEPARTMENT_SIZE && iterator.hasNext()) {
                teachers.add(iterator.next());
            }
            if (extraTeachers > 0 && iterator.hasNext()) {
                teachers.add(iterator.next());
                extraTeachers--;
            }
            Department department = new Department("Department" + departmentsCount++, teachers);
            departments.add(department);
        }
    }

    private void fillSubjects() {
        Iterator<Student> iteratorStudents = students.iterator();
        Iterator<Teacher> iteratorTeachers = teachers.iterator();
        Iterator<Department> iteratorDepartments = departments.iterator();
        int extraStudents = EXTRA_SUBJECT_STUDENTS;
        int subjectCount = 1;
        while (subjects.size() < SUBJECTS_NUMBER) {
            Set<Student> students = new HashSet<>();
            Teacher teacher;
            Department department;
            while (students.size() < STUDENTS_PER_SUBJECT && iteratorStudents.hasNext()) {
                students.add(iteratorStudents.next());
            }
            if (extraStudents > 0 && iteratorStudents.hasNext()) {
                students.add(iteratorStudents.next());
                extraStudents--;
            }
            if (!iteratorTeachers.hasNext()) iteratorTeachers = teachers.iterator();
            teacher = iteratorTeachers.next();
            if (!iteratorDepartments.hasNext()) iteratorDepartments = departments.iterator();
            department = iteratorDepartments.next();
            Subject subject = new Subject("Subject" + subjectCount++, students, department, teacher);
            subjects.add(subject);
        }
    }

    private void generateAll() {
        generateStudents();
        generateTeachers();
        fillDepartments();
        fillStudentGroups();
        fillSubjects();
    }

    public Set<StudentsGroup> getStudentsGroups() {
        return studentsGroups;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

}
