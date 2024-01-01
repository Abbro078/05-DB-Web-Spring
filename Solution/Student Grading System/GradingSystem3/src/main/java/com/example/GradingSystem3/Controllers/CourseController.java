package com.example.GradingSystem3.Controllers;

import com.example.GradingSystem3.Models.Course;
import com.example.GradingSystem3.Services.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
@Slf4j
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardToCoursePage(request, response);
    }

    @PostMapping
    public void getCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseId = request.getParameter("courseId");

        try {
            Course course = courseService.getCourse(Integer.parseInt(courseId));
            String avg = String.valueOf(courseService.getAvg(course.getId()));
            String min = String.valueOf(courseService.getMin(course.getId()));
            String max = String.valueOf(courseService.getMax(course.getId()));
            String median = String.valueOf(courseService.getMedian(course.getId()));

            request.setAttribute("course", course);
            request.setAttribute("avg", avg);
            request.setAttribute("max", max);
            request.setAttribute("min", min);
            request.setAttribute("median", median);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        forwardToCoursePage(request, response);
    }

    private void forwardToCoursePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/course.jsp").forward(request, response);
    }
}
