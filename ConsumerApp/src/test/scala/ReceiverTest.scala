import com.rabbitmq.client.{Channel, DeliverCallback, Delivery}
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.flatspec.AnyFlatSpec

class ReceiverTest extends AnyFlatSpec {
  val mockChannel: Channel = mock[Channel]
  val deliverCallback: DeliverCallback = mock[DeliverCallback]
  //val receiver: Receiver = mock[Receiver]

/*  "initializeConnection" should "be unsuccessful if establishing connection fails" in {
    // val mockChannel: Channel = mock[Channel]
    val receiver = new Receiver
    when(RabbitMQConnection.getConnection) thenThrow new Exception("Failed!")
    val error = intercept[Exception] {
      receiver.consumeMessage(Try(mockChannel))
    }
    assert(error.getMessage == "Failed!")
  }*/

 /* "consumeMessage" should "successfully consume message" in {
    val receiver: Receiver = new Receiver
    val expectedConsumerTag: String = "localadminconsumer"
    val callback: DeliverCallback = mock[DeliverCallback]
    val actualConsumerTag: String = receiver.consumeMessage(Try(mockChannel))
    assertResult(expectedConsumerTag)(actualConsumerTag)
  }*/

  "deliverCallback" should "should be called" in {
    val testMessage = "Hello!".getBytes()
    val mockMessage: Delivery = mock[Delivery]
    when(mockMessage.getBody) thenReturn testMessage
    deliverCallback.handle("", mockMessage)
  }

}
