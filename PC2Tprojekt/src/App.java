import java.util.Scanner;

public class App {
	public static int pouzeCelaCisla(Scanner sc) {
		int cislo = 0;
		try	{
			cislo = sc.nextInt();
		}
		catch(Exception e) {
			System.out.println("error - enter a number: ");
			sc.nextLine();
			cislo = pouzeCelaCisla(sc);
		}
		return cislo;
	}
	
	public static void main(String[] args) {
        Integer newID;
        Integer id;
        String name;
        String surname;
        Integer yearOfBirth;

        Scanner scanner = new Scanner(System.in);
        Database database = new Database();
        TelecommunicationStudent.initMorseCode();
        newID = database.maxID();
        boolean run = true;
        while(run)
        {
            System.out.println(" 1 ... add new student");
            System.out.println(" 2 ... add new mark");
            System.out.println(" 3 ... remove student");
            System.out.println(" 4 ... student information");
            System.out.println(" 5 ... activate skill");
            System.out.println(" 6 ... list of students");
            System.out.println(" 7 ... group average");
            System.out.println(" 8 ... number of students");
            System.out.println(" 9 ... save student to file");
            System.out.println("10 ... import student from file");
            System.out.println(" 0 ... quit");

            Integer choice = pouzeCelaCisla(scanner);
            String group;
            switch(choice)
            {
                case 1:
                    newID++;
                    System.out.println("Select student group:");
                    System.out.println("(t = telecommunication / c = cybersecurity)");
                    group = scanner.next();
                    System.out.println("Enter student name");
                    name = scanner.next();
                    System.out.println("Enter student surname");
                    surname = scanner.next();
                    System.out.println("Enter student year of birth");
                    yearOfBirth = pouzeCelaCisla(scanner);
                    if(group.equals("t")) {
                        database.setStudent(newID, new TelecommunicationStudent(newID, name, surname, yearOfBirth));
                    }else if(group.equals("c")) {
                        database.setStudent(newID, new CybersecurityStudent(newID, name, surname, yearOfBirth));
                    }
                    System.out.println("Student added with id: " + newID);
                    break;
                case 2:
                    System.out.println("Enter student ID");
                    id = pouzeCelaCisla(scanner);
                    System.out.println("Enter new mark");
                    Integer mark = pouzeCelaCisla(scanner);
                    try {
                        database.addMark(id, mark);
                    }catch(Exception e) {
                        System.out.println("student not found");
                    }
                    break;
                case 3:
                    System.out.println("Enter student ID");
                    id = pouzeCelaCisla(scanner);
                    try {
                        database.removeStudent(id);
                    }catch(Exception e) {
                        System.out.println("student not found");
                    }
                    System.out.println("student removed");
                    break;
                case 4:
                    System.out.println("Enter student ID");
                    id = pouzeCelaCisla(scanner);
                    try {
                        System.out.println("name: " + database.getStudent(id).getName());
                        System.out.println("surname: " + database.getStudent(id).getSurname());
                        System.out.println("year of birth: " + database.getStudent(id).getYearOfBirth());
                        System.out.println("average mark: " + database.getStudent(id).getAverageMark());
                    }catch(Exception e) {
                        System.out.println("student not found");
                    }
                    break;
                case 5:
                    System.out.println("Enter student ID");
                    id = pouzeCelaCisla(scanner);
                    try {
                        database.getStudent(id).skill();
                    }catch(Exception e) {
                        System.out.println("student not found");
                    }
                    break;
                case 6:
                    System.out.println("Select student group:");
                    System.out.println("(t = telecommunication / c = cybersecurity)");
                    group = scanner.next();
                    if(group.equals("t")) {
                        database.telecomStudentsList().forEach(s -> System.out.println(s.getId()+": "+s.getName()+" "+s.getSurname()+" ("+s.getYearOfBirth()+"), "+s.getAverageMark()));
                    }else if(group.equals("c")) {
                        database.cybersecurityStudentsList().forEach(s -> System.out.println(s.getId()+": "+s.getName()+" "+s.getSurname()+" ("+s.getYearOfBirth()+"), "+s.getAverageMark()));
                    }
                    break;
                case 7:
                    System.out.println("Select student group:");
                    System.out.println("(t = telecommunication / c = cybersecurity)");
                    group = scanner.next();
                    if(group.equals("t")) {
                        System.out.println(database.telecommunicationAverage());
                    }else if(group.equals("c")) {
                        System.out.println(database.cybersecurityAverage());
                    }
                    break;
                case 8:
                    System.out.println("Telecommunication students: " + database.numberOfTelecommunicationStudents());
                    System.out.println("Cybersecurity students: " + database.numberOfCyberSecurityStudents());
                    break;
                case 9:
                    System.out.println("Enter student ID");
                    id = pouzeCelaCisla(scanner);
                    try {
                        if (FileIO.writeStudent(database.getStudent(id))) {
                            System.out.println("Student saved to file");
                        } else {
                            System.out.println("error");
                        }
                    }catch(Exception e) {
                        System.out.println("student not found");
                    }
                    break;
                case 10:
                    newID++;
                    FileIO.readStudent(newID);
                    Student student = FileIO.readStudent(newID);
                    if(student != null) {
                        database.setStudent(newID, student);
                        System.out.println("Student added with id: " + newID);
                    }else {
                        System.out.println("error");
                    }
                    break;
                case 0:
                    run = false;
                    SQL.newSQL();
                    database.saveDatabase();
                    break;
            }
        }
    }
}
