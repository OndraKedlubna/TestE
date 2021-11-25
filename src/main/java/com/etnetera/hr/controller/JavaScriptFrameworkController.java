package com.etnetera.hr.controller;

import com.etnetera.hr.data.JavaScriptFrameworkVersion;
import com.etnetera.hr.repository.JavaScriptFrameworkVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

    //TODO 1 rozsireni entity, 2 vytvoreni framevorku, 4 mazani frameworku, 5 vyhledavani frameworku
    // 3 uprava frameworku, ,
    private final JavaScriptFrameworkRepository repository;

    private final JavaScriptFrameworkVersionRepository versionRepository;

    @Autowired
    public JavaScriptFrameworkController(JavaScriptFrameworkRepository repository, JavaScriptFrameworkVersionRepository versionRepository) {
        this.repository = repository;
        this.versionRepository = versionRepository;
    }

    @GetMapping("/frameworks")
    public Iterable<JavaScriptFramework> frameworks() {
        return repository.findAll();
    }

    @GetMapping("/framework/{name}")
    public JavaScriptFramework framework(String name) {
        return repository.findByName(name);
    }

    @PostMapping("/framework")
    public void framework(JavaScriptFramework javaScriptFramework) {
        repository.save(javaScriptFramework);
    }

    @DeleteMapping("/framework/{name}")
    public void deleteFramework(String name) {
        repository.deleteByName(name);
    }

}
