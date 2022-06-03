import com.rabbitmq.client.Channel
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach}

import scala.util.Try

class SenderTest extends AnyFlatSpec with BeforeAndAfterAll with BeforeAndAfterEach {
  var mockChannel: Channel = mock[Channel]

  "sendMessage" should "successfully send message" in {
    val sender: Sender = new Sender()
    val expectedMessage: String = "Hello!"
    val sentMessage: String = sender.sendMessage(Try(mockChannel), expectedMessage)
    assertResult(expectedMessage)(sentMessage)
  }

  "initializeConnection" should "be unsuccessful if establishing connection fails" in {
    val sender = new Sender
    val message = "test"
    when(RabbitMQConnection.getConnection) thenThrow new Exception("Failed!")
    val error = intercept[Exception] {
      sender.sendMessage(Try(mockChannel), message)
    }
    assert(error.getMessage == "Failed!")
  }
}
