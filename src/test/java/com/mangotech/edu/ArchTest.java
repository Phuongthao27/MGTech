package com.mangotech.edu;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.mangotech.edu");

        noClasses()
            .that()
            .resideInAnyPackage("com.mangotech.edu.service..")
            .or()
            .resideInAnyPackage("com.mangotech.edu.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.mangotech.edu.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
