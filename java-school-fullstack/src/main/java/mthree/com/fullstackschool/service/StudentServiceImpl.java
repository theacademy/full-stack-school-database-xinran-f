package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.CourseDao;
import mthree.com.fullstackschool.dao.StudentDao;
import mthree.com.fullstackschool.model.Course;
import mthree.com.fullstackschool.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentServiceInterface {

    //YOUR CODE STARTS HERE
    private StudentDao studentDao;
    private CourseDao courseDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public StudentServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }
    //YOUR CODE ENDS HERE

    public List<Student> getAllStudents() {
        //YOUR CODE STARTS HERE
        return studentDao.getAllStudents();
        //YOUR CODE ENDS HERE
    }

    public Student getStudentById(int id) {
        //YOUR CODE STARTS HERE
        Student student;

        try {
            student = studentDao.findStudentById(id);
        } catch (DataAccessException e) {
            student = new Student();
            student.setStudentFirstName("Student Not Found");
            student.setStudentLastName("Student Not Found");
        }

        return student;
        //YOUR CODE ENDS HERE
    }

    public Student addNewStudent(Student student) {
        //YOUR CODE STARTS HERE
        if (student.getStudentFirstName() == null || student.getStudentFirstName().trim().isEmpty()) {
            student.setStudentFirstName("First Name blank, student NOT added");
        }

        if (student.getStudentLastName() == null || student.getStudentLastName().trim().isEmpty()) {
            student.setStudentLastName("Last Name blank, student NOT added");
        }

        return studentDao.createNewStudent(student);
        //YOUR CODE ENDS HERE
    }

    public Student updateStudentData(int id, Student student) {
        //YOUR CODE STARTS HERE
        if (id != student.getStudentId()) {
            student.setStudentFirstName("IDs do not match, student not updated");
            student.setStudentLastName("IDs do not match, student not updated");
        }

        studentDao.updateStudent(student);

        return student;
        //YOUR CODE ENDS HERE
    }

    public void deleteStudentById(int id) {
        //YOUR CODE STARTS HERE
        studentDao.deleteStudent(id);
        //YOUR CODE ENDS HERE
    }

    public void deleteStudentFromCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE
        Student student = studentDao.findStudentById(studentId);
        Course course = courseDao.findCourseById(courseId);

        if (student.getStudentFirstName().equals("Student Not Found")) {
            System.out.println("Student Not Found");
            return;
        } else if (course.getCourseName().equals("Course Not Found")) {
            System.out.println("Course Not Found");
            return;
        }

        studentDao.deleteStudentFromCourse(studentId, courseId);

        System.out.printf("Student: <%d> deleted from course: <%d>\n", studentId, courseId);
        //YOUR CODE ENDS HERE
    }

    public void addStudentToCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE
        try {
            Student student = studentDao.findStudentById(studentId);
            Course course = courseDao.findCourseById(courseId);

            if (student.getStudentFirstName().equals("Student Not Found")) {
                System.out.println("Student Not Found");
                return;
            } else if (course.getCourseName().equals("Course Not Found")) {
                System.out.println("Course Not Found");
                return;
            }

            studentDao.addStudentToCourse(studentId, courseId);

            System.out.printf("Student: <%d> added to course: <%d>\n", studentId, courseId);
        } catch (Exception e) {
            System.out.printf("Student: <%d> already enrolled in course: <%d>\n", studentId, courseId);
        }
        //YOUR CODE ENDS HERE
    }
}
