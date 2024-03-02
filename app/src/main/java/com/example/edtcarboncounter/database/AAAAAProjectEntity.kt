package com.example.edtcarboncounter.database

//Setting out all entities -- these are our tables that hold our data
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class MaterialEntity(
    @PrimaryKey(autoGenerate = true)
    val materialId: Long = 0,
    val materialName: String,
    val carbonContent: Long
)

@Entity
data class ProjectEntity(
    @PrimaryKey(autoGenerate = true)
    val projectId: Long = 0,
    val projectName: String
)

@Entity
data class ProjectMaterial(
    val projectId: Long,
    val materialId: Long,
    val kgMaterialUsed: Long
)

data class MaterialWithCarbonCount (
    @Embedded val material: MaterialEntity,
    val kgMaterialUsed: Long
)


data class ProjectWithMaterialAndCarbonCount ( //this is what we are querying for info
    @Embedded val project: ProjectEntity,
    @Relation(
        entity = MaterialEntity::class,
        parentColumn = "projectId",
        entityColumn = "materialId"
    )
    val materialWithCarbonCount: List<MaterialWithCarbonCount>
)