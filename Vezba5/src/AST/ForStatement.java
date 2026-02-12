package AST;

import java.io.BufferedWriter;
import java.io.IOException;
import SymbolTable.Variable;

public class ForStatement extends Statement {
    private Variable id;         // The real variable (e.g., 'a')
    private Variable tempVar;    // The temporary iterator (e.g., 'x')
    private Statement expression; // The body of the loop
    
    // Updated constructor to accept the temp variable
    public ForStatement(Variable id, Variable tempVar, Statement expression) {
        this.id = id;
        this.tempVar = tempVar;
        this.expression = expression;
    }

    @Override
    public void translate(BufferedWriter out) throws IOException {
        // 1. Copy real variable (a) into temp (x)
        out.write("\tLoad\tR1, " + id.name); 
        out.newLine();
        out.write("\tStore\t" + tempVar.name + ", R1"); 
        out.newLine();

        // 2. Run the body (which uses x)
        expression.translate(out);

        // 3. Copy temp (x) back into real variable (a)
        out.write("\tLoad\tR1, " + tempVar.name); 
        out.newLine();
        out.write("\tStore\t" + id.name + ", R1"); // Fixed the quote typo here
        out.newLine();
    }
}