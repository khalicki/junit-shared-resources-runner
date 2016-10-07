# shared-resources-runner

##Usage

Implement resource:
```java
public class SampleSharedResource implements SharedResource {
    private final Logger log = Logger.getLogger(SampleSharedResource.class);
    // requires public constructor
    public SampleSharedResource() {
        log.info("Creating SampleSharedResource object");
        instances++;
    }
    public void initialize() throws Exception {
        log.info("initialize SampleSharedResource");
    }
}
```
Specify runner in test and required resources:
```java
@RunWith(JUnitSharedResourceRunner.class)
@JUnitSharedResourceRunner.WithSharedResources({SampleSharedResource.class})
public class SharedResourceRunnerATest {
...
```

If you want to access resource object, no problem:
```java
JUnitSharedResourceRunner.getResource(SampleSharedResource.class);
```

##Add to your project

Add maven repository:
```xml
<repository>
    <id>eanlr-public</id>
    <url>http://vps308418.ovh.net/mvn/repo/public</url>
</repository>
```

Add dependency:
```xml
<dependency>
    <groupId>org.eanlr.testing</groupId>
    <artifactId>junit-shared-resources-runner</artifactId>
    <version>0.1</version>
    <scope>test</scope>
</dependency>
```

Or simply download jar [junit-shared-resources-runner-0.1.jar](http://vps308418.ovh.net/mvn/repo/public/org/eanlr/testing/junit-shared-resources-runner/0.1/junit-shared-resources-runner-0.1.jar)


