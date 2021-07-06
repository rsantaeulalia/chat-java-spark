package com.asapp.backend.challenge.archunit;

import static com.tngtech.archunit.base.DescribedPredicate.alwaysTrue;
import static com.tngtech.archunit.core.domain.properties.HasName.Predicates.nameMatching;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;


import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.asapp.backend.challenge", importOptions = {ImportOption.DoNotIncludeTests.class})
public class LayerArchitectureTest {

    @ArchTest
    public static final ArchRule layerDependenciesAreRespected =
            layeredArchitecture()
                    .layer("Filter").definedBy("com.asapp.backend.challenge.filter..")
                    .layer("Adapters").definedBy("com.asapp.backend.challenge.model.adapter..")
                    .layer("Controllers").definedBy("com.asapp.backend.challenge.controller..")
                    .layer("Services").definedBy("com.asapp.backend.challenge.service..")
                    .layer("Repository").definedBy("com.asapp.backend.challenge.repository..")

                    .whereLayer("Controllers").mayOnlyBeAccessedByLayers("Adapters")
                    .whereLayer("Services").mayOnlyBeAccessedByLayers("Controllers", "Filter")
                    .whereLayer("Repository").mayOnlyBeAccessedByLayers("Services")
                    .ignoreDependency(
                            nameMatching(".*\\.Application"), alwaysTrue());

}
