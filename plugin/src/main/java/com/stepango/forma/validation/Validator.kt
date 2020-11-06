package com.stepango.forma.validation

import com.stepango.forma.error.ProjectValidationError
import com.stepango.forma.target.LibraryTarget
import com.stepango.forma.target.TargetDefinition
import org.gradle.api.Project

interface Validator {
    fun validate(project: Project)
}

object EmptyValidator : Validator {
    override fun validate(project: Project) = Unit
}

fun TargetDefinition.validate(name: String): Boolean {
    return name == suffix || name.endsWith("-$suffix")
}

fun Project.validate(definition: TargetDefinition) {
    validator(definition).validate(this)
}

fun validator(vararg targetDefinitions: TargetDefinition): Validator = object : Validator {
    override fun validate(project: Project) {
        validateName(project.name, *targetDefinitions)
    }
}

fun validateName(
    name: String,
    vararg targetDefinitions: TargetDefinition
) {
    //Name should match with at least one targetName
    if (targetDefinitions.map { it.validate(name) }.contains(true).not()) {
        throwProjectValidationError(name, LibraryTarget)
    }
}

fun throwProjectValidationError(
    name: String,
    targetDefinition: TargetDefinition
) {
    throw ProjectValidationError(
        """
            Project ${name}: name does not match type requirements
            Projects of type "${targetDefinition::class.simpleName}" should contain name suffix "${targetDefinition.suffix}" 
        """.trimIndent()
    )
}

fun throwProjectDepsValidationError(
    project: Project,
    vararg allowedTargets: TargetDefinition
) {
    throw ProjectValidationError(
        """
            Project ${project.name}: incorrect type of dependencies
            Allowed only ${allowedTargets.map { it.suffix }} types
        """.trimIndent()
    )
}