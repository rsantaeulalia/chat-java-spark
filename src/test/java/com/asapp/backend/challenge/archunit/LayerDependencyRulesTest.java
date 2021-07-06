package com.asapp.backend.challenge.archunit;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.asapp.backend.challenge", importOptions = {ImportOption.DoNotIncludeTests.class})
public class LayerDependencyRulesTest {
    @ArchTest
    public static final ArchRule servicesShouldNotAccessControllers =
            noClasses().that().resideInAPackage("..service..")
                    .should().accessClassesThat().resideInAPackage("..controller..");

    @ArchTest
    public static final ArchRule repositoryShouldNotAccessServices =
            noClasses().that().resideInAPackage("..repository..")
                    .should().accessClassesThat().resideInAPackage("..service..");

    @ArchTest
    public static final ArchRule servicesShouldOnlyAccessRepositoryOrOtherServices =
            classes().that().resideInAPackage("..service..")
                    .should().onlyAccessClassesThat().resideInAnyPackage("..service..", "..repository..",
                    "..exceptions..", "..utils..", "..model..", "..resources..", "io..", "java..");
}
