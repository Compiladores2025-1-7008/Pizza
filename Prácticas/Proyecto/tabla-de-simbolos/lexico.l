%{
    #include "y.tab.h"
    int countn=0;
%}

%option yylineno

alpha [a-zA-Z]
digit [0-9]
unary "++"|"--"

%%

"printf"                    { return PRINTFF; }
"scanf"                     { return SCANFF; }
"int"                       { return INT; }
"float"                     { return FLOAT; }
"char"                      { return CHAR; }
"void"                      { return VOID; }
"return"                    { return RETURN; }
"for"	                    { return FOR; }
"if"	                    { return IF; }
"else"	                    { return ELSE; }
^"#include"[ ]*<.+\.h>      { return INCLUDE; }
"true"                      { return TRUE; }
"false"                     { return FALSE; }
[-]?{digit}+                { return NUMERO; }
[-]?{digit}+\.{digit}{1,6}  { return FLOAT_NUM; }
{alpha}({alpha}|{digit})*   { return ID; }
{unary}                     { return UNARIO; }
"<="                        { return MENOR_O_IGUAL; }
">="                        { return MAYOR_O_IGUAL; }
"=="                        { return IGUALDAD; }
"!="                        { return DESIGUALDAD; }
">"	                    { return MAYOR; }
"<"	                    { return MENOR; }
"&&"	                    { return Y; }
"||"	                    { return O; }
"+"                         { return SUMA; }
"-"                         { return RESTA; }
"/"                         { return DIV; }
"*"                         { return MULT; }
\/\/.*                      { ; }
\/\*(.*\n)*.*\*\/           { ; } 
[ \t]*                      { ; }
[\n]                        { countn++; }
.	                    { return *yytext; }
["].*["]                    { return STR; }
['].[']                     { return CHARACTER; }

%%

int yywrap() {
    return 1;
}
