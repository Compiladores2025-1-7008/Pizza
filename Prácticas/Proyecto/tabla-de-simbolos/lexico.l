%{
    #include "y.tab.h"
    int countn=0;
%}

%option yylineno

letra [a-zA-Z]
digito [0-9]
unario "++"|"--"

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
[-]?{digito}+                { return NUMERO; }
[-]?{digito}+\.{digito}{1,6}  { return FLOAT_NUM; }
{letra}({letra}|{digito})*   { return ID; }
{unario}                     { return UNARIO; }
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
[ \t]*                      { ; }
[\n]                        { countn++; }
.	                    { return *yytext; }
["].*["]                    { return STR; }
['].[']                     { return CHARACTER; }

%%

int yywrap() {
    return 1;
}
