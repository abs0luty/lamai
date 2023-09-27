package ast;

public enum TokenKind {
  /**
   * An integer literal token.
   */
  INTEGER,

  /**
   * A string literal token.
   */
  STRING,

  /**
   * An identifier token.
   */
  IDENTIFIER,

  /**
   * Keyword `print`.
   */
  PRINT,

  /**
   * Keyword `if`.
   */
  IF,

  /**
   * Keyword `else`.
   */
  ELSE,

  /**
   * Keyword `while`.
   */
  WHILE,

  /**
   * Keyword `for`.
   */
  FOR,

  /**
   * Keyword `do`.
   */
  DO,

  /**
   * Keyword `break`.
   */
  BREAK,

  /**
   * Keyword `continue`.
   */
  CONTINUE,

  /**
   * Keyword `fn`.
   */
  FN,

  /**
   * Keyword `return`.
   */
  RETURN,

  /**
   * Keyword `import`.
   */
  IMPORT,

  /**
   * Plus operator `+`.
   */
  PLUS,

  /**
   * Minus operator `-`.
   */
  MINUS,

  /**
   * Asterisk operator `*`.
   */
  ASTERISK,

  /**
   * Slash operator `/`.
   */
  SLASH,

  /**
   * Percent operator `%`.
   */
  PERCENT,

  EQUAL,
  DOUBLE_EQUAL,
  EXCLAMATION,
  EXCLAMATION_EQUAL,
  LESS,
  LESS_EQUAL,
  GREATER,
  GREATER_EQUAL,
  LEFT_SHIFT,
  RIGHT_SHIFT,
  TRIPLE_GREATER,
  TILDE,
  CARET,
  BAR,
  DOUBLE_BAR,
  AMPERSAND,
  DOUBLE_AMPERSAND,
  QUESTION,
  COLON,
  OPEN_PARENTHESIS,
  CLOSE_PARENTHESIS,
  OPEN_BRACKET,
  CLOSE_BRACKET,
  OPEN_BRACE,
  CLOSE_BRACE,
  EOF
}
