import ast.token.Token;
import parser.Lexer;

public class Main {

    public static void main(String[] args) {
        Lexer lexer = new Lexer(
                "test",
                "// hello\nprint 1;");
        try {
            Token token = lexer.nextToken();
            System.out.println(token.getKind());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}