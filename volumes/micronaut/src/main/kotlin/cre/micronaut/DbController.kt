package cre.micronaut

// https://github.com/cw-bhanunadar/Micronaut-playground

// bootstrap
import io.micronaut.runtime.event.annotation.EventListener
import io.micronaut.runtime.server.event.ServerStartupEvent
//import io.sentry.Sentry
//import io.sentry.SentryOptions
/// import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory


// entity 
import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
// repository
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
// controller1
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces 
// controller2
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import jakarta.inject.Inject

// bootstrap
@Singleton 
class Bootstrap {

    val log: Logger = LoggerFactory.getLogger("Bootstrap")

//    @Inject
//    lateinit var sentryDsn: SentryConfiguration

    @EventListener 
    fun onStartupEvent(event: ServerStartupEvent) {
        //configureSentry()
        log.info("This Event got trigger during Startup")
        log.error("This Dummy Event got trigger during Startup")
        log.error("This Event got trigger during Startup")
    }

}


// entity
@MappedEntity(value = "license")
@Introspected
data class License(
    @field:Id @GeneratedValue var id: Long?,
    var label: String?
)


// repository
@JdbcRepository(dialect = Dialect.POSTGRES)
interface LicenseRepository : CrudRepository<License, Long>



// controller1
@Controller("/hallo") 
class HalloController {

    @Get
    @Produces(MediaType.TEXT_PLAIN) 
    fun index() = "Hallo World!!"
}



// controller2
@Controller("/license")
class LicenseController {

    @Inject
    lateinit var licenseRepository: LicenseRepository
    @Post("/")
    suspend fun save(@Body license: License): License {
        return licenseRepository.save(license)
    }

/*  @Get("/{id}")
    fun get(@PathVariable id: Long): License? {                // suspend fun NOT working
        return licenseRepository.findById(id).get()
    }
*/
    @Get("/{id}")
    @Produces(MediaType.TEXT_PLAIN) 
    fun get(@PathVariable id: Long): String? {                // suspend fun NOT working
        return licenseRepository.findById(id).get().label
    }

    @Get("/count")
    fun count(): Long? {
        return licenseRepository.count()
    }

}


// controller3
@Controller("/grrr")
class GrrrController {


    @Get
    @Produces(MediaType.TEXT_PLAIN) 
    fun count12() = "13"

    @Get("/count2")
    @Produces(MediaType.ALL) 
    fun count2(): String {
        return "17"
    }

    @Get("/add2/{num}")
    @Produces(MediaType.ALL) 
    fun add2(@PathVariable num: Long): Long? {
        return num+2
    }
}
