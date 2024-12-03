%%

%byaccj

%{
  private Parser yyparser;

  public Yylex(java.io.Reader r, Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
  }
%}

%%

"."  { return Parser.PUNTO; }
","  { return Parser.COMA; }
";"  { return Parser.PUNTO_Y_COMA; }
"("  { return Parser.PARENTESIS_IZQ; }
")"  { return Parser.PARENTESIS_DER; }
"{"  { return Parser.LLAVE_IZQ; }
"}"  { return Parser.LLAVE_DER; }
"["  { return Parser.CORCHETE_IZQ; }
"]"  { return Parser.CORCHETE_DER; }
"="  { return Parser.ASIGNACION; }
"+"  { return Parser.SUMA; }
"-"  { return Parser.RESTA; }
"*"  { return Parser.MULT; }
"/"  { return Parser.DIV; }
"%"  { return Parser.MODULO; }
"||" { return Parser.O; }
"&&" { return Parser.Y; }
">"  { return Parser.MAYOR; }
"<"  { return Parser.MENOR; }
">=" { return Parser.MAYOR_O_IGUAL; }
"<=" { return Parser.MENOR_O_IGUAL; }
"!=" { return Parser.DESIGUALDAD; }
"==" { return Parser.IGUALDAD; }

"def"      { return Parser.DEF; }
"struct"   { return Parser.STRUCT; }
"register" { return Parser.REGISTER; }
"int"      { return Parser.INT; }
"float"    { return Parser.FLOAT; }
"break"    { return Parser.BREAK; }
"return"   { return Parser.RETURN; }
"if"       { return Parser.IF; }
"else"     { return Parser.ELSE; }
"while"    { return Parser.WHILE; }
"for"      { return Parser.FOR; }
"true"     { return Parser.TRUE; }
"false"    { return Parser.FALSE; }

[a-zA-Z][a-zA-Z0-9]* { return Parser.ID; }
[0-9]+               { return Parser.NUM; }
[0-9]+\.[0-9]+			 { return Parser.NUM; }
[ \t\n]+             { }
.                    { System.err.println("Carácter no reconocido: " + yytext()); }
