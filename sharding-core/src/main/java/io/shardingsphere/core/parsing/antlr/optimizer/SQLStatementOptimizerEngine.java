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

package io.shardingsphere.core.parsing.antlr.optimizer;

import com.google.common.base.Optional;
import io.shardingsphere.core.constant.DatabaseType;
import io.shardingsphere.core.metadata.table.ShardingTableMetaData;
import io.shardingsphere.core.parsing.antlr.ast.SQLStatementType;
import io.shardingsphere.core.parsing.antlr.optimizer.impl.SQLStatementOptimizer;
import io.shardingsphere.core.parsing.antlr.optimizer.impl.SQLStatementOptimizerFactory;
import io.shardingsphere.core.parsing.parser.sql.SQLStatement;

/**
 * SQL statement optimizer engine.
 * 
 * @author zhangliang
 */
public final class SQLStatementOptimizerEngine {
    
    /**
     * Optimize SQL statement.
     *
     * @param databaseType database type
     * @param sqlStatementType SQL statement type
     * @param sqlStatement SQL statement
     * @param shardingTableMetaData sharding table meta data
     */
    public void optimize(final DatabaseType databaseType, final SQLStatementType sqlStatementType, final SQLStatement sqlStatement, final ShardingTableMetaData shardingTableMetaData) {
        Optional<SQLStatementOptimizer> optimizer = SQLStatementOptimizerFactory.getInstance(databaseType, sqlStatementType);
        if (optimizer.isPresent()) {
            optimizer.get().optimize(sqlStatement, shardingTableMetaData);
        }
    }
}
