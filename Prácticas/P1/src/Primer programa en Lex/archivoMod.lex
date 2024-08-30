/**
 * Escáner que detecta números y palabras
*/

%%

%public
%class Lexer
%standalone

digito=[0-9]
letra=[a-zA-Z]

hexadecimal=0[xX][0-9a-fA-F]+
palabraReservada="for"|"return"|"public"|"static"|"void"
identificador=({letra}|_|\$){1}({letra}|{digito}|_|\$){0,31}
espacio=[ \t\n]

%%

{espacio} { System.out.print("Encontré un espacio\n"); }
{hexadecimal} { System.out.print("Encontré un hexadecimal: "+yytext()+"\n"); }
{palabraReservada} { System.out.print("Encontré una palabra reservada: "+yytext()+"\n"); }
{identificador} { System.out.print("Encontré un identificador: "+yytext()+"\n"); }
