import java.util.*;
import java.util.stream.Collectors;

public class Database
{
    private Map<Integer, Student> students;
    public Database()
    {
        students = SQL.loadDatabase();
    }
    public void setStudent(Integer id, Student student)
    {
        students.put(id, student);
    }
    public Student getStudent(Integer id)
    {
        return students.get(id);
    }
    public void addMark(Integer id, Integer mark)
    {
        students.get(id).getMarks().add(mark);
    }
    public void removeStudent(Integer id)
    {
        students.remove(id);
    }
    public Integer maxID()
    {
        return students.keySet().stream()
            .max(Integer::compareTo)
            .orElse(0);
    }
    public List<TelecommunicationStudent> telecomStudentsList ()
    {
        return students.values().stream()
                .filter(TelecommunicationStudent.class::isInstance)
                .map(TelecommunicationStudent.class::cast)
                .sorted(Comparator.comparing(Student::getSurname))
                .collect(Collectors.toList());
    }
    public List<CybersecurityStudent> cybersecurityStudentsList()
    {
        return students.values().stream()
                .filter(CybersecurityStudent.class::isInstance)
                .map(CybersecurityStudent.class::cast)
                .sorted(Comparator.comparing(Student::getSurname))
                .collect(Collectors.toList());
    }
    public Float telecommunicationAverage()
    {
        Float groupAverage = 0.f;
        Integer count = 0;
        for(Student student : students.values()) {
            if(student instanceof TelecommunicationStudent) {
                groupAverage += student.getAverageMark();
                count++;
            }
        }
        return count > 0 ? groupAverage/count : 0f;
    }
    public Float cybersecurityAverage()
    {
        Float groupAverage = 0.f;
        Integer count = 0;
        for(Student student : students.values()) {
            if(student instanceof CybersecurityStudent) {
                groupAverage += student.getAverageMark();
                count++;
            }
        }
        return count > 0 ? groupAverage/count : 0f;
    }
    public Integer numberOfTelecommunicationStudents()
    {
        Integer count = 0;
        for(Student student : students.values()) {
            if(student instanceof TelecommunicationStudent) {
                count++;
            }
        }
        return count;
    }
    public Integer numberOfCyberSecurityStudents()
    {
        Integer count = 0;
        for(Student student : students.values()) {
            if(student instanceof CybersecurityStudent) {
                count++;
            }
        }
        return count;
    }
    public void saveDatabase(){
        for(Student student : students.values()) {
            SQL.saveStudent(student);
        }
    }
}
