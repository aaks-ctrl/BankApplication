package repository

import org.jetbrains.exposed.sql.Database
import util.constants
import java.io.IOException
import java.sql.DriverManager
import java.sql.SQLException
import java.util.*

class DatabaseDriver {
    private val constants = constants();

    private fun loadDatabaseProperties() : Properties {
        val properties = Properties()
        try {
            val inputStream = this::class.java.getResourceAsStream("/${constants.properties}")
            properties.load(inputStream);
        }catch(e : IOException){
            println("Error loading database properties")
        }
        return properties;
    }

    fun connectToDatabase() : Database? {

        val properties = loadDatabaseProperties();
        val url = properties.getProperty("db.url")
        val user = properties.getProperty("db.user")
        val password = properties.getProperty("db.password")

        return try {
            Database.connect({ DriverManager.getConnection(url, user, password) })
        } catch (e: SQLException) {
            println("Failed to connect to the database")
            null
        }
    }
}
