package net.ent.etnc.seigneuriespring.models.services.commun;

import net.ent.etnc.seigneuriespring.models.services.commun.exceptions.ServiceException;

import java.util.List;

public interface CRUDService<T> {
    T save(T t) throws ServiceException;

    T findById(Long idT) throws ServiceException;

    void deleteById(Long idT) throws ServiceException;

    boolean existsById(Long idT) throws ServiceException;

    boolean isValid(T batiment) throws ServiceException;

    List<T> getAll() throws ServiceException;

    long count() throws ServiceException;

}
