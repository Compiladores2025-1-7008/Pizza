BYACCJ = byaccj -J
JFLEX  = jflex

test: all
	java -cp out Parser test.txt

all: build

build: Parser.class

clean:
	rm -rf out

Parser.class: Yylex.java Parser.java
	javac -d out out/*.java

Yylex.java: src/main/jflex/Lexico.flex
	$(JFLEX) -d out $<

Parser.java: src/main/byacc/Sintactico.y
	$(BYACCJ) $< ;  mv Parser.java ParserVal.java out
