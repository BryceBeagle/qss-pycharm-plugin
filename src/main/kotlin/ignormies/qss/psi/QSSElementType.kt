package ignormies.qss.psi

import com.intellij.psi.tree.IElementType
import ignormies.qss.QSSLanguage
import org.jetbrains.annotations.NonNls

class QSSElementType(@NonNls debugName: String) : IElementType(debugName, QSSLanguage)
