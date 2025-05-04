import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileIO {
    public static boolean writeStudent(Student student) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("studentOUTPUT.txt", true))) {
            bw.write(student.toString());
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public static Student readStudent(Integer id) {
        Student student = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("studentINPUT.txt"))) {
            String line;
            Character group = null;
            String name = "";
            String surname = "";
            Integer yearOfBirth = null;
            List<Integer> marks = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("t") || line.startsWith("T")) {
                    group = 't';
                } else if (line.startsWith("c") || line.startsWith("C")) {
                    group = 'c';
                } else if (line.startsWith("name:")) {
                    name = line.substring(6).trim();
                } else if (line.startsWith("surname:")) {
                    surname = line.substring(9).trim();
                } else if (line.startsWith("year of birth:")) {
                    yearOfBirth = Integer.parseInt(line.substring(15).trim());
                } else if (line.startsWith("marks:")) {
                    String marksString = line.substring(7);
                    String[] parts = marksString.split(",");
                    for (String part : parts) {
                        marks.add(Integer.parseInt(part.trim()));
                    }
                }
            }
            if (group == 't') {
                student = new TelecommunicationStudent(id, name, surname, yearOfBirth);
            }else if (group == 'c') {
                student = new CybersecurityStudent(id, name, surname, yearOfBirth);
            }
            student.getMarks().addAll(marks);
        } catch (IOException e) {
        }
        return student;
    }
}
