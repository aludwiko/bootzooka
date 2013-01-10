package pl.softwaremill.bootstrap.dao

import org.specs2.mutable.Specification
import pl.softwaremill.bootstrap.domain.PasswordResetCode
import org.bson.types.ObjectId
import org.joda.time.DateTime

/**
 * Specification for [[pl.softwaremill.bootstrap.dao.InMemoryPasswordResetCodeDAO]]
 */
class InMemoryPasswordResetCodeDAOSpec extends Specification {
  isolated

  var dao: InMemoryPasswordResetCodeDAO = _

  "InMemoryPasswordResetCodeDAO" should {

    step({
      dao = new InMemoryPasswordResetCodeDAO
    })

    "store code" in {
      val currentCount = dao.count
      val code = PasswordResetCode(code = "code", userId = new ObjectId())
      dao.store(code)
      dao.count - currentCount === 1
    }

    "load stored code" in {
      val code = PasswordResetCode(code = "code", userId = new ObjectId())
      dao.store(code)
      dao.load(code.code)  match {
        case Some(code) => assert(code.code === "code")
        case None => failure("Code should be found")
      }
    }

    "not load code when not stored" in {
      dao.load("nonexistantcode") match {
        case Some(code) => failure("This code should not be found")
        case None =>
      }
    }
  }
}