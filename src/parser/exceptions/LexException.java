package parser.exceptions;

import ast.location.SegmentLocation;

public class LexException extends ParseException {

  public LexException(SegmentLocation location, String message) {
    super(location, message);
  }
}
