%{
  import java.io.*;
%}

%token LETRA DIGITO VAR

%%

s : expr | asig;
expr : term expr1;
expr1 : '+' term expr1 | '-' term expr1 | /* epsilon */;
term : factor term1;
term1 : '*' factor term1 | '/' factor term1 | /* epsilon */;
factor : num | var | '(' expr ')' | '-' expr;
num : entero decimal;
decimal : '.' entero | /* epsilon */;
entero : DIGITO | DIGITO entero;
asig : VAR var "=" expr;
var : LETRA pos;
pos : var | /* epsilon */;

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
    System.out.println("Gramatica con BYACC/Java");

    Parser yyparser = new Parser(new FileReader(args[0]));;

    yyparser.yyparse();
  }
