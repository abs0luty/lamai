package ast;

import ast.location.LocationAware;
import ast.visitor.Visitor;

public interface Node extends LocationAware {

    void accept(Visitor visitor);
}
