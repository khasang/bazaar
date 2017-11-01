package io.khasang.bazaar.dao;

import io.khasang.bazaar.entity.Support;

import java.util.List;

public interface SupportDao extends BasicDao<Support> {
    /**
     * Receive support's from database with specified ountRequest
     *
     * @param countRequests - support's countRequests
     * @return list of support's with specified countRequest
     */
    List<Support> getSupportByCountRequests(String countRequests);
}