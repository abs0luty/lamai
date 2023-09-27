package parser.exceptions;

import ast.SegmentLocation;

public final class UnexpectedCharacterException extends LexException {
  public UnexpectedCharacterException(SegmentLocation location, int characterCodePoint) {
    super(
        location,
        String.format(
            "Unexpected character `%c` (0x%04x)",
            characterCodePoint,
            characterCodePoint));
  }
}
