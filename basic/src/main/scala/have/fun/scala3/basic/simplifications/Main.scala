package have.fun.scala3.basic.simplifications

object Main:

  @main def runMain(): Unit =
    /** Constructor Proxy */
    val c1 = ConstructorProxy("abc")
    val c2 = ConstructorProxy()
    c1.hello
    c2.hello

    ConstructorProxyGenerateRule.CHaveConstructorProxies(3).hello
    ConstructorProxyGenerateRule.CHaveConstructorProxy(3).hello
    ConstructorProxyGenerateRule.CNotHaveConstructorProxies(3).hello
