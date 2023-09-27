package ast;

public record Token(TokenKind kind, SegmentLocation location) implements LocationAware {

  @Override
  public SegmentLocation getLocation() {
    return location;
  }
}
