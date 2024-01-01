<%@ page import="Servlets.Models.Course" %>
<%@ page import="Servlets.Models.Student" %>
<!DOCTYPE html>
<html>
<head>
    <title>Grading System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            color: #353c4e;
        }
        .grading-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #f2f2f2;
            padding: 10px;
        }
        .grading-header h2 {
            margin: 0;
            color: #353c4e;
        }
        .main {
            padding: 20px;
        }
        .information {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }
        .left h4 {
            margin-bottom: 10px;
        }
        .info {
            margin-bottom: 20px;
        }
        .info h3 {
            margin-bottom: 10px;
            color: #353c4e;
        }
        .info_data {
            display: flex;
        }
        .data {
            margin-right: 30px;
        }
        .data h4 {
            color: #353c4e;
            font-weight: bold;
            margin-bottom: 5px;
        }
        .courses {
            margin-top: 20px;
        }
        .courses h3 {
            margin-bottom: 40px;
            color: #353c4e;
        }
        .course-name button {
            color: #353c4e;
            margin-bottom: -5px;
            font-weight: bold;
            border: none;
            font-size: 16px;
            background: none;
            cursor: pointer;
        }
        .projects_data .data {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="grading-header">
    <div>
        <h2>Grading System</h2>
    </div>
    <div>
        <form action="/login" method="GET">
            <button type="submit" style="background: none; border: none; cursor: pointer;">
                <div style="display: flex; flex-direction: column; align-items: center;">
                    <span style="color: #353c4e;">Logout</span>
                </div>
            </button>
        </form>
    </div>
</div>
<div class="main">
    <div class="information">
        <div class="wrapper">
            <div class="left">
                <h4>${student.getName()}</h4>
            </div>
            <div class="right">
                <div class="info">
                    <h3>Information</h3>
                    <div class="info_data">
                        <div class="data">
                            <h4>Email</h4>
                            <p>${student.getEmail()}</p>
                        </div>
                        <div class="data">
                            <h4>Major</h4>
                            <p>${student.getMajor()}</p>
                        </div>
                    </div>
                </div>

                <div class="courses" id="style-2">
                    <h3>Courses</h3>
                    <div class="projects_data">
                        <% Student student = (Student) request.getAttribute("student");
                            for (Course course:student.getCourses()) { %>
                        <div class="data">
                            <div class="course-name">
                                <form method="POST" action="/course">
                                    <input name="courseId" value="<%=course.getId()%>" hidden>
                                    <button type="submit">
                                        <%=course.getName()%>
                                    </button>
                                </form>
                            </div>
                            <p>
                                <%=course.getMark()%>
                            </p>
                        </div>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>