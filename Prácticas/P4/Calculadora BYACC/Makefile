BYACCJ = byaccj -J
JFLEX  = jflex

run: all
	java -cp out Parser

all: build

build: Parser.class

clean:
	rm -rf out

Parser.class: Yylex.java Parser.java
	javac -d out out/*.java

Yylex.java: src/main/jflex/Lexer.flex
	$(JFLEX) -d out $<

Parser.java: src/main/byacc/Parser.y
	$(BYACCJ) $< ;  mv Parser.java ParserVal.java out
