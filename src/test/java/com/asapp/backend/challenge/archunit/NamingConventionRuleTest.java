package com.asapp.backend.challenge.archunit;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.asapp.backend.challenge", importOptions = {ImportOption.DoNotIncludeTests.class})
public class NamingConventionRuleTest {

    @ArchTest
    public static ArchRule servicesShouldBeSuffixed =
            classes()
                    .that().resideInAPackage("..service..")
                    .should().haveSimpleNameEndingWith("Service").orShould()
                    .resideInAPackage("..service.implementation..");

    @ArchTest
    public static ArchRule controllersShouldBeSuffixed =
            classes()
                    .that().resideInAPackage("..controller..")
                    .should().haveSimpleNameEndingWith("Controller").orShould()
                    .resideInAPackage("..controller.model..");

    @ArchTest
    public static ArchRule repositoriesShouldBeSuffixed =
            classes()
                    .that().resideInAPackage("..repository..")
                    .should().haveSimpleNameEndingWith("Repository");

    @ArchTest
    public static ArchRule classesNamedControllerShouldBeInProperPackage =
            classes()
                    .that().haveSimpleNameContaining("Controller")
                    .should().resideInAPackage("..controller..");

}
