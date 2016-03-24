package parser;

import java.io.*;
import lexer.*;
import symbols.*;
import inter.*;

public class Parser {

    private Lexer lex; // lexer analizator
    private Token look; // preview
    private Env top = null; // table of symbols
    int used = 0; // memory for allocation

    public Parser(Lexer l) throws IOException {
        lex = l;
        move();
    }

    void move() throws IOException {
        look = lex.scan();
    }

    void error(String s) {
        throw new Error("near line " + lex.line + ": " + s);
    }

    void match(int t) throws IOException {
        if (look.tag == t) {
            move();
        } else {
            error("syntax error");
        }
    }

    public void program() throws IOException {
        // program -> block
        Stmt s = block();
        int begin = s.newlabel();
        int after = s.newlabel();
        s.emitlabel(begin);
        s.gen(begin, after);
        s.emitlabel(after);
    }
    private Stmt block() throws IOException {
        // blocl -> { decls stmts}
        match('{');
        Env saveEnv = top;
        top = new Env(top);
        decls();
        Stmt s = stmts();
        match('}');
        top = saveEnv;
        return s;
    }
}
