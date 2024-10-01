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
"int"                    { return ClaseLexica.INT; }
"float"                  { return ClaseLexica.FLOAT; }
[a-zA-Z_][a-zA-Z_0-9]*   { return ClaseLexica.IDENTIFICADOR; }
","                      { return ClaseLexica.COMA; }
";"                      { return ClaseLexica.PUNTO_Y_COMA; }
"="                      { return ClaseLexica.ASIGNACION; }
"+"                      { return ClaseLexica.SUMA; }
"-"                      { return ClaseLexica.RESTA; }
[0-9]+                   { return ClaseLexica.NUMERO; }
<<EOF>>                  { return ClaseLexica.EOF; }
