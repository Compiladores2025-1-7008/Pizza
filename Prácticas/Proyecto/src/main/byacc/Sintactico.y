%{
  import java.io.*;
%}

%token PUNTO_Y_COMA DEF ID PARENTESIS_IZQ PARENTESIS_DER
%token LLAVE_IZQ LLAVE_DER REGISTER INT FLOAT 
%token CORCHETE_IZQ NUM CORCHETE_DER COMA BREAK RETURN
%token ASIGNACION IF ELSE WHILE FOR SUMA RESTA MULT DIV MODULO
%token O Y MAYOR MENOR MAYOR_O_IGUAL MENOR_O_IGUAL 
%token DESIGUALDAD IGUALDAD TRUE FALSE PUNTO 

%%

p : d;

d : t l PUNTO_Y_COMA d
  | DEF t ID PARENTESIS_IZQ f PARENTESIS_DER LLAVE_IZQ s LLAVE_DER d
  | /* epsilon */;

t : b a;
  | REGISTER LLAVE_IZQ d LLAVE_DER;

b : INT 
  | FLOAT; 

a : CORCHETE_IZQ NUM CORCHETE_DER a 
  | /* epsilon */;

l : l COMA ID
  | ID;

f : g 
   | /* epsilon */;

g : g COMA t ID 
  | t ID; 

s : s s 
  | LLAVE_IZQ s LLAVE_DER 
  | BREAK PUNTO_Y_COMA 
  | RETURN e PUNTO_Y_COMA 
  | ID ASIGNACION e PUNTO_Y_COMA 
  | c ASIGNACION e PUNTO_Y_COMA 
  | z ASIGNACION e PUNTO_Y_COMA 
  | IF PARENTESIS_IZQ e PARENTESIS_DER s 
  | IF PARENTESIS_IZQ e PARENTESIS_DER s ELSE s 
  | WHILE PARENTESIS_IZQ e PARENTESIS_DER s 
  | FOR PARENTESIS_IZQ s e PUNTO_Y_COMA s PARENTESIS_DER s;

e : e SUMA e 
  | e RESTA e 
  | e MULT e 
  | e DIV e 
  | e MODULO e 
  | e O e 
  | e Y e 
  | e MAYOR e 
  | e MENOR e
  | e MAYOR_O_IGUAL e 
  | e MENOR_O_IGUAL e 
  | e DESIGUALDAD e 
  | e IGUALDAD e
  | c 
  | ID 
  | ID PARENTESIS_IZQ n PARENTESIS_DER 
  | z 
  | NUM 
  | TRUE 
  | FALSE; 

n : m 
  | /* epsilon */;

m : m COMA e 
  | e; 

c : ID CORCHETE_IZQ e CORCHETE_DER 
  | c CORCHETE_IZQ e CORCHETE_DER; 

z : z PUNTO ID 
  | ID; 

%%

  private Yylex lexer;


  private int yylex () {
    int yyl_return = -1;
    try {
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
  }


  public void yyerror (String error) {
    System.err.println ("Error: " + error);
  }


  public Parser(Reader r) {
    lexer = new Yylex(r, this);
  }

  public static void main(String args[]) throws IOException {
    System.out.println("test");

    Parser yyparser = new Parser(new FileReader(args[0]));;

    yyparser.yyparse();
  }