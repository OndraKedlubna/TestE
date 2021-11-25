package com.etnetera.hr.controller;

import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.data.JavaScriptFrameworkVersion;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class JavaScriptFrameworkControllerTest {

    @Autowired
    private JavaScriptFrameworkController javaScriptFrameworkController;

    @Test
    public void frameworksPost() {
        javaScriptFrameworkController.framework(createTestFramework());
        Iterable<JavaScriptFramework> frameworks = javaScriptFrameworkController.frameworks();
        Assert.assertThat(frameworks,
                Matchers.contains(
                        Matchers.allOf(
                                Matchers.hasProperty("name", Matchers.equalTo("framework 1")),
                                Matchers.hasProperty("versions", Matchers.contains(
                                        Matchers.allOf(
                                                Matchers.hasProperty("versionNumber", Matchers.equalTo("1.0.0")),
                                                Matchers.hasProperty("deprecationDate", Matchers.equalTo(LocalDate.of(2012, 1, 1)))
                                        )
                                )),
                                Matchers.hasProperty("hypeLevel", Matchers.equalTo("MEDIUM"))
                        )
                ));
    }

    @Test
    public void frameworkGetOne() {
        javaScriptFrameworkController.framework(createTestFramework());
        JavaScriptFramework framework = javaScriptFrameworkController.framework("framework 1");
        Assert.assertThat(framework,
                Matchers.allOf(
                        Matchers.hasProperty("name", Matchers.equalTo("framework 1")),
                        Matchers.hasProperty("versions", Matchers.contains(
                                Matchers.allOf(
                                        Matchers.hasProperty("versionNumber", Matchers.equalTo("1.0.0")),
                                        Matchers.hasProperty("deprecationDate", Matchers.equalTo(LocalDate.of(2012, 1, 1)))
                                )
                        )),
                        Matchers.hasProperty("hypeLevel", Matchers.equalTo("MEDIUM"))
                )
        );
    }

    @Test
    public void frameworkGetOneEmpty() {
        javaScriptFrameworkController.framework(createTestFramework());
        JavaScriptFramework framework = javaScriptFrameworkController.framework("ss");
        Assert.assertNull(framework);
    }

    @Test
    public void frameworkDelete() {
        javaScriptFrameworkController.framework(createTestFramework());
        JavaScriptFramework framework = javaScriptFrameworkController.framework("framework 1");
        Assert.assertThat(framework,
                Matchers.allOf(
                        Matchers.hasProperty("name", Matchers.equalTo("framework 1")),
                        Matchers.hasProperty("versions", Matchers.contains(
                                Matchers.allOf(
                                        Matchers.hasProperty("versionNumber", Matchers.equalTo("1.0.0")),
                                        Matchers.hasProperty("deprecationDate", Matchers.equalTo(LocalDate.of(2012, 1, 1)))
                                )
                        )),
                        Matchers.hasProperty("hypeLevel", Matchers.equalTo("MEDIUM"))
                )
        );
        javaScriptFrameworkController.deleteFramework("framework 1");
        JavaScriptFramework deletedFramework = javaScriptFrameworkController.framework("framework 1");
        Assert.assertNull(deletedFramework);
    }

    private JavaScriptFramework createTestFramework() {
        JavaScriptFrameworkVersion version = new JavaScriptFrameworkVersion();
        version.setVersionNumber("1.0.0");
        version.setDeprecationDate(LocalDate.of(2012, 1, 1));
        JavaScriptFramework framework = new JavaScriptFramework();
        framework.setName("framework 1");
        framework.setHypeLevel("MEDIUM");
        Set<JavaScriptFrameworkVersion> versions = new HashSet<>();
        versions.add(version);
        version.setFramework(framework);
        framework.setVersions(versions);
        return framework;
    }

}
