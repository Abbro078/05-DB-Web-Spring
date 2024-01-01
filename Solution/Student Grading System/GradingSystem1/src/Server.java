import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.*;

public class Server {
    private static ServerSocket serverSocket;
    private static Scanner scanner;
    Connection connection;
    Statement statement ;
    Socket clientSocket;
    InputStreamReader inputStreamReader;
    ObjectOutputStream objectOutputStream;
    PrintWriter printWriter;
    BufferedReader bufferedReader;

    public Server(ServerSocket socket) throws IOException, SQLException {
        serverSocket =socket;
        scanner = new Scanner(System.in);
        String url ="jdbc:mysql://localhost:3306/atypon";
        String uName = "root";
        String uPassword = "admin";
        connection = DriverManager.getConnection(url,uName,uPassword);
        statement = connection.createStatement();
        clientSocket = serverSocket.accept();
        inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
        objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        printWriter= new PrintWriter(clientSocket.getOutputStream());
        bufferedReader = new BufferedReader(inputStreamReader);

    }
    public void executeInstructions(){
        try {
            while(!clientSocket.isClosed()) {
                String inst = bufferedReader.readLine();
                if (inst.equals("login")) {
                    login();
                }
                else if(inst.substring(0,6).equals("course")){
                    getCourseData(inst);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public Boolean isExist(String email,String password) throws SQLException {
        String query = "select * from Student";
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()){

            String dEmail = resultSet.getString(2);
            String dPassword = resultSet.getString(3);

            if (dEmail.equals(email)&&dPassword.equals(password)){
                return true;
            }
        }
        return false;
    }

    public Student getData(String email) throws SQLException {
        List<Course> courses = new ArrayList<>();
        String courseQuery = "select course.Course_ID,course.Course_Name,studentcourserelation.Mark from (\n" +
                "(studentcourserelation inner join student on student.Student_ID = studentcourserelation.Student_ID)\n" +
                "inner join course on course.Course_ID=studentcourserelation.Course_ID\n" +
                ") where student.Student_Email=\""+email+"\";";
        ResultSet courseSet = statement.executeQuery(courseQuery);
        while(courseSet.next()) {
            courses.add(new Course(courseSet.getInt(1),courseSet.getString(2),courseSet.getInt(3)));
        }
        String studentQuery = "select Student_ID,Student_Email,Student_Name,Major from student where student.Student_Email=\""+email+"\";";
        ResultSet studentSet = statement.executeQuery(studentQuery);
        studentSet.next();
        return new Student(studentSet.getInt(1),studentSet.getString(2),studentSet.getString(3),studentSet.getString(4),courses);

    }
    public void login() throws IOException, SQLException {
        String email = bufferedReader.readLine();
        String password = bufferedReader.readLine();
        Boolean isExist = isExist(email, password);
        printWriter.println(isExist);
        printWriter.flush();
        if (isExist) {
            objectOutputStream.writeObject(getData(email));
        }
    }
    public void getCourseData(String inst) throws SQLException {
        int courseId =Integer.parseInt(inst.substring(7));
        String marksQuery= "select studentcourserelation.Mark from studentcourserelation where Course_ID="+courseId+";";
        ResultSet resultSet = statement.executeQuery(marksQuery);
        ArrayList<Double>numbers = new ArrayList<>();
        double sum=0,max=0,min=1000000000,avg,median;
        int size=0;
        while(resultSet.next()){
            sum+=resultSet.getInt(1);
            max=Math.max(max,resultSet.getInt(1));
            min=Math.min(min,resultSet.getInt(1));
            size++;
            numbers.add(resultSet.getDouble(1));
        }
        Collections.sort(numbers);
        avg = sum/size;
        if (size % 2 == 0)
            median = (numbers.get(size/2)  + numbers.get(size/2 - 1))/2;
        else
            median =  numbers.get(size/2);

        printWriter.println(min);
        printWriter.flush();
        printWriter.println(max);
        printWriter.flush();
        printWriter.println(avg);
        printWriter.flush();
        printWriter.println(median);
        printWriter.flush();

    }

    public static void main(String[] args) throws IOException, SQLException {

        ServerSocket serverSocket= new ServerSocket(33333);
        Server server = new Server(serverSocket);
        server.executeInstructions();

    }
}