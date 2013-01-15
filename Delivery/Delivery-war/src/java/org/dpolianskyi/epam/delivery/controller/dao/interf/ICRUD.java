package org.dpolianskyi.epam.delivery.controller.dao.interf;

import java.io.Serializable;
import java.util.List;

public interface ICRUD<E, K> {

    void persist(E entity) throws Exception;

    E findById(K id) throws Exception;

    List<E> findEntities(boolean all, int maxResults, int firstResult) throws Exception;

    void merge(E entity) throws Exception;

    void remove(E entity) throws Exception;
}
