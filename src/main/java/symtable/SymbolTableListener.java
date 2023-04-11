package symtable;

import cymbol.CymbolBaseListener;
import cymbol.CymbolParser;

public class SymbolTableListener extends CymbolBaseListener {
  private final SymbolTableTreeGraph graph = new SymbolTableTreeGraph();
  private GlobalScope globalScope = null;
  private Scope currentScope = null;
  private int localScopeCounter = 0;

  @Override
  public void enterProg(CymbolParser.ProgContext ctx) {
  }

  @Override
  public void enterBlock(CymbolParser.BlockContext ctx) {
  }

  @Override
  public void enterFunctionDecl(CymbolParser.FunctionDeclContext ctx) {
  }

  @Override
  public void exitBlock(CymbolParser.BlockContext ctx) {
  }

  @Override
  public void exitFunctionDecl(CymbolParser.FunctionDeclContext ctx) {
  }

  @Override
  public void exitProg(CymbolParser.ProgContext ctx) {
  }

  @Override
  public void exitVarDecl(CymbolParser.VarDeclContext ctx) {
  }

  @Override
  public void exitId(CymbolParser.IdContext ctx) {
  }

  @Override
  public void exitFormalParameter(CymbolParser.FormalParameterContext ctx) {
  }

  public SymbolTableTreeGraph getGraph() {
    return graph;
  }
}