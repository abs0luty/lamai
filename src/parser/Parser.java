package parser;

import ast.BreakStatement;
import ast.Token;
import ast.TokenKind;
import parser.exceptions.ParseException;
import parser.exceptions.UnexpectedTokenException;

public final class Parser {

  private Lexer lexer;
  private Token currentToken, nextToken;

  public Parser(Lexer lexer) throws Exception {
    this.lexer = lexer;
    this.currentToken = lexer.nextTokenExceptComment();
    this.nextToken = lexer.nextTokenExceptComment();
  }

  private void advance() throws ParseException {
    currentToken = nextToken;
    nextToken = lexer.nextTokenExceptComment();
  }

  private BreakStatement parseBreakStatement() throws ParseException {
    consume(TokenKind.BREAK);

    return new BreakStatement(currentToken.getLocation());
  }

  private void consume(TokenKind kind) throws ParseException {
    expect(kind);
    advance();
  }

  private void expect(TokenKind expected) throws ParseException {
    if (nextToken.getKind() != expected) {
      throw new UnexpectedTokenException(expected, nextToken);
    }

    nextToken = lexer.nextTokenExceptComment();
  }
}
