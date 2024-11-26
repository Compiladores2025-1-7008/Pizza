//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "src/main/byacc/Sintactico.y"
  import java.io.*;
//#line 19 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short PUNTO_Y_COMA=257;
public final static short DEF=258;
public final static short ID=259;
public final static short PARENTESIS_IZQ=260;
public final static short PARENTESIS_DER=261;
public final static short LLAVE_IZQ=262;
public final static short LLAVE_DER=263;
public final static short REGISTER=264;
public final static short INT=265;
public final static short FLOAT=266;
public final static short CORCHETE_IZQ=267;
public final static short NUM=268;
public final static short CORCHETE_DER=269;
public final static short COMA=270;
public final static short BREAK=271;
public final static short RETURN=272;
public final static short ASIGNACION=273;
public final static short IF=274;
public final static short ELSE=275;
public final static short WHILE=276;
public final static short FOR=277;
public final static short SUMA=278;
public final static short RESTA=279;
public final static short MULT=280;
public final static short DIV=281;
public final static short MODULO=282;
public final static short O=283;
public final static short Y=284;
public final static short MAYOR=285;
public final static short MENOR=286;
public final static short MAYOR_O_IGUAL=287;
public final static short MENOR_O_IGUAL=288;
public final static short DESIGUALDAD=289;
public final static short IGUALDAD=290;
public final static short TRUE=291;
public final static short FALSE=292;
public final static short PUNTO=293;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    1,    2,    2,    6,    6,    7,    7,
    3,    3,    4,    4,    8,    8,    5,    5,    5,    5,
    5,    5,    5,    5,    5,    5,    5,    9,    9,    9,
    9,    9,    9,    9,    9,    9,    9,    9,    9,    9,
    9,    9,    9,    9,    9,    9,    9,   12,   12,   13,
   13,   10,   10,   11,   11,
};
final static short yylen[] = {                            2,
    1,    4,   10,    0,    2,    4,    1,    1,    4,    0,
    3,    1,    1,    0,    4,    2,    2,    3,    2,    3,
    4,    4,    4,    5,    7,    5,    8,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    1,    1,    4,    1,    1,    1,    1,    1,    0,    3,
    1,    4,    4,    3,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    7,    8,    0,    1,    0,    0,    0,    0,
   12,    0,    0,    5,    0,    0,    0,    0,    0,    0,
    6,    2,   11,    0,    0,    0,    0,    9,   16,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   15,    0,    0,    0,   19,    0,   45,
   46,   47,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   18,    0,   20,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    3,    0,    0,    0,   54,
   52,   21,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   53,   22,   23,   43,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yydgoto[] = {                          5,
    6,    7,   12,   26,   60,    8,   14,   27,   53,   54,
   55,   94,   95,
};
final static short yysindex[] = {                       -10,
 -160, -256,    0,    0,    0,    0, -249, -243, -217,  -10,
    0, -218, -224,    0, -213, -214,  -10, -199, -212, -160,
    0,    0,    0, -243, -195, -196, -190,    0,    0, -181,
 -160,  272, -174, -265,  272, -156, -241, -135, -134, -131,
  142, -146, -259,    0, -241, -241,  239,    0, -235,    0,
    0,    0,    3, -136, -152, -241, -241,  272,  -10,  272,
 -241, -241, -241, -117,  185,   23,    0, -241,    0, -241,
 -241, -241, -241, -241, -241, -241, -241, -241, -241, -241,
 -241, -241,  150,  163, -255,    0,  207,   37,   57,    0,
    0,    0,  288, -118, -126,  288,  288,  288,  288,  288,
  288,  288,  288,  288,  288,  288,  288,  288,  272,  272,
  -48,   71, -146, -259,    0,    0,    0,    0, -241,  246,
  272,  272,  288,  272,  265,  272,  272,  272,
};
final static short yyrindex[] = {                       146,
    0,    0,    0,    0,    0,    0,    0,  -99,    0,  -92,
    0,    0,    0,    0,    0,    0,    1,    0,    0,  -97,
    0,    0,    0,  -99,    0,    0,  -66,    0,    0,    0,
    0,    0,    0, -124,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -96,    0,
    0,    0,    0,  -46,  -11,    0,    0,    0,    1, -230,
    0,    0,    0,    0,    0,    0,    0,  -44,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -186,    0,  -40, -202, -150, -133,  -94,  -91,
  -89,  -43,  -41,   -8,  151,  290,  293,  295,    0,    0,
  -80,    0,   91,  105,    0,    0,    0,    0,    0, -189,
 -205,    0, -179,    0,    0, -192,    0,  135,
};
final static short yygindex[] = {                         0,
   -5,   10,    0,    0,    8,    0,  198,    0,   77,  -32,
  -12,    0,    0,
};
final static int YYTABLESIZE=578;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         42,
    4,   45,   42,  111,   16,   10,   35,   46,   42,   11,
    9,   22,   50,   63,   42,   36,   37,   49,   38,   43,
   39,   40,   43,   13,   68,   42,   50,   42,   43,   25,
   17,   45,   17,   64,   43,   51,   52,   17,   17,   41,
   33,   15,   47,   19,   17,   43,   20,   43,   21,   51,
   52,   18,  113,   86,   28,   26,   24,   26,   28,   23,
   17,   17,   26,   29,   30,   85,   28,   28,   25,   26,
   25,   24,  114,   24,   51,   25,   42,   42,   24,   31,
   32,   50,   25,   51,   44,   26,   26,   42,   42,   42,
   50,   42,   42,   42,   42,   42,   43,   43,   25,   25,
   48,   24,   24,    2,    3,    4,   29,   43,   43,   43,
   29,   43,   43,   43,   43,   43,  120,  121,   29,   29,
   61,   65,   66,   30,   56,   57,   62,   30,   58,  125,
   61,  126,   83,   84,  128,   30,   30,   87,   88,   89,
   64,   90,  118,  119,   93,    4,   96,   97,   98,   99,
  100,  101,  102,  103,  104,  105,  106,  107,  108,   10,
   42,  112,   31,   14,   42,   32,   31,   33,   55,   32,
    4,   33,   42,   42,   31,   31,   42,   32,   32,   33,
   33,   42,   42,   42,   42,   42,   42,   42,   42,   42,
   42,   42,   42,   42,   13,  123,   55,   42,   42,   42,
   42,   42,   42,   42,   42,   42,   42,   42,   42,   42,
   41,   68,   55,   34,   41,   35,   49,   34,   45,   35,
   48,   28,   41,   41,   46,   34,   34,   35,   35,    0,
    0,   41,   41,   41,   41,   41,   41,   41,   41,   41,
   41,   41,   41,   41,    0,   44,    0,    1,   36,   44,
    0,    0,   36,    2,    3,    4,    0,   44,   44,   69,
   36,   36,    0,    4,    0,    0,   44,   44,   44,   44,
   44,   44,   44,   44,   44,   44,   44,   44,   44,   92,
   70,   71,   72,   73,   74,   75,   76,   77,   78,   79,
   80,   81,   82,  116,    0,    0,    0,    0,    0,    0,
   70,   71,   72,   73,   74,   75,   76,   77,   78,   79,
   80,   81,   82,  117,   70,   71,   72,   73,   74,   75,
   76,   77,   78,   79,   80,   81,   82,  122,    0,    0,
    0,    0,    0,    0,   70,   71,   72,   73,   74,   75,
   76,   77,   78,   79,   80,   81,   82,   41,   70,   71,
   72,   73,   74,   75,   76,   77,   78,   79,   80,   81,
   82,   44,    0,    0,    0,    0,    0,    0,   41,   41,
   41,   41,   41,   41,   41,   41,   41,   41,   41,   41,
   41,    0,   44,   44,   44,   44,   44,   44,   44,   44,
   44,   44,   44,   44,   44,   27,    0,   27,    0,    0,
   34,    0,   27,   35,   59,    0,    0,   37,    0,   27,
  109,   37,   36,   37,    0,   38,    0,   39,   40,   37,
   37,    0,    0,  110,    0,   27,   27,   70,   71,   72,
   73,   74,   75,   76,   77,   78,   79,   80,   81,   82,
   70,   71,   72,   73,   74,   75,   76,   77,   78,   79,
   80,   81,   82,   91,    0,    0,    0,    0,    0,    0,
    0,    0,   70,   71,   72,   73,   74,   75,   76,   77,
   78,   79,   80,   81,   82,  115,    0,    0,    0,    0,
    0,    0,    0,    0,   70,   71,   72,   73,   74,   75,
   76,   77,   78,   79,   80,   81,   82,   34,    0,    0,
   35,   67,    0,    0,   34,    0,    0,   35,    0,   36,
   37,    0,   38,    0,   39,   40,   36,   37,    0,   38,
  124,   39,   40,   34,    0,  127,   35,    0,    0,    0,
   34,    0,    0,   35,    0,   36,   37,    0,   38,    0,
   39,   40,   36,   37,    0,   38,   38,   39,   40,   39,
   38,   40,    0,   39,    0,   40,    0,    0,   38,   38,
    0,   39,   39,   40,   40,   70,   71,   72,   73,   74,
   75,   76,   77,   78,   79,   80,   81,   82,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         32,
    0,  267,   35,  259,   10,  262,  262,  273,   41,  259,
    1,   17,  268,  273,   47,  271,  272,  259,  274,   32,
  276,  277,   35,  267,  260,   58,  268,   60,   41,   20,
  261,  267,  263,  293,   47,  291,  292,  268,  257,   32,
   31,  259,   35,  268,  275,   58,  260,   60,  263,  291,
  292,  270,   85,   59,  257,  261,  269,  263,  261,  259,
  291,  292,  268,  259,  261,   58,  269,  270,  261,  275,
  263,  261,   85,  263,  261,  268,  109,  110,  268,  270,
  262,  261,  275,  270,  259,  291,  292,  120,  121,  122,
  270,  124,  125,  126,  127,  128,  109,  110,  291,  292,
  257,  291,  292,  264,  265,  266,  257,  120,  121,  122,
  261,  124,  125,  126,  127,  128,  109,  110,  269,  270,
  267,   45,   46,  257,  260,  260,  273,  261,  260,  122,
  267,  124,   56,   57,  127,  269,  270,   61,   62,   63,
  293,  259,  261,  270,   68,    0,   70,   71,   72,   73,
   74,   75,   76,   77,   78,   79,   80,   81,   82,  259,
  257,   85,  257,  261,  261,  257,  261,  257,  293,  261,
  263,  261,  269,  270,  269,  270,  257,  269,  270,  269,
  270,  278,  279,  280,  281,  282,  283,  284,  285,  286,
  287,  288,  289,  290,  261,  119,  293,  278,  279,  280,
  281,  282,  283,  284,  285,  286,  287,  288,  289,  290,
  257,  260,  293,  257,  261,  257,  261,  261,  267,  261,
  261,   24,  269,  270,  273,  269,  270,  269,  270,   -1,
   -1,  278,  279,  280,  281,  282,  283,  284,  285,  286,
  287,  288,  289,  290,   -1,  257,   -1,  258,  257,  261,
   -1,   -1,  261,  264,  265,  266,   -1,  269,  270,  257,
  269,  270,   -1,  263,   -1,   -1,  278,  279,  280,  281,
  282,  283,  284,  285,  286,  287,  288,  289,  290,  257,
  278,  279,  280,  281,  282,  283,  284,  285,  286,  287,
  288,  289,  290,  257,   -1,   -1,   -1,   -1,   -1,   -1,
  278,  279,  280,  281,  282,  283,  284,  285,  286,  287,
  288,  289,  290,  257,  278,  279,  280,  281,  282,  283,
  284,  285,  286,  287,  288,  289,  290,  257,   -1,   -1,
   -1,   -1,   -1,   -1,  278,  279,  280,  281,  282,  283,
  284,  285,  286,  287,  288,  289,  290,  257,  278,  279,
  280,  281,  282,  283,  284,  285,  286,  287,  288,  289,
  290,  257,   -1,   -1,   -1,   -1,   -1,   -1,  278,  279,
  280,  281,  282,  283,  284,  285,  286,  287,  288,  289,
  290,   -1,  278,  279,  280,  281,  282,  283,  284,  285,
  286,  287,  288,  289,  290,  261,   -1,  263,   -1,   -1,
  259,   -1,  268,  262,  263,   -1,   -1,  257,   -1,  275,
  261,  261,  271,  272,   -1,  274,   -1,  276,  277,  269,
  270,   -1,   -1,  261,   -1,  291,  292,  278,  279,  280,
  281,  282,  283,  284,  285,  286,  287,  288,  289,  290,
  278,  279,  280,  281,  282,  283,  284,  285,  286,  287,
  288,  289,  290,  269,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  278,  279,  280,  281,  282,  283,  284,  285,
  286,  287,  288,  289,  290,  269,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  278,  279,  280,  281,  282,  283,
  284,  285,  286,  287,  288,  289,  290,  259,   -1,   -1,
  262,  263,   -1,   -1,  259,   -1,   -1,  262,   -1,  271,
  272,   -1,  274,   -1,  276,  277,  271,  272,   -1,  274,
  275,  276,  277,  259,   -1,  261,  262,   -1,   -1,   -1,
  259,   -1,   -1,  262,   -1,  271,  272,   -1,  274,   -1,
  276,  277,  271,  272,   -1,  274,  257,  276,  277,  257,
  261,  257,   -1,  261,   -1,  261,   -1,   -1,  269,  270,
   -1,  269,  270,  269,  270,  278,  279,  280,  281,  282,
  283,  284,  285,  286,  287,  288,  289,  290,
};
}
final static short YYFINAL=5;
final static short YYMAXTOKEN=293;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"PUNTO_Y_COMA","DEF","ID","PARENTESIS_IZQ","PARENTESIS_DER",
"LLAVE_IZQ","LLAVE_DER","REGISTER","INT","FLOAT","CORCHETE_IZQ","NUM",
"CORCHETE_DER","COMA","BREAK","RETURN","ASIGNACION","IF","ELSE","WHILE","FOR",
"SUMA","RESTA","MULT","DIV","MODULO","O","Y","MAYOR","MENOR","MAYOR_O_IGUAL",
"MENOR_O_IGUAL","DESIGUALDAD","IGUALDAD","TRUE","FALSE","PUNTO",
};
final static String yyrule[] = {
"$accept : p",
"p : d",
"d : t l PUNTO_Y_COMA d",
"d : DEF t ID PARENTESIS_IZQ f PARENTESIS_DER LLAVE_IZQ s LLAVE_DER d",
"d :",
"t : b a",
"t : REGISTER LLAVE_IZQ d LLAVE_DER",
"b : INT",
"b : FLOAT",
"a : CORCHETE_IZQ NUM CORCHETE_DER a",
"a :",
"l : l COMA ID",
"l : ID",
"f : g",
"f :",
"g : g COMA t ID",
"g : t ID",
"s : s s",
"s : LLAVE_IZQ s LLAVE_DER",
"s : BREAK PUNTO_Y_COMA",
"s : RETURN e PUNTO_Y_COMA",
"s : ID ASIGNACION e PUNTO_Y_COMA",
"s : c ASIGNACION e PUNTO_Y_COMA",
"s : z ASIGNACION e PUNTO_Y_COMA",
"s : IF PARENTESIS_IZQ e PARENTESIS_DER s",
"s : IF PARENTESIS_IZQ e PARENTESIS_DER s ELSE s",
"s : WHILE PARENTESIS_IZQ e PARENTESIS_DER s",
"s : FOR PARENTESIS_IZQ s e PUNTO_Y_COMA s PARENTESIS_DER s",
"e : e SUMA e",
"e : e RESTA e",
"e : e MULT e",
"e : e DIV e",
"e : e MODULO e",
"e : e O e",
"e : e Y e",
"e : e MAYOR e",
"e : e MENOR e",
"e : e MAYOR_O_IGUAL e",
"e : e MENOR_O_IGUAL e",
"e : e DESIGUALDAD e",
"e : e IGUALDAD e",
"e : c",
"e : ID",
"e : ID PARENTESIS_IZQ n PARENTESIS_DER",
"e : z",
"e : NUM",
"e : TRUE",
"e : FALSE",
"n : m",
"n :",
"m : m COMA e",
"m : e",
"c : ID CORCHETE_IZQ e CORCHETE_DER",
"c : c CORCHETE_IZQ e CORCHETE_DER",
"z : z PUNTO ID",
"z : ID",
};

//#line 84 "src/main/byacc/Sintactico.y"

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
//#line 441 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
