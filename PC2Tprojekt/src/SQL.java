import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQL {
    static Connection con = null;
    static Statement st = null;

    public static Map<Integer, Student> loadDatabase()
    {
        Map<Integer, Student> studentMap = new HashMap<Integer, Student>();
        try {
            con = DriverManager.getConnection("jdbc:sqlite::memory:mydb.db");
            Statement studentSt = con.createStatement();
            ResultSet rs = studentSt.executeQuery("SELECT * FROM students");

            while (rs.next()) {
                String group = rs.getString("student_group");
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                Integer year = rs.getInt("year");

                PreparedStatement markSt = con.prepareStatement("SELECT mark FROM marks WHERE student_id = ?");
                markSt.setInt(1, id);
                ResultSet markRs = markSt.executeQuery();

                List<Integer> marks = new ArrayList<>();
                while (markRs.next()) {
                    marks.add(markRs.getInt("mark"));
                }
                Student student = null;
                if (group.equalsIgnoreCase("telecommunication")) {
                    student = new TelecommunicationStudent(id, name, surname, year);
                } else if (group.equalsIgnoreCase("cybersecurity")) {
                    student = new CybersecurityStudent(id, name, surname, year);
                }
                if (student != null) {
                    student.getMarks().addAll(marks);
                }
                studentMap.put(id, student);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return studentMap;
    }
    public static void newSQL()
    {
        try{
            con = DriverManager.getConnection("jdbc:sqlite::memory:mydb.db");
            st = con.createStatement();
            st.execute("DROP TABLE IF EXISTS marks");
            st.execute("DROP TABLE IF EXISTS students");
            st.execute("CREATE TABLE IF NOT EXISTS students (student_group VARCHAR(50), id INTEGER PRIMARY KEY, name VARCHAR(50), surname VARCHAR(50), year INTEGER)");
            st.execute("CREATE TABLE IF NOT EXISTS marks (student_id INTEGER, mark INTEGER, FOREIGN KEY (student_id) REFERENCES students(id))");
        }catch(Exception e)
        {
        System.out.println(e.getMessage());
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static void saveStudent(Student student)
    {
        try {
            con = DriverManager.getConnection("jdbc:sqlite::memory:mydb.db");
            con.setAutoCommit(false);
            PreparedStatement studentSt = con.prepareStatement("INSERT OR REPLACE INTO students (student_group, id, name, surname, year) VALUES (?, ?, ?, ?, ?)");
            studentSt.setString(1, student.getGroup());
            studentSt.setInt(2, student.getId());
            studentSt.setString(3, student.getName());
            studentSt.setString(4, student.getSurname());
            studentSt.setInt(5, student.getYearOfBirth());
            studentSt.executeUpdate();

            PreparedStatement markSt = con.prepareStatement("INSERT INTO marks (student_id, mark) VALUES (?, ?)");
            for (Integer mark : student.getMarks()) {
                markSt.setInt(1, student.getId());
                markSt.setInt(2, mark);
                markSt.addBatch();
            }
            markSt.executeBatch();
            con.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
