package controllers

import org.scalatest._
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.Application
import play.api.test._
import play.api.test.Helpers._
import play.api.inject.bind
import play.api.inject.guice.GuiceApplicationBuilder
import scalikejdbc.PlayModule
import services.{MessageService, MockMessageService}



class CreateMessageControllerSpec extends FunSpec
  with MustMatchers
  with GuiceOneAppPerSuite {

  override def fakeApplication(): Application =
    new GuiceApplicationBuilder()
      .disable[PlayModule]
      .overrides(bind[MessageService].to[MockMessageService]) // モックに差し替える
      .build()

  describe("CreateMessageController") {
    describe("route of CreateMessageController#index") {
      it("should be valid") {
        val result = route(app, FakeRequest(GET, routes.CreateMessageController.index.toString)).get
        status(result) mustBe OK
      }
    }
    describe("route of CreateMessageController#create") {
      val result =
        route(app,
          FakeRequest(POST, routes.CreateMessageController.create.toString)
            .withFormUrlEncodedBody("title" -> "a", "body" -> "b")).get
      status(result) mustBe SEE_OTHER
    }
  }

}