package Servlets.course;

import Servlets.Models.Course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CourseService {
    private CourseDao courseDao;

    public CourseService() throws SQLException {
        courseDao = new CourseDao();
    }

    public Course getCourse(int courseId) throws SQLException {
        ResultSet resultSet = courseDao.getCourseData(courseId);
        resultSet.next();
        return new Course(resultSet.getInt(1), resultSet.getString(2), null);
    }

    public double getAvg(int courseId) throws SQLException {
        ResultSet resultSet = courseDao.getData(courseId);
        double sum = 0;
        int size = 0;

        while (resultSet.next()) {
            sum += resultSet.getInt(1);
            size++;
        }

        if (size == 0) {
            return 0;
        }

        return sum / size;
    }

    public double getMin(int courseId) throws SQLException {
        ResultSet resultSet = courseDao.getData(courseId);
        double min = Double.POSITIVE_INFINITY;

        while (resultSet.next()) {
            min = Math.min(min, resultSet.getInt(1));
        }

        return min;
    }

    public double getMax(int courseId) throws SQLException {
        ResultSet resultSet = courseDao.getData(courseId);
        double max = Double.NEGATIVE_INFINITY;

        while (resultSet.next()) {
            max = Math.max(max, resultSet.getInt(1));
        }

        return max;
    }

    public double getMedian(int courseId) throws SQLException {
        ResultSet resultSet = courseDao.getData(courseId);
        List<Double> numbers = new ArrayList<>();

        while (resultSet.next()) {
            numbers.add(resultSet.getDouble(1));
        }

        int size = numbers.size();
        Collections.sort(numbers);

        if (size % 2 == 0) {
            int mid = size / 2;
            return (numbers.get(mid) + numbers.get(mid - 1)) / 2;
        } else {
            return numbers.get(size / 2);
        }
    }
}