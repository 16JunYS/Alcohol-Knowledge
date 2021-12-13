package com.atable.alcholknowledge.repository;

import com.atable.alcholknowledge.model.WineInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryWineInfoRepositoryTest {
    MemoryWineInfoReository repository = new MemoryWineInfoReository();

    @AfterEach
    public void afterEach(){
        repository.clearWineInfo();
    }
    @Test
    public void save(){
        WineInfo wineInfo = new WineInfo();
        wineInfo.setNameKor("파이퍼하이직");
        wineInfo.setPrice(10000);
        repository.save(wineInfo);

        wineInfo = new WineInfo();
        wineInfo.setNameKor("파이퍼하이직");
        wineInfo.setPrice(20000);
        repository.save(wineInfo);

        wineInfo = new WineInfo();
        wineInfo.setNameKor("네비게이션");
        wineInfo.setPrice(12000);
        repository.save(wineInfo);

        ArrayList<WineInfo> list = new ArrayList<>();
        List<WineInfo> result = repository.findByNameKor("파이퍼하이직");
        //Assertions.assertEquals(repository.findAll(),result);
        Assertions.assertThat(repository.findByNameKor("파이퍼하이직")).isEqualTo(result);

    }

    @Test
    public void findByAll(){
        WineInfo wineInfo = new WineInfo();
        wineInfo.setNameKor("파이퍼하이직");
        wineInfo.setPrice(10000);
        repository.save(wineInfo);

        wineInfo = new WineInfo();
        wineInfo.setNameKor("파이퍼하이직");
        wineInfo.setPrice(20000);
        repository.save(wineInfo);

        wineInfo = new WineInfo();
        wineInfo.setNameKor("네비게이션");
        wineInfo.setPrice(12000);
        repository.save(wineInfo);

        List<WineInfo> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(3);

    }
}