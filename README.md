# SecureRMIConnection
:rabbit: 
O exemplo HelloClient.java ilustra como criar uma conexão Java Remote Method Invocation (RMI) segura. O código de amostra é basicamente um exemplo "Hello World" modificado para instalar e usar uma fábrica de soquetes RMI personalizada. Ele usa RMI sobre uma camada de transporte SSL usando JSSE. O servidor executa HelloImpl.java, que configura um registro RMI interno (em vez de usar o comando rmiregistry). O cliente executa o HelloClient e se comunica por meio de uma conexão segura.

### Usage

Configurar este exemplo pode ser um pouco complicado; aqui estão as etapas necessárias:

- % javac *.java
- % rmic HelloImpl
- % java -Djava.security.policy=policy HelloImpl (run in another window)
- % java HelloClient (run in another window)

Para o servidor, o gerenciador de segurança RMI será instalado e o arquivo de política fornecido concede permissão para aceitar conexões de qualquer host. Obviamente, conceder todas as permissões não deve ser feito em um ambiente de produção. Você precisará conceder a ele os privilégios de rede restritivos apropriados, como:

```permission java.net.SocketPermission "hostname:1024-", "accept,resolve";```

Além disso, este exemplo pode ser facilmente atualizado para ser executado com as fábricas de soquetes RMI baseadas em SSL / TLS padrão. Para fazer isso, modifique o arquivo HelloImpl.java para usar:

```javax.rmi.ssl.SslRMIClientSocketFactory
javax.rmi.ssl.SslRMIServerSocketFactory```

em vez de:

```RMISSLClientSocketFactory
RMISSLServerSocketFactory```

Essas classes usam SSLSocketFactory.getDefault () e SSLServerSocketFactory.getDefault (), portanto, você precisará configurar o sistema adequadamente para localizar sua chave e material de confiança.

### NOTA
Se você usar as fábricas de soquetes RMI baseadas em SSL / TLS padrão, poderá especificar os armazenamentos de chaves com propriedades do sistema:

```-Djavax.net.ssl.keyStore=testkeys
-Djavax.net.ssl.keyStorePassword=passphrase```
