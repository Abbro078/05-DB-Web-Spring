import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Client {
    private static Socket socket;
    private static InputStreamReader inputStreamReader;
    private static ObjectInputStream objectInputStream;
    private static PrintWriter printWriter;
    private static BufferedReader bufferedReader;
    private static Scanner scanner;
    private static Boolean isLoggedIn;
    private static Student student;

    public Client(Socket socket) throws IOException {
        Client.socket = socket;
        inputStreamReader = new InputStreamReader(socket.getInputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        printWriter= new PrintWriter(socket.getOutputStream());
        bufferedReader = new BufferedReader(inputStreamReader);
        scanner = new Scanner(System.in);
        isLoggedIn=false;
    }

    public void getInstructions () throws IOException {
        while (!socket.isClosed()) {
            System.out.println("Instructions : (Press the number of the instruction wanted)");
            System.out.println("1- User Information");
            System.out.println("2- User enrolled courses");
            System.out.println("3- sign out from the account");
            String temp = scanner.nextLine();
            if (temp.equals("3")) {
                isLoggedIn=false;
                student=null;
                break;
            }
            else if(temp.equals("1")){
                userInformation();
            }
            else if(temp.equals("2")){
                userCourses();
            }
        }

    }
    public void login() throws IOException, ClassNotFoundException {

        while(!isLoggedIn) {
            System.out.print("Please Enter Your Email : ");
            String email = scanner.nextLine();
            System.out.print("Please Enter Your Password : ");
            String password = scanner.nextLine();
            printWriter.println("login");
            printWriter.flush();
            printWriter.println(email);
            printWriter.flush();
            printWriter.println(password);
            printWriter.flush();
            Boolean isExist = Boolean.valueOf(bufferedReader.readLine());
            if (isExist){
                isLoggedIn=true;
                Object object = objectInputStream.readObject();
                student = (Student) object;
            }
            else {
                System.out.println("Please re-enter your credentials ");
            }
        }

    }
    public void start() throws IOException, ClassNotFoundException {
        while(!socket.isClosed()) {
            if (!isLoggedIn) {
                login();
            } else {
                getInstructions();
            }
        }
    }

    public void userInformation(){
        System.out.println("Name : "+student.getName());
        System.out.println("Email : "+student.getEmail());
        System.out.println("Major : "+student.getMajor());
        System.out.println("Courses : ");
        for (Course course:  student.getCourses()) {
            System.out.println(course.getName());
        }

        System.out.println();
    }
    public void userCourses() throws IOException {
        Map<Integer, Course> courseMap = new HashMap<>();
        int courseNumber = 1;
        System.out.println("Course\t\t\t\tMark");
        for (Course course : student.getCourses()) {
            System.out.println(courseNumber + "- " + course.getName() + "\t\t\t" + course.getMark());
            courseMap.put(courseNumber++, course);
        }
        while (true) {
            System.out.println("Instructions : ");
            System.out.println("<Course Number> : Course information (Average, Minimum mark...etc)");
            System.out.println("e- exit : back to the main screen");
            String inst = scanner.nextLine();
            if (inst.equals("e") || inst.equals("E")) {
                break;
            } else {
                try {
                    int courseNum = Integer.parseInt(inst);
                    Course selectedCourse = courseMap.get(courseNum);
                    if (selectedCourse == null) {
                        System.out.println("Please re-enter the wanted instruction.");
                        continue;
                    }
                    int courseId = selectedCourse.getId();
                    printWriter.println("course:" + courseId);
                    printWriter.flush();
                    double min, max, avg, median;
                    min = Double.parseDouble(bufferedReader.readLine());
                    max = Double.parseDouble(bufferedReader.readLine());
                    avg = Double.parseDouble(bufferedReader.readLine());
                    median = Double.parseDouble(bufferedReader.readLine());
                    System.out.println("Minimum mark : " + min);
                    System.out.println("Maximum mark : " + max);
                    System.out.println("Average mark : " + avg);
                    System.out.println("Median mark : " + median);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid instruction.");
                }
            }
        }
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost",33333);
        Client client = new Client(socket);
        System.out.println("Grading System");
        client.start();
    }

}
