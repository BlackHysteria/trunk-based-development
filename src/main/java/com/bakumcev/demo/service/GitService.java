package com.bakumcev.demo.service;

public interface GitService {

    /**
     * Push в GitHub
     */
    String push();

    /**
     * Получение последнего локального хэша коммита
     */
    String getLastSha(String command);

}
