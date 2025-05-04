public class CybersecurityStudent extends Student
{
    public CybersecurityStudent(Integer id, String name, String surname, Integer yearOfBirth)
    {
        super(id, name, surname, yearOfBirth);
        this.group = "cybersecurity";
    }

    @Override
    public String toString(){
        return "Cybersecurity Student" + super.toString();
    }
    @Override
    public void skill()
    {
        Integer hash = 0;
        String nameAndSurname = (this.name + " " + this.surname).toLowerCase();
        for (int i = 0; i < nameAndSurname.length(); i++){
            hash += nameAndSurname.charAt(i) * (i + 123);
        }
        System.out.println(100000 + (hash % 900000));
    }
}
