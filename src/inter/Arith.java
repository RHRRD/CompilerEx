package inter;

import lexer.*;
import symbols.*;

public class Arith extends Op {

    public Expr expr1, expr2;

    public Arith(Token tok, Expr ex1, Expr ex2) {
        super(tok, null);
        expr1 = ex1;
        expr2 = ex2;
        type = Type.max(expr1.type, expr2.type);
        if (type == null) {
            error("type error");
        }
    }

    public Expr gen() {
        return new Arith(op, expr1.reduce(), expr2.reduce());
    }

    @Override
    public String toString() {
        return expr1.toString() + " " + op.toString() + " " + expr2.toString();
    }
}