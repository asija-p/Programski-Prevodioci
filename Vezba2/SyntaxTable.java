public class SyntaxTable {
    public static final int ERR = -1;
    public static final int POP = -2;
    public static final int ACC = -3;

    // Maksimalno neterminali + terminali za matricu
    public static int[][] M = new int[40][40];
    
    static {
        // Inicijalizacija na ERR
        for(int i = 0; i < 40; i++)
            for(int j = 0; j < 40; j++)
                M[i][j] = ERR;

        // POP akcije
        int[] terminals = {sym.FOR, sym.IN, sym.LBRACKET, sym.RBRACKET, sym.APPLY, sym.ID, sym.CONST, sym.PLUS, sym.COMMA, sym.SEMICOLON, sym.EOF};
        for(int t : terminals)
            M[t][t] = POP;

        // ACC akcija
        M[sym.EOF][sym.EOF] = ACC;

        // RULE akcije (pravila) – numerisana 1..n
        M[sym.APPLY_EXPR][sym.FOR] = 1;      // ApplyExpression -> for ID in [ NameList ] apply Expression
        M[sym.NAMELIST][sym.ID] = 2;         // NameList -> ID NameList'
        M[sym.NAMELIST_P][sym.COMMA] = 3;    // NameList' -> , ID NameList'
        M[sym.NAMELIST_P][sym.RBRACKET] = 4; // NameList' -> epsilon
        M[sym.EXPRESSION][sym.ID] = 5;       // Expression -> Term Expression'
        M[sym.EXPRESSION][sym.CONST] = 5;
        M[sym.EXPRESSION_P][sym.PLUS] = 6;   // Expression' -> + Term Expression'
        M[sym.EXPRESSION_P][sym.RBRACKET] = 7; // Expression' -> epsilon
        M[sym.EXPRESSION_P][sym.EOF] = 7;
        M[sym.TERM][sym.ID] = 8;             // Term -> ID
        M[sym.TERM][sym.CONST] = 9;          // Term -> CONST
    }
}
