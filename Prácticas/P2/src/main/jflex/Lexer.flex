/**
 * Escáner que detecta el lenguaje C_1
*/
package main.jflex;

import main.java.ClaseLexica;
import main.java.Token;

%%

%public
%class Lexer
%standalone
%unicode

%{

public Token actual;

%}

espacio=[ \t\n]

%%

{espacio} { /* La acción léxica puede ir vacía si queremos que el escáner ignore la regla  */ }

"int"          { actual = new Token(ClaseLexica.INT, yytext()); System.out.println(actual); }
"float"        { actual = new Token(ClaseLexica.FLOAT, yytext()); System.out.println(actual); }
"if"           { actual = new Token(ClaseLexica.IF, yytext()); System.out.println(actual); }
"else"         { actual = new Token(ClaseLexica.ELSE, yytext()); System.out.println(actual); }
"while"        { actual = new Token(ClaseLexica.WHILE, yytext()); System.out.println(actual); }

[0-9]+         { actual = new Token(ClaseLexica.NUMERO, yytext()); System.out.println(actual); }
[0-9]+(\.[0-9]+)?([eE][-+]?[0-9]+)? { actual = new Token(ClaseLexica.NUMERO, yytext()); System.out.println(actual); }

[_a-zA-Z][_a-zA-Z0-9]*  { actual = new Token(ClaseLexica.ID, yytext()); System.out.println(actual); }

";"           { actual = new Token(ClaseLexica.PYC, yytext()); System.out.println(actual); }
","           { actual = new Token(ClaseLexica.COMA, yytext()); System.out.println(actual); }
"\("          { actual = new Token(ClaseLexica.LPAR, yytext()); System.out.println(actual); }
"\)"          { actual = new Token(ClaseLexica.RPAR, yytext()); System.out.println(actual); }

.             { System.out.println("Error léxico: " + yytext()); }
