package parser;

import java.util.HashMap;
import java.util.PrimitiveIterator.OfInt;

import ast.ByteLocation;
import ast.SegmentLocation;
import ast.Token;
import ast.TokenKind;

public class Lexer {

  private final OfInt codePointsIterator;
  private ByteLocation cursorLocation;

  private Integer currentCodePoint;
  private Integer nextCodePoint;

  private static final HashMap<String, TokenKind> KEYWORDS;

  static {
    KEYWORDS = new HashMap<>();

    KEYWORDS.put("print", TokenKind.PRINT);
    KEYWORDS.put("if", TokenKind.IF);
    KEYWORDS.put("else", TokenKind.ELSE);
    KEYWORDS.put("while", TokenKind.WHILE);
    KEYWORDS.put("for", TokenKind.FOR);
    KEYWORDS.put("do", TokenKind.DO);
    KEYWORDS.put("break", TokenKind.BREAK);
    KEYWORDS.put("continue", TokenKind.CONTINUE);
    KEYWORDS.put("fn", TokenKind.FN);
    KEYWORDS.put("return", TokenKind.RETURN);
    KEYWORDS.put("import", TokenKind.IMPORT);
  }

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

  private SegmentLocation currentByteLocation() {
    return new SegmentLocation(
        cursorLocation,
        cursorLocation.locationOfNextByte());
  }

  private SegmentLocation currentTwoBytesLocation() {
    return new SegmentLocation(
        cursorLocation,
        cursorLocation.locationOfNextByte(2));
  }

  private SegmentLocation previousByteLocation() {
    return new SegmentLocation(
        cursorLocation.locationOfPreviousByte(),
        cursorLocation);
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
    nextCodePoint = codePointsIterator.next();
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
  private Token nextIdentifierOrKeywordToken() {
    final StringBuilder builder = new StringBuilder();
    final ByteLocation startLocation = cursorLocation;

    while (Character.isAlphabetic(currentCodePoint)) {
      builder.appendCodePoint(currentCodePoint);
      advance();
    }

    final String buffer = builder.toString();
    final SegmentLocation location = new SegmentLocation(startLocation, cursorLocation);

    final TokenKind potentialKeyword = KEYWORDS.get(buffer);

    if (potentialKeyword != null) {
      return new Token(potentialKeyword, location);
    }

    return new Token(
        TokenKind.IDENTIFIER,
        location);
  }

  private void skipThroughWhitespaces() {
    while (Character.isWhitespace(currentCodePoint)) {
      advance();
    }
  }

  private boolean isEof() {
    return currentCodePoint == null;
  }

  private Token nextSingleLineCommentToken() {
    advanceTwice(); // eat two slashes (`//`)

    final ByteLocation startLocation = cursorLocation;

    while (currentCodePoint != '\n' && currentCodePoint != '\r' && !isEof()) {
      advance();
    }

    return new Token(TokenKind.SINGLELINE_COMMENT, new SegmentLocation(startLocation, cursorLocation));
  }

  private Token nextMultiLineCommentToken() throws UnterminatedMultiLineComment {
    advanceTwice(); // eat slash + asterisk (`/*`)

    final ByteLocation startLocation = cursorLocation;

    while (currentCodePoint != '*' && currentCodePoint != '/') {
      if (isEof()) {
        throw new UnterminatedMultiLineComment(new SegmentLocation(startLocation, cursorLocation));
      }

      advance();
    }

    return new Token(TokenKind.MULTILINE_COMMENT, new SegmentLocation(startLocation, cursorLocation));
  }

  public Token nextTokenExceptComments() throws UnexpectedCharacterException, UnterminatedMultiLineComment {
    Token token = nextToken();

    while (token.getKind() == TokenKind.SINGLELINE_COMMENT
        || token.getKind() == TokenKind.MULTILINE_COMMENT) {
      token = nextToken();
    }

    return token;
  }

  public Token nextToken() throws UnexpectedCharacterException, UnterminatedMultiLineComment {
    skipThroughWhitespaces();

    if (isEof()) {
      return new Token(TokenKind.EOF, new SegmentLocation(cursorLocation));
    }

    if (currentCodePoint == '/' && nextCodePoint == '/') {
      return nextSingleLineCommentToken();
    }

    if (currentCodePoint == '/' && nextCodePoint == '*') {
      return nextMultiLineCommentToken();
    }

    if (Character.isAlphabetic(currentCodePoint)) {
      return nextIdentifierOrKeywordToken();
    }

    if (currentCodePoint == '(') {
      return new Token(TokenKind.OPEN_PARENTHESIS, currentByteLocation());
    } else if (currentCodePoint == ')') {
      return new Token(TokenKind.CLOSE_PARENTHESIS, currentByteLocation());
    } else if (currentCodePoint == '[') {
      return new Token(TokenKind.OPEN_BRACKET, currentByteLocation());
    } else if (currentCodePoint == ']') {
      return new Token(TokenKind.CLOSE_BRACKET, currentByteLocation());
    } else if (currentCodePoint == '{') {
      return new Token(TokenKind.OPEN_BRACE, currentByteLocation());
    } else if (currentCodePoint == '}') {
      return new Token(TokenKind.CLOSE_BRACE, currentByteLocation());
    } else if (currentCodePoint == '+') {
      return new Token(TokenKind.PLUS, currentByteLocation());
    } else if (currentCodePoint == '-') {
      return new Token(TokenKind.MINUS, currentByteLocation());
    } else if (currentCodePoint == '*') {
      return new Token(TokenKind.ASTERISK, currentByteLocation());
    } else if (currentCodePoint == '/') {
      return new Token(TokenKind.SLASH, currentByteLocation());
    } else if (currentCodePoint == '%') {
      return new Token(TokenKind.PERCENT, currentByteLocation());
    } else if (currentCodePoint == '=') {
      if (nextCodePoint == '=') {
        return new Token(TokenKind.DOUBLE_EQUALS, currentTwoBytesLocation());
      }

      return new Token(TokenKind.EQUALS, currentByteLocation());
    } else if (currentCodePoint == '!') {
      if (nextCodePoint == '=') {
        return new Token(TokenKind.EXCLAMATION_EQUALS, currentTwoBytesLocation());
      }

      return new Token(TokenKind.EXCLAMATION, currentByteLocation());
    } else if (currentCodePoint == '<') {
      if (nextCodePoint == '=') {
        return new Token(TokenKind.LESS_EQUALS, currentTwoBytesLocation());
      }

      if (nextCodePoint == '<') {
        return new Token(TokenKind.LEFT_SHIFT, currentTwoBytesLocation());
      }

      return new Token(TokenKind.LESS, currentByteLocation());
    } else if (currentCodePoint == '>') {
      if (nextCodePoint == '=') {
        return new Token(TokenKind.GREATER_EQUALS, currentTwoBytesLocation());
      }

      advance();

      if (currentCodePoint == '>' && nextCodePoint == '>') {
        return new Token(TokenKind.TRIPLE_GREATER,
            new SegmentLocation(cursorLocation.locationOfPreviousByte(), cursorLocation.locationOfNextByte(2)));
      }

      if (currentCodePoint == '>') {
        return new Token(TokenKind.RIGHT_SHIFT,
            new SegmentLocation(cursorLocation.locationOfPreviousByte(), cursorLocation.locationOfNextByte()));
      }

      return new Token(TokenKind.GREATER, previousByteLocation());
    } else if (currentCodePoint == '&') {
      if (nextCodePoint == '&') {
        return new Token(TokenKind.DOUBLE_AMPERSAND, currentTwoBytesLocation());
      }

      return new Token(TokenKind.AMPERSAND, currentByteLocation());
    } else if (currentCodePoint == '|') {
      if (nextCodePoint == '|') {
        return new Token(TokenKind.DOUBLE_BAR, currentTwoBytesLocation());
      }

      return new Token(TokenKind.BAR, currentByteLocation());
    } else if (currentCodePoint == '~') {
      return new Token(TokenKind.TILDE, currentByteLocation());
    } else if (currentCodePoint == '?') {
      return new Token(TokenKind.QUESTION, currentByteLocation());
    } else if (currentCodePoint == ':') {
      return new Token(TokenKind.COLON, currentByteLocation());
    }

    throw new UnexpectedCharacterException(currentCodePointLocation(), currentCodePoint.intValue());
  }
}
