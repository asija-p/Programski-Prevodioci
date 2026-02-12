// import sekcija
import java_cup.runtime.*;

%%

// sekcija deklaracija
%class MPLexer

%cup

%line
%column

%eofval{
    return new Symbol(sym.EOF);
%eofval}

%{
	public int getLine()
	{
		return yyline;
	}
%}

// stanja
%x KOMENTAR
// macros
slovo = [a-zA-Z]
cifra = [0-9]
hex_cifra = [0-9a-fA-F]
oct_cifra = [0-7]
%%
 
// rules section
// Komentari
"//".* { /* preskoci sve */ }

[\t\r\n ]+ { ; }

// operatori
":=" { return new Symbol(sym.ASSIGN); }
"+"  { return new Symbol(sym.PLUS); }
"-"  { return new Symbol(sym.MINUS); }

// separatori
";"  { return new Symbol(sym.SEMICOLON); }
","  { return new Symbol(sym.COMMA); }
"["  { return new Symbol(sym.LBRACKET); }
"]"  { return new Symbol(sym.RBRACKET); }

// kljucne reci
"main" { return new Symbol(sym.MAIN); }
"exit" { return new Symbol(sym.EXIT); }
"int" { return new Symbol(sym.INT); }
"float" { return new Symbol(sym.FLOAT); }
"bool" { return new Symbol(sym.BOOL); }
"for" { return new Symbol(sym.FOR); }
"in" { return new Symbol(sym.IN); }
"apply" { return new Symbol(sym.APPLY); }
"true" { return new Symbol(sym.TRUE); }
"false" { return new Symbol(sym.FALSE); }

// identifikatori
{slovo}({slovo}|{cifra})* { return new Symbol(sym.ID); }

// konstante
{cifra}+                     { return new Symbol(sym.CONST, yytext()); }
0x{hex_cifra}+               { return new Symbol(sym.CONST, yytext()); }
0{oct_cifra}+                { return new Symbol(sym.CONST, yytext()); }
{cifra}+"."{cifra}*([Ee][+-]?{cifra}+)? { return new Symbol(sym.CONST, yytext()); }
"."{cifra}+([Ee][+-]?{cifra}+)?         { return new Symbol(sym.CONST, yytext()); }

// ERROR HANDLING
. { System.out.println("LEXICAL ERROR: " + yytext() + " at line " + yyline + ", column " + yycolumn); }