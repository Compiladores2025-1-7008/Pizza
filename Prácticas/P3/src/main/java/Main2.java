package main.java;

import java.io.FileNotFoundException;
import java.io.FileReader;
import main.jflex.Lexer;
import main.java.Parser2;

public class Main2 {
    public static void main(String[] args) {
        try {
            Parser2 parser = new Parser2(new Lexer(new FileReader(args[0])));
            parser.parse();
        } catch (FileNotFoundException fnfe) {
            System.err.println("Error: No fue posible leer del archivo de entrada: "+args[0]);
            System.exit(1);
        }
    }
}
