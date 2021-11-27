package com.etnetera.hr.controller;

import com.etnetera.hr.data.JavaScriptFrameworkUpdateDto;
import com.etnetera.hr.data.JavaScriptFrameworkVersion;
import com.etnetera.hr.repository.JavaScriptFrameworkVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Simple REST controller for accessing application logic.
 *
 * @author Etnetera
 */
@RestController
public class JavaScriptFrameworkController {

    private final JavaScriptFrameworkRepository repository;

    @Autowired
    public JavaScriptFrameworkController(JavaScriptFrameworkRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/frameworks")
    public Iterable<JavaScriptFramework> getFrameworks() {
        return repository.findAll();
    }

    @GetMapping("/framework/{name}")
    public JavaScriptFramework getFramework(@RequestParam String name) {
        return repository.findByName(name);
    }

    @PostMapping("/framework")
    public void saveFramework(JavaScriptFramework javaScriptFramework) {
        repository.save(javaScriptFramework);
    }

    @DeleteMapping("/framework/{name}")
    public void deleteFramework(@RequestParam String name) {
        repository.deleteByName(name);
    }

    @PutMapping("/framework/{name}")
    public void updateFramework(@RequestParam String name, JavaScriptFrameworkUpdateDto updateDto) {
        JavaScriptFramework frameworkToUpdate = repository.findByName(name);
        if (frameworkToUpdate != null) {
            frameworkToUpdate.setHypeLevel(updateDto.getHypeLevel());
            repository.save(frameworkToUpdate);
        }
    }

    @PostMapping("/framework/{name}/version/")
    public void addVersion(@RequestParam String name, JavaScriptFrameworkVersion version) {
        JavaScriptFramework frameworkToUpdate = repository.findByName(name);
        if (frameworkToUpdate != null) {
            frameworkToUpdate.getVersions().add(version);
            version.setFramework(frameworkToUpdate);
            repository.save(frameworkToUpdate);
        }
    }

    @DeleteMapping("/framework/{name}/{versionName}/")
    public void removeVersion(@RequestParam String name, @RequestParam String versionNumber) {
        JavaScriptFramework frameworkToUpdate = repository.findByName(name);
        if (frameworkToUpdate != null) {
            for(JavaScriptFrameworkVersion version : frameworkToUpdate.getVersions()){
                if (version.getVersionNumber().equals(versionNumber)){
                    frameworkToUpdate.getVersions().remove(version);
                    version.setFramework(null);
                }
            }
        }
    }

}
