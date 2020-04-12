package tech.donau;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeBookRepositoryIT extends BookRepositoryTest {

    // Execute the same tests but in native mode.
}