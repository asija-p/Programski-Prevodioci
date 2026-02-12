// import sekcija

%%

// sekcija opcija i deklaracija
%class MPLexer
%function next_token
%line
%column
%type Yytoken

%eofval{
return new Yytoken( sym.EOF, null, yyline, yycolumn);
%eofval}

%{
//dodatni clanovi generisane klase
KWTable kwTable = new KWTable();
Yytoken getKW()
{
	return new Yytoken( kwTable.find( yytext() ),
	yytext(), yyline, yycolumn );
}
%}

//stanja
%xstate COMMENT
//makroi
slovo = [a-zA-Z]
cifra = [0-9]

%%

// pravila

"//".* { /* KOMENTAR // */ }

[\t\r\n ] { ; }

//operatori
":=" { return new Yytoken(sym.ASSIGN, yytext(), yyline, yycolumn); }
"+" { return new Yytoken(sym.PLUS, yytext(), yyline, yycolumn); }
"-" { return new Yytoken(sym.MINUS, yytext(), yyline, yycolumn); }

//separatori
";" { return new Yytoken(sym.SEMICOLON, yytext(), yyline, yycolumn); }
"," { return new Yytoken(sym.COMMA, yytext(), yyline, yycolumn); }
"[" { return new Yytoken(sym.LBRACKET, yytext(), yyline, yycolumn); }
"]" { return new Yytoken(sym.RBRACKET, yytext(), yyline, yycolumn); }

//kljucne reci
{slovo}+ { return getKW(); }

//identifikatori
{slovo}({slovo}|{cifra})* { return new Yytoken(sym.ID, yytext(), yyline, yycolumn); }

//konstante
(0[0-7]+)|(0x[0-9a-fA-F]+)|([0-9]+) {
    return new Yytoken(sym.CONST, yytext(), yyline, yycolumn);
}

//float
0\.{cifra}+(E[+-]?{cifra}+)? {
    return new Yytoken(sym.CONST, yytext(), yyline, yycolumn);
}

//obrada gresaka
. {
    System.out.println("LEXICAL ERROR: " + yytext()
        + " at line " + yyline + ", column " + yycolumn);
}
