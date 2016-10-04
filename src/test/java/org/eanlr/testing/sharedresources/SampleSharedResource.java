package org.eanlr.testing.sharedresources;

import org.apache.log4j.Logger;

public class SampleSharedResource implements SharedResource {
    private final Logger log = Logger.getLogger(SampleSharedResource.class);
    static int instances = 0;
    static int initializeTimes = 0;

    // requires public constructor
    public SampleSharedResource() {
        log.info("Creating SampleSharedResource object");
        instances++;
    }

    public void initialize() throws Exception {
        log.info("initialize SampleSharedResource");
        initializeTimes++;
    }
}

