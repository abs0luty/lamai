package parser.exceptions;

import ast.Token;
import ast.TokenKind;

public final class UnexpectedTokenException extends LexException {

  public UnexpectedTokenException(TokenKind expected, Token found) {
    super(found.getLocation(), String.format("Expected %s, found %s", expected, found));
  }
}
