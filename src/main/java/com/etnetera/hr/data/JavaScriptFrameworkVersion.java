package com.etnetera.hr.data;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Simple data entity describing basic properties of every version of javascript Framework.
 *
 * @author Etnetera
 */
@Entity
public class JavaScriptFrameworkVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long versionId;

    @Column(nullable = false, length = 30)
    private String versionNumber;

    @Column(nullable = false)
    private LocalDate deprecationDate;

    @ManyToOne
    @JoinColumn(name="name", nullable=false)
    private JavaScriptFramework framework;

    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public LocalDate getDeprecationDate() {
        return deprecationDate;
    }

    public void setDeprecationDate(LocalDate deprecationDate) {
        this.deprecationDate = deprecationDate;
    }

    public JavaScriptFramework getFramework() {
        return framework;
    }

    public void setFramework(JavaScriptFramework framework) {
        this.framework = framework;
    }

    @Override
    public String toString() {
        return String.format("JavascriptFrameworkVersion [versionId = %d, versionNumber=%s, deprecationDate=%s, javaScriptFramework=%s]",
                versionId, versionNumber, deprecationDate.toString(), framework.getName());
    }
}
