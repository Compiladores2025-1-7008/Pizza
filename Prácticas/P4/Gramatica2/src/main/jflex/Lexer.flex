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

"=" |
"." |
"+" |
"-" |
"*" |
"/" |
"(" |
")"    { return (int) yycharat(0); }

"var" { return Parser.VAR; }

[a-zA-Z]+ { return Parser.LETRA; }
[0-9]+ { return Parser.DIGITO; }
[ \t\n]+ { }
. { System.err.println("carácter no reconocido");}
