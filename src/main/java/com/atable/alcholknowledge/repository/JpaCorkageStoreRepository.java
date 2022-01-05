package com.atable.alcholknowledge.repository;

import com.atable.alcholknowledge.model.CorkageStore;

import javax.persistence.EntityManager;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class JpaCorkageStoreRepository implements CorkageStoreRepository{

    private final EntityManager em;

    public JpaCorkageStoreRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public CorkageStore save(CorkageStore cStore) {
        em.persist(cStore);
        return cStore;
    }

    @Override
    public Optional<CorkageStore> findById(Long id) {
        CorkageStore ckStore = em.find(CorkageStore.class, id);
        return Optional.ofNullable(ckStore);
    }

    @Override
    public Optional<CorkageStore> findByName(String name) {
        List<CorkageStore> result = em.createQuery("Select ckStore from CorkageStore ckStore where ckStore.name = :name", CorkageStore.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public Optional<CorkageStore> findByAddr(String addr) {
        List<CorkageStore> result = em.createQuery("Select ckStore from CorkageStore ckStore where ckStore.addr = :addr", CorkageStore.class)
                .setParameter("addr", addr)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<CorkageStore> findAll() {
        return em.createQuery("Select ckStore from CorkageStore ckStore", CorkageStore.class)
                .getResultList();
    }
}