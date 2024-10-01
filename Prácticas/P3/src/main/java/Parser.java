package main.java;

import java.io.IOException;
import main.jflex.Lexer;

public class Parser implements ParserInterface {
    private Lexer lexer;
    private int actual;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public void eat(int claseLexica) {
        if (actual == claseLexica) {
            try {
                actual = lexer.yylex();
            } catch (IOException ioe) {
                System.err.println("Failed to read next token");
            }
        } else {
            System.err.println("Se esperaba el token: " + actual);
            error("Token inesperado");
        }
    }

    public void error(String msg) {
        System.err.println("ERROR DE SINTAXIS: " + msg + " en la línea " + lexer.getLine());
        System.exit(1);
    }

    public void parse() {
        try {
            this.actual = lexer.yylex();
        } catch (IOException ioe) {
            System.err.println("Error: No fue posible obtener el primer token de la entrada.");
            System.exit(1);
        }
        S();
        if (actual == ClaseLexica.EOF) // llegamos al EOF sin error
            System.out.println("La cadena es aceptada");
        else
            System.out.println("La cadena no pertenece al lenguaje generado por la gramática");
    }

    public void S() { // S() = programa()
        declaraciones();
        sentencias();
    }

    public void declaraciones() {
        while (actual == ClaseLexica.INT || actual == ClaseLexica.FLOAT) {
            if (actual == ClaseLexica.INT) {
                eat(ClaseLexica.INT);
            } else if (actual == ClaseLexica.FLOAT) {
                eat(ClaseLexica.FLOAT);
            }
            // Suponiendo que hay identificadores después del tipo
            if (actual == ClaseLexica.IDENTIFICADOR) {
                eat(ClaseLexica.IDENTIFICADOR);
                while (actual == ClaseLexica.COMA) {
                    eat(ClaseLexica.COMA);
                    if (actual == ClaseLexica.IDENTIFICADOR) {
                        eat(ClaseLexica.IDENTIFICADOR);
                    } else {
                        error("Se esperaba un identificador después de la coma");
                    }
                }
                eat(ClaseLexica.PUNTO_Y_COMA); // Asumimos que las declaraciones terminan con ;
            } else {
                error("Se esperaba un identificador después del tipo");
            }
        }
    }

    public void sentencias() {
        while (actual == ClaseLexica.IDENTIFICADOR) {
            sentenciasAsignacion();
        }
    }

    public void sentenciasAsignacion() {
        if (actual == ClaseLexica.IDENTIFICADOR) {
            eat(ClaseLexica.IDENTIFICADOR);
            eat(ClaseLexica.ASIGNACION);
            expresion();
            eat(ClaseLexica.PUNTO_Y_COMA);
        } else {
            error("Se esperaba un identificador para la asignación");
        }
    }

    public void expresion() {
        // Asumimos que una expresión puede ser un identificador o un número
        if (actual == ClaseLexica.IDENTIFICADOR || actual == ClaseLexica.NUMERO) {
            eat(actual); // consume el identificador o número
            while (actual == ClaseLexica.SUMA || actual == ClaseLexica.RESTA) {
                eat(actual); // consume el operador
                if (actual == ClaseLexica.IDENTIFICADOR || actual == ClaseLexica.NUMERO) {
                    eat(actual); // consume el siguiente identificador o número
                } else {
                    error("Se esperaba un identificador o número en la expresión");
                }
            }
        } else {
            error("Se esperaba un identificador o número en la expresión");
        }
    }
}
