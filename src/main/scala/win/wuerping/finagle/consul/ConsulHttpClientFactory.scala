package win.wuerping.finagle.consul

import com.twitter.conversions.time._
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finagle.tracing.NullTracer
import com.twitter.finagle.{Http, Service}

import scala.collection.mutable


object ConsulHttpClientFactory {

  type Client = Service[Request, Response]

  private val clients: mutable.Map[String, Client] = mutable.Map()

  def getClient(hosts: String): Client = synchronized {
    clients.getOrElseUpdate(hosts, {
      Http.client.withSession.acquisitionTimeout(1.second)
        .withTracer(new NullTracer)
        .newService(hosts)
    })
  }
}
