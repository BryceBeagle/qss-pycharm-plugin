package ignormies.qss.psi

import com.intellij.psi.tree.IElementType
import ignormies.qss.QSSLanguage
import org.jetbrains.annotations.NonNls

class QSSTokenType(@NonNls debugName: String) : IElementType(debugName, QSSLanguage) {

    override fun toString(): String {
        return "QSSTokenType.${super.toString()}"
    }

}