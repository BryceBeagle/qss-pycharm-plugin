package ignormies.qss

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import ignormies.qss.parser.QSSParser
import ignormies.qss.psi.QSSFile
import ignormies.qss.psi.QSSTypes

class QSSParserDefinition : ParserDefinition {

    companion object {
        var WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE)
        var COMMENTS = TokenSet.create(QSSTypes.COMMENT)

        var FILE = IFileElementType(QSSLanguage)
    }

    override fun createLexer(project: Project?): Lexer {
        return QSSLexerAdapter()
    }

    override fun getWhitespaceTokens(): TokenSet {
        return WHITE_SPACES
    }

    override fun getCommentTokens(): TokenSet {
        return COMMENTS
    }

    override fun getStringLiteralElements(): TokenSet {
        return TokenSet.EMPTY
    }

    override fun createParser(project: Project?): PsiParser {
        return QSSParser()
    }

    override fun getFileNodeType(): IFileElementType {
        return FILE
    }

    override fun createFile(viewProvider: FileViewProvider): PsiFile {
        return QSSFile(viewProvider)
    }

    override fun spaceExistenceTypeBetweenTokens(left: ASTNode?, right: ASTNode?): ParserDefinition.SpaceRequirements {
        return ParserDefinition.SpaceRequirements.MAY
    }

    override fun createElement(node: ASTNode): PsiElement {
        return QSSTypes.Factory.createElement(node)
    }
}