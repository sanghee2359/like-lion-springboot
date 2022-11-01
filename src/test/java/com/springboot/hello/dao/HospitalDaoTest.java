package com.springboot.hello.dao;

import com.springboot.hello.domain.Hospital;
import com.springboot.hello.parser.HospitalParser;
import com.springboot.hello.parser.ReadLineContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@SpringBootTest
class HospitalDaoTest {
    String line1 = "\"1\",\"의원\",\"01_01_02_P\",\"3620000\",\"PHMA119993620020041100004\",\"19990612\",\"\",\"01\",\"영업/정상\",\"13\",\"영업중\",\"\",\"\",\"\",\"\",\"062-515-2875\",\"\",\"500881\",\"광주광역시 북구 풍향동 565번지 4호 3층\",\"광주광역시 북구 동문대로 24, 3층 (풍향동)\",\"61205\",\"효치과의원\",\"20211115113642\",\"U\",\"2021-11-17 02:40:00.0\",\"치과의원\",\"192630.735112\",\"185314.617632\",\"치과의원\",\"1\",\"0\",\"0\",\"52.29\",\"401\",\"치과\",\"\",\"\",\"\",\"0\",\"0\",\"\",\"\",\"0\",\"\",";
    @Autowired
    ReadLineContext<Hospital> hospitalReadLineContext;

    @Autowired // HospitalDao는 Factory가 없는데 왜 DI가 될까?
    HospitalDao hospitalDao; // component가 붙어있는 클래스를 springboot가 Bean으로 등록한다. - HospitalDao
    @Test
    @DisplayName("Insert가 잘 동작하는지")
    void add() {
        HospitalParser hp = new HospitalParser();
        Hospital hospital = hp.parse(line1);
        hospitalDao.add(hospital);
    }
    @Test
    @DisplayName("getcount 동작확인")
    void getCount() {
        System.out.println(hospitalDao.getCount());
    }



}