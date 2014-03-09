/*
 * Copyright 2010-2013 Ning, Inc.
 *
 * Ning licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.killbill.billing.account.glue;

import org.skife.config.ConfigSource;

import org.killbill.billing.GuicyKillbillTestNoDBModule;
import org.killbill.billing.account.dao.AccountDao;
import org.killbill.billing.account.dao.MockAccountDao;
import org.killbill.billing.mock.glue.MockNonEntityDaoModule;
import org.killbill.billing.util.bus.InMemoryBusModule;

public class TestAccountModuleNoDB extends TestAccountModule {

    public TestAccountModuleNoDB(final ConfigSource configSource) {
        super(configSource);
    }

    @Override
    protected void installAccountDao() {
        bind(AccountDao.class).to(MockAccountDao.class);
    }

    @Override
    public void configure() {
        super.configure();

        install(new GuicyKillbillTestNoDBModule());
        install(new MockNonEntityDaoModule());
        install(new InMemoryBusModule(configSource));
    }
}