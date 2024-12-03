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
public final static short STRUCT=264;
public final static short REGISTER=265;
public final static short INT=266;
public final static short FLOAT=267;
public final static short CORCHETE_IZQ=268;
public final static short NUM=269;
public final static short CORCHETE_DER=270;
public final static short COMA=271;
public final static short BREAK=272;
public final static short RETURN=273;
public final static short ASIGNACION=274;
public final static short IF=275;
public final static short ELSE=276;
public final static short WHILE=277;
public final static short FOR=278;
public final static short SUMA=279;
public final static short RESTA=280;
public final static short MULT=281;
public final static short DIV=282;
public final static short MODULO=283;
public final static short O=284;
public final static short Y=285;
public final static short MAYOR=286;
public final static short MENOR=287;
public final static short MAYOR_O_IGUAL=288;
public final static short MENOR_O_IGUAL=289;
public final static short DESIGUALDAD=290;
public final static short IGUALDAD=291;
public final static short TRUE=292;
public final static short FALSE=293;
public final static short PUNTO=294;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    1,    1,    2,    2,    6,    6,    7,
    7,    3,    3,    4,    4,    8,    8,    5,    5,    5,
    5,    5,    5,    5,    5,    5,    5,    5,    9,    9,
    9,    9,    9,    9,    9,    9,    9,    9,    9,    9,
    9,    9,    9,    9,    9,    9,    9,    9,   12,   12,
   13,   13,   10,   10,   11,   11,
};
final static short yylen[] = {                            2,
    1,    4,   10,    7,    0,    2,    4,    1,    1,    4,
    0,    3,    1,    1,    0,    4,    2,    2,    3,    2,
    3,    4,    4,    4,    5,    7,    5,    8,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    1,    1,    4,    1,    1,    1,    1,    1,    0,
    3,    1,    4,    4,    3,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    8,    9,    0,    1,    0,    0,    0,
    0,    0,   13,    0,    0,    6,    0,    0,    0,    0,
    0,    0,    0,    0,    7,    2,   12,    0,    0,    0,
    0,    0,   10,   17,    0,    0,    0,    0,    0,    4,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   16,    0,    0,    0,   20,    0,   46,   47,   48,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   19,    0,   21,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    3,    0,    0,    0,   55,   53,   22,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   54,   23,   24,   44,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,
};
final static short yydgoto[] = {                          6,
    7,    8,   14,   30,   67,    9,   16,   31,   60,   61,
   62,  101,  102,
};
final static short yysindex[] = {                       -60,
 -155, -247, -234,    0,    0,    0,    0, -251, -233, -220,
  -60,  -60,    0, -254, -216,    0, -191, -190, -187,  -60,
 -182, -186, -155, -251,    0,    0,    0, -233, -158, -154,
 -153, -245,    0,    0, -145, -155,  -60,  204, -139,    0,
 -210,  204, -135, -177, -132, -128, -126, -192, -206, -272,
    0, -177, -177,   -2,    0, -236,    0,    0,    0,   -1,
 -133, -157, -177, -177,  204,  -60,  204, -177, -177, -177,
 -107,  260,   12,    0, -177,    0, -177, -177, -177, -177,
 -177, -177, -177, -177, -177, -177, -177, -177, -177,  225,
  238,  176,    0,  282,   47,   60,    0,    0,    0,  318,
 -105, -117,  318,  318,  318,  318,  318,  318,  318,  318,
  318,  318,  318,  318,  318,  204,  204, -141,   95, -206,
 -272,    0,    0,    0,    0, -177,   46,  204,  204,  318,
  204,   94,  204,  204,  204,
};
final static short yyrindex[] = {                       158,
    0,    0,    0,    0,    0,    0,    0,    0,  -96,    0,
 -104, -104,    0,    0,    0,    0,    0,    0,    0,    1,
    0,    0,  -95,    0,    0,    0,    0,  -96,    0,    0,
  -94,    0,    0,    0,    0,    0,    1,    0,    0,    0,
 -125,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -106,    0,    0,    0,    0,
  -71,  -36,    0,    0,    0,    1,  144,    0,    0,    0,
    0,    0,    0,    0,  -89,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -238,
    0,  -67, -250, -196, -100,  -68,  -34,  -31,  145,  147,
  223,  317,  319,  322,  324,    0,    0, -239,    0,  108,
  143,    0,    0,    0,    0,    0, -101,  181,    0, -205,
    0,    0,  195,    0,  209,
};
final static short yygindex[] = {                         0,
   -6,   36,  171,    0,   -8,    0,  168,    0,   61,  -38,
  -29,    0,    0,
};
final static int YYTABLESIZE=609;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         49,
    5,   70,   20,   49,   18,   19,   29,   13,   50,   49,
   29,   37,   50,   26,   11,   49,   21,   43,   50,   29,
   29,   71,   52,   75,   50,   21,   49,   12,   49,   48,
   40,   52,   52,   54,   15,   50,   10,   50,   17,   43,
   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
   43,   43,   22,  120,   56,   51,   92,   52,   29,   93,
   30,   68,  121,   53,   30,   51,   41,   69,   23,   42,
   66,   39,   24,   30,   30,   25,   27,   49,   49,   43,
   44,   56,   45,   28,   46,   47,   50,   50,   49,   49,
   49,   57,   49,   49,   49,   49,   49,   50,   50,   50,
   34,   50,   50,   50,   50,   50,   35,  127,  128,    3,
    4,    5,   72,   73,   58,   59,   38,   36,   75,   51,
  132,   55,  133,   90,   91,  135,   52,   63,   94,   95,
   96,   64,   53,   65,   68,  100,   71,  103,  104,  105,
  106,  107,  108,  109,  110,  111,  112,  113,  114,  115,
   43,   97,  119,  126,   43,  125,   31,    5,    5,   25,
   31,   25,   11,   43,   43,   15,   14,   25,   56,   31,
   31,   50,   43,   43,   43,   43,   43,   43,   43,   43,
   43,   43,   43,   43,   43,   42,  130,   56,   32,   42,
   25,   25,   32,   49,   32,   33,    0,    1,   42,   42,
    0,   32,   32,    2,    3,    4,    5,   42,   42,   42,
   42,   42,   42,   42,   42,   42,   42,   42,   42,   42,
   45,    0,   33,    0,   45,   34,   33,    0,    0,   34,
    0,    0,    0,   45,   45,   33,   33,    0,   34,   34,
    0,    0,   45,   45,   45,   45,   45,   45,   45,   45,
   45,   45,   45,   45,   45,   76,   41,    0,    0,   42,
   74,    0,    0,    5,    0,    0,    0,    0,   99,   43,
   44,    0,   45,    0,   46,   47,    0,   77,   78,   79,
   80,   81,   82,   83,   84,   85,   86,   87,   88,   89,
   77,   78,   79,   80,   81,   82,   83,   84,   85,   86,
   87,   88,   89,  123,   41,    0,    0,   42,    0,    0,
    0,    0,    0,    0,    0,    0,  124,   43,   44,    0,
   45,  131,   46,   47,    0,   77,   78,   79,   80,   81,
   82,   83,   84,   85,   86,   87,   88,   89,   77,   78,
   79,   80,   81,   82,   83,   84,   85,   86,   87,   88,
   89,  129,   41,    0,  134,   42,    0,    0,    0,    0,
    0,    0,    0,    0,   42,   43,   44,    0,   45,    0,
   46,   47,    0,   77,   78,   79,   80,   81,   82,   83,
   84,   85,   86,   87,   88,   89,   42,   42,   42,   42,
   42,   42,   42,   42,   42,   42,   42,   42,   42,   45,
    0,   35,    0,   36,   18,   35,   18,   36,    0,    0,
    0,    0,   18,    0,   35,   35,   36,   36,    0,   18,
    0,   45,   45,   45,   45,   45,   45,   45,   45,   45,
   45,   45,   45,   45,  118,   18,   18,   42,    0,    0,
    0,   27,    0,   27,   57,    0,    0,   43,   44,   27,
   45,    0,   46,   47,    0,   26,   27,   26,    0,    0,
    0,    0,   41,   26,    0,   42,    0,   58,   59,   28,
   26,   28,   27,   27,    0,   43,   44,   28,   45,   37,
   46,   47,    0,   37,   28,  116,   26,   26,    0,    0,
    0,    0,   37,   37,    0,    0,    0,    0,  117,    0,
   28,   28,    0,   77,   78,   79,   80,   81,   82,   83,
   84,   85,   86,   87,   88,   89,   77,   78,   79,   80,
   81,   82,   83,   84,   85,   86,   87,   88,   89,   98,
    0,    0,    0,    0,    0,    0,    0,    0,   77,   78,
   79,   80,   81,   82,   83,   84,   85,   86,   87,   88,
   89,  122,    0,    0,    0,    0,    0,    0,    0,    0,
   77,   78,   79,   80,   81,   82,   83,   84,   85,   86,
   87,   88,   89,   38,    0,   39,    0,   38,   40,   39,
   41,    0,   40,    0,   41,    0,   38,   38,   39,   39,
    0,   40,   40,   41,   41,    0,   77,   78,   79,   80,
   81,   82,   83,   84,   85,   86,   87,   88,   89,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         38,
    0,  274,  257,   42,   11,   12,  257,  259,   38,   48,
  261,  257,   42,   20,  262,   54,  271,  257,   48,  270,
  271,  294,  261,  260,   54,  271,   65,  262,   67,   38,
   37,  268,  271,   42,  268,   65,    1,   67,  259,  279,
  280,  281,  282,  283,  284,  285,  286,  287,  288,  289,
  290,  291,  269,   92,  294,  261,   65,  268,   23,   66,
  257,  268,   92,  274,  261,  271,  259,  274,  260,  262,
  263,   36,  263,  270,  271,  263,  259,  116,  117,  272,
  273,  259,  275,  270,  277,  278,  116,  117,  127,  128,
  129,  269,  131,  132,  133,  134,  135,  127,  128,  129,
  259,  131,  132,  133,  134,  135,  261,  116,  117,  265,
  266,  267,   52,   53,  292,  293,  262,  271,  260,  259,
  129,  257,  131,   63,   64,  134,  268,  260,   68,   69,
   70,  260,  274,  260,  268,   75,  294,   77,   78,   79,
   80,   81,   82,   83,   84,   85,   86,   87,   88,   89,
  257,  259,   92,  271,  261,  261,  257,    0,  263,  261,
  261,  263,  259,  270,  271,  261,  261,  269,  294,  270,
  271,  261,  279,  280,  281,  282,  283,  284,  285,  286,
  287,  288,  289,  290,  291,  257,  126,  294,  257,  261,
  292,  293,  261,  261,   24,   28,   -1,  258,  270,  271,
   -1,  270,  271,  264,  265,  266,  267,  279,  280,  281,
  282,  283,  284,  285,  286,  287,  288,  289,  290,  291,
  257,   -1,  257,   -1,  261,  257,  261,   -1,   -1,  261,
   -1,   -1,   -1,  270,  271,  270,  271,   -1,  270,  271,
   -1,   -1,  279,  280,  281,  282,  283,  284,  285,  286,
  287,  288,  289,  290,  291,  257,  259,   -1,   -1,  262,
  263,   -1,   -1,  263,   -1,   -1,   -1,   -1,  257,  272,
  273,   -1,  275,   -1,  277,  278,   -1,  279,  280,  281,
  282,  283,  284,  285,  286,  287,  288,  289,  290,  291,
  279,  280,  281,  282,  283,  284,  285,  286,  287,  288,
  289,  290,  291,  257,  259,   -1,   -1,  262,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  272,  273,   -1,
  275,  276,  277,  278,   -1,  279,  280,  281,  282,  283,
  284,  285,  286,  287,  288,  289,  290,  291,  279,  280,
  281,  282,  283,  284,  285,  286,  287,  288,  289,  290,
  291,  257,  259,   -1,  261,  262,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  257,  272,  273,   -1,  275,   -1,
  277,  278,   -1,  279,  280,  281,  282,  283,  284,  285,
  286,  287,  288,  289,  290,  291,  279,  280,  281,  282,
  283,  284,  285,  286,  287,  288,  289,  290,  291,  257,
   -1,  257,   -1,  257,  261,  261,  263,  261,   -1,   -1,
   -1,   -1,  269,   -1,  270,  271,  270,  271,   -1,  276,
   -1,  279,  280,  281,  282,  283,  284,  285,  286,  287,
  288,  289,  290,  291,  259,  292,  293,  262,   -1,   -1,
   -1,  261,   -1,  263,  269,   -1,   -1,  272,  273,  269,
  275,   -1,  277,  278,   -1,  261,  276,  263,   -1,   -1,
   -1,   -1,  259,  269,   -1,  262,   -1,  292,  293,  261,
  276,  263,  292,  293,   -1,  272,  273,  269,  275,  257,
  277,  278,   -1,  261,  276,  261,  292,  293,   -1,   -1,
   -1,   -1,  270,  271,   -1,   -1,   -1,   -1,  261,   -1,
  292,  293,   -1,  279,  280,  281,  282,  283,  284,  285,
  286,  287,  288,  289,  290,  291,  279,  280,  281,  282,
  283,  284,  285,  286,  287,  288,  289,  290,  291,  270,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  279,  280,
  281,  282,  283,  284,  285,  286,  287,  288,  289,  290,
  291,  270,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  279,  280,  281,  282,  283,  284,  285,  286,  287,  288,
  289,  290,  291,  257,   -1,  257,   -1,  261,  257,  261,
  257,   -1,  261,   -1,  261,   -1,  270,  271,  270,  271,
   -1,  270,  271,  270,  271,   -1,  279,  280,  281,  282,
  283,  284,  285,  286,  287,  288,  289,  290,  291,
};
}
final static short YYFINAL=6;
final static short YYMAXTOKEN=294;
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
"LLAVE_IZQ","LLAVE_DER","STRUCT","REGISTER","INT","FLOAT","CORCHETE_IZQ","NUM",
"CORCHETE_DER","COMA","BREAK","RETURN","ASIGNACION","IF","ELSE","WHILE","FOR",
"SUMA","RESTA","MULT","DIV","MODULO","O","Y","MAYOR","MENOR","MAYOR_O_IGUAL",
"MENOR_O_IGUAL","DESIGUALDAD","IGUALDAD","TRUE","FALSE","PUNTO",
};
final static String yyrule[] = {
"$accept : p",
"p : d",
"d : t l PUNTO_Y_COMA d",
"d : DEF t ID PARENTESIS_IZQ f PARENTESIS_DER LLAVE_IZQ s LLAVE_DER d",
"d : STRUCT LLAVE_IZQ d LLAVE_DER l PUNTO_Y_COMA d",
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

//#line 85 "src/main/byacc/Sintactico.y"

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

    Parser yyparser = new Parser(new FileReader(args[0]));;
    int i = yyparser.yyparse();
    if (i == 0) {
      System.out.println("Analisis exitoso");
    }
  }
//#line 453 "Parser.java"
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
