package parser;

import ast.SegmentLocation;
import exception.LamaException;

public class UnterminatedMultiLineComment extends LamaException {

  public UnterminatedMultiLineComment(SegmentLocation location) {
    super(
        location,
        String.format(
            "Unterminated multiline comment: reached EOF before the end of comment"));
  }
}
