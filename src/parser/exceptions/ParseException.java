package parser.exceptions;

import ast.SegmentLocation;
import exceptions.LamaException;

public class ParseException extends LamaException {

  public ParseException(SegmentLocation location, String message) {
    super(location, message);
  }

}
