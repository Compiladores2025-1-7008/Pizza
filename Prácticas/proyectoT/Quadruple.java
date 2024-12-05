public class Quadruple {
    private String operator;  // Operador (+, -, *, /, =, goto, etc.)
    private String operand1;  // Primer operando
    private String operand2;  // Segundo operando (puede ser null)
    private String result;    // Resultado de la operación

    public Quadruple(String operator, String operand1, String operand2, String result) {
        this.operator = operator;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.result = result;
    }

    // Getters y Setters
    public String getOperator() { return operator; }
    public String getOperand1() { return operand1; }
    public String getOperand2() { return operand2; }
    public String getResult() { return result; }

    @Override
    public String toString() {
        return "(" + operator + ", " + operand1 + ", " + operand2 + ", " + result + ")";
    }
}
