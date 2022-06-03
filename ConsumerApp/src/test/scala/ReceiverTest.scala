import com.rabbitmq.client.{DeliverCallback, Delivery}
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.flatspec.AnyFlatSpec

class ReceiverTest extends AnyFlatSpec {
  val deliverCallback: DeliverCallback = mock[DeliverCallback]

  "deliverCallback" should "should be called" in {
    val testMessage = "Hello!".getBytes()
    val mockMessage: Delivery = mock[Delivery]
    when(mockMessage.getBody) thenReturn testMessage
    deliverCallback.handle("", mockMessage)
  }
}
