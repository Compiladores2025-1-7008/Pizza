

# Reglas



run : compilar
	java Main	

compilar: lexer quadruple proyecto main


lexer: 
	jflex Lexer.flex


quadruple: 
	javac QuadrupleGenerator.java

proyecto : 
	byaccj -J parserProyecto.y

main :
	javac Main.java

