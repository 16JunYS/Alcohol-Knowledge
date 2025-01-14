package com.atable.alcoholknowledge.service;

import com.atable.alcoholknowledge.model.CorkageStore;
import com.atable.alcoholknowledge.repository.CorkageStoreRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class CorkageStoreService {
    private final CorkageStoreRepository ckStoreRepository;

    public CorkageStoreService(CorkageStoreRepository ckStoreRepository) {
        this.ckStoreRepository = ckStoreRepository;

    }

    public Long register(CorkageStore ckStore) {
        validateDuplicateStore(ckStore);
        ckStoreRepository.save(ckStore);

        return ckStore.getId();
    }

    private void validateDuplicateStore(CorkageStore ckStore) {
        ckStoreRepository.findByAddr(ckStore.getAddr())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 등록된 콜키지 매장입니다");
                });
    }

    public Optional<CorkageStore> findOneByAddr(String addr) {
        return ckStoreRepository.findByAddr(addr);
    }

    public Optional<CorkageStore> findOne(Long id) {
        return ckStoreRepository.findById(id);
    }

    public List<CorkageStore> findByKeyword(String keyword) {
        String word = "%" + keyword + "%";
        return ckStoreRepository.findByKeyword(word);
    }

    public List<CorkageStore> findByKeyWord(String keyword, int pageIndex, int pageSize) {
        String word = "%" + keyword + "%";
        return ckStoreRepository.findPageByKeyword(word, pageIndex, pageSize);
    }

    public List<CorkageStore> findCkStores() {
        return ckStoreRepository.findAll();
    }

    public List<CorkageStore> findCkStores(int pageIndex, int pageSize) {
        return ckStoreRepository.findPage(pageIndex, pageSize);
    }
}
