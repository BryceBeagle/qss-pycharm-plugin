// This is a generated file. Not intended for manual editing.
package ignormies.qss.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import ignormies.qss.psi.impl.*;

public interface QSSTypes {

  IElementType PROPERTY = new QSSElementType("PROPERTY");

  IElementType COMMENT = new QSSTokenType("COMMENT");
  IElementType CRLF = new QSSTokenType("CRLF");
  IElementType KEY = new QSSTokenType("KEY");
  IElementType SEPARATOR = new QSSTokenType("SEPARATOR");
  IElementType VALUE = new QSSTokenType("VALUE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == PROPERTY) {
        return new QSSPropertyImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
