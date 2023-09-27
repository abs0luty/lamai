package ast.token;

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

  EQUALS,
  DOUBLE_EQUALS,
  EXCLAMATION,
  EXCLAMATION_EQUALS,
  LESS,
  LESS_EQUALS,
  GREATER,
  GREATER_EQUALS,
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
  COMMA,
  SINGLELINE_COMMENT,
  MULTILINE_COMMENT,
  EOF
}
