pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "BookShelf"
include(":app")
include(":utils")
include(":features:authentication")
include(":features:booklist")
include(":data:authentication")
include(":network")
include(":theme")
include(":database")
include(":models")
include(":data:bookshelf")
include(":data:countryList")
include(":data:ipLocation")
