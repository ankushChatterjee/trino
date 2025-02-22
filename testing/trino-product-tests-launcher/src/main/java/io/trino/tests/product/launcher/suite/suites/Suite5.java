/*
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
 */
package io.trino.tests.product.launcher.suite.suites;

import com.google.common.collect.ImmutableList;
import io.trino.tests.product.launcher.env.EnvironmentConfig;
import io.trino.tests.product.launcher.env.environment.EnvMultinodeHiveCaching;
import io.trino.tests.product.launcher.env.environment.EnvSinglenodeHiveImpersonation;
import io.trino.tests.product.launcher.env.environment.EnvSinglenodeKerberosHiveImpersonation;
import io.trino.tests.product.launcher.env.environment.EnvSinglenodeKerberosHiveImpersonationWithCredentialCache;
import io.trino.tests.product.launcher.suite.Suite;
import io.trino.tests.product.launcher.suite.SuiteTestRun;

import java.util.List;

import static io.trino.tests.product.TestGroups.AUTHORIZATION;
import static io.trino.tests.product.TestGroups.CONFIGURED_FEATURES;
import static io.trino.tests.product.TestGroups.HDFS_IMPERSONATION;
import static io.trino.tests.product.TestGroups.HIVE_CACHING;
import static io.trino.tests.product.TestGroups.HIVE_KERBEROS;
import static io.trino.tests.product.TestGroups.ICEBERG;
import static io.trino.tests.product.TestGroups.STORAGE_FORMATS;
import static io.trino.tests.product.launcher.suite.SuiteTestRun.testOnEnvironment;

public class Suite5
        extends Suite
{
    @Override
    public List<SuiteTestRun> getTestRuns(EnvironmentConfig config)
    {
        return ImmutableList.of(
                testOnEnvironment(EnvSinglenodeHiveImpersonation.class)
                        .withGroups(CONFIGURED_FEATURES, STORAGE_FORMATS, HDFS_IMPERSONATION)
                        .build(),
                testOnEnvironment(EnvSinglenodeKerberosHiveImpersonation.class)
                        .withGroups(CONFIGURED_FEATURES, STORAGE_FORMATS, HDFS_IMPERSONATION, AUTHORIZATION, HIVE_KERBEROS)
                        .build(),
                testOnEnvironment(EnvSinglenodeKerberosHiveImpersonationWithCredentialCache.class)
                        .withGroups(CONFIGURED_FEATURES, STORAGE_FORMATS, HDFS_IMPERSONATION, AUTHORIZATION)
                        .build(),
                testOnEnvironment(EnvMultinodeHiveCaching.class)
                        .withGroups(CONFIGURED_FEATURES, HIVE_CACHING, STORAGE_FORMATS)
                        .withExcludedGroups(ICEBERG)
                        .build());
    }
}
