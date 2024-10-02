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
%standalone
%unicode
%line

espacio=[ \t\n]

%%

{espacio}+ { }
"int"                    { System.out.println("Encontramos una palabra reservada"); return ClaseLexica.INT; }
"float"                  { System.out.println("Encontramos una palabra reservada"); return ClaseLexica.FLOAT; }
[a-zA-Z_][a-zA-Z_0-9]*   { System.out.println("Encontramos un identificador"); return ClaseLexica.IDENTIFICADOR; }
","                      { System.out.println("Encontramos una coma"); return ClaseLexica.COMA; }
";"                      { System.out.println("Encontramos un punto y coma"); return ClaseLexica.PUNTO_Y_COMA; }
"="                      { System.out.println("Encontramos una asignacion"); return ClaseLexica.ASIGNACION; }
"+"                      { System.out.println("Encontramos una suma"); return ClaseLexica.SUMA; }
"-"                      { System.out.println("Encontramos una resta"); return ClaseLexica.RESTA; }
[0-9]+                   { System.out.println("Encontramos un numero"); return ClaseLexica.NUMERO; }
<<EOF>>                  { System.out.println("Encontramos el final de la linea"); return ClaseLexica.EOF; }

// Manejar cualquier entrada no válida
. { System.err.println("Caracter no reconocido."); }
