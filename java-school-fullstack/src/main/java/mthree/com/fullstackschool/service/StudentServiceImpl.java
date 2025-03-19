package mthree.com.fullstackschool.service;

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

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
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

    // TODO
    // If student first name is equal to "Student Not Found", print a line to server console: "Student not found"
    // else if the course name is equal to "Course Not Found", print a line to server console: "Course not found"
    // else print a line to server console: "Student: <STUDENT ID> deleted from course: <COURSE ID>"
    public void deleteStudentFromCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE

        studentDao.deleteStudentFromCourse(studentId, courseId);
        
        //YOUR CODE ENDS HERE
    }

    // TODO
    // If student first name is equal to "Student Not Found", print a line to the server console "Student not found"
    // else if the course name is equal to "Course Not Found", print a line to the server console "Course not found"
    // else print a line to the server console: "Student: <STUDENT ID> added to course: <COURSE ID>"
    // or catch exception print line to server console: "Student: <STUDENT ID> already enrolled in course: <COURSE ID>"
    public void addStudentToCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE

        studentDao.addStudentToCourse(studentId, courseId);

        //YOUR CODE ENDS HERE
    }
}
