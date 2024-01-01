package Servlets.course;

import Servlets.Models.Course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/course")
public class CourseServlet extends HttpServlet {

    private CourseService courseService;

    public CourseServlet () throws SQLException {
        courseService=new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/course.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String id = httpServletRequest.getParameter("courseId");

        try {
            Course course = courseService.getCourse(Integer.parseInt(id));
            String avg = String.valueOf(courseService.getAvg(course.getId()));
            String min = String.valueOf(courseService.getMin(course.getId()));
            String max = String.valueOf(courseService.getMax(course.getId()));
            String median = String.valueOf(courseService.getMedian(course.getId()));
            httpServletRequest.setAttribute("course", course);
            httpServletRequest.setAttribute("avg", avg);
            httpServletRequest.setAttribute("max", max);
            httpServletRequest.setAttribute("min", min);
            httpServletRequest.setAttribute("median", median);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        httpServletRequest.getRequestDispatcher("/jsp/course.jsp").forward(httpServletRequest, httpServletResponse);
    }
}
