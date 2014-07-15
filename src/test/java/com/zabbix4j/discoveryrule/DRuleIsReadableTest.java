package com.zabbix4j.discoveryrule;

import com.zabbix4j.ZabbixApiTestBase;
import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author suguru yajima 2014
 */
public class DRuleIsReadableTest extends ZabbixApiTestBase {

    public DRuleIsReadableTest() {
        super();
    }

    @Test
    public void testIsReadable() throws Exception {
        DummyDiscoveryRule dummyDiscoveryRule = new DummyDiscoveryRule(zabbixApi);
        Integer targetId = dummyDiscoveryRule.create();

        try {
            DRuleIsReadableRequest request = new DRuleIsReadableRequest();
            request.addDruleId(targetId);

            DRuleIsReadableResponse response = zabbixApi.discoveryRule().isReadable(request);
            assertNotNull(response);

            logger.debug(getGson().toJson(response));

            assertThat(response.getResult(), Is.is(Boolean.TRUE));
        } finally {
            dummyDiscoveryRule.delete(targetId);
        }

    }
}
