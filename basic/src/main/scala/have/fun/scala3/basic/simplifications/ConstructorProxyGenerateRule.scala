package have.fun.scala3.basic.simplifications

/** Examples of generate rule of constructor proxy */
object ConstructorProxyGenerateRule:

  /** This class will have constructor proxies:
   *
   * Since a companion object is not defined,
   * the constructor proxy companion object will be generated.
   * And the `apply` method will be generated in the companion.
   */
  class CHaveConstructorProxies(v: Int):
    def hello = println("CHaveConstructorProxies class have constructor proxies (companion and apply)")

  /** This class will have a constructor proxy:
   *
   * The companion object is defined.
   * But an `apply` method is not defined,
   * then the `apply` method will be generated in the companion.
   */
  class CHaveConstructorProxy(v: Int):
    def hello = println("CHaveConstructorProxy class have a constructor proxy (apply)")
  object CHaveConstructorProxy:
    def zero = CHaveConstructorProxy(0)

  /** This class will not have constructor proxies:
   *
   * The companion object is defined.
   * The companion has already defined apply method.
   */
  class CNotHaveConstructorProxies(v: Int):
    def hello = println("CNotHaveConstructorProxies class don't have constructor proxies")
  object CNotHaveConstructorProxies:
    def apply(v: Int) = CHaveConstructorProxy(v)
