import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duplicate
{
    public static void main(String args[])
    {
        String regex = "\\b([a-z]+)\\b(?:\\s+\\1\\b)+";
		Pattern pt = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        
        Scanner sc = new Scanner(System.in);
		System.out.println("Eneter no.of line of text");
        int test=Integer.parseInt(sc.nextLine());
        
        while(test>0)
        {
            String input=sc.nextLine();
            Matcher mat=pt.matcher(input);
            boolean find=true;
            
            while(mat.find())
            {
                input = input.replaceAll(mat.group(), mat.group(1));
                find= false;
            }
			System.out.println("output");
            System.out.println(input);
            test--;
        }
        sc.close();
    }

}