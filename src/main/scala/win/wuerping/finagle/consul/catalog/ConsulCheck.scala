package win.wuerping.finagle.consul.catalog

sealed trait ConsulCheck

case class TtlCheck(TTL: String) extends ConsulCheck
