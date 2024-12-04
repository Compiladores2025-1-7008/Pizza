test: parser
	java Parser test.txt 

parser: Parser.java scanner.java
	javac -classpath "" *.java

Parser.java: semantico.y
	byaccj -Jsemantic=Semantic semantico.y

scanner.java: lexico.flex	
	jflex lexico.flex

clean:
	rm -f *.class *.java
