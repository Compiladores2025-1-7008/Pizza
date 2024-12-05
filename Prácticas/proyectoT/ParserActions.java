


public class ParserActions {
    private QuadrupleGenerator codeGen = new QuadrupleGenerator();

    /**Recibe operandos y genera las acciones semanticas del archivo byacc para 
     * no ocupar tanto espacio en el archivo principal 
     */

    public String handleAddition(ParserVal operand1, ParserVal operand2) {
        //Aqui tambien tendria que veficar de que tipo son para poder pasarlo a string correctamente , porque solo esta con double 
        String temp = codeGen.newTemp();
        codeGen.emit("+",Double.toString( operand1.dval),Double.toString( operand2.dval), temp);
        return temp;
    }

    public String handleSubtraction(ParserVal  operand1,ParserVal  operand2) {
        String temp = codeGen.newTemp();
        codeGen.emit("-", Integer.toString( operand1.ival), Integer.toString( operand2.ival), temp);
        return temp;
    }

    public String handleMultiplication(ParserVal  operand1, ParserVal  operand2) {
        String temp = codeGen.newTemp();
        codeGen.emit("*", Integer.toString( operand1.ival), Integer.toString( operand2.ival), temp);
        return temp;
    }

    public String handleDivi(ParserVal  operand1, ParserVal  operand2) {
        String temp = codeGen.newTemp();
        codeGen.emit("/", Integer.toString( operand1.ival), Integer.toString( operand2.ival), temp);
        return temp;
    }




    // Método para imprimir el código generado
    public void printGeneratedCode() {
        codeGen.printQuadruples();
    }
}
