package parser.exceptions;

import ast.SegmentLocation;

public final class UnterminatedMultiLineComment extends LexException {

  public UnterminatedMultiLineComment(SegmentLocation location) {
    super(
        location,
        String.format(
            "Unterminated multiline comment: reached EOF before the end of comment"));
  }
}
