package parser;

import java.util.PrimitiveIterator.OfInt;

import ast.ByteLocation;
import ast.SegmentLocation;
import ast.Token;
import ast.TokenKind;

class Lexer {

  private final OfInt codePointsIterator;
  private ByteLocation cursorLocation;

  private Integer currentCodePoint;
  private Integer nextCodePoint;

  /**
   * @param filepath path of the file that's going to be processed
   * @param input    contents of the file that's going to be processed
   */
  public Lexer(String filepath, String input) {
    this.codePointsIterator = input.codePoints().iterator();
    this.cursorLocation = new ByteLocation(filepath, 1, 0, 0);

    this.currentCodePoint = codePointsIterator.next();
    this.nextCodePoint = codePointsIterator.next();
  }

  /**
   * Calculates the number of bytes required to encode a given Unicode code point.
   *
   * @param codepoint Unicode code point to be encoded
   * @return number of bytes required to encode the code point
   */
  private int bytesInCodePoint(int codepoint) {
    if (codepoint <= 0x7F) {
      return 1;
    } else if (codepoint <= 0x7FF) {
      return 2;
    } else if (codepoint <= 0xFFFF) {
      return 3;
    } else if (codepoint <= 0x10FFFF) {
      return 4;
    } else {
      return 1;
    }
  }

  private SegmentLocation currentCodePointLocation() {
    return new SegmentLocation(
        cursorLocation,
        cursorLocation.locationOfNextByte(
            bytesInCodePoint(currentCodePoint)));
  }

  /**
   * Advances code points iterator to the next code point in the file contents.
   */
  private void advance() {
    // if it's the end of the file, do nothing
    if (currentCodePoint == null) {
      return;
    }

    if (currentCodePoint == '\n') {
      cursorLocation.setColumn(0);
      cursorLocation.setLineNumber(cursorLocation.getLineNumber() + 1);
    } else {
      cursorLocation.setColumn(cursorLocation.getColumn() + 1);
    }

    cursorLocation.setOffset(cursorLocation.getOffset() + bytesInCodePoint(currentCodePoint));

    currentCodePoint = nextCodePoint;
    nextCodePoint = codePointsIterator.hasNext() ? codePointsIterator.next() : 0;
  }

  /**
   * Advances code points iterator to the next code point in the file contents
   * twice.
   */
  private void advanceTwice() {
    advance();
    advance();
  }

  /**
   * Retrieves the next identifier or keyword token.
   *
   * @return an identifier or a keyword token
   */
  private Token nextIdentifierOrKeyword() {
    ByteLocation startLocation = cursorLocation;

    while (Character.isAlphabetic(currentCodePoint)) {
      advance();
    }

    return new Token(
        TokenKind.IDENTIFIER,
        new SegmentLocation(startLocation, cursorLocation));
  }

  public Token nextToken() throws UnexpectedCharacterException {
    if (currentCodePoint == null) {
      return new Token(TokenKind.EOF, new SegmentLocation(cursorLocation));
    }

    throw new UnexpectedCharacterException(
        currentCodePointLocation(),
        currentCodePoint.intValue());
  }
}
