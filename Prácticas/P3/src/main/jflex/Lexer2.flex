package main.jflex;

import main.java.ClaseLexica;
import main.java.Token;

%%

%{
public Token actual;
public int getLine() { return yyline; }
%}

%public
%class Lexer
%function yylex
%type Token
%unicode

espacio=[ \t\n]

%%

// Ignorar espacios
{espacio}+ { }

// Definición de tokens
"int"       { actual = new Token(ClaseLexica.INT, yytext()); System.out.println(actual); return actual; }
"float"     { actual = new Token(ClaseLexica.FLOAT, yytext()); System.out.println(actual); return actual; }
[a-zA-Z_][a-zA-Z_0-9]* { actual = new Token(ClaseLexica.IDENTIFICADOR, yytext()); System.out.println(actual); return actual; }
","         { actual = new Token(ClaseLexica.COMA, yytext()); System.out.println(actual); return actual; }
";"         { actual = new Token(ClaseLexica.PUNTO_Y_COMA, yytext()); System.out.println(actual); return actual; }
"="         { actual = new Token(ClaseLexica.ASIGNACION, yytext()); System.out.println(actual); return actual; }
"+"         { actual = new Token(ClaseLexica.SUMA, yytext()); System.out.println(actual); return actual; }
"-"         { actual = new Token(ClaseLexica.RESTA, yytext()); System.out.println(actual); return actual; }
[0-9]+      { actual = new Token(ClaseLexica.NUMERO, yytext()); System.out.println(actual); return actual; }
<<EOF>>     { actual = new Token(ClaseLexica.EOF, "EOF"); System.out.println(actual); return actual; }

// Manejar cualquier entrada no válida
. { System.err.println("Caracter no reconocido: " + yytext()); }
