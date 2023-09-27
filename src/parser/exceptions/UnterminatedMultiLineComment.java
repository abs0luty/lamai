package parser.exceptions;

import ast.location.SegmentLocation;

public final class UnterminatedMultiLineComment extends LexException {

  public UnterminatedMultiLineComment(SegmentLocation location) {
    super(
        location,
        String.format(
            "parse error: unterminated multiline comment: reached EOF before the end of comment"));
  }
}
