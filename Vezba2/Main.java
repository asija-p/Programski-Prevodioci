import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("testinput.txt");
            MPLexer lexer = new MPLexer(fr);

            LL1Parser parser = new LL1Parser(lexer);
            if (parser.sa_ll1()) {
                System.out.println("Sintaksna analiza uspesna! Kod je ispravan.");
            } else {
                System.out.println("Sintaksna analiza neuspesna!");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
