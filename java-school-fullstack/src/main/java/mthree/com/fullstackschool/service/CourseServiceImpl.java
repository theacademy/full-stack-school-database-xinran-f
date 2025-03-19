package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.CourseDao;
import mthree.com.fullstackschool.dao.StudentDao;
import mthree.com.fullstackschool.dao.TeacherDao;
import mthree.com.fullstackschool.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseServiceInterface {

    //YOUR CODE STARTS HERE
    private CourseDao courseDao;

    @Autowired
    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }
    //YOUR CODE ENDS HERE

    public List<Course> getAllCourses() {
        //YOUR CODE STARTS HERE
        return courseDao.getAllCourses();
        //YOUR CODE ENDS HERE
    }

    public Course getCourseById(int id) {
        //YOUR CODE STARTS HERE
        Course course = new Course();

        try {
            course = courseDao.findCourseById(id);
        } catch (DataAccessException e) {
            // TODO: Not sure
            String courseNotFound = "Course Not Found";
            course.setCourseName(courseNotFound);
            course.setCourseDesc(courseNotFound);
            courseDao.createNewCourse(course);
            return course;
        }

        return course;
        //YOUR CODE ENDS HERE
    }

    public Course addNewCourse(Course course) {
        //YOUR CODE STARTS HERE
        if (course.getCourseName() == null || course.getCourseName().trim().isEmpty()) {
            course.setCourseName("Name blank, course NOT added");
        }

        if (course.getCourseDesc() == null || course.getCourseDesc().trim().isEmpty()) {
            course.setCourseDesc("Description blank, course NOT added");
        }

        return courseDao.createNewCourse(course);
        //YOUR CODE ENDS HERE
    }

    public Course updateCourseData(int id, Course course) {
        //YOUR CODE STARTS HERE
        if (id != course.getCourseId()) {
            course.setCourseName("IDs do not match, course not updated");
            course.setCourseDesc("IDs do not match, course not updated");
        }

        courseDao.updateCourse(course);

        return course;
        //YOUR CODE ENDS HERE
    }

    public void deleteCourseById(int id) {
        //YOUR CODE STARTS HERE
        courseDao.deleteCourse(id);

        System.out.println("Course ID: " + id + " deleted");
        //YOUR CODE ENDS HERE
    }
}
