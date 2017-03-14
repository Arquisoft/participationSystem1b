package hello.producers;

import java.io.IOException;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooKeeper.States;

public class ZkConnector {

	ZooKeeper zookeeper;
	java.util.concurrent.CountDownLatch connectedSignal = new java.util.concurrent.CountDownLatch(1);
	
	/**
	 * Metodo para establecer la conexion con ZooKeeper para el correcto uso con Apache Kafka.
	 * @param host en el que se va a lanzar ZooKeeper.
	 * @throws IOException con cualquier caso de excepcion.
	 * @throws InterruptedException En caso de que se interrumpa la conexion.
	 */
	public void connect(String host) throws IOException, InterruptedException {
	    zookeeper = new ZooKeeper(host, 5000, 
	                              new Watcher() {
	                                  public void process(WatchedEvent event) {
	                                      if (event.getState() == KeeperState.SyncConnected) {
	                                          connectedSignal.countDown();
	                                      }
	                                  }
	                              });
	                              connectedSignal.await();
	}
	
	/**
	 * Metodo para cerrar la conexion establecida con ZooKeeper.
	 * @throws InterruptedException En caso de que se interrumpa la conexion.
	 */
	public void close() throws InterruptedException {
	    zookeeper.close();
	}
	
	/**
	 * Metodo que permite obtener el ZooKeeper con el que se establece la conexion.
	 * @return El ZooKeeper de la conexion.
	 */
	public ZooKeeper getZooKeeper() {
	    if (zookeeper == null || !zookeeper.getState().equals(States.CONNECTED)) {
	        throw new IllegalStateException("ZooKeeper is not connected.");
	    }
	    return zookeeper;
	}
	
}
