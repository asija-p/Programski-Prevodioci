import java_cup.runtime.*;
import java.io.*;
import AST.ASTNode;

public class Main {

    public static void main(String[] args) {
        try {
            // Definišemo ime fajla na jednom mestu
            String inputFileName = "src/Primer.mp";
            
            FileReader reader = new FileReader(inputFileName);
            MPLexer lexer = new MPLexer(reader);
            MPParser parser = new MPParser(lexer);
            
            // Parsiranje
            ASTNode ast = (ASTNode) parser.parse().value;
            
            // --- ISPRAVLJEN DEO ---
            // Umesto args[0], koristimo string koji smo gore definisali
            String outFileName = inputFileName.substring(0, inputFileName.indexOf(".") + 1);
            outFileName += "ic"; // Rezultat će biti src/Primer.ic
            
            System.out.println("Generisanje u fajl: " + outFileName);
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(outFileName));
            
            // Generisanje koda (ovo radi ApplyNode.translate i ostali čvorovi)
            ast.translate(writer);
            
            writer.close();
            System.out.println("Gotovo!");
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}