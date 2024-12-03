LEX = lex
YACC = yacc
CC = gcc
LEX_SRC = lexico.l
YACC_SRC = sintactico.y
OUT_DIR = out
EXECUTABLE = a.out
INPUT = entrada.c

test: build
	./$(OUT_DIR)/$(EXECUTABLE) < $(INPUT)

build: clean $(OUT_DIR)
	$(LEX) $(LEX_SRC)
	$(YACC) -v -d $(YACC_SRC)
	$(CC) y.tab.c -o $(EXECUTABLE)
	mv lex.yy.c y.tab.c y.tab.h y.output $(EXECUTABLE) $(OUT_DIR)

clean:
	rm -rf $(OUT_DIR)/*

$(OUT_DIR):
	mkdir -p $(OUT_DIR)

