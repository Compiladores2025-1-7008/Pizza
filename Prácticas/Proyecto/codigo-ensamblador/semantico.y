%{
import java.io.*;
import java.util.*;
import java.text.*;

%}

%token ID NUM ASIGNACION SUMA

%%

s: ID ASIGNACION expr
{System.out.println("\tsw $t" + $3.ival +"," + $1.name); }
;

expr: expr SUMA term 
{System.out.println("\tadd $t" + $1.ival + ",$t" + $1.ival + ",$t" + $3.ival); $$.ival = $1.ival; reg.freeRegister($3.ival);}
| term
{$$ = $1;}
;

term: ID
{ $$.ival  =reg.getRegister();
System.out.println("\tlw $t"+ $$.ival +"," + $1.name); 
}
| NUM
{int k = reg.getRegister(); 
System.out.println("\tli $t" + k +"," + $1.ival); $$.ival=k;}
;
	

%%
     private Registers reg = new Registers();

     public static final class Semantic {
        public String name = null;
        public int ival = -1;

        public Semantic(Semantic sem) {
            name = sem.name;
            ival = sem.ival;
        }
        public Semantic(String id)   { name = id; } 
        public Semantic(int i) { ival = i; }
        public Semantic() { }

    }

	private scanner lexer;
	
	private int yylex() {
		int retVal = -1;
		try {
			retVal = lexer.yylex();
		} catch (IOException e) {
			System.err.println("IO Error:" + e);
		}
		return retVal;
	}

	public Parser (Reader r) {
		lexer = new scanner (r, this);
	}

	public static void main (String [] args) throws IOException {
                Parser yyparser = new Parser(new FileReader(args[0]));;
		yyparser.yyparse();
	}

  
     public void yyerror(String error) {
       System.err.println("Error : " + error + " at line " + lexer.getLine() );
     }

     public class Registers {
        int available[]; 
        public Registers() { available=new int[8]; }
        public int getRegister() {
           for (int i = 0;i<7;i++)
               if (available[i] == 0) {available[i] = 1; return i;}
           return 0;
        }
        public void freeRegister(int r) {
            available[r] = 1;
        }
     }


