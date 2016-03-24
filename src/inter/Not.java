package inter;

import lexer.*;
import symbols.*;

public class Not extends Logical {

    public Not(Token tol, Expr x2) {
        super(tol, x2, x2);
    }

    @Override
    public void jumping(int t, int f) {
        expr2.jumping(f, t);
    }

    @Override
    public String toString() {
        return op.toString() + " " + expr2.toString();
    }
}
