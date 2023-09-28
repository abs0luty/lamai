package parser;

import java.util.ArrayList;
import java.util.List;

import ast.Identifier;
import ast.expression.Expression;
import ast.location.ByteLocation;
import ast.location.SegmentLocation;
import ast.statement.BreakStatement;
import ast.statement.ContinueStatement;
import ast.statement.DoWhileStatement;
import ast.statement.FunctionDefinitionStatement;
import ast.statement.Statement;
import ast.token.Token;
import ast.token.TokenKind;
import parser.exceptions.ParseException;
import parser.exceptions.ExpectedNodeException;
import parser.exceptions.ExpectedTokenException;

public final class Parser {

  private Lexer lexer;
  private Token currentToken, nextToken;

  public Parser(Lexer lexer) throws ParseException {
    this.lexer = lexer;
    this.currentToken = lexer.nextTokenExceptComment();
    this.nextToken = lexer.nextTokenExceptComment();
  }

  private void advance() throws ParseException {
    currentToken = nextToken;
    nextToken = lexer.nextTokenExceptComment();
  }

  public List<Statement> parse() throws ParseException {
    List<Statement> statements = new ArrayList<>();

    while (currentToken.getKind() != TokenKind.EOF) {
      statements.add(parseStatement());
    }

    return statements;
  }

  private Statement parseStatement() throws ParseException {
    switch (currentToken.getKind()) {
      case BREAK:
        return parseBreakStatement();
      case CONTINUE:
        return parseContinueStatement();
      case FN:
        return parseFunctionDefinitionStatement();
      case DO:
        return parseDoWhileStatement();
      default:
        throw new ExpectedNodeException("statement", currentToken);
    }
  }

  private FunctionDefinitionStatement parseFunctionDefinitionStatement() throws ParseException {
    advance();
    consume(TokenKind.OPEN_PARENTHESIS);

    final Identifier name = consumeIdentifier();
    final List<Identifier> parameters = new ArrayList<>();

    while (!currentToken.getKind().equals(TokenKind.CLOSE_PARENTHESIS)) {
      parameters.add(consumeIdentifier());
    }

    final Statement body = parseStatement();

    return new FunctionDefinitionStatement(
        name,
        parameters,
        body);
  }

  private DoWhileStatement parseDoWhileStatement() throws ParseException {
    advance();

    final ByteLocation startLocation = currentToken.getLocation().getFirstByteLocation();
    final Statement statement = parseStatement();

    consume(TokenKind.WHILE);

    final Expression condition = parseExpression();

    final SegmentLocation location = new SegmentLocation(startLocation,
        currentToken.getLocation().getLastByteLocation());

    return new DoWhileStatement(condition, statement, location);
  }

  private BreakStatement parseBreakStatement() throws ParseException {
    advance();
    return new BreakStatement(currentToken.getLocation());
  }

  private ContinueStatement parseContinueStatement() throws ParseException {
    advance();
    return new ContinueStatement(currentToken.getLocation());
  }

  private Expression parseExpression() throws ParseException {
    switch (currentToken.getKind()) {
      // TODO: add more cases
      default:
        throw new ExpectedNodeException("expression", currentToken);
    }
  }

  private Identifier consumeIdentifier() throws ParseException {
    expect(TokenKind.IDENTIFIER);

    return new Identifier(
        resolveLocation(currentToken.getLocation()),
        currentToken.getLocation());
  }

  private String resolveLocation(SegmentLocation location) {
    return lexer.getContents().substring(
        location.getFirstByteLocation().getOffset(),
        location.getLastByteLocation().getOffset());
  }

  private void consume(TokenKind kind) throws ParseException {
    expect(kind);
    advance();
  }

  private void expect(TokenKind expected) throws ParseException {
    if (nextToken.getKind() != expected) {
      throw new ExpectedTokenException(expected, nextToken);
    }

    nextToken = lexer.nextTokenExceptComment();
  }
}
