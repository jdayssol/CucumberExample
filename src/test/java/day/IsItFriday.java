package day;

public class IsItFriday {
    public static String isItFriday(String today) {
    	if (today.equals("Friday")) {
            return "TGIF";
        }
    	return "Nope";
    }
}