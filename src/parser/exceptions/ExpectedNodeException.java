package parser.exceptions;

import ast.token.Token;

public class ExpectedNodeException extends ParseException {

  public ExpectedNodeException(String expected, Token found) {
    super(found.getLocation(), String.format("parse error: expected %s, found %s", expected, found));
  }
}
