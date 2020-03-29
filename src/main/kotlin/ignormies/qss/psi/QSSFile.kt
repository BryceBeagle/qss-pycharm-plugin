package ignormies.qss.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import ignormies.qss.QSSFileType
import ignormies.qss.QSSLanguage

class QSSFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, QSSLanguage) {
    override fun getFileType(): FileType {
        return QSSFileType
    }

    override fun toString(): String {
        return "QSS File"
    }
}
