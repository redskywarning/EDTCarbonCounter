package com.example.edtcarboncounter.database

//Setting out all entities -- these are our tables that hold our data
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class MaterialEntity(
    val materialName: String,
    val carbonContent: Double,
    @PrimaryKey(autoGenerate = true)
    val materialId: Long = 0
)

@Entity
data class ProjectEntity(
    val projectName: String,
    val projectTotalCarbon: Double,
    @PrimaryKey(autoGenerate = true)
    val projectId: Long = 0
)

@Entity
data class TransportEntity(
    val transportName: String,
    val carbonKgPerKm: Double,
    @PrimaryKey(autoGenerate = true)
    val transportId: Long = 0
)

//@Entity(primaryKeys = ["projectId","materialId","transportId"])
@Entity
data class ProjectMaterialTransport(
    val projectId: Long,
    val materialId: Long,
    val transportId: Long,
    val kgMaterialUsed: Double,
    val kmTravelled: Double,
    val countedBefore: Int,
    @PrimaryKey(autoGenerate = true)
    val projectMaterialTransportId: Long = 0
)

//data class ProjectWithMaterialAndTransport(
//    @Embedded val project: ProjectEntity,
//    @Relation(
//        parentColumn = "projectId",
//        entityColumn = "materialId",
//        associateBy = Junction(ProjectMaterialTransport::class)
//    )
//    val material: List<MaterialEntity>,
//    @Relation(
//        parentColumn = "projectId",
//        entityColumn = "transportId",
//        associateBy = Junction(ProjectMaterialTransport::class)
//    )
//    val transport: List<TransportEntity>
//)

//@Entity(primaryKeys = ["materialId","transportId"])
//data class MaterialTransport(
//    val materialId: Int,
//    val transportId: Int,
//    val kgMaterialUsed: Double,
//    val kmTravelled: Double
//)

//data class MaterialWithTransport(
//    @Embedded val material: MaterialEntity,
//    @Relation(
//        parentColumn = "materialId",
//        entityColumn = "transportId",
//        associateBy = Junction(MaterialTransport::class)
//    )
//    val transport: List<TransportEntity>
//)

//data class ProjectWithMaterial(
//    @Embedded val project: ProjectEntity,
//    @Relation(
//        parentColumn = "projectId",
//        entityColumn = "materialId",
//        associateBy = Junction(ProjectMaterial::class)
//    )
//    val material: List<MaterialEntity>
//)


//data class ProjectWithMaterialWithTransport(
//    @Embedded val project: ProjectEntity,
//    @Relation(
//        entity = MaterialEntity::class,
//        parentColumn = "projectId",
//        entityColumn = "materialId"
//    )
//    val material: List<MaterialEntity>
//)




//@Entity
//data class ProjectMaterialTransport(
//    @PrimaryKey val projectId: Int,
//    val materialId: Int,
//    val transportId: Int,
//    val materialUsedKg: Double,
//    val distanceKmTravelled: Double
//)

//data class ProjectWithMaterialAndTransport(
//    @Embedded val project: ProjectEntity,
//    @Relation(
//        parentColumn = "projectId",
//        entityColumn = "materialId",
//        associateBy = Junction(ProjectMaterialTransport::class)
//    )
//    val material: MaterialEntity,
//    @Relation(
//        parentColumn = "projectId",
//        entityColumn = "transportId",
//        associateBy = Junction(ProjectMaterialTransport::class)
//    )
//    val transportMode: TransportEntity
//)

//@Entity
//data class ProjectMaterial(
//    @PrimaryKey val projectId: Int,
//    val materialId: Int,
//    val kgMaterialUsed: Double
//)

//data class MaterialWithCarbonCount (
//    @Embedded val material: MaterialEntity,
//    val kgMaterialUsed: Double
//)

//@Entity(primaryKeys = ["playlistId", "songId"])
//data class PlaylistSongCrossRef(
//    val playlistId: Long,
//    val songId: Long
//)
//
//data class MaterialWithTransport(
//    @Embedded val material: MaterialEntity,
//    @Relation(
//        parentColumn = "materialId",
//        entityColumn = "transportId"
//    )
//    val transport: List<TransportEntity>,
//    val kmTravelled: Double
//)
//
//data class UserWithPlaylistsAndSongs(
//    @Embedded val user: User
//    @Relation(
//entity = Playlist::class,
//parentColumn = "userId",
//entityColumn = "userCreatorId"
//)
//val playlists: List<PlaylistWithSongs>
//)
///**/


//@DatabaseView("SELECT ProjectEntity.projectId AS projectId, ProjectEntity.projectName AS projectName, " +
//        "MaterialEntity.materialId AS materialId, MaterialEntity.materialName AS materialName, " +
//        "MaterialEntity.carbonContent AS carbonContent, ProjectMaterial.kgMaterialUsed AS kgMaterialUsed, " +
//        "TransportEntity.transportId AS transportId, TransportEntity.transportName AS transportName, " +
//        "TransportEntity.carbonKgPerKm AS carbonKgPerKm " +
//        "FROM ProjectEntity " +
//        "INNER JOIN ProjectMaterial ON ProjectEntity.projectId = ProjectMaterial.projectId " +
//        "INNER JOIN MaterialEntity ON ProjectMaterial.materialId = MaterialEntity.materialId " +
//        "INNER JOIN TransportEntity ON ProjectEntity.projectId = TransportEntity.transportId")
//data class ProjectWithMaterialAndCarbonCountAndTransport(
//    val projectId: Int,
//    val projectName: String,
//    val materialId: Int,
//    val materialName: String,
//    val carbonContent: Double,
//    val kgMaterialUsed: Double,
//    val transportId: Int,
//    val transportName: String,
//    val carbonPerKm: Double
//)


//data class MaterialWithCarbonCountAndTransport (
//    @Embedded val materialWithCarbonCount: MaterialWithCarbonCount,
//    @Relation(
//        entity = TransportEntity::class,
//        parentColumn = "materialId",
//        entityColumn = "transportId"
//    )
//    val transportEntity: List<TransportEntity>
//)
//
//
//data class ProjectWithMaterialAndCarbonCountAndTransport (
//    @Embedded val project: ProjectEntity,
//    @Relation(
//        entity = MaterialWithCarbonCountAndTransport::class,
//        parentColumn = "projectId",
//        entityColumn = "materialId"
//    )
//    val materialWithCarbonCountAndTransport: List<MaterialWithCarbonCountAndTransport>
//)