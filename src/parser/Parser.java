package parser;

import ast.Token;

public final class Parser {

  private Lexer lexer;
  private Token currentToken, nextToken;

  public Parser(Lexer lexer) throws Exception {
    this.lexer = lexer;
    this.currentToken = lexer.nextTokenExceptComment();
    this.nextToken = lexer.nextTokenExceptComment();
  }
}
