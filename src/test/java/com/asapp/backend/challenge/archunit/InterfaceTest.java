package com.asapp.backend.challenge.archunit;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.asapp.backend.challenge", importOptions = {ImportOption.DoNotIncludeTests.class})
public class InterfaceTest {

    @ArchTest
    public static final ArchRule interfacesMustNotBeInImplementationPackages =
            noClasses().that().resideInAnyPackage("..implementation..", "..sqliteimplementation..").should().beInterfaces();

}