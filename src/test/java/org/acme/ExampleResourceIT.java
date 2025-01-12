package org.acme;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import org.junit.jupiter.api.Disabled;

@QuarkusIntegrationTest
@Disabled
class ExampleResourceIT extends ExampleResourceTest {
    // Execute the same tests but in packaged mode.
}
