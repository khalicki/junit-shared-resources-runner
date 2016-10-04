package org.eanlr.testing.sharedresources;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitSharedResourceRunner.class)
@JUnitSharedResourceRunner.WithSharedResources({SampleSharedResource.class})
public class SharedResourceRunnerBTest {

    private final Logger log = Logger.getLogger(SharedResourceRunnerBTest.class);

    @Before
    public void init() {
        // get shared resource
        SampleSharedResource sampleSharedResource = JUnitSharedResourceRunner.getResource(SampleSharedResource.class);
        assertNotNull(sampleSharedResource);
    }

    @Test
    public void testRunnerB1() {
        log.info("testRunnerB1");
        assertTrue("Shared resource should have only one instance", SampleSharedResource.instances == 1);
        assertTrue("Shared resource should be initialized only once", SampleSharedResource.initializeTimes == 1);
    }

}
