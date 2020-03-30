package ignormies.qss;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import ignormies.qss.psi.QSSTypes;
import com.intellij.psi.TokenType;

%%

%class QSSLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType

EOL=\R
WHITE_SPACE=\s+

IDENTIFIER=[a-zA-Z_]\w*

SEMICOLON=";"
OPEN_BRACE="{"
CLOSE_BRACE="}"

%state DECLARATION

%%

<YYINITIAL> {
    {IDENTIFIER}                                                   {return QSSTypes.CLASS_NAME;}
    {OPEN_BRACE}                                                   {yybegin(DECLARATION); return QSSTypes.OPEN_BRACE;}
}

<DECLARATION> {
    {WHITE_SPACE}                                                  {}
    {IDENTIFIER} / {WHITE_SPACE}? ({SEMICOLON} | {CLOSE_BRACE})    {return QSSTypes.PROPERTY;}
    {IDENTIFIER}                                                   {}
    {SEMICOLON}                                                    {return QSSTypes.SEMICOLON;}
    {CLOSE_BRACE}                                                  {yybegin(YYINITIAL); return QSSTypes.CLOSE_BRACE;}
}

{WHITE_SPACE}                                                      { return TokenType.WHITE_SPACE; }

[^]                                                                { return TokenType.BAD_CHARACTER; }
