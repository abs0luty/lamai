package ast;

public final class Token implements LocationAware {

  private final TokenKind kind;
  private final SegmentLocation location;

  public Token(TokenKind kind, SegmentLocation location) {
    this.kind = kind;
    this.location = location;
  }

  /**
   * @return the kind of the token
   */
  public TokenKind getKind() {
    return kind;
  }

  @Override
  public SegmentLocation getLocation() {
    return location;
  }
}
