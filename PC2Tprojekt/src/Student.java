import java.util.ArrayList;

abstract public class Student
{
    protected String group;
    protected Integer id;
    protected String name;
    protected String surname;
    private Integer yearOfBirth;
    private ArrayList<Integer> marks;

    public Student(Integer id, String name, String surname, Integer yearOfBirth)
    {
        group = "none";
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.yearOfBirth = yearOfBirth;
        this.marks = new ArrayList<Integer>();
    }

    abstract public void skill();

    public String getGroup(){
        return group;
    }
    public Integer getId() {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public String getSurname()
    {
        return surname;
    }
    public Integer getYearOfBirth()
    {
        return yearOfBirth;
    }
    public ArrayList<Integer> getMarks() {
        return marks;
    }
    public Float getAverageMark()
    {
        Float averageMark = 0.0f;
        for (Integer mark : this.marks) {
            averageMark += mark;
        }
        return averageMark / this.marks.size();
    }
    @Override
    public String toString(){
        return "\nID: "+id+"\nname: "+name+"\nsurname: "+surname+"\nyearOfBirth: "+yearOfBirth+"\nmarks: "+getMarks()+"\naverageMark: "+getAverageMark()+"\n\n";
    }
}
