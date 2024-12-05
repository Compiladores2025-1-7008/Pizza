%{
  import java.lang.Math;
  import java.io.Reader;
  import java.io.IOException;
  import java.io.*;
  import java.util.*;
  
  


%}



/// los tokes seran los terminales que seran reconocidos

%token SUMA RESTA MULTI DIVI POTE  LPAR RPAR  NUMERO SALTOLINEA ID
%token PROTO STRUCT PTR INT FLOAT DOUBLE COMPLEX RUNE VOID STRING FUNC IF ELSE WHILE DO BREAK RETURN
%token SWITCH CASE DEFAULT PRINT SCAN TRUE FALSE
%token LLLAVE RLLAVE LCORCHETE RCORCHETE COMA SEMICOLON COLON PUNTO ASIG OR AND IGUAL DESIGUAL MENORQUE MENORIGUAL MAYORQUE
%token MAYORIGUAL DIVISIONENTERA NEG
%token  LITERAL_RUNA  LITERAL_ENTERA LITERAL_CADENA PRED   LITERAL_COMPLEJA LITERAL_FLOTANTE LITERAL_DOBLE


// Precedencias y asociatividades de operadores
%left RESTA SUMA          
%left MULTI DIVI           
%right POTE               
//%left NEG                 // Negación unaria
%nonassoc LPAR RPAR   // Paréntesis son no asociativos

%left OR       /* Precedencia más baja */
%left AND
%left IGUAL DESIGUAL
%left MENORQUE MENORIGUAL MAYORQUE MAYORIGUAL
%left MAS MENOS
%left MULT DIV MOD DIVENTERA
%right NEG      /* Negación lógica y menos unario tienen mayor precedencia */
%nonassoc '(' ')' /* Paréntesis son no asociativos para evitar problemas de ambigüedad */



/* Gramatica */

%%
input:
    /* Cadena vacía */
  | input line          
;

line:
    SALTOLINEA                // Si detecta un salto de linea que no haga nada 
  | exp SALTOLINEA { System.out.println("Resultado: " + $1.sval); }  // Muestra el resultado de una expresión
;






// Operaciones básicas en BYACC/Java
exp:  LITERAL_DOBLE
    {
        //System.out.print("enterea");
        $$ = $1;  // Si es un número, simplemente lo retornamos.

    }

    |exp SUMA exp  
    {
        //System.out.print("suma");
        $$ = new ParserVal (parserActions.handleAddition($1, $3));

    }
    | exp RESTA exp 
    {
        //System.out.print("resta");
        $$ = new ParserVal (parserActions.handleSubtraction($1, $3));
    
    }
    | exp MULTI exp  
    {
        //System.out.print("por");
        $$ = new ParserVal (parserActions.handleMultiplication($1, $3));     }

    | LPAR exp RPAR    { $$ = $2; }  
    
    | exp DIVI exp          {$$ = new ParserVal (parserActions.handleDivi($1, $3));}


    //las siguiente hacerle el mismo proceso que las de arriba , pasar los parametros y guardar en la quadrupla 

    | RESTA exp %prec NEG   //{ $$ = new ParserVal(-$2.dval); }       
    | exp POTE exp          //{ $$ = new ParserVal(Math.pow($1.dval, $3.dval)); }  
    | exp OR exp                  
    | exp AND exp                 
    | exp IGUAL exp               
    | exp DESIGUAL exp            
    | exp MENORQUE exp            
    | exp MENORIGUAL exp          
    | exp MAYORQUE exp            
    | exp MAYORIGUAL exp          
    | exp MOD exp                 
    | exp DIVENTERA exp           
    | NEG exp                     

    | ID
    | ID '(' parametros ')'
    | TRUE
    | FALSE


   ;


    
/* 


exp:

    //Faltan estas 

    | LITERAL_ENTERA
    | LITERAL_RUNA
    | LITERAL_CADENA
    | LITERAL_FLOTANTE
    | LITERAL_DOBLE
    | LITERAL_COMPLEJA
    ;

    */



programa:
      decl_proto decl_var decl_func

decl_proto:
      PROTO tipo ID LPAR argumentos RPAR SEMICOLON decl_proto
    | /* ε */

decl_var:
      tipo lista_var SEMICOLON decl_var
    | /* ε */

tipo:
      basico compuesto
    | STRUCT LLLAVE decl_var RLLAVE
    | puntero

puntero:
      PTR basico

basico:
      INT
    | FLOAT
    | DOUBLE
    | COMPLEX
    | RUNE
    | VOID
    | STRING

compuesto:
      LCORCHETE LITERAL_ENTERA RCORCHETE compuesto
    | /* ε */

lista_var:
      lista_var COMA ID
    | ID

decl_func:
      FUNC tipo ID LPAR argumentos RPAR bloque decl_func
    | /* ε */

argumentos:
      lista_args
    | /* ε */

lista_args:
      lista_args COMA tipo ID
    | tipo ID

bloque:
      LLLAVE declaraciones instrucciones RLLAVE

declaraciones:
      /* ε */

instrucciones:
      instrucciones sentencia
    | sentencia

sentencia:
      matched_stmt
    | unmatched_stmt

matched_stmt:
      parte_izquierda ASIG exp SEMICOLON
    | IF LPAR exp RPAR matched_stmt ELSE matched_stmt
    | WHILE LPAR exp RPAR matched_stmt
    | DO matched_stmt WHILE LPAR exp RPAR SEMICOLON
    | BREAK SEMICOLON
    | bloque
    | RETURN exp SEMICOLON
    | RETURN SEMICOLON
    | SWITCH LPAR exp RPAR LLLAVE casos RLLAVE
    | PRINT exp SEMICOLON
    | SCAN parte_izquierda

unmatched_stmt:
      IF LPAR exp RPAR unmatched_stmt
    | IF LPAR exp RPAR matched_stmt ELSE unmatched_stmt
    | WHILE LPAR exp RPAR unmatched_stmt
    | DO unmatched_stmt WHILE LPAR exp RPAR SEMICOLON
    | bloque

casos:
      caso casos
    | PRED
    | /* ε */

caso:
      CASE opcion COLON instrucciones

opcion:
      LITERAL_ENTERA
    | LITERAL_RUNA

parte_izquierda:
      ID localizacion
    | ID

localizacion:
      arreglo
    | estructurado

arreglo:
      arreglo LCORCHETE exp RCORCHETE
    | LCORCHETE exp RCORCHETE

estructurado:
      estructurado PUNTO ID
    | PUNTO ID

parametros:
      lista_param
    | /* ε */

lista_param:
      lista_param COMA exp
    | exp




%%










/* Instancia del lexer */
Lexer scanner;
ParserActions parserActions =new ParserActions();

/* Constructor del parser */
public Parser(Reader r) {
  this.scanner = new Lexer(r, this);  // Inicializa el lexer con el lector de entrada
 
}

// Método para establecer  el valor del token actual 
public void setValor(ParserVal valor) {
  ///yylval es una variable para guardar el valor//el lexema // del token 
  this.yylval = valor; 
}


//METODO QUE  INICIA TODO  
  //desde el main
public void parse() {
  // este metodo pide manda a llamar yylex repetidamente para obtener los tokens uno a uno 
  this.yyparse();  
}


 
//El metodo se recomienda sobreescribirlo para permitir personalizar cómo se manejan y reportan errores

void yyerror(String error1) {
  System.out.println("Error sintactico: " + error1);  
}


// Obtener el token actual
// Debido a que implemente el lexer dentro del parser tengo que definir la función yylex para que sea 
// llamada por parse() 

int yylex() {
  int token = -1;
  try {
    token= scanner.yylex(); 
  } catch (IOException e) {
    System.err.println("Error ");  
  }
  parserActions.printGeneratedCode();
  //System.out.print("Token leido"+token.);
  return token;  // Retorna el token obtenido
}


