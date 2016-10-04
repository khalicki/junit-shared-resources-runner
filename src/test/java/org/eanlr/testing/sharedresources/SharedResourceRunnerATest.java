package org.eanlr.testing.sharedresources;

import org.apache.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

@RunWith(JUnitSharedResourceRunner.class)
@JUnitSharedResourceRunner.WithSharedResources({SampleSharedResource.class})
public class SharedResourceRunnerATest {

    private final Logger log = Logger.getLogger(SharedResourceRunnerATest.class);

    @Test
    public void testRunnerA1() {
        log.info("testRunnerA1");
        assertTrue("Shared resource should have only one instance", SampleSharedResource.instances == 1);
        assertTrue("Shared resource should be initialized only once", SampleSharedResource.initializeTimes == 1);
    }
}
