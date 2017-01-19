package win.wuerping.finagle.consul

import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class ConsulQuerySpec extends WordSpecLike with Matchers with BeforeAndAfterAll {

  "parse values" in {
    ConsulQuery.decodeString("/name?dc=DC&ttl=50&tag=prod&tag=trace") match {
      case Some(ConsulQuery(name, ttl, tags, dc)) =>
        assert(name          == "name")
        assert(ttl.toString  == "50.milliseconds")
        assert(tags          == Set("prod", "trace"))
        assert(dc.contains("DC"))
    }
  }

  "parse empty" in {
    ConsulQuery.decodeString("") match {
      case Some(ConsulQuery(name, ttl, tags, dc)) =>
        assert(name          == "")
        assert(ttl.toString  == "200.milliseconds")
        assert(tags          == Set())
        assert(dc.isEmpty)
    }
  }
}
