package parser;

import ast.SegmentLocation;
import exception.LamaException;

public class UnexpectedCharacterException extends LamaException {
  public UnexpectedCharacterException(SegmentLocation location, int characterCodePoint) {
    super(
        location,
        String.format(
            "Unexpected character `%c` (0x%04x)",
            characterCodePoint,
            characterCodePoint));
  }
}
