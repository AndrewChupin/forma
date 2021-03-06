import tools.forma.android.feature.AndroidLibraryFeatureConfiguration
import tools.forma.android.feature.androidLibraryFeatureDefinition
import tools.forma.android.feature.applyFeatures
import tools.forma.android.feature.kotlinAndroidFeatureDefinition
import tools.forma.android.target.AndroidTestUtilTarget
import tools.forma.android.target.TestUtilTarget
import tools.forma.android.owner.NoOwner
import tools.forma.android.owner.Owner
import tools.forma.validation.validate
import tools.forma.validation.validator
import tools.forma.android.visibility.Public
import tools.forma.android.visibility.Visibility
import org.gradle.api.Project
import tools.forma.deps.applyDependencies
import tools.forma.deps.FormaDependency

fun Project.androidTestUtil(
    packageName: String,
    owner: Owner = NoOwner,
    visibility: Visibility = Public,
    dependencies: FormaDependency = emptyDependency()
) {
    validate(AndroidTestUtilTarget)

    val androidFeatureConfig = AndroidLibraryFeatureConfiguration(
        packageName
    )
    applyFeatures(
        androidLibraryFeatureDefinition(androidFeatureConfig),
        kotlinAndroidFeatureDefinition()
    )

    applyDependencies(
        validator = validator(AndroidTestUtilTarget, TestUtilTarget),
        dependencies = dependencies,
        repositoriesConfiguration = Forma.configuration.repositories
    )
}
