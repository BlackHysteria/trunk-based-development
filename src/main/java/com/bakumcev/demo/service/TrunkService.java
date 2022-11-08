package com.bakumcev.demo.service;

public interface TrunkService {

    /**
     * Запускает pipeline
     */
    boolean run();

    /**
     * Для эмуляции работы, при чётных значениях минуту выдаёт true
     * При нечётных false
     */
    boolean solution();

}
