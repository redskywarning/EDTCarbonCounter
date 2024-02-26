package com.example.edtcarboncounter.database

//Setting out all entities -- these are our tables that hold our data
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
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


data class ProjectWithMaterialAndCarbonCount (
    @Embedded val project: ProjectEntity,
    @Relation(
    entity = MaterialEntity::class,
    parentColumn = "projectId",
    entityColumn = "materialId"
    )
    val playlists: List<MaterialWithCarbonCount>
)




//@Entity
//data class ProjectWithCarbons (
//    @Embedded
//    val projectName: ProjectEntity,
//
//    @Relation (
//        parentColumn = "projectId",
//        entity = CarbonEntity::class,
//        entityColumn = "carbonId",
//        associateBy = Junction(
//            value = ProjectMaterial::class,
//            parentColumn = "projectId",
//            entityColumn = "carbonId"
//        )
//    )
//    val carbonWeights: List<Map<CarbonEntity, Long>> //the Long here maps to kgMaterialUsed
//)

//@Entity
//data class CarbonWithWeight(
//    @Embedded val carbon: CarbonEntity,
//    val kgs: Long
//)
//
//@Entity
//data class ProjectWithCarbons(
//    @Embedded
//    val project: ProjectEntity,
//    @Relation(
//        parentColumn = ,
//        entity = ::class,
//        entityColumn = ,
//        associateBy = Junction(
//            value = ::class,
//            parentColumn = "",
//            entityColumn = ""
//        )
//    )
//    val carbons: List<CarbonWithWeight>)