%%
%class scanner
%unicode
%line
%column
%byaccj

%eof{

%eof}

%{
    private Parser yyparser;

    public int getLine() { return yyline+1; }

	public scanner (java.io.Reader r, Parser yyparser) {
		this (r);	
		this.yyparser = yyparser;
	}

%}

%%
[a-z]+	{yyparser.yylval = new Parser.Semantic(yytext());
         return Parser.ID;}
[0-9]+	{yyparser.yylval = new Parser.Semantic(Integer.parseInt(yytext()));
         return Parser.NUM; }
"="	{return Parser.ASIGNACION;}
"+"	{return Parser.SUMA;}
[ \t\n] {;}


