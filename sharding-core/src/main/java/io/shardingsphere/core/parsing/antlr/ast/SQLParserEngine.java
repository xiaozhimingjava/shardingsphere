/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingsphere.core.parsing.antlr.ast;

import io.shardingsphere.core.constant.DatabaseType;
import io.shardingsphere.core.parsing.antlr.ast.impl.SQLParserFactory;
import io.shardingsphere.core.parsing.parser.exception.SQLParsingUnsupportedException;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * SQL parser engine.
 * 
 * @author zhangliang
 */
public final class SQLParserEngine {
    
    /**
     * Parse SQL to AST.
     * 
     * @param databaseType database type
     * @param sql SQL
     * @return Abstract syntax tree of SQL
     */
    public SQLAST parse(final DatabaseType databaseType, final String sql) {
        ParseTree parseTree = SQLParserFactory.newInstance(databaseType, sql).execute().getChild(0);
        if (parseTree instanceof ErrorNode) {
            throw new SQLParsingUnsupportedException(String.format("Unsupported SQL of `%s`", sql));
        }
        return new SQLAST((ParserRuleContext) parseTree);
    }
}
