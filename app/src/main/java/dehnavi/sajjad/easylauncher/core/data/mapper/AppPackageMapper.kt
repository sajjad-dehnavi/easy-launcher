package dehnavi.sajjad.easylauncher.core.data.mapper

import dehnavi.sajjad.easylauncher.core.database.entity.AppPackageEntity
import dehnavi.sajjad.easylauncher.core.model.AppPackage

fun AppPackage.toEntity() = AppPackageEntity(
    packageName = this.packageName,
    name = this.name,
    isFavorite = this.isFavorite
)

fun AppPackageEntity.toModel() = AppPackage(
    packageName = this.packageName,
    name = this.name,
    isFavorite = this.isFavorite
)