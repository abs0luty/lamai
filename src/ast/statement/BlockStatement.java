package ast.statement;

import java.util.List;

import ast.literal.Statement;
import ast.location.SegmentLocation;
import ast.visitor.Visitor;

public class BlockStatement implements Statement {

  private SegmentLocation location;
  private List<Statement> statements;

  public BlockStatement(SegmentLocation location, List<Statement> statements) {
    this.location = location;
    this.statements = statements;
  }

  @Override
  public SegmentLocation getLocation() {
    return location;
  }

  public List<Statement> getStatements() {
    return statements;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
