package parser.exceptions;

import ast.SegmentLocation;

public class LexException extends ParseException {

  public LexException(SegmentLocation location, String message) {
    super(location, message);
  }
}
