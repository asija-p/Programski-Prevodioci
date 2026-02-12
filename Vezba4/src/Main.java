import java_cup.runtime.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("src/testinput.txt");
            MPLexer lexer = new MPLexer(reader);
            MPParser parser = new MPParser(lexer);
            
            System.out.println("Započeto parsiranje...");
            parser.parse();
            System.out.println("Parsiranje uspešno završeno!");
            
        } catch (Exception e) {
            System.out.println("Greška prilikom parsiranja: " + e.getMessage());
            e.printStackTrace();
        }
    }
}