package ignormies.qss

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

object QSSFileType : LanguageFileType(QSSLanguage) {
    override fun getIcon(): Icon? {
        return QSSIcons.FILE
    }

    override fun getName(): String {
        return "QSS"
    }

    override fun getDefaultExtension(): String {
        return "qss"
    }

    override fun getDescription(): String {
        return "Qt Stylesheet file"
    }
}