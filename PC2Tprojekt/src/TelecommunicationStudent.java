import java.util.HashMap;
import java.util.Map;

public class TelecommunicationStudent extends Student {

    public TelecommunicationStudent(Integer id, String name, String surname, Integer yearOfBirth)
    {
        super(id, name, surname, yearOfBirth);
        this.group = "telecommunication";
    }

    public static Map<Character, String> morseCode;

    @Override
    public String toString(){
        return "Telecommunication Student" + super.toString();
    }
    @Override
    public void skill()
    {
        String morseCodeName = "";
        String nameAndSurname = (this.name + " " + this.surname).toLowerCase();
        for (int i = 0; i < nameAndSurname.length(); i++) {
           char c = nameAndSurname.charAt(i);
           morseCodeName += morseCode.get(c);
           morseCodeName += "  ";
        }
        System.out.println(morseCodeName);
    }

    public static void initMorseCode() {
        morseCode = new HashMap<Character, String>();
        morseCode.put('a', ".-");
        morseCode.put('b', "-...");
        morseCode.put('c', "-.-.");
        morseCode.put('d', "-..");
        morseCode.put('e', ".");
        morseCode.put('f', "..-.");
        morseCode.put('g', "--.");
        morseCode.put('h', "....");
        morseCode.put('i', "..");
        morseCode.put('j', ".---");
        morseCode.put('k', "-.-");
        morseCode.put('l', ".-..");
        morseCode.put('m', "--");
        morseCode.put('n', "-.");
        morseCode.put('o', "---");
        morseCode.put('p', ".--.");
        morseCode.put('q', "--.-");
        morseCode.put('r', ".-.");
        morseCode.put('s', "...");
        morseCode.put('t', "-");
        morseCode.put('u', "..-");
        morseCode.put('v', "...-");
        morseCode.put('w', ".--");
        morseCode.put('x', "-..-");
        morseCode.put('y', "-.--");
        morseCode.put('z', "--..");
        morseCode.put(' ', "   ");
    }
}

