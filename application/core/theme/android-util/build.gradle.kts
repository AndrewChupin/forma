androidUtil(
    packageName = "com.stepango.blockme.core.theme.android.util",
    owner = Teams.core,
    dependencies = deps(
        androidx.appcompat,
        google.material,
        google.dagger
    ) + deps(
        project(":core:theme:res")
    )
)