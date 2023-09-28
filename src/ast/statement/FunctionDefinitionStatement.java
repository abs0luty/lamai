package ast.statement;

import java.util.List;

import ast.Identifier;
import ast.location.SegmentLocation;
import ast.visitor.Visitor;

public class FunctionDefinitionStatement implements Statement {

  private final Identifier name;
  private final List<Identifier> parameters;
  private final Statement body;

  public FunctionDefinitionStatement(Identifier name, List<Identifier> parameters, Statement body) {
    this.name = name;
    this.parameters = parameters;
    this.body = body;
  }

  public Identifier getName() {
    return name;
  }

  public List<Identifier> getParameters() {
    return parameters;
  }

  public Statement getBody() {
    return body;
  }

  @Override
  public SegmentLocation getLocation() {
    return new SegmentLocation(
        name.getLocation().getFirstByteLocation(),
        body.getLocation().getLastByteLocation());
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
