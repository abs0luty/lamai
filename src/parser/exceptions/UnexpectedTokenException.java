package parser.exceptions;

import ast.token.Token;
import ast.token.TokenKind;

public final class UnexpectedTokenException extends LexException {

  public UnexpectedTokenException(TokenKind expected, Token found) {
    super(found.getLocation(), String.format("parse error: expected %s, found %s", expected, found));
  }
}
