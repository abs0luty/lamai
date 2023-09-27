package parser.exceptions;

import ast.location.SegmentLocation;

public final class UnexpectedCharacterException extends LexException {

  public UnexpectedCharacterException(SegmentLocation location, int characterCodePoint) {
    super(
        location,
        String.format(
            "parse error: unexpected character `%c` (0x%04x)",
            characterCodePoint,
            characterCodePoint));
  }
}
