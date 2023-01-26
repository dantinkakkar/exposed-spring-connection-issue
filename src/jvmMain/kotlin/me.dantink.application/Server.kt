package me.dantink.application

//import com.zaxxer.hikari.HikariConfig
//import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.spring.SpringTransactionManager
//import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.insert
import org.springframework.jdbc.datasource.SingleConnectionDataSource

object Cities : Table() {
  val id = integer("id").autoIncrement() // Column<Int>
  val name = varchar("name", 50) // Column<String>

  override val primaryKey = PrimaryKey(id, name = "PK_Cities_ID")
}

//private fun hikari(): HikariDataSource {
//  val config = HikariConfig()
//  config.driverClassName = "org.postgresql.Driver"
//  config.jdbcUrl = "jdbc:postgresql://localhost:5432/exposed_bug"
//  config.username = "postgres"
//  config.password = "postgres"
//  config.maximumPoolSize = 1
//  config.connectionTimeout = 5000L
//  config.isAutoCommit = false
//  config.validate()
//  return HikariDataSource(config)
//}

fun main() {
  val dataSource = SingleConnectionDataSource().let {
    it.url = "jdbc:postgresql://localhost:5432/exposed_bug"
    it.username = "postgres"
    it.password = "postgres"
    it
  }
  val txnManager = SpringTransactionManager(dataSource)
  val txn: Transaction = txnManager.newTransaction(isolation = 8)
  Cities.insert {
    it[name] = "SomeCity3"
  }
  txn.commit()
  txn.close()
}