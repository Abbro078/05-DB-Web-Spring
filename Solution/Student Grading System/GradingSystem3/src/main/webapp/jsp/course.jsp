<%@ page import="com.example.GradingSystem3.Models.Course" %>
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
        }

        .main {
            padding: 20px;
        }

        .course-header {
            font-size: 24px;
            margin-bottom: 10px;
        }

        .course-information {
            display: flex;
            justify-content: space-between;
        }

        .course-information div {
            text-align: center;
            flex: 1;
            padding: 10px;
            background-color: #f2f2f2;
        }

        .back-button {
            margin-top: 20px;
            text-align: center;
        }

        .back-button button {
            padding: 10px 30px;
            background-color: #353c4e;
            color: #ffffff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .back-button button:hover {
            background-color: #2c3144;
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
                    <span>Logout</span>
                </div>
            </button>
        </form>
    </div>
</div>
<div class="main">
    <div class="information">
        <div class="courses">
            <div class="wrapper">
                <div class="course-header">
                    <% Course course = (Course) request.getAttribute("course"); %>
                    <h3><%= course.getName() %>
                    </h3>
                </div>
                <div class="course-information">
                    <% String avg = (String) request.getAttribute("avg"); %>
                    <div>
                        AVG
                        <span><%= avg %></span>
                    </div>
                    <% String median = (String) request.getAttribute("median"); %>
                    <div>
                        MED
                        <span><%= median %></span>
                    </div>
                    <% String max = (String) request.getAttribute("max"); %>
                    <div>
                        MAX
                        <span><%= max %></span>
                    </div>
                    <% String min = (String) request.getAttribute("min"); %>
                    <div>
                        MIN
                        <span><%= min %></span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="back-button">
    <button onclick="goBack()">Go Back</button>
</div>
<script>
    function goBack() {
        window.history.back();
    }
</script>
</body>
</html>
