package com.bookhub.net.remote;

import com.bookhub.domain.MediaItem;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * RMI 示例接口：远程检索服务。RMI 允许跨 JVM 调用，实现分布式协作。
 */
public interface RemoteSearchService extends Remote {

    List<MediaItem> search(String keyword) throws RemoteException;
}
