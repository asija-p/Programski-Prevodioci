import java.util.Stack;

public class LL1Parser {
    private MPLexer lexer;
    private Yytoken next;
    private Stack<Integer> stack = new Stack<>();

    public LL1Parser(MPLexer lexer) {
        this.lexer = lexer;
    }

    public boolean sa_ll1() throws Exception {
        for (int i = 0; i < SyntaxTable.M.length; i++) {
            for (int j = 0; j < SyntaxTable.M[i].length; j++) {
                System.out.printf("%4d", SyntaxTable.M[i][j]);
            }
            System.out.println();
        }

        stack.push(sym.EOF);
        stack.push(sym.APPLY_EXPR); // start simbol

        next = lexer.next_token();
        boolean prepoznat = false;
        boolean greska = false;

        while(!stack.isEmpty() && !prepoznat && !greska) {
            int top = stack.peek();
            int action = SyntaxTable.M[top][next.m_index];

            if(action == SyntaxTable.POP) {
                stack.pop();
                next = lexer.next_token();
            } else if(action == SyntaxTable.ACC) {
                prepoznat = true;
            } else if(action == SyntaxTable.ERR) {
                greska = true;
                System.err.println("Sintaksna greska kod tokena: " + next.m_text + 
                                   " na liniji " + (next.m_line + 1));
            } else {
                stack.pop();
                pushRule(action);
            }
        }

        return prepoznat;
    }

    private void pushRule(int k) {
        switch(k) {
            case 1: // ApplyExpression -> for ID in [ NameList ] apply Expression
                stack.push(sym.EXPRESSION);
                stack.push(sym.APPLY);
                stack.push(sym.RBRACKET);
                stack.push(sym.NAMELIST);
                stack.push(sym.LBRACKET);
                stack.push(sym.IN);
                stack.push(sym.ID);
                stack.push(sym.FOR);
                break;
            case 2: // NameList -> ID NameList'
                stack.push(sym.NAMELIST_P);
                stack.push(sym.ID);
                break;
            case 3: // NameList' -> , ID NameList'
                stack.push(sym.NAMELIST_P); // epsilon recursion at the top
                stack.push(sym.ID);         // ID next
                stack.push(sym.COMMA);      // COMMA first (bottom)
                break;
            case 4: // NameList' -> epsilon
                // ništa
                break;
            case 5: // Expression -> Term Expression'
                stack.push(sym.EXPRESSION_P);
                stack.push(sym.TERM);
                break;
            case 6: // Expression' -> + Term Expression'
                stack.push(sym.EXPRESSION_P);
                stack.push(sym.TERM);
                stack.push(sym.PLUS);
                break;
            case 7: // Expression' -> epsilon
                break;
            case 8: // Term -> ID
                stack.push(sym.ID);
                break;
            case 9: // Term -> CONST
                stack.push(sym.CONST);
                break;
        }
    }
}
