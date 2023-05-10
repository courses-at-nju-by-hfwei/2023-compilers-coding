package codegen;

import java.io.FileWriter;
import java.io.IOException;

public class CodeGenVisitor extends ControlBaseVisitor<String> {
  private final FileWriter fileWriter;
  private int tempCounter = 1;
  private int labelCounter = 1;

  public CodeGenVisitor(String file) throws IOException {
    fileWriter = new FileWriter(file);
  }

  private void emitLabel(String label) {
    try {
      fileWriter.write(label + ":\n");
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage() + " : " + label);
    }
  }

  private void emit(String code) {
    try {
      fileWriter.write(code + '\n');
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage() + " : " + code);
    }
  }

  private String getNewTemp() {
    return "t" + tempCounter++;
  }

  private String getNewLabel(String label) {
    return label + labelCounter++;
  }
}